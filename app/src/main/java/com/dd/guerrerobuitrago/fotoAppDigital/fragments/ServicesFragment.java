package com.dd.guerrerobuitrago.fotoAppDigital.fragments;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.guerrerobuitrago.fotoAppDigital.AllServices;
import com.dd.guerrerobuitrago.fotoAppDigital.Chat;
import com.dd.guerrerobuitrago.fotoAppDigital.PersonList;
import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.Statistics;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Booked;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;
import com.dd.guerrerobuitrago.fotoAppDigital.models.TypeBooked;
import com.dd.guerrerobuitrago.fotoAppDigital.utilities.MyConexion;

import java.io.Console;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {

    private TextView txtDate;
    private TextView txtDate1;

    private int myDay;
    private int myMonth;
    private int myYear;

    private String myHour;
    private TypeBooked myType;

    private Spinner spHour;
    private Spinner spTypeBooked;

    private Calendar currentDate;
    private Person person;

    private ArrayList<String> hourList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View servicesView = inflater.inflate(R.layout.fragment_services, container, false);
        init(servicesView);
        return servicesView;
    }

    private void init(View view) {
        View cvSearch = view.findViewById(R.id.cv_search_services);
        View cvBooked = view.findViewById(R.id.cv_booked_services);
        View cvChat = view.findViewById(R.id.cv_chat);
        View cvStadistics = view.findViewById(R.id.cv_stadistics);
        View cvAllServeces = view.findViewById(R.id.cv_all_services);

        //cvSearch.setVisibility(View.GONE);
        if(person.getTypeUser().equals("Cliente")){
            cvSearch.setVisibility(View.GONE);
            cvChat.setVisibility(View.GONE);
            cvAllServeces.setVisibility(View.GONE);
            cvStadistics.setVisibility(View.GONE);
        }
        cvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearch();
            }
        });

        cvBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBooked(v);
            }
        });

        cvChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChat();
            }
        });

        cvStadistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStadistics();
            }
        });

        cvAllServeces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAllServeces();
            }
        });

    }

    private void onStadistics() {
        Intent stadisticsIntent = new Intent(getActivity(), Statistics.class);
        startActivity(stadisticsIntent);
    }

    private void onChat() {
        Intent chatIntent = new Intent(getActivity(), Chat.class);
        chatIntent.putExtra("nameUser", person.getFirstName() + " " + person.getLastName());
        startActivity(chatIntent);
    }

    private void onSearch() {
        Intent editUserIntent = new Intent(getActivity(), PersonList.class);
        startActivity(editUserIntent);
    }

    private void onAllServeces() {
        Intent editUserIntent = new Intent(getActivity(), AllServices.class);
        startActivity(editUserIntent);
    }

    private void onBooked(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog_booked_service, null);

        txtDate = mView.findViewById(R.id.txt_date_service);
        txtDate1 = mView.findViewById(R.id.txt_title_date_service);
        spHour = mView.findViewById(R.id.sp_hour_serivce);

        spTypeBooked = mView.findViewById(R.id.sp_type_service);

        Button btnCancel = mView.findViewById(R.id.btn_cancel_service);
        Button btnAccept = mView.findViewById(R.id.btn_accept_service);

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(true);

        txtDate1 = mView.findViewById(R.id.txt_title_date_service);
        txtDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate(v);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
//                Log.e("Cancel", "Numero de objetos:  " + Manager.getSizeBookedList());
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(acceptService()){
                    alertDialog.dismiss();
                }
            }
        });

        alertDialog.show();
    }

    private void chooseDate(View view) {
        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int yearC, int monthC, int dayOfMonth) {
                if(day < dayOfMonth){
                    txtDate.setText(dayOfMonth + "/" + (monthC+1) + "/" + yearC);
                    verifyHours(view, dayOfMonth, monthC+1, yearC);
                }else{
                    Toast.makeText(getContext(), "Fecha no valida", Toast.LENGTH_LONG).show();
                }
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    private boolean acceptService() {
        if(verifyData(spTypeBooked.getSelectedItem().toString())) {
            myHour = spHour.getSelectedItem().toString();
            Booked booked = new Booked(Manager.getBookedList().size(), myYear, myMonth, myDay, myHour, myType, person);
            Manager.addBooked(booked);
            MyConexion.loadDataBaseBooked(booked);
            Toast.makeText(getContext(), "Servicio creado", Toast.LENGTH_LONG).show();
            return true;
        }else{
            Toast.makeText(getContext(), "Elija un tipo de Servicio", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean verifyData(String cc) {
        if(cc.equals("Estudio a Diseño")){
            myType = TypeBooked.PHOTO_DESIGN;
            return true;
        }else if(cc.equals("Princesitas")){
            myType = TypeBooked.STARS;
            return true;
        }else if(cc.equals("Mameluco")){
            myType = TypeBooked.MAMELUCO;
            return true;
        }
        else if(cc.equals("Estrellitas")){
            myType = TypeBooked.STARS;
            return true;
        }
        else if(cc.equals("Sin Diseño")){
            myType = TypeBooked.NO_DESIGN;
            return true;
        }else{
            return false;
        }
    }

    private void verifyHours(View view, int dayC, int monthC, int yearC) {
        fillListHours();
        this.myYear = yearC;
        this.myMonth = monthC;
        this.myDay = dayC;
        for(int i = 0; i < Manager.getBookedList().size(); i++){
            Booked currentBooked = Manager.getBookedList().get(i);
            if(myYear ==  currentBooked.getYear()){
                if (myMonth == currentBooked.getMonth()){
                    if (myDay == currentBooked.getDay()){
                        removeHour(currentBooked.getHour());
                    }
                }
            }
        }
        spHour.setAdapter(new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, hourList));
    }

    public void removeHour(String hour){
        for (int i=0; i< hourList.size(); i++){
            if(hourList.get(i).equals(hour)){
                hourList.remove(hourList.get(i));

            }
        }
    }

    private void fillListHours(){
        hourList = new ArrayList<>();
        hourList.add("09:00am");
        hourList.add("09:30am");
        hourList.add("10:00am");
        hourList.add("10:30am");
        hourList.add("11:00am");
        hourList.add("11:30am");
        hourList.add("12:00m");
        hourList.add("12:30pm");
        hourList.add("01:00pm");
        hourList.add("01:30pm");
        hourList.add("02:00pm");
        hourList.add("02:30pm");
        hourList.add("03:00pm");
        hourList.add("03:30pm");
        hourList.add("04:00pm");
        hourList.add("04:30pm");
        hourList.add("05:00pm");
        hourList.add("05:30pm");
        hourList.add("06:00pm");
        hourList.add("06:30pm");
    }

    public static ServicesFragment newInstance(Person person){
        Bundle args = new Bundle();
        ServicesFragment fragment = new ServicesFragment();
        fragment.person = person;
        fragment.setArguments(args);
        return fragment;
    }
}