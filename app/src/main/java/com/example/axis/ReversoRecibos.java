package com.example.axis;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.axis.Models.Transaccion;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReversoRecibos extends AppCompatActivity{
    private Toolbar toolbar;
    private TextView Ref, Ced, Mon,Est,Fec;
    private Button Reversar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Transaccion transaccion  = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reversar_detalle);
        setUpToolbar();
        Ref = (TextView) findViewById(R.id.textViewRef);
        Ced = (TextView) findViewById(R.id.TextViewCedula);
        Mon = (TextView) findViewById(R.id.textViewMonto);
        Fec = (TextView) findViewById(R.id.textViewFecha);
        Est = (TextView) findViewById(R.id.textViewEstatus);

        Bundle extras = getIntent().getExtras();

        if (extras!=null){
            transaccion= (Transaccion) extras.getSerializable("Tra");
            Ref.setText(transaccion.getIdTransaccion());
            Ced.setText(transaccion.getIdCliente());
            Mon.setText(transaccion.getMonto());
            Fec.setText(transaccion.getFecha());
            String estatus = "";
            if (String.valueOf(String.valueOf( transaccion.getIdEstatus())).equals(1)){
                estatus = "Aprobado";
            }else if (String.valueOf(String.valueOf( transaccion.getIdEstatus())).equals(2)){
                estatus ="Negado";
            }else if (String.valueOf(String.valueOf( transaccion.getIdEstatus())).equals(3)){
                estatus = "Reversado";
            }
            else if (String.valueOf(String.valueOf( transaccion.getIdEstatus())).equals(12)){
                estatus="Procesado en lote";
            }
            Est.setText(estatus);
        }
        Reversar = (Button) findViewById(R.id.btnReversar);
        inicializarfirebase();
        Reversar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaccion.setIdEstatus(3);
                databaseReference.child("Transaccion").child(transaccion.getIdTransaccion()).setValue(transaccion);
                Est.setText("Negado");
            }
        });
    }
    private void inicializarfirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
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
    public void reversar(){


    }
}


