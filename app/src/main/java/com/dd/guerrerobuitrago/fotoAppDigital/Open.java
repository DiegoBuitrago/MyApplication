package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getLogIn();
                loadPerson();
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

    public void loadPerson(){
        AndroidNetworking.get("https://polar-plains-39256.herokuapp.com/SQLPersons_GET.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String respuesta = response.getString("respuesta");
                            if(respuesta.equals("200")){
                                JSONArray arrayPerson = response.getJSONArray("data");
                                for (int i=0; i<arrayPerson.length();i++){
                                    Log.e("entro al for", "Error");
                                     JSONObject jsonPerson = arrayPerson.getJSONObject(i);
                                     int id = jsonPerson.getInt("id");
                                     String firstName = jsonPerson.getString("first_name");
                                     String lastName = jsonPerson.getString("last_name");
                                     String userName = jsonPerson.getString("user_name");
                                     String password = jsonPerson.getString("password");
                                     String photo = jsonPerson.getString("photo");
                                     String type = jsonPerson.getString("type");
                                     Manager.addPerson(new Person(id,firstName,lastName,userName,password,photo,type));
                                }
                            }else{
                                Log.e("Datos vacios", "Error");
                            }
                        } catch (JSONException e) {
                            Log.e("Error consultadddd", "Error");
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error consulta", "Error");
                    }
                });
    }
}