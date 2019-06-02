package com.contreras.myquizapplication.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.contreras.myquizapplication.Interfaces.IHome;
import com.contreras.myquizapplication.MainActivity;
import com.contreras.myquizapplication.Presenter.HomePresenter;
import com.contreras.myquizapplication.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements IHome.IHomeView {

    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        presenter = new HomePresenter(this);
        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick(R.id.btn_juega)
    public void jugar(){
        Intent i = new Intent(HomeActivity.this, JugarActivity.class);
        finish();
        startActivity(i);
    }

    @OnClick(R.id.btn_ranking)
    public void ranking(){
        Intent i = new Intent(HomeActivity.this, RankingActivity.class);
        finish();
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mi_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        switch(id) {
            case R.id.mi_logout:
                salir();
                break;
        }
        return false;
    }

    @Override
    public void salir() {
        presenter.solicitarLogout();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void mostrarLogout() {
        Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
    }
}
