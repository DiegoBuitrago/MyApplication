package com.dd.guerrerobuitrago.fotoAppDigital.fragments;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Booked;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {

    private TextView txtDate;

//    private int day;
//    private int month;

    private Spinner spHour;
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
    }

    private void onSearch() {

    }

    private void onBooked(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog_booked_service, null);

        Button btnChooseDate = mView.findViewById(R.id.btn_choose_date);
        txtDate = mView.findViewById(R.id.txt_date_service);
        spHour = mView.findViewById(R.id.sp_hour_serivce);
        //Spinner spTypeBooked = mView.findViewById(R.id.sp_type_service);
        Button btnCancel = mView.findViewById(R.id.btn_cancel_service);
        Button btnAccept = mView.findViewById(R.id.btn_accept_service);

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(true);

        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate(v);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptService();
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
                if(year == yearC){
                    if(month <= monthC){
                        if(day < dayOfMonth){
                            txtDate.setText(dayOfMonth + "/" + (monthC+1) + "/" + yearC);
                            verifyHours(view, dayOfMonth, monthC+1, yearC);
                        }else{
                            Toast.makeText(getContext(), "Fecha no valida", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "Fecha no valida", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Fecha no valida", Toast.LENGTH_LONG).show();
                }

            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void acceptService() {
        Log.d("Persona", this.person.getFirstName());
    }

    private void verifyHours(View view, int dayC, int monthC, int yearC) {
        fillListHours();
        for(int i = 0; i < Manager.getBookedList().size(); i++){
            Booked currentBooked = Manager.getBookedList().get(i);
            if(yearC ==  currentBooked.getYear()){
                if (monthC == currentBooked.getMonth()){
                    if (dayC == currentBooked.getDay()){
                        removeHour(currentBooked.getHour());
                    }
                }
            }
        }
        spHour.setAdapter(new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, hourList));
    }

    public void removeHour(String hour){
        for (int i=0; i< hourList.size(); i++){
            if(hour.equals(hourList.get(i))){
                hourList.remove(i);
                return;
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