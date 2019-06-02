package com.contreras.myquizapplication.Resultados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.contreras.myquizapplication.View.JugarActivity;
import com.contreras.myquizapplication.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_siguiente)
    public void siguiente(){
        Intent intent =new Intent(BadActivity.this, JugarActivity.class);
        startActivity(intent);
        finish();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    public void onBackPressed() {
    }
}
