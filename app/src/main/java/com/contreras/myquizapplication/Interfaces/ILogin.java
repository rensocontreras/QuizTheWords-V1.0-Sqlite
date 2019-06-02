package com.contreras.myquizapplication.Interfaces;

import com.contreras.myquizapplication.Entity.Usuario;

public class ILogin {

    public interface ILoginView{
        public void enviarInformacion(String correo, String password);
        public void obtenerUsuario(Usuario usuario);
    }

    public interface ILoginPresenter{
        public void solicitarValidacion(String correo, String password);
        public void obtenerValidacion(Usuario usuario);
    }

    public interface ILoginModel{
        public void validarUsuario(String correo, String password);
    }

}
