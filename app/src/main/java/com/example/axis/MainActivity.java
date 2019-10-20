package com.example.axis;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GestureDetectorCompat;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final long DURATION_TRANSITION = 1000;

    private EditText et1,et2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_usuario);
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
            Intent siguiente = new Intent(this,InicioSesion.class);
            startActivity(siguiente);
            }else {
                Toast.makeText(this, "Nombre de usuario o contrasena incorrectos", Toast.LENGTH_LONG).show();
            }
        }









}
