package com.example.axis;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TarjetaCedula extends AppCompatActivity {
    private Toolbar toolbar;
    EditText cedula;
    String numeroTajeta;
    String ccv;
    String fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cedula);
        setUpToolbar();
        cedula = (EditText) findViewById(R.id.numerodecedula);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             numeroTajeta = extras.getString("ntarjeta");
             ccv = extras.getString("ccv");
             fecha = extras.getString("fecha");
        }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent siguiente = new Intent(this, DashboardDeUsuario.class);
                startActivity(siguiente);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Metodo para regresar
    public void regresar(View view) {
        Intent siguiente = new Intent(this, DashboardDeUsuario.class);
        startActivity(siguiente);
    }

    //Metodo para seguir el workflow
    public void siguiente(View view) {
        if (cedula.getText().toString().isEmpty()) {
            Toast.makeText(this, "El número de cedula no puede estar vacio", Toast.LENGTH_SHORT).show();
        }else {
            Intent siguiente = new Intent(this, TarjetaMonto.class);
            siguiente.putExtra("ntarjeta", numeroTajeta);
            siguiente.putExtra("ccv", ccv);
            siguiente.putExtra("fecha", fecha);
            siguiente.putExtra("cedula", cedula.getText().toString());
            startActivity(siguiente);
        }
    }
}
