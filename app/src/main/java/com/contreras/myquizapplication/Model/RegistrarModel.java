package com.contreras.myquizapplication.Model;

import com.contreras.myquizapplication.Database.MyOpenHelper;
import com.contreras.myquizapplication.Entity.Usuario;
import com.contreras.myquizapplication.Interfaces.IRegistrar;
import com.contreras.myquizapplication.Presenter.RegistrarPresenter;
import com.contreras.myquizapplication.View.RegistrarActivity;

public class RegistrarModel implements IRegistrar.IRegistrarModel {

    MyOpenHelper db;
    RegistrarPresenter presenter;

    public RegistrarModel(RegistrarPresenter presenter, RegistrarActivity view) {
        this.presenter = presenter;
        db = new MyOpenHelper(view);
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        db.insertarUsuario(usuario);
    }
}
