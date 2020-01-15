package com.dd.guerrerobuitrago.fotoAppDigital.models;

import java.util.ArrayList;

public class Manager {

    private static ArrayList<Person> personList;
    private static ArrayList<Booked> bookedList;

    public Manager(){
        personList = new ArrayList<>();
        personList.add(new Person(1, "Diego", "Guerrero", "d", "12345678", null));

        bookedList = new ArrayList<>();
    }

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
}
