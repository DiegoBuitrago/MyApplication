package com.dd.guerrerobuitrago.fotoAppDigital.models;

import android.net.Uri;

public class Promotion {
    private int id_product;
    private String name;
    private String photo;
    private Uri photo2;

//    public Promotion(int id_product, String name, String photo) {
//        this.id_product = id_product;
//        this.name = name;
//        this.photo = photo;
//    }

    public Promotion(String name, Uri photo){
        this.id_product = 0;
        this.name = name;
        this.photo2 = photo;
    }

    public int getId_product() {
        return id_product;
    }

    public Uri getPhoto2() {
        return photo2;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Promotions{" +
                "id_product=" + id_product +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}