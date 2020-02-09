package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.androidnetworking.AndroidNetworking;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.ServicesRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Booked;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;

import java.util.ArrayList;

public class Services extends AppCompatActivity {

    private RecyclerView rvServices;
    private ServicesRVAdapter rvAdapter;

    private String userName;
//    private ArrayList<Booked> bookeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_services);
        initComponents();
    }

    private void initComponents() {
        rvServices = findViewById(R.id.rv_services);
        Intent i = getIntent();
        userName = i.getSerializableExtra("userr").toString();
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false);
        rvAdapter = new ServicesRVAdapter(listSelectUser());
        //rvAdapter = new ServicesRVAdapter(Manager.getBookedList());
        rvAdapter.setListener(new ServicesRVAdapter.onItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                Manager.removeBooked(position);
                rvAdapter.notifyItemRemoved(position);
            }
        });
        rvServices.setLayoutManager(layoutManager);
        rvServices.setAdapter(rvAdapter);
        rvAdapter.notifyItemInserted(Manager.getSizeBookedList());
    }

    private ArrayList<Booked> listSelectUser(){
        ArrayList<Booked> iii = new ArrayList<>();
        for (int i = 0; i < Manager.getSizeBookedList(); i++) {
            if (Manager.getBooked(i).getOwner().getUserName().equals(userName)){
                iii.add(Manager.getBooked(i));
            }
        }
        return iii;
    }
}