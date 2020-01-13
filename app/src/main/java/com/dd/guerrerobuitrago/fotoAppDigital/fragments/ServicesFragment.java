package com.dd.guerrerobuitrago.fotoAppDigital.fragments;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {

    private TextView txtDate;

    private int day;
    private int month;
    private int year;

    private Spinner spHour;
    private Calendar currentDate;

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

        hourList = new ArrayList<>();
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
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int yearC, int monthC, int dayOfMonth) {
                if(year == yearC){
                    if(month <= monthC){
                        if(day < dayOfMonth){
                            txtDate.setText(dayOfMonth + "/" + (monthC+1) + "/" + yearC);
                            verifyHours(view);
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

    }

    private void verifyHours(View view) {
        fillListHours();
        for(int i = 0; i < Manager.getBookedList().size(); i++){
            Booked currentBooked = Manager.getBookedList().get(i);
            if(year ==  currentBooked.getYear()){
                if (month == currentBooked.getMonth()){
                    if (day == currentBooked.getDay()){
                        removeHour(currentBooked.getHour());
                    }
                }
            }
        }
        spHour.setAdapter(new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, hourList));
    }

    private void fillListHours(){
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

    public void removeHour(String hour){
        for (int i=0; i< hourList.size(); i++){
            if(hour == hourList.get(i).toString()){
                hourList.remove(hourList.get(i));
                return;
            }
        }
    }

    public static ServicesFragment newInstance(){

        Bundle args = new Bundle();

        ServicesFragment fragment = new ServicesFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
