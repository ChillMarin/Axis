package com.example.axis;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.axis.Models.Persona;
import com.example.axis.Models.Tarjeta;
import com.example.axis.Models.Transaccion;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TarjetaPin extends AppCompatActivity{
    private Toolbar toolbar;
    private Button btnalert;
    EditText Eclave;
    private String resultado;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Tarjeta tarjeta1 = new Tarjeta();
    Persona cliente = new Persona();
    Transaccion transaccion = new Transaccion();
    String Mensaje;
    String estatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Eclave = (EditText)findViewById(R.id.editTextpin);
        setContentView(R.layout.activity_pin);
        setUpToolbar();
        Bundle extras = getIntent().getExtras();
        estatus = "Negado";
        transaccion.setIdEstatus(estatus);
            Mensaje = "Negado";


        if (extras != null) {
            try {
                String tarjeta = extras.getString("ntarjeta");
                String ccv = extras.getString("ccv");
                String fecha = extras.getString("fecha");
                String cedula =extras.getString("cedula");
                String monto = extras.getString("monto");
                String Tipodecuenta = extras.getString("Tipodecuenta");
                String Tpersona = extras.getString("Tipodepersona");
                tarjeta1.setNumerodecuenta(tarjeta);
                tarjeta1.setCcv((ccv));
                tarjeta1.setFecha(fecha);
                tarjeta1.setTipodecuenta(Tipodecuenta);
                cliente.setPersonaCedula((cedula));
                cliente.setPersonaTipo(Tpersona);
                transaccion.setMonto(monto);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = new Date();
                transaccion.setFecha(dateFormat.format(date));
            }catch (Exception e){
                Log.d("ErrorInsertando",e.toString());
            }

        }
        inicializarfirebase();
        btnalert = (Button) findViewById(R.id.btnCierre);
        btnalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(TarjetaPin.this);
                alerta.setMessage(Mensaje).setCancelable(false).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(TarjetaPin.this, ReversoRecibos.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Tra",transaccion);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                Eclave = (EditText)findViewById(R.id.editTextpin);
                if (Eclave.getText().toString().isEmpty()) {
                    Toast.makeText(TarjetaPin.this, "Digite su clave", Toast.LENGTH_SHORT).show();
                }else {

                    CrearTransaccion();
                    AlertDialog titulo = alerta.create();
                    titulo.setTitle("Estatus");
                    titulo.show();
                }
            }
        });
    }
    private void CrearTransaccion(){
        String id = databaseReference.push().getKey();
        tarjeta1.setIdCliente(cliente.getPersonaCedula());
        cliente.setIdPersona(id);
        transaccion.setIdCliente(cliente.getPersonaCedula());
        databaseReference.child("Cliente").child(id).setValue(cliente);
        id = databaseReference.push().getKey();
        tarjeta1.setIdTarjeta(id);
        transaccion.setIdTarjeta(tarjeta1.getNumerodecuenta());
        databaseReference.child("Tarjeta").child(id).setValue(tarjeta1);
        id = databaseReference.push().getKey();
        transaccion.setIdTransaccion(id);
        transaccion.setProcesada("0");
        databaseReference.child("Transaccion").child(id).setValue(transaccion);
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
}


