package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.usage.NetworkStats;
import android.content.Intent;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkSpecifier;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Message;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Product;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Promotion;
import com.dd.guerrerobuitrago.fotoAppDigital.utilities.MyConexion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Open extends AppCompatActivity {

    private static int SPLASH_TIME = 3000;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_open);

        new Manager();

        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        initSplash();

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

    private void initSplash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getLogIn();
                MyConexion.loadPerson();
                MyConexion.loadMessage();
                MyConexion.loadProduct();
                MyConexion.loadPromotion();
                finish();
                progressBar.setVisibility(View.GONE);
            }
        },SPLASH_TIME);
    }
}