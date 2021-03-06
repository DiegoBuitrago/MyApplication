package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;
import com.dd.guerrerobuitrago.fotoAppDigital.utilities.MyConexion;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private TextInputLayout firstName;
    private TextInputLayout lastName;
    private TextInputLayout userName;
    private TextInputLayout password;
    private ImageView imageUser;
    private Uri path;

    private ArrayList<Person> personList;

    private  int RESULT_LOAD_IMG = 0;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    String imgDecodableString;
    private String userChoosenTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_register);
        if(Manager.getPersonList() != null){
            init();
        }else{
            Toast.makeText(getBaseContext(), "Cancelar", Toast.LENGTH_LONG).show();
        }

    }

    private void init() {
        personList = Manager.getPersonList();
        firstName = findViewById(R.id.text_input_name);
        lastName = findViewById(R.id.text_input_surname);
        userName = findViewById(R.id.text_input_userName);
        password = findViewById(R.id.text_input_password);
        imageUser = findViewById(R.id.image_user_change);
        this.path = null;

        Button btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //userNameIsCorrect();
                getRegister(v);
            }
        });
        Button btnCancel = findViewById(R.id.btn_cancelRegister);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getCancel(v);
            }
        });
        imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
    }

    private void chooseImage() {
        loadImage();
        //galleryIntent();
    }

    public void getRegister(View view){

            if(userNameIsCorrect() && firstNameIsCorrect() && lastNameIsCorrect() && passwordIsCorrect()){
                personList.add(myNewPerson());
                sendPersons();
            }
    }

    private boolean userNameIsCorrect(){
        String userNameInput = userName.getEditText().getText().toString().trim();
        if(userNameInput.isEmpty()){
            userName.setError("El campo se encuentra vacio");
            return false;
        }else if(validateList(userNameInput)){
            userName.setError("Nombre de usuario existente");
            return false;
        }else{
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean firstNameIsCorrect(){
        String firstNameInput = firstName.getEditText().getText().toString().trim();
        if(firstNameInput.isEmpty()){
            firstName.setError("El campo se encuentra vacio");
            return false;
        }else if(firstNameInput.length() > 30){
            firstName.setError("Excede el numero maximo de caracteres");
            return false;
        } else{
            firstName.setError(null);
            firstName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean lastNameIsCorrect(){
        String lastNameInput = lastName.getEditText().getText().toString().trim();
        if(lastNameInput.isEmpty()){
            lastName.setError("El campo se encuentra vacio");
            return false;
        }else if(lastNameInput.length() > 30){
            lastName.setError("Excede el numero maximo de caracteres");
            return false;
        }else{
            lastName.setError(null);
            lastName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean passwordIsCorrect(){
        String passwordInput = password.getEditText().getText().toString().trim();
        if(passwordInput.isEmpty()){
            password.setError("El campo se encuentra vacio");
            return false;
        }else if(passwordInput.length() < 8){
            password.setError("Contraseña debe tener minimo 8 caracteres");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateList(String userNameInput){
        for (int i= 0; i < personList.size(); i++){
            if(personList.get(i).getUserName().equals(userNameInput)){
                return true;
            }
        }
        return false;
    }


    public void sendPersons(){
        Manager.setPersonList(personList);
        //Toast.makeText(getBaseContext(), "Usuario creado con exito", Toast.LENGTH_LONG).show();
        Intent loginIntent = new Intent(getBaseContext(), LogIn.class);
        startActivity(loginIntent);
    }

    public void getCancel(View view){
//        firstName.setText("");
//        lastName.setText("");
//        userName.setText("");
//        password.setText("");
        Intent loginIntent = new Intent(getBaseContext(), LogIn.class);
        startActivity(loginIntent);
        //path = null;
        //this.imageUser = findViewById(R.id.image_user_change);
    }

    private void loadImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    private void galleryIntent(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            try{
                path = data.getData();

                Bitmap one = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), data.getData());
                Bitmap bitmap = Bitmap.createScaledBitmap(one, 400, 400, true);

                //Bitmap bitmap = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), data.getData());
                imageUser.setImageBitmap(bitmap);
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public Person myNewPerson(){
        Person person;
        if(path != null){
        person =  new Person(personList.size(), firstName.getEditText().getText().toString().trim(), "" +
                lastName.getEditText().getText().toString().trim(), userName.getEditText().getText().toString().trim(), password.getEditText().getText().toString(), path, "Cliente");
        } else {
        person =  new Person(personList.size(), firstName.getEditText().getText().toString().trim(), "" +
                lastName.getEditText().getText().toString().trim(), userName.getEditText().getText().toString().trim(), password.getEditText().getText().toString(), "Cliente");
        }
        MyConexion.loadDataBasePerson(person);
        return person;
    }
}