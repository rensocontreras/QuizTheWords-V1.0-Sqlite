package com.contreras.myquizapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.contreras.myquizapplication.View.LoginActivity;
import com.contreras.myquizapplication.View.RegistrarActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void login(){
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_registrar)
    public void registrar(){
        Intent i = new Intent(MainActivity.this, RegistrarActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_salir)
    public void salir(){
        finish();
    }
}
