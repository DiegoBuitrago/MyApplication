package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText userName;
    private EditText password;

    private ArrayList<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if(Manager.getPersonList() != null){
            init();
        }else{
            Toast.makeText(getBaseContext(), "Cancelar", Toast.LENGTH_LONG).show();
        }

    }

    private void init() {
        personList = Manager.getPersonList();
        firstName = findViewById(R.id.et_firstName_register);
        lastName = findViewById(R.id.et_lastName_register);
        userName = findViewById(R.id.et_username_register);
        password = findViewById(R.id.et_password_register);

        Button btnRegister = findViewById(R.id.btn_register);
        Button btnChooseImage = findViewById(R.id.btn_choose_photo_user_register);

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getRegister(v);
            }
        });
        Button btnCancel = findViewById(R.id.btn_cancelRegister);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Cancelar", Toast.LENGTH_LONG).show();
                getCancel(v);
            }
        });

        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
    }

    private void chooseImage() {

    }

    //No acepta dos usuarios
    public void getRegister(View view){
        Log.d("jj", lastName.getText().toString());
        if(firstName.getText().equals("") || lastName.getText() == null || userName.getText().equals("") || password.getText().equals("")){
            Toast.makeText(getBaseContext(), "Faltan algunos datos", Toast.LENGTH_LONG).show();
        }else{
            if(personList.size() == 0){
                personList.add(new Person(personList.size()+1, "" + firstName.getText(), "" +
                        lastName.getText(), "" + userName.getText(), "" + password.getText(), "photo"));
                sendPersons();
            }else{
                for (int i= 0; i < personList.size(); i++){
                    if(!userName.getText().equals(personList.get(i).getUserName())){
                        personList.add(new Person(personList.size()+1, "" + firstName.getText(), "" +
                                lastName.getText(), "" + userName.getText(), "" + password.getText(), "photo"));
                        sendPersons();
                    }else{
                        Toast.makeText(getBaseContext(), "Nombre de usuario ya existente", Toast.LENGTH_LONG).show();
                    }
                }
            }

        }
    }

    public void sendPersons(){
        Manager.setPersonList(personList);
        Toast.makeText(getBaseContext(), "Usuario creado con exito", Toast.LENGTH_LONG).show();
        Intent loginIntent = new Intent(getBaseContext(), LogIn.class);
        startActivity(loginIntent);
    }

    public void getCancel(View view){
        firstName.setText("");
        lastName.setText("");
        userName.setText("");
        password.setText("");
    }
}
