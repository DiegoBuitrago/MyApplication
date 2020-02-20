package com.dd.guerrerobuitrago.fotoAppDigital.utilities;

import android.net.Uri;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Message;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Product;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Promotion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyConexion {

    public static void loadPerson(){
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
                                    Manager.addPerson(new Person(id,firstName,lastName,userName,password, Uri.parse(photo),type));
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

    public static void loadProduct(){
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

    public static void loadPromotion(){
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

    public static void loadMessage(){
        AndroidNetworking.get("https://polar-plains-39256.herokuapp.com/SQLChat_GET.php")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String respuesta = response.getString("respuesta");
                            if(respuesta.equals("200")){
                                JSONArray arrayMessage = response.getJSONArray("data");
                                for (int i=0; i<arrayMessage.length();i++){
                                    JSONObject jsonMessage = arrayMessage.getJSONObject(i);
                                    int id = jsonMessage.getInt("id");
                                    String name = jsonMessage.getString("name");
                                    String text = jsonMessage.getString("text");
                                    Manager.addMessage(new Message(id, name, text));
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

    public static void loadDataBasePromotion(Promotion promotion){
        Map<String,String> datos = new HashMap<>();
        datos.put("id",String.valueOf(promotion.getId_product()));
        datos.put("name",promotion.getName());
        datos.put("photo", promotion.getPhoto().toString());
        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post("https://polar-plains-39256.herokuapp.com/SQLPromotion_INSERT.php")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String   state = response.getString("estado");
                            Log.d("Estado","blablabla"+state);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error","blablablapromotion");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error",anError.getErrorDetail());
                    }
                });
    }

    public static void deleteDataBasePromotion(int position){
        Map<String,String> datos = new HashMap<>();
        datos.put("id",String.valueOf(position));
        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post("https://polar-plains-39256.herokuapp.com/SQLPromotion_DELETE.php")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String   state = response.getString("estado");
                            Log.d("Estado","blablabla"+state);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error","blablablapromotion");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error",anError.getErrorDetail());
                    }
                });
    }

    public static void loadDataBaseProduct(Product product){
        Map<String,String> datos = new HashMap<>();
        datos.put("id",String.valueOf(product.getId()));
        datos.put("name",product.getName());
        datos.put("description",product.getDescription());
        if(product.getPhoto()!=null) {
            datos.put("photo", product.getPhoto().toString());
        }else{
            datos.put("photo", null);
        }

        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post("https://polar-plains-39256.herokuapp.com/SQLProduct_INSERT.php")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String   state = response.getString("estado");
                            Log.d("Estado","blablabla"+state);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error","blablabla");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error",anError.getErrorDetail());
                    }
                });
    }

    public static void deleteDataBaseProduct(int position){
        Map<String,String> datos = new HashMap<>();
        datos.put("id",String.valueOf(position));
        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post("https://polar-plains-39256.herokuapp.com/SQLProduct_DELETE.php")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String   state = response.getString("estado");
                            Log.d("Estado","blablabla"+state);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error","blablablapromotion");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error",anError.getErrorDetail());
                    }
                });
    }

    public static void loadDataBasePerson(Person person){
        Map<String,String> datos = new HashMap<>();
        datos.put("firstName",person.getFirstName());
        datos.put("lastName",person.getLastName());
        datos.put("userName",person.getUserName());
        datos.put("password",person.getPassword());
        if(person.getPhoto()!=null) {
            datos.put("photo", person.getPhoto());
        }else{
            datos.put("photo", null);
        }
        datos.put("type",person.getTypeUser());

        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post("https://polar-plains-39256.herokuapp.com/SQLPerson_INSERT.php")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String   state = response.getString("estado");
                            Log.d("Estado","blablabla"+state);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error","blablabla");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error",anError.getErrorDetail());
                    }
                });
    }

    public static void deleteDataBasePerson(String nameUser){
        Map<String,String> datos = new HashMap<>();
        datos.put("userName",nameUser);
        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post("https://polar-plains-39256.herokuapp.com/SQLPerson_DELETE.php")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String   state = response.getString("estado");
                            Log.d("Estado","blablabla"+state);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error","blablablapromotion");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error",anError.getErrorDetail());
                    }
                });
    }

    public static void updateDataBasePerson(Person person){
        Map<String,String> datos = new HashMap<>();

        datos.put("firstName",person.getFirstName());
        datos.put("lastName",person.getLastName());
        datos.put("password",person.getPassword());
        if(person.getPhoto() != null){
            datos.put("photo",person.getPhoto());
        } else  {
            datos.put("photo",null);
        }
        datos.put("type",person.getTypeUser());
        datos.put("userName",person.getUserName());
        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post("https://polar-plains-39256.herokuapp.com/SQLPerson_UPDATE.php")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String   state = response.getString("estado");
                            Log.d("Estado","blablabla"+state);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error","blablablapromotion");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error",anError.getErrorDetail());
                    }
                });
    }
}
