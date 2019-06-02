package com.contreras.myquizapplication.Presenter;

import com.contreras.myquizapplication.Entity.Usuario;
import com.contreras.myquizapplication.Interfaces.IRegistrar;
import com.contreras.myquizapplication.Model.RegistrarModel;
import com.contreras.myquizapplication.View.RegistrarActivity;

public class RegistrarPresenter implements IRegistrar.IRegistrarPresenter {

    private RegistrarActivity view;
    private RegistrarModel model;

    public RegistrarPresenter(RegistrarActivity view){
        this.view = view;
        model = new RegistrarModel(this,view);
    }

    @Override
    public void solicitarUsuario(Usuario usuario) {
        model.insertarUsuario(usuario);
    }
}
