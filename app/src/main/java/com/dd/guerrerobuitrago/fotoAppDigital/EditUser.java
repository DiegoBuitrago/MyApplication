package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.guerrerobuitrago.fotoAppDigital.fragments.SettingsFragment;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;

public class EditUser extends AppCompatActivity {

    private EditText firstNameChange;
    private EditText lastNameChange;
    private EditText passwordChange;

    private TextView tvNamePerson;
    private TextView tvTypePerson;
    private TextView tvUserName;

    private ImageView imageUser;

    private Button btnChangeImageEdit;
    private Button btnUpdateUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        init();
    }

    private void init(){
        for (int i = 0; i < Manager.getPersonList().size(); i++){
            //Toast.makeText(getBaseContext(), Manager.getPersonList().get(i).getUserName(), Toast.LENGTH_LONG).show();
        }
        firstNameChange = findViewById(R.id.et_firstName_change);
        lastNameChange = findViewById(R.id.et_lastName_change);
        passwordChange = findViewById(R.id.et_password_change);

        tvNamePerson = findViewById(R.id.txt_name_person_edit);
        tvTypePerson = findViewById(R.id.txt_desc_user_edit);
        tvUserName = findViewById(R.id.txt_userName_edit);

        imageUser = findViewById(R.id.image_user_change);

        btnChangeImageEdit = findViewById(R.id.btn_change_image);
        btnUpdateUser = findViewById(R.id.btn_update);

        Bundle bundleFN = getIntent().getExtras();
        if(bundleFN != null){
            firstNameChange.setText(bundleFN.getString("firstName"));
            lastNameChange.setText(bundleFN.getString("lastName"));
            passwordChange.setText(bundleFN.getString("password"));
            tvTypePerson.setText(bundleFN.getString("typeUser"));
            tvNamePerson.setText(bundleFN.getString("firstName") + " " + bundleFN.getString("lastName"));
            tvUserName.setText(bundleFN.getString("userName"));
        }

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

    }

    private void updateUser() {
        //Arreglar la lista de usuarios
        for (int i = 0; i < Manager.getPersonList().size(); i++){
            Toast.makeText(getBaseContext(), Manager.getPersonList().get(i).getUserName(), Toast.LENGTH_LONG).show();
            if(tvUserName.getText().toString().equals(Manager.getPersonList().get(i).getUserName())){
                Manager.getPersonList().get(i).setFirstName(firstNameChange.getText().toString());
                Manager.getPersonList().get(i).setLastName(lastNameChange.getText().toString());
                Manager.getPersonList().get(i).setPassword(passwordChange.getText().toString());
                goToHome(Manager.getPersonList().get(i));
                return;
            }
        }
    }

    private void goToHome(Person current) {
        Toast.makeText(getBaseContext(), "Usuario actualizado con exito", Toast.LENGTH_LONG).show();
        Intent homeIntent = new Intent(this, Home.class);
        homeIntent.putExtra("user", current);
        startActivity(homeIntent);
        finish();
    }

}