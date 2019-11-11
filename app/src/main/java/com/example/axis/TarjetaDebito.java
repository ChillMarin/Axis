package com.example.axis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class TarjetaDebito extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText nTarjerta,ccv,fVencimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjeta_debito);
        nTarjerta = (EditText) findViewById(R.id.numerodeTarjeta);
        ccv = (EditText) findViewById(R.id.ccv);
        fVencimiento = (EditText) findViewById(R.id.fechaVencimiento);
        setUpToolbar();
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


    //Metodo para regresar
    public void regresar(View view){
        Intent siguiente = new Intent(this,DashboardDeUsuario.class);
        startActivity(siguiente);
    }

    //Metodo para ir a la pantalla de cedula
    public void siguiente(View view){

        Intent siguiente = new Intent(this,TajetaDebito_cedula.class);
        String nTarjeta1 = nTarjerta.getText().toString();
        String ccv1 = ccv.getText().toString();
        String fecha = fVencimiento.getText().toString();
        siguiente.putExtra("ntarjeta",nTarjeta1);
        siguiente.putExtra("ccv",ccv1);
        siguiente.putExtra("fecha",fecha);
        startActivity(siguiente);
    }

    //
}
