package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.androidnetworking.AndroidNetworking;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.PersonRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;

public class PersonList extends AppCompatActivity {

    private RecyclerView rvPerson;
    private PersonRVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_person_list);
        initComponents();
    }

    private void initComponents() {
        rvPerson = findViewById(R.id.rv_person_list);
//        Intent i = getIntent();
//        userName = i.getSerializableExtra("userr").toString();
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false);
        rvAdapter = new PersonRVAdapter(Manager.getPersonList());
        rvAdapter.setListener(new PersonRVAdapter.onItemClickListener() {
            @Override
            public void onUpdateClick(int position, String textNew) {
                updateUser(position, textNew);
                rvAdapter.notifyItemChanged(position);
            }
        });
        rvPerson.setLayoutManager(layoutManager);
        rvPerson.setAdapter(rvAdapter);
        rvAdapter.notifyItemInserted(Manager.getSizeBookedList());
    }

    private void updateUser(int position, String textNew) {
        for (int i=0; i< Manager.getPersonList().size();i++){
            if(i == position){
                Manager.getPersonList().get(i).setTypeUser(textNew);
            }
        }
    }
}