package com.dd.guerrerobuitrago.fotoAppDigital;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.AllServicesRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.ServicesRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Booked;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;

import java.util.ArrayList;

public class AllServices extends AppCompatActivity {

    private RecyclerView rvServices;
    private AllServicesRVAdapter rvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_all_services);
        initComponents();
    }

    private void initComponents() {
        rvServices = findViewById(R.id.rv_all_services);
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false);
        rvAdapter = new AllServicesRVAdapter(Manager.getBookedList());
        rvServices.setLayoutManager(layoutManager);
        rvServices.setAdapter(rvAdapter);
        rvAdapter.notifyItemInserted(Manager.getSizeBookedList());
    }
}