package com.dd.guerrerobuitrago.fotoAppDigital.fragments;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.guerrerobuitrago.fotoAppDigital.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {

    private TextView txtDate;

    private int day;
    private int month;
    private int year;

    private Calendar currentDate;

    private int currentDay;
    private int currentMonth;
    private int currentYear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        Spinner spHour = mView.findViewById(R.id.sp_hour_serivce);
        Spinner spTypeBooked = mView.findViewById(R.id.sp_type_service);
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

    private void getCurrentDate(){
        currentDate = Calendar.getInstance();
//        currentDate.set(Calendar.HOUR_OF_DAY, 0);
//        currentDate.set(Calendar.MINUTE, 0);
//        currentDate.set(Calendar.SECOND, 0);
    }

    public static ServicesFragment newInstance(){

        Bundle args = new Bundle();

        ServicesFragment fragment = new ServicesFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
