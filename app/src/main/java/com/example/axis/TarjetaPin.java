package com.example.axis;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TarjetaPin extends AppCompatActivity{
    private Toolbar toolbar;
    private Button btnalert;
    EditText clave;
    private String resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clave = (EditText)findViewById(R.id.editTextpin);
        setContentView(R.layout.activity_pin);
        setUpToolbar();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String tarjeta = extras.getString("ntarjeta");
            String ccv = extras.getString("ccv");
            String fecha = extras.getString("fecha");
            String cedula =extras.getString("cedula");
            String monto = extras.getString("monto");
            resultado = " Numero de cedula: " + cedula + " fecha: " + fecha + " monto: " + monto;
        }
        btnalert = (Button) findViewById(R.id.btnCierre);
        btnalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(TarjetaPin.this);
                alerta.setMessage("  Aprobado" + resultado).setCancelable(false).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent siguiente = new Intent(TarjetaPin.this, DashboardDeUsuario.class);
                        startActivity(siguiente);
                    }
                });
                clave = (EditText)findViewById(R.id.editTextpin);
                if (clave.getText().toString().isEmpty()) {
                    Toast.makeText(TarjetaPin.this, "Digite su clave", Toast.LENGTH_SHORT).show();
                }else {
                    AlertDialog titulo = alerta.create();
                    titulo.setTitle("Estatus");
                    titulo.show();
                }
            }
        });
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
    public void regresar(View view){
        Intent siguiente = new Intent(this,DashboardDeUsuario.class);
        startActivity(siguiente);
    }
}


