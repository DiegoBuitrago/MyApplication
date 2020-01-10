package com.dd.guerrerobuitrago.fotoAppDigital.models;

public class Promotion {
    private int id_product;
    private String name;
    private String photo;

    public Promotion(int id_product, String name, String photo) {
        this.id_product = id_product;
        this.name = name;
        this.photo = photo;
    }

    public Promotion(String name, String photo){
        this.id_product = 0;
        this.name = name;
        this.photo = photo;
    }

    public int getId_product() {
        return id_product;
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