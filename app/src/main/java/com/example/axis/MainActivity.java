package com.example.axis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
    }

    //Metodo del boton
    public void entrar(View view){
        String usuario = et1.getText().toString();
        String password = et2.getText().toString();

        if ((usuario.length() == 0) || (password.length()==0)){
            Toast.makeText(this,"Campos vacios",Toast.LENGTH_LONG).show();
        }
            else if((usuario.equals("Admin")) && password.equals("admin")){
            Intent siguiente = new Intent(this,DashboardDeUsuario.class);
            startActivity(siguiente);
            }else {
                Toast.makeText(this, "Nombre de usuario o contrase;a incorrectos", Toast.LENGTH_LONG).show();
            }
        }
}
