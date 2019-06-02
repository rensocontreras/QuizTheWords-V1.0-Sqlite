package com.contreras.myquizapplication.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.contreras.myquizapplication.Entity.Usuario;
import com.contreras.myquizapplication.Interfaces.IRegistrar;
import com.contreras.myquizapplication.MainActivity;
import com.contreras.myquizapplication.Presenter.RegistrarPresenter;
import com.contreras.myquizapplication.R;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrarActivity extends AppCompatActivity implements Validator.ValidationListener, IRegistrar.IRegistrarView {

    @BindView(R.id.edt_nombre)
    @Required(order = 1, message = "Debe ingresar su nombre")
    EditText edt_nombre;

    @BindView(R.id.edt_apellido)
    @Required(order = 2, message = "Debe ingresar su apellido")
    EditText edt_apellido;

    @BindView(R.id.edt_correo)
    @Required(order=3,message = "Debe ingresar correo")
    @Email(order = 4,message = "Correo incorrecto")
    EditText edt_correo;

    @BindView(R.id.edt_password)
    @Password(order = 5,message = "Debe ingresar su contraseña")
    EditText edt_password;

    @BindView(R.id.rb_masculino)
    RadioButton rb_masculino;

    @BindView(R.id.rb_femenino)
    RadioButton rb_femenino;

    RegistrarPresenter presenter;
    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        ButterKnife.bind(this);

        presenter = new RegistrarPresenter(this);

        validator = new Validator(this);
        validator.setValidationListener(this);
    }


    @OnClick(R.id.btn_registrar)
    public void registrar(){

      validator.validate();
    }

    @Override
    public void onValidationSucceeded() {

        String sexo;

        Usuario usuario = new Usuario();
        usuario.setNombres(edt_nombre.getText().toString());
        usuario.setApellidos(edt_apellido.getText().toString());
        usuario.setCorreo(edt_correo.getText().toString());
        usuario.setPassword(edt_password.getText().toString());

        if(rb_masculino.isChecked())
            sexo="masculino";
        else
            sexo="femenino";

        usuario.setSexo(sexo);

        enviarUsuario(usuario);

        Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(RegistrarActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        final String failureMessage = failedRule.getFailureMessage(); //Para capturar el error del saripaar
        if (failedView instanceof EditText) {
            EditText failed = (EditText) failedView;
            failed.requestFocus(); //Para que il touch si posizioni subito nella cassella in cui c'è stato l'errore
            failed.setError(failureMessage); //Para q aparezca el error
        } else {
            Toast.makeText(this, failureMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void enviarUsuario(Usuario usuario) {
        presenter.solicitarUsuario(usuario);
    }
}
