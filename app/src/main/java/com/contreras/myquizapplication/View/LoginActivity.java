package com.contreras.myquizapplication.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.contreras.myquizapplication.Entity.Usuario;
import com.contreras.myquizapplication.Interfaces.ILogin;
import com.contreras.myquizapplication.Presenter.LoginPresenter;
import com.contreras.myquizapplication.R;
import com.contreras.myquizapplication.Util.Constantes;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener, ILogin.ILoginView {

    @BindView(R.id.edt_correo)
    @Required(order=1,message = "Debe ingresar correo")
    @Email(order = 2,message = "Correo incorrecto")
    EditText edt_correo;

    @BindView(R.id.edt_password)
    @Password(order = 3,message = "Debe ingresar su contraseña")
    EditText edt_password;

    LoginPresenter presenter;
    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        presenter = new LoginPresenter(this);

        validator = new Validator(this);
        validator.setValidationListener(this);

    }


    @OnClick(R.id.btn_login)
    public void login(){
        validator.validate();
    }

    private void guardarPreferenciaUsuario(int codigoUsuario) {
        SharedPreferences.Editor editor = getSharedPreferences(Constantes.PREFERENCIA_USUARIO,0).edit();
        editor.putInt("codigo_usuario",codigoUsuario);
        editor.commit();
    }

    @Override
    public void onValidationSucceeded() {
        String correo = edt_correo.getText().toString();
        String password = edt_password.getText().toString();

        enviarInformacion(correo,password);

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
    public void enviarInformacion(String correo, String password) {
            presenter.solicitarValidacion(correo,password);
    }

    @Override
    public void obtenerUsuario(Usuario usuario) {
        if(usuario!=null){
            guardarPreferenciaUsuario(usuario.getCodigoUsuario());
            finish();
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
        }
    }
}
