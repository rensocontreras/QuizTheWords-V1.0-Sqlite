package com.contreras.myquizapplication.Presenter;

import com.contreras.myquizapplication.Entity.Compite;
import com.contreras.myquizapplication.Entity.Nivel;
import com.contreras.myquizapplication.Interfaces.IJugar;
import com.contreras.myquizapplication.Model.JugarModel;
import com.contreras.myquizapplication.View.JugarActivity;

import java.util.ArrayList;

public class JugarPresenter implements IJugar.IJugarPresenter {

    JugarActivity view;
    JugarModel model;

    public JugarPresenter(JugarActivity view) {
        this.view = view;
        model = new JugarModel(this,view);
    }

    @Override
    public void solicitarNiveles(int codigoUsuario) {
        model.consultaListaNiveles(codigoUsuario);
    }

    @Override
    public void obtenerListaNiveles(ArrayList<Nivel> niveles, ArrayList<Compite> competiciones) {
        view.mostrarListaNiveles(niveles,competiciones);
    }
}
