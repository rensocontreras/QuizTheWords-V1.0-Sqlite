package com.contreras.myquizapplication.Model;

import com.contreras.myquizapplication.Database.MyOpenHelper;
import com.contreras.myquizapplication.Interfaces.ILogin;
import com.contreras.myquizapplication.Presenter.LoginPresenter;
import com.contreras.myquizapplication.View.LoginActivity;

public class LoginModel implements ILogin.ILoginModel {

    MyOpenHelper db;
    LoginPresenter presenter;

    public LoginModel(LoginPresenter presenter, LoginActivity view) {
        this.presenter = presenter;
        db = new MyOpenHelper(view);
    }

    @Override
    public void validarUsuario(String correo, String password) {
        presenter.obtenerValidacion(db.validarUsuario(correo, password));
    }
}
