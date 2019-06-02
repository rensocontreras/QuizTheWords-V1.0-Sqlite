package com.contreras.myquizapplication.Model;

import com.contreras.myquizapplication.Database.MyOpenHelper;
import com.contreras.myquizapplication.Entity.ItemEntity.ItemCompetidorTop;
import com.contreras.myquizapplication.Interfaces.IRanking;
import com.contreras.myquizapplication.Presenter.RankingPresenter;
import com.contreras.myquizapplication.View.RankingActivity;

import java.util.ArrayList;

public class RankingModel implements IRanking.IRankingmodel {

    RankingPresenter presenter;
    RankingActivity view;
    MyOpenHelper db;
    ArrayList<ItemCompetidorTop> competidoresTop;


    public RankingModel(RankingPresenter presenter, RankingActivity view){
        this.presenter = presenter;
        this.view = view;
        db = new MyOpenHelper(view);
    }


    @Override
    public void consultaListaCompetidoresTop() {
        competidoresTop = db.obtenerItemCompetidoresTop();
        presenter.obtenerListaCompetidoresTop(competidoresTop);
    }
}
