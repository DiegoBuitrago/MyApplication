package com.dd.guerrerobuitrago.fotoAppDigital.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String photo;
    private String typeUser;
    private ArrayList<Booked> bookedList;

    public Person(int id, String firstName, String lastName, String userName, String password, String photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.photo = photo;
        this.typeUser = "Cliente";
        this.bookedList = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getUserName() {
        return userName;
    }

    public void addBooked(Booked booked){
        bookedList.add(booked);
    }

    public void removeBooked(int index){ //static
        bookedList.remove(bookedList.get(index));
    }

    public Booked getBooked(int index){
        return bookedList.get(index);
    }

    public ArrayList<Booked> getBookedList(){
        return this.bookedList;
    }

    public void setBookedList(ArrayList<Booked> bookedList){
        this.bookedList = bookedList;
    }

    public int getSizeBookedList(){
        return bookedList.size();
    }
}