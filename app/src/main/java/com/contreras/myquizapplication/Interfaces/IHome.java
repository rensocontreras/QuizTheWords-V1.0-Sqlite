package com.contreras.myquizapplication.Interfaces;

public class IHome {

    public interface IHomeView {
        void salir();
        void mostrarLogout();
    }

    public interface IHomePresenter{
        void solicitarLogout();
        void obtenerRespuesta();
    }

    public interface IHomeModel{
        void executeLogout();
    }

}
