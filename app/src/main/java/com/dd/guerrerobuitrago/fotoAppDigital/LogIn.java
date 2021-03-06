package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LogIn extends AppCompatActivity {

    private TextInputLayout etUsername;
    private TextInputLayout etPassword;

    private Button btnIntro;
    private TextView txtAddUser;
    private EditUser editUser;

    private ArrayList<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_log_in);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        init();
    }

    public void init(){
        personList = Manager.getPersonList();
        etUsername = findViewById(R.id.text_input_userName_login);
        etPassword = findViewById(R.id.text_input_password_login);

        btnIntro = findViewById(R.id.btn_intro);
        btnIntro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getIntroActivity(v);
            }
        });

        txtAddUser = findViewById(R.id.txt_add_user);
        txtAddUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getAddUserActivity(v);
            }
        });


    }

    public void getIntroActivity(View view){
        if(passwordIsCorrect() && userNameIsCorrect()){
            int index = validateList(etUsername.getEditText().getText().toString().trim());
            if(index != -1){
                //Toast.makeText(getBaseContext(), "photoUserLogIn" + personList.get(index).getPhoto(), Toast.LENGTH_LONG).show();
                enterIntoActivity(personList.get(index));
                return;
            }
        }
    }


    private boolean userNameIsCorrect(){
        String userNameInput = etUsername.getEditText().getText().toString().trim();
        if(userNameInput.isEmpty()){
            etUsername.setError("El campo se encuentra vacio.");
            return false;
        }else{
            etUsername.setError(null);
            etUsername.setErrorEnabled(false);
            return true;
        }
    }

    private int validateList(String userNameIn){
        for (int i = 0; i < personList.size(); i++){
            if(personList.get(i).getUserName().equals(userNameIn)){
                if(personList.get(i).getPassword().equals(etPassword.getEditText().getText().toString().trim())){
                    return i;
                }else{
                    etPassword.setError("Usuario y contraseña no coinciden");
                    return -1;
                }
            }
        }
        etUsername.setError("Usuario no encontrado.");
        return -1;
    }

    private boolean passwordIsCorrect(){
        String passwordInput = etPassword.getEditText().getText().toString().trim();
        if(passwordInput.isEmpty()){
            etPassword.setError("El campo se encuentra vacio");
            return false;
        }else{
            etPassword.setError(null);
            etPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void enterIntoActivity(Person person){
        Manager.setPersonList(personList);
        //TODO Error aqui
        Intent homeIntent = new Intent(this, Home.class);
        homeIntent.putExtra("user", person);
        startActivity(homeIntent);
        finish();
    }

    public void getAddUserActivity(View view){
        Intent addUserActivity = new Intent(getBaseContext(), Register.class);
        startActivity(addUserActivity);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void facebookIntent(View view) {
        String url = "https://facebook.com/FOTO-Digital-1147679955261694";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void instagramIntent(View view) {
        String url = "https://instagram.com/fotodigital_tunja?igshid=1odjoksrhkzh1";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void whatsappIntent(View view){
        String url = "whatsapp://send?phone=\" + \"57 + 3007825168\"";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void pdfIntent(View view){
        String url = "https://docs.google.com/document/d/1EJPVdDDSbH9TxOkpCUyYrOR1D2DauydlyEtm3VNXwiU/edit?usp=sharing";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}