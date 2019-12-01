package com.example.axis;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TarjetaMonto extends AppCompatActivity {
    private Toolbar toolbar;
    EditText monto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monto);
        setUpToolbar();
        monto= (EditText) findViewById(R.id.Monto);

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
        switch (item.getItemId()){
            case android.R.id.home:
                Intent siguiente = new Intent(this,DashboardDeUsuario.class);
                startActivity(siguiente);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Metodo para continuar
    public void continuar(View view){
        if (monto.getText().toString().isEmpty()) {
            Toast.makeText(this, "El número de cedula no puede estar vacio", Toast.LENGTH_SHORT).show();
        }else {
            Intent siguiente = new Intent(this, TarjetaPin.class);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                siguiente.putExtra("ntarjeta", extras.getString("ntarjeta"));
                siguiente.putExtra("ccv", extras.getString("ccv"));
                siguiente.putExtra("fecha", extras.getString("fecha"));
                siguiente.putExtra("cedula", extras.getString("cedula"));
                siguiente.putExtra("monto", monto.getText().toString());
                startActivity(siguiente);
            }
        }

    }
}



