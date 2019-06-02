package com.contreras.myquizapplication.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.contreras.myquizapplication.Adapter.NivelAdapter;
import com.contreras.myquizapplication.Entity.Compite;
import com.contreras.myquizapplication.Entity.Nivel;
import com.contreras.myquizapplication.Interfaces.IJugar;
import com.contreras.myquizapplication.Presenter.JugarPresenter;
import com.contreras.myquizapplication.R;
import com.contreras.myquizapplication.Util.Constantes;

import java.util.ArrayList;

public class JugarActivity extends AppCompatActivity implements IJugar.IJugarView {

    NivelAdapter nivelAdapter;
    RecyclerView recycler_niveles;

    JugarPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter = new JugarPresenter(this);

        recycler_niveles = findViewById(R.id.recycler_niveles);

        SharedPreferences pref = getSharedPreferences(Constantes.PREFERENCIA_USUARIO,0);
        int codigo = pref.getInt("codigo_usuario",-1);

        obtenerNiveles(codigo);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(JugarActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void obtenerNiveles(int codigoUsuario) {
        presenter.solicitarNiveles(codigoUsuario);
    }

    @Override
    public void mostrarListaNiveles(ArrayList<Nivel> niveles, ArrayList<Compite> competiciones) {
        nivelAdapter = new NivelAdapter(this,niveles,competiciones,R.layout.item_nivel);
        recycler_niveles.setAdapter(nivelAdapter);
        recycler_niveles.setLayoutManager(new LinearLayoutManager(this));
    }
}
