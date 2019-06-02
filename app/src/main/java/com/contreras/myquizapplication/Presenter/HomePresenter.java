package com.contreras.myquizapplication.Presenter;

import com.contreras.myquizapplication.Interfaces.IHome;
import com.contreras.myquizapplication.Model.HomeModel;
import com.contreras.myquizapplication.View.HomeActivity;

public class HomePresenter implements IHome.IHomePresenter {

    HomeActivity view;
    HomeModel model;

    public HomePresenter(HomeActivity view) {
        this.view = view;
        model = new HomeModel(this);
    }

    @Override
    public void solicitarLogout() {
        model.executeLogout();
    }

    @Override
    public void obtenerRespuesta() {
        view.mostrarLogout();
    }
}
