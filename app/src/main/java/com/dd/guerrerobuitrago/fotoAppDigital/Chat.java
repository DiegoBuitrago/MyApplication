package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.androidnetworking.AndroidNetworking;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.ChatRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.PersonRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;

public class Chat extends AppCompatActivity {

    private RecyclerView rvChat;
    private ChatRVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_chat);
        initComponents();
    }

    private void initComponents() {
        rvChat = findViewById(R.id.rv_chat_message);
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false);
        rvAdapter = new ChatRVAdapter(Manager.getMessageList());
        rvChat.setLayoutManager(layoutManager);
        rvChat.setAdapter(rvAdapter);
        rvAdapter.notifyItemInserted(Manager.getSizeBookedList());
    }
}