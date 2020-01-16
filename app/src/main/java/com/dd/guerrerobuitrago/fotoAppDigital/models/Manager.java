package com.dd.guerrerobuitrago.fotoAppDigital.models;

import java.util.ArrayList;

public class Manager {
    private static ArrayList<Person> personList;
    private static ArrayList<Booked> bookedList;

    private static ArrayList<Promotion> promotionList;
    private static ArrayList<Product> productList;

    public Manager(){
        personList = new ArrayList<>();
        bookedList = new ArrayList<>();
        promotionList = new ArrayList<>();
        productList = new ArrayList<>();
    }

    //--------------personas
    public static void addPerson(Person person){
        personList.add(person);
    }

    public static void removePerson(int index){ //static
        personList.remove(personList.get(index));
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