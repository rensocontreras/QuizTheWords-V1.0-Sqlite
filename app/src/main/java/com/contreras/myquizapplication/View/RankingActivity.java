package com.contreras.myquizapplication.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.contreras.myquizapplication.Adapter.CompetidorAdapter;
import com.contreras.myquizapplication.Entity.ItemEntity.ItemCompetidorTop;
import com.contreras.myquizapplication.Interfaces.IRanking;
import com.contreras.myquizapplication.Presenter.RankingPresenter;
import com.contreras.myquizapplication.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RankingActivity extends AppCompatActivity implements IRanking.IRankingView {

    @BindView(R.id.recycler_competidoresTop)
    RecyclerView recycler_competidoresTop;

    RankingPresenter presenter;
    CompetidorAdapter competidorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ButterKnife.bind(this);
        presenter = new RankingPresenter(this);
        obtenerCompetidoresTop();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(RankingActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void obtenerCompetidoresTop() {
        presenter.solicitarListaCompetidoresTop();
    }

    @Override
    public void mostrarListaCompetidoresTop(ArrayList<ItemCompetidorTop> competidoresTop) {
        competidorAdapter = new CompetidorAdapter(this,competidoresTop,R.layout.item_competidor_top);
        recycler_competidoresTop.setAdapter(competidorAdapter);
        recycler_competidoresTop.setLayoutManager(new LinearLayoutManager(this));
    }
}
