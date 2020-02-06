package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;

public class Open extends AppCompatActivity {

    private static int SPLASH_TIME = 3000;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_open);

        new Manager();

        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getLogIn();
                finish();
                progressBar.setVisibility(View.GONE);
            }
        },SPLASH_TIME);

//        ImageView btnIsOpen = findViewById(R.id.btnIsOpen);
//        btnIsOpen.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                //Si esta abierto
//                getLogIn(v);
//                //choose();
//                //Si esta cerrado
//                //Snackbar.make(view, "No disponible aun", Snackbar.LENGTH_LONG)
//                        //.setAction("Action", null).show();
//            }
//        });
    }

    public void getLogIn(){
        Intent logInIntent = new Intent(this, LogIn.class);
        //Toast.makeText(getBaseContext(), "Abierto", Toast.LENGTH_LONG).show();
        startActivity(logInIntent);
    }
}