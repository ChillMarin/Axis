package com.example.axis;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.text.Layout;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

public class DashboardDeUsuario extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_dashboard_de_usuario);
        setUpToolbar();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }

    private void setUpToolbar() {
        toolbar = findViewById(R.id.toolbarmenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        showHomeUpIcon();

    }
    private void showHomeUpIcon() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Toast.makeText(this,"Hacia atras",Toast.LENGTH_LONG).show();
                break;
            case R.id.configuracion:
                Intent configuracion = new Intent(this,Configuracion.class);
                startActivity(configuracion);

                break;
            case R.id.testComunicacion:

                break;
            case R.id.Logon:

                break;
            case R.id.Salir:
                Intent salir = new Intent(this, Login.class);
                finish();
                startActivity(salir);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //if (id == R.id.avancedepago) {
            // Handle the camera action
     if (id == R.id.CierredeLotes) {
            onSlideClicked();
            Intent reversar = new Intent(this, CierreDeLotes.class);
            startActivity(reversar, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());

        } else if (id == R.id.CopiaDeRecibo) {
            onSlideClicked();
            Intent intent = new Intent(this, Recibos.class);
            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());


        } else if (id == R.id.Reportes) {
         onSlideClicked();
         Intent intent = new Intent(this, DetalleLote.class);
         startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Metodo para la siguiente ventana de pago debito
    public void tarjetaDebito(View view){
        Intent siguiente = new Intent(this, Tarjeta.class);
        startActivity(siguiente);
    }

    private Transition transition;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onSlideClicked(){
        transition = new Slide(Gravity.BOTTOM );
        iniciarLotes();
    }

    @SuppressWarnings("unchecked")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void iniciarLotes(){
        transition.setDuration(1000);
        transition.setInterpolator(new DecelerateInterpolator());
        getWindow().setExitTransition(transition);
    }
}
