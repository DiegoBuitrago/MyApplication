package com.dd.guerrerobuitrago.fotoAppDigital.models;

import android.graphics.Bitmap;
import android.net.Uri;


public class Promotion {
    private int id_product;
    private String name;
    private Uri photo;

    public Promotion(int id_product, String name, Uri photo) {
        this.id_product = id_product;
        this.name = name;
        this.photo = photo;
    }

    public int getId_product() {
        return id_product;
    }

    public Uri getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(Uri photo) {
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