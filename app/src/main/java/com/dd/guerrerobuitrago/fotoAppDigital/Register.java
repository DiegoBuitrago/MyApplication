package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText userName;
    private EditText password;
    private ImageView imagen;
    private Uri path;

    private ArrayList<Person> personList;

//    private boolean flagImage;

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
        this.imagen = findViewById(R.id.image_user_change);
        this.path = null;

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
        loadImage();
    }

    //No acepta dos usuarios
    public void getRegister(View view){
        Log.d("jj", lastName.getText().toString());
        if(firstName.getText().toString().equals("") || lastName.getText().toString() == null || userName.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(getBaseContext(), "Faltan algunos datos", Toast.LENGTH_LONG).show();
        }else{
            if(personList.size() == 0){
                personList.add(myNewPerson());
                sendPersons();
            }else{
                for (int i= 0; i < personList.size(); i++){
                    if(!userName.getText().toString().equals(personList.get(i).getUserName())){
                        personList.add(myNewPerson());
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
        path = null;
        this.imagen = findViewById(R.id.image_user_change);
    }

    private void loadImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicacion"), 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            this.path = data.getData();
//          this.imagen = (ImageView) findViewById(R.id.image_user_change);
            imagen.setImageURI(path);
            Toast.makeText(getBaseContext(), path.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public Person myNewPerson(){
        Person person;
        if(path != null){
        person =  new Person(personList.size()+1, "" + firstName.getText(), "" +
                lastName.getText(), "" + userName.getText(), "" + password.getText(), this.path.toString());
        } else {
        person =  new Person(personList.size()+1, "" + firstName.getText(), "" +
                lastName.getText(), "" + userName.getText(), password.getText().toString(), imagen.toString());
        }
        return person;
    }
}