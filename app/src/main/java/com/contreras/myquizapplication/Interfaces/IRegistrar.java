package com.contreras.myquizapplication.Interfaces;

import com.contreras.myquizapplication.Entity.Usuario;

public class IRegistrar {

    public interface IRegistrarView{
        void enviarUsuario(Usuario usuario);
    }

    public interface IRegistrarPresenter{
        void solicitarUsuario(Usuario usuario);
    }

    public interface IRegistrarModel{
        void insertarUsuario(Usuario usuario);
    }

}
