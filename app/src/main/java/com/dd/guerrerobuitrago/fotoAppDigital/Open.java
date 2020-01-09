package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.google.android.material.snackbar.Snackbar;

public class Open extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        new Manager();

        ImageView btnIsOpen = findViewById(R.id.btnIsOpen);
        btnIsOpen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Si esta abierto
                getLogIn(v);
                //Si esta cerrado
                //Snackbar.make(view, "No disponible aun", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
            }
        });
    }

    public void getLogIn(View view){
        Intent logInIntent = new Intent(this, LogIn.class);
        Toast.makeText(getBaseContext(), "Abierto", Toast.LENGTH_LONG).show();
        startActivity(logInIntent);
    }
}