package com.example.axis;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;

public class CierreDeLotes extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    ProgressBar p;
    Handler h = new Handler();
    boolean isActivo = false;
    int i = 0;
    TextView t;
    Intent x ;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cierre_de_lotes);
        setUpToolbar();
        p = (ProgressBar)findViewById(R.id.progressBar);
        t = (TextView)findViewById(R.id.tvCierreLotes);
        b = (Button)findViewById(R.id.btnCierre);
        b.setOnClickListener(this);
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
    public void onClick(View v){
        if(v.getId()==R.id.btnCierre){
            if(!isActivo){
                Thread hilo = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (i<=100) {

                            h.post(new Runnable() {
                                @Override
                                public void run() {
                                    t.setText(i + " %");
                                    p.setProgress(i);
                                }
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (i==100){

                            }

                            i++;
                            isActivo = true;
                        }
                    }
                });
                hilo.start();
            }
        }
    }



    //
}
