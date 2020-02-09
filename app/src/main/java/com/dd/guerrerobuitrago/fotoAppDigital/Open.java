package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
import com.dd.guerrerobuitrago.fotoAppDigital.models.Product;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Promotion;
import com.dd.guerrerobuitrago.fotoAppDigital.utilities.Utilities;

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
                loadProduct();
                loadPromotion();
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
                                     JSONObject jsonPerson = arrayPerson.getJSONObject(i);
                                     int id = jsonPerson.getInt("id");
                                     String firstName = jsonPerson.getString("first_name");
                                     String lastName = jsonPerson.getString("last_name");
                                     String userName = jsonPerson.getString("user_name");
                                     String password = jsonPerson.getString("password");
                                     String photo = jsonPerson.getString("photo");
                                     String type = jsonPerson.getString("type");
                                     Manager.addPerson(new Person(id,firstName,lastName,userName,password,Uri.parse(photo),type));
                                }
                            }else{
                                Log.e("Datos vacios", "Error");
                            }
                        } catch (JSONException e) {
                            Log.e("Error Consulta Json", "Error Consulta JSON");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error consulta", "Error consulta metodo conexion onError");
                    }
                });
    }

    public void loadProduct(){
        AndroidNetworking.get("https://polar-plains-39256.herokuapp.com/SQLProduct_GET.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String respuesta = response.getString("respuesta");
                            if(respuesta.equals("200")){
                                JSONArray arrayProduct = response.getJSONArray("data");
                                for (int i=0; i<arrayProduct.length();i++){
                                    JSONObject jsonProduct = arrayProduct.getJSONObject(i);
                                    int id = jsonProduct.getInt("id");
                                    String name = jsonProduct.getString("name");
                                    String description = jsonProduct.getString("description");
                                    String photo = jsonProduct.getString("photo");
                                    Manager.addProduct(new Product(id, name, description, Uri.parse(photo)));
                                }
                            }else{
                                Log.e("Datos vacios", "Error");
                            }
                        } catch (JSONException e) {
                            Log.e("Error Consulta Json", "Error Consulta JSON");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error consulta", "Error consulta metodo conexion onError");
                    }
                });
    }

    public void loadPromotion(){
        AndroidNetworking.get("https://polar-plains-39256.herokuapp.com/SQLPromotion_GET.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String respuesta = response.getString("respuesta");
                            if(respuesta.equals("200")){
                                JSONArray arrayPromotion = response.getJSONArray("data");
                                for (int i=0; i<arrayPromotion.length();i++){
                                    JSONObject jsonPromotion = arrayPromotion.getJSONObject(i);
                                    int id = jsonPromotion.getInt("id");
                                    String name = jsonPromotion.getString("name");
                                    String photo = jsonPromotion.getString("photo");
                                    Manager.addPromotion(new Promotion(id, name, Uri.parse(photo)));
                                }
                            }else{
                                Log.e("Datos vacios", "Error");
                            }
                        } catch (JSONException e) {
                            Log.e("Error Consulta Json", "Error Consulta JSON");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error consulta", "Error consulta metodo conexion onError");
                    }
                });
    }
}