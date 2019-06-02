package com.contreras.myquizapplication.Model;

import com.contreras.myquizapplication.Database.MyOpenHelper;
import com.contreras.myquizapplication.Entity.Compite;
import com.contreras.myquizapplication.Entity.Nivel;
import com.contreras.myquizapplication.Interfaces.IJugar;
import com.contreras.myquizapplication.Presenter.JugarPresenter;
import com.contreras.myquizapplication.View.JugarActivity;

import java.util.ArrayList;

public class JugarModel implements IJugar.IJugarModel {

    JugarPresenter presenter;
    MyOpenHelper db;
    ArrayList<Nivel> niveles;
    ArrayList<Compite> competiciones;

    public JugarModel(JugarPresenter presenter, JugarActivity view) {
        this.presenter = presenter;
        db = new MyOpenHelper(view);

    }

    @Override
    public void consultaListaNiveles(int codigoUsuario) {
        niveles = db.obtenerNiveles();
        competiciones = db.obtenerCompeticion(codigoUsuario);
        presenter.obtenerListaNiveles(niveles,competiciones);
    }
}
