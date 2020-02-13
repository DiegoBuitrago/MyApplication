package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.dd.guerrerobuitrago.fotoAppDigital.fragments.SettingsFragment;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EditUser extends AppCompatActivity {

    private TextInputLayout firstNameChange;
    private TextInputLayout lastNameChange;
    private TextInputLayout passwordChange;

    private TextView tvNamePerson;
    private TextView tvTypePerson;
    private TextView tvUserName;

    private ImageView imageUser;
    private Bitmap imageBitmap;
    private Uri imageUri;

    private Person person;

    private Button btnChangeImageEdit;
    private Button btnUpdateUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_edit_user);
        init();
    }

    private void init(){
        firstNameChange = findViewById(R.id.et_firstName_change);
        lastNameChange = findViewById(R.id.et_lastName_change);
        passwordChange = findViewById(R.id.et_password_change);

        tvNamePerson = findViewById(R.id.txt_name_person_edit);
        tvTypePerson = findViewById(R.id.txt_desc_user_edit);
        tvUserName = findViewById(R.id.txt_userName_edit);

        imageUser = findViewById(R.id.image_user_change);

        btnChangeImageEdit = findViewById(R.id.btn_change_image);
        btnUpdateUser = findViewById(R.id.btn_update);

        Intent i = getIntent();
        person = (Person) i.getSerializableExtra("user");
        firstNameChange.getEditText().setText(person.getFirstName());
        lastNameChange.getEditText().setText(person.getLastName());
        tvUserName.setText(person.getUserName());
        passwordChange.getEditText().setText(person.getPassword());
        imageBitmap = null;
        tvTypePerson.setText(person.getTypeUser());
        tvNamePerson.setText(firstNameChange.getEditText().getText().toString() + " " + lastNameChange.getEditText().getText().toString());

        try {
            if(person.getPhoto() != null) {
                imageUri = Uri.parse(person.getPhoto());
                //imageBitmap = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), imageUri);

                Bitmap one = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), imageUri);
                imageBitmap = Bitmap.createScaledBitmap(one, 400, 400, true);
                loadPathImage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnChangeImageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    private void updateUser() {
        if(firstNameIsCorrect() && lastNameIsCorrect() && passwordIsCorrect()){
            person.setFirstName(firstNameChange.getEditText().getText().toString());
            person.setLastName(lastNameChange.getEditText().getText().toString());
            person.setPassword(passwordChange.getEditText().getText().toString());
            person.setPhoto(imageUri);
            Manager.removePerson(person.getId());
            Manager.addPerson(person);
            goToHome(person);
        }
    }

    private void goToHome(Person current) {
        Intent homeIntent = new Intent(this, Home.class);
        homeIntent.putExtra("user", current);
        startActivity(homeIntent);
        //finish();
    }

    private void loadImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            try {
                imageUri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), imageUri);
                imageUser.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadPathImage(){
        if(imageBitmap != null) {
            this.imageUser.setImageBitmap(imageBitmap);
        }
    }

    private boolean firstNameIsCorrect(){
        String firstNameInput = firstNameChange.getEditText().getText().toString().trim();
        if(firstNameInput.isEmpty()){
            firstNameChange.setError("El campo se encuentra vacio");
            return false;
        }else if(firstNameInput.length() > 30){
            firstNameChange.setError("Excede el numero maximo de caracteres");
            return false;
        } else{
            firstNameChange.setError(null);
            firstNameChange.setErrorEnabled(false);
            return true;
        }
    }

    private boolean lastNameIsCorrect(){
        String lastNameInput = lastNameChange.getEditText().getText().toString().trim();
        if(lastNameInput.isEmpty()){
            lastNameChange.setError("El campo se encuentra vacio");
            return false;
        }else if(lastNameInput.length() > 30){
            lastNameChange.setError("Excede el numero maximo de caracteres");
            return false;
        }else{
            lastNameChange.setError(null);
            lastNameChange.setErrorEnabled(false);
            return true;
        }
    }

    private boolean passwordIsCorrect(){
        String passwordInput = passwordChange.getEditText().getText().toString().trim();
        if(passwordInput.isEmpty()){
            passwordChange.setError("El campo se encuentra vacio");
            return false;
        }else if(passwordInput.length() < 8){
            passwordChange.setError("Contraseña debe tener minimo 8 caracteres");
            return false;
        }else{
            passwordChange.setError(null);
            passwordChange.setErrorEnabled(false);
            return true;
        }
    }
}