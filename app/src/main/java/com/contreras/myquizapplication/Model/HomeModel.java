package com.contreras.myquizapplication.Model;

import com.contreras.myquizapplication.Interfaces.IHome;
import com.contreras.myquizapplication.Presenter.HomePresenter;

public class HomeModel implements IHome.IHomeModel {

    HomePresenter presenter;

    public HomeModel(HomePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void executeLogout() {
        presenter.obtenerRespuesta();
    }
}
