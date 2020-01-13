package com.example.axis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.axis.Models.Transaccion;
import com.example.axis.adapters.recibosadapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Recibos extends AppCompatActivity{
    private Toolbar toolbar;
    private DatabaseReference TDatabase,TDatabase2;
    private recibosadapter Tadapter;
    private RecyclerView TrecyclerView;
    private ArrayList<Transaccion> transaccions = new ArrayList<>();
    private TextView textView;
    private Transaccion t= new Transaccion();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibos);
        setUpToolbar();
        Bundle extras = getIntent().getExtras();
        TrecyclerView = (RecyclerView) findViewById(R.id.RecyclerViewTransacciones);
        TrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        textView = (TextView) findViewById(R.id.tituloActivityTransacciones);
        TDatabase = FirebaseDatabase.getInstance().getReference();
        getTransacciones();

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
private void getTransacciones(){
        TDatabase.child("Transaccion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    transaccions.clear();
                    try {
                        for (DataSnapshot ds: dataSnapshot.getChildren()
                        ) {
                            Transaccion t = new Transaccion();
                            String cedula = ds.child("idCliente").getValue().toString();
                            t.setMonto((ds.child("monto").getValue().toString()));
                            t.setIdEstatus(Integer.parseInt(ds.child("idEstatus").getValue().toString()));
                            t.setFecha(ds.child("fecha").getValue().toString());
                            t.setIdTransaccion(ds.child("idTransaccion").getValue().toString());
                            t.setIdTarjeta(ds.child("idTarjeta").getValue().toString());
                            t.setIdCliente(cedula);
                            transaccions.add(t);
                        }
                    }catch (Exception e){
                        Log.d("Error", e.toString());
                    }

                    Tadapter = new recibosadapter(transaccions,R.layout.activity_copia_de_recibo);

                    Tadapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int i = TrecyclerView.getChildAdapterPosition(v);
                            t.setFecha(transaccions.get(i).getFecha().toString());
                            t.setIdTransaccion(transaccions.get(i).getIdTransaccion().toString());
                            t.setIdEstatus(transaccions.get(i).getIdEstatus());
                            t.setIdCliente(transaccions.get(i).getIdCliente().toString());
                            t.setMonto(transaccions.get(i).getMonto().toString());
                            t.setIdTarjeta(transaccions.get(i).getIdTarjeta().toString());
                            Intent intent = new Intent(Recibos.this, ReversoRecibos.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("Tra",t);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }


                    });
                    TrecyclerView.setAdapter(Tadapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

}





}


