package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;

import java.util.ArrayList;

public class LogIn extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;

    private Button btnIntro;
    private Button btnCancel;
    private ImageView btnAddUser;

    private ArrayList<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        init();
    }

    public void init(){
        personList = Manager.getPersonList();
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        btnIntro = findViewById(R.id.btn_intro);
        btnIntro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getIntroActivity(v);
            }
        });

        btnCancel = findViewById(R.id.btn_cancelRegister);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getCancel(v);
            }
        });

        btnAddUser = findViewById(R.id.btn_addUser);
        btnAddUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getAddUserActivity(v);
            }
        });
    }

    public void getIntroActivity(View view){
        if(etUsername.getText().toString() != null || etPassword.getText().toString() != null){
            for (int i = 0; i < personList.size(); i++){
                if(personList.get(i).getUserName().equals(etUsername.getText().toString())){
                    if(personList.get(i).getPassword().equals(etPassword.getText().toString())){
                        enterIntoActivity();
                        return;
                    }
                    Toast.makeText(getBaseContext(), "Contraseña Incorrecta", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            Toast.makeText(getBaseContext(), "Usuario no encontrado", Toast.LENGTH_LONG).show();
        }
    }

    public void enterIntoActivity(){
        Manager.setPersonList(personList);
        Intent main2Intent = new Intent(getBaseContext(), Home.class);
        startActivity(main2Intent);
    }

    public void getCancel(View view){
        etUsername.setText("");
        etPassword.setText("");
    }

    public void getAddUserActivity(View view){
        Intent addUserActivity = new Intent(getBaseContext(), Register.class);
        startActivity(addUserActivity);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
