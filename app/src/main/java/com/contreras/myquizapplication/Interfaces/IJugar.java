package com.contreras.myquizapplication.Interfaces;

import com.contreras.myquizapplication.Entity.Compite;
import com.contreras.myquizapplication.Entity.Nivel;

import java.util.ArrayList;

public class IJugar {

    public interface IJugarView{
        void obtenerNiveles(int codigoUsuario);
        void mostrarListaNiveles(ArrayList<Nivel> niveles, ArrayList<Compite> competiciones);
    }

    public interface IJugarPresenter{
        void solicitarNiveles(int codigoUsuario);
        void obtenerListaNiveles(ArrayList<Nivel> niveles, ArrayList<Compite> competiciones);
    }

    public interface IJugarModel{
        void consultaListaNiveles(int codigoUsuario);
    }
}
