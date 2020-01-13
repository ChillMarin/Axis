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
            Ced.setText("Cédula: "+transaccion.getIdCliente());
            Mon.setText("Monto: "+transaccion.getMonto());
            Fec.setText("Fecha: "+transaccion.getFecha());
            Est.setText("Estatus: "+ transaccion.getIdEstatus());
        }
        Reversar = (Button) findViewById(R.id.btnReversar);
        inicializarfirebase();
        Reversar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(ReversoRecibos.this);
                alerta.setMessage("¿Seguro de que desea reversar la operacion?").setCancelable(false).setPositiveButton("Reversar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        transaccion.setIdEstatus("Reversado");
                        databaseReference.child("Transaccion").child(transaccion.getIdTransaccion()).setValue(transaccion);
                        Est.setText("Reversado");
                    }
                });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("Estatus de transacción");
                titulo.show();

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


