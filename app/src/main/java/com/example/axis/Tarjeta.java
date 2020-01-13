package com.example.axis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Tarjeta extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText nTarjerta,ccv,fVencimiento;
    private TextView datep;
    private DatePickerDialog.OnDateSetListener date;
    private String fecha="";
    private Spinner spinner,spinnerpersona;
    private String Tipodecuenta="";
    private String Tpersona="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjeta);
        nTarjerta = (EditText) findViewById(R.id.numerodeTarjeta);
        ccv = (EditText) findViewById(R.id.ccv);
        fVencimiento = (EditText) findViewById(R.id.fechaVencimiento);
        datep = (TextView) findViewById(R.id.TVDatepicker);
        spinner = (Spinner) findViewById(R.id.spinnerTcuenta);
        spinnerpersona = (Spinner) findViewById(R.id.spinnerPersona);
        setUpToolbar();
        datep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Tarjeta.this,android.R.style.Theme_Holo_Dialog_MinWidth,date,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }

        });
     date = new DatePickerDialog.OnDateSetListener() {
         @Override
         public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
             month = month +1;
             datep.setText(dayOfMonth + "/" +month  +"/" + year);
             fecha =datep.getText().toString();
         }
     };
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.combo,android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               Tipodecuenta = parent.getItemAtPosition(position).toString();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });


        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(this,R.array.persona,android.R.layout.simple_spinner_item);
        spinnerpersona.setAdapter(arrayAdapter2);
        spinnerpersona.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Tpersona = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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


    //Metodo para regresar
    public void regresar(View view){
        Intent siguiente = new Intent(this,DashboardDeUsuario.class);
        startActivity(siguiente);
    }

    //Metodo para ir a la pantalla de cedula
    public void siguiente(View view){
        if (nTarjerta.getText().toString().isEmpty()){
            Toast.makeText(this, "El número de tarjeta no puede estar vacio", Toast.LENGTH_SHORT).show();
        }else if (ccv.getText().toString().isEmpty()){
            Toast.makeText(this, "El número ccv no puede estar vacio", Toast.LENGTH_SHORT).show();
        }else if (fecha.toString().isEmpty()){
            Toast.makeText(this, "La fecha de vencimiento no puede estar vacia", Toast.LENGTH_SHORT).show();
        }else {
            Intent siguiente = new Intent(this, TarjetaCedula.class);
            String nTarjeta1 = nTarjerta.getText().toString();
            String ccv1 = ccv.getText().toString();
            siguiente.putExtra("ntarjeta", nTarjeta1);
            siguiente.putExtra("ccv", ccv1);
            siguiente.putExtra("fecha", fecha);
            siguiente.putExtra("Tipodecuenta",Tipodecuenta);
            siguiente.putExtra("Tipodepersona",Tpersona);
            startActivity(siguiente);
        }
    }

    //
}
