package com.example.axis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class pagoUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_usuario);
    }

    //Metodo flechita atrás
    public void regresar(View view){
        Intent siguiente = new Intent(this,DashboardDeUsuario.class);
        startActivity(siguiente);
    }
}
