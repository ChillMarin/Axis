package com.example.axis;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;

import com.example.axis.Models.Lote;
import com.example.axis.Models.Transaccion;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CierreDeLotes extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    ProgressBar p;
    Handler h = new Handler();
    boolean isActivo = false;
    int i = 0;
    TextView t;
    Intent x ;
    private DatabaseReference TDatabase,TDatabase2,TDatabase3;
    Button b;
    private ArrayList<Transaccion> transaccions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cierre_de_lotes);
        setUpToolbar();
        p = (ProgressBar)findViewById(R.id.progressBar);
        t = (TextView)findViewById(R.id.tvCierreLotes);
        b = (Button)findViewById(R.id.btnCierre);
        b.setOnClickListener(this);
        TDatabase = FirebaseDatabase.getInstance().getReference();
        TDatabase2 = FirebaseDatabase.getInstance().getReference();
        TDatabase3 = FirebaseDatabase.getInstance().getReference();

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCierre) {
            enviartransacciones();
        }
    }
    private void enviartransacciones() {
        TDatabase.child("Transaccion").orderByChild("procesada").equalTo("0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                transaccions.clear();
                try {
                    for (DataSnapshot ds : dataSnapshot.getChildren()
                    ) {
                        Transaccion t = new Transaccion();
                        String cedula = ds.child("idCliente").getValue().toString();
                        t.setMonto((ds.child("monto").getValue().toString()));
                        t.setIdEstatus((ds.child("idEstatus").getValue().toString()));
                        t.setFecha(ds.child("fecha").getValue().toString());
                        t.setIdTransaccion(ds.child("idTransaccion").getValue().toString());
                        t.setIdTarjeta(ds.child("idTarjeta").getValue().toString());
                        t.setIdCliente(ds.child("idCliente").getValue().toString());
                        t.setProcesada("1");
                        transaccions.add(t);
                        TDatabase3.child("Transaccion").child(t.getIdTransaccion()).setValue(t);
                    }
                } catch (Exception e) {
                    Log.d("Error", e.toString());
                }
                Lote lote = new Lote();
                String id = TDatabase2.push().getKey();
                lote.setIdLote(id);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = new Date();
                lote.setLote_fecha(dateFormat.format(date));
                lote.setLoteEstatus("Aprobado");
                lote.setTransaccions(transaccions);
                TDatabase2.child("Lote").child(id).setValue(lote);
            }
    }  //

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
