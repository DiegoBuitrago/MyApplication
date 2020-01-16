package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.guerrerobuitrago.fotoAppDigital.fragments.SettingsFragment;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;
import com.google.android.material.textfield.TextInputLayout;

public class EditUser extends AppCompatActivity {

    private TextInputLayout firstNameChange;
    private TextInputLayout lastNameChange;
    private TextInputLayout passwordChange;

    private TextView tvNamePerson;
    private TextView tvTypePerson;
    private TextView tvUserName;

    private ImageView imageUser;
    private Uri imageUri;

    private Person person;

    private Button btnChangeImageEdit;
    private Button btnUpdateUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        imageUri = Uri.parse(person.getPhoto());
        tvTypePerson.setText(person.getTypeUser());
        tvNamePerson.setText(firstNameChange.getEditText().getText().toString() + " " + lastNameChange.getEditText().getText().toString());

        loadPathImage(person.getPhoto());

        btnChangeImageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
            }
        });

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    private void changeImage() {
        loadImage();
    }

    private void updateUser() {
        if(firstNameIsCorrect() && lastNameIsCorrect() && passwordIsCorrect()){
            person.setFirstName(firstNameChange.getEditText().getText().toString());
            person.setLastName(lastNameChange.getEditText().getText().toString());
            person.setPassword(passwordChange.getEditText().getText().toString());
            person.setPhoto(imageUri.toString());
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
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicacion"), 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            imageUser.setImageURI(data.getData());
            imageUri = data.getData();
        }
    }

    private void loadPathImage(String pathString){
        if(!(pathString == null || pathString =="")) {
            this.imageUri = Uri.parse(pathString);
            this.imageUser.setImageURI(imageUri);
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