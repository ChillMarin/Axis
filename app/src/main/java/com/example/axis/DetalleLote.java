package com.example.axis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.axis.Models.Lote;
import com.example.axis.Models.Transaccion;
import com.example.axis.adapters.operacionesAdapter;
import com.example.axis.adapters.recibosadapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetalleLote extends AppCompatActivity{
    private Toolbar toolbar;
    private DatabaseReference TDatabase,TDatabase2;
    private operacionesAdapter Tadapter;
    private RecyclerView TrecyclerView;
    private ArrayList<Lote> transaccions = new ArrayList<>();
    private TextView textView;
    private Transaccion t= new Transaccion();
    FirebaseDatabase firebaseDatabase;
    private String cedula1;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lote);
        setUpToolbar();
        Bundle extras = getIntent().getExtras();
        TrecyclerView = (RecyclerView) findViewById(R.id.RecyclerViewLotes);
        TrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TDatabase = FirebaseDatabase.getInstance().getReference();
        TDatabase2 = FirebaseDatabase.getInstance().getReference();
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
        TDatabase.child("Lote").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    transaccions.clear();
                    try {
                        for (DataSnapshot ds: dataSnapshot.getChildren()
                        ) {
                            Lote t = new Lote();
                            /*Query query = TDatabase2.child("Cliente").orderByChild("idPersona").equalTo(bcedula);
                            query.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    try {
                                            for (DataSnapshot ds1 : dataSnapshot.getChildren()){
                                                ds1.child("personaCedula").getValue().toString()
                                                cedula1 = ds1.child("personaCedula").getValue().toString();
                                            }

                                    } catch (Exception e) {
                                        Log.d("Error", e.toString() + cedula1);
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });*/
                            t.setLote_fecha(ds.child("lote_fecha").getValue().toString());
                            t.setIdLote(ds.child("idLote").getValue().toString());
                            t.setLoteEstatus(ds.child("lote_fecha").getValue().toString());
                            transaccions.add(t);
                        }
                    }catch (Exception e){
                        Log.d("Error", e.toString());
                    }

                    Tadapter = new operacionesAdapter(transaccions,R.layout.activity_lotes_detalle);
                    TrecyclerView.setAdapter(Tadapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

}





}


