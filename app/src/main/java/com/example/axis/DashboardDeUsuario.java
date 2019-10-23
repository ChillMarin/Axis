package com.example.axis;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
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

                break;
            case R.id.testComunicacion:

                break;
            case R.id.Logon:

                break;
            case R.id.Salir:
                Intent siguiente = new Intent(this,MainActivity.class);
                finish();
                startActivity(siguiente);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.avancedepago) {
            // Handle the camera action
        } else if (id == R.id.ReversarPago) {

        } else if (id == R.id.CierredeLotes) {

        } else if (id == R.id.CopiaDeRecibo) {

        } else if (id == R.id.Reportes) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Metodo para la siguiente ventana de pago
    public void tarjetaDebito(View view){
        Intent siguiente = new Intent(this,TarjetaDebito.class);
        startActivity(siguiente);
    }
}
