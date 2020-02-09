package com.dd.guerrerobuitrago.fotoAppDigital.models;

import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dd.guerrerobuitrago.fotoAppDigital.utilities.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Manager {
    private static ArrayList<Person> personList;
    private static ArrayList<Booked> bookedList;
    private static ArrayList<Promotion> promotionList;
    private static ArrayList<Product> productList;

    public Manager(){
        personList = new ArrayList<Person>();
        bookedList = new ArrayList<Booked>();
        promotionList = new ArrayList<Promotion>();
        productList = new ArrayList<Product>();
    }

    //--------------personas
    public static void addPerson(Person person){
        personList.add(person);

//        Map<String,String> datos = new HashMap<>();
//        datos.put("id",""+person.getId());
//        datos.put("firstName",person.getFirstName());
//        datos.put("lastName",person.getLastName());
//        datos.put("userName",person.getUserName());
//        datos.put("password",person.getPassword());
//        datos.put("photo",person.getPhoto());
//        datos.put("type",person.getTypeUser());
//
////        Map<String,String> header = new HashMap<>();
////        datos.put("Content_Type","application/json");
//
//
//        JSONObject jsonData = new JSONObject(datos);
//
//        AndroidNetworking.post("https://polar-plains-39256.herokuapp.com/SQLPerson_INSERT.php")
//                .addJSONObjectBody(jsonData)
//                .setPriority(Priority.MEDIUM)
//                .setContentType("application/json")
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            String state = response.getString("estado");
//                            Log.d("estado","blablabla"+state);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Log.e("Error","blablabla");
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        Log.e("Error",anError.getErrorDetail());
//                    }
//                });
    }

    public static void removePerson(int index){ //static
        personList.remove(personList.get(index));
    }

    public static void removePerson(Person person){ //static
        personList.remove(person);
    }

    public static Person getPerson(int index){
        return personList.get(index);
    }

    public static ArrayList<Person> getPersonList(){
        return personList;
    }

    public static void setPersonList(ArrayList<Person> persons){
        personList = persons;
    }

    //----------------reservas
    public static  void addBooked(Booked booked){
        bookedList.add(booked);
    }

    public static void removeBooked(int index){
        bookedList.remove(index);
    }

    public static Booked getBooked(int index){
        return bookedList.get(index);
    }

    public static ArrayList<Booked> getBookedList(){
        return bookedList;
    }

    public static void setBookedList(ArrayList<Booked> bookeds){
        bookedList = bookeds;
    }

    public static int getSizeBookedList(){
        return bookedList.size();
    }

    //----------------promociones
    public static void addPromotion(Promotion promotion){
        promotionList.add(promotion);
    }

    public static void removePromotion(int index){ //static
        promotionList.remove(promotionList.get(index));
    }

    public static Promotion getPromotion(int index){
        return promotionList.get(index);
    }

    public static ArrayList<Promotion> getPromotionList(){
        return promotionList;
    }

    public static void setPromotionList(ArrayList<Promotion> promotion){
        promotionList = promotion;
    }

    public static int getSizePromotionList(){
        return promotionList.size();
    }

    // --------------productos
    public static void addProduct(Product product){
        productList.add(product);
    }

    public static void removeProduct(int index){ //static
        productList.remove(productList.get(index));
    }

    public static Product getProduct(int index){
        return productList.get(index);
    }

    public static ArrayList<Product> getProductList(){
        return productList;
    }

    public static void setProductList(ArrayList<Product> product){
        productList = product;
    }

    public static int getSizeProductList(){
        return productList.size();
    }
}