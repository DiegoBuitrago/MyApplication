package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.ChatRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.PersonRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Message;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Promotion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Chat extends AppCompatActivity {

    private RecyclerView rvChat;
    private ChatRVAdapter rvAdapter;

    private String name_user_message;

    private Button buttonSend;
    private EditText textMessage;

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
//        buttonSend = findViewById(R.id.btn_send);
//        textMessage = findViewById(R.id.rv_chat_message);
        Intent i = this.getIntent();
        this.name_user_message = i.getStringExtra("nameUser");
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
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

    private void sendMessage(){
        String text = textMessage.getText().toString();
        if (!text.isEmpty()){
            Message message = new Message("",text);
            loadDataBaseMessage(message);
        }
    }

    public void loadDataBaseMessage(Message message){
        Map<String,String> datos = new HashMap<>();
        datos.put("name",message.getNameUser());
        datos.put("text",message.getMessage());
        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post("https://polar-plains-39256.herokuapp.com/SQLChat_INSERT.php")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String state = response.getString("estado");
                            Log.d("Estado","blablabla"+state);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error","blablablapromotion");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error",anError.getErrorDetail());
                    }
                });
    }
}