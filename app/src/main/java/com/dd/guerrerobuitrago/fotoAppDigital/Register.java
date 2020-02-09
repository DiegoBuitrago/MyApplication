package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;
import com.dd.guerrerobuitrago.fotoAppDigital.utilities.Utilities;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
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
    private String path;

    private ArrayList<Person> personList;

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
        Toast.makeText(getBaseContext(), "Usuario creado con exito", Toast.LENGTH_LONG).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if(resultCode == Activity.RESULT_OK){
            //this.path = data.getData();
//          this.imageUser = (ImageView) findViewById(R.id.image_user_change);
            //imageUser.setImageURI(path);

            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                Bitmap newProfilePic = extras.getParcelable("data");
                this.path = bitMapToString(newProfilePic);
                imageUser.setImageBitmap(newProfilePic);
            }
        }*/
        if (requestCode == 1){
            //Uri uri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(), data.getData());
                imageUser.setImageBitmap(bitmap);
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String bitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public Bitmap stringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Person myNewPerson(){
        Person person;
        if(path != null){
        person =  new Person(personList.size(), firstName.getEditText().getText().toString().trim(), "" +
                lastName.getEditText().getText().toString().trim(), userName.getEditText().getText().toString().trim(), "" + password.getEditText().getText().toString(), new Utilities().stringToBase64(this.path), "Cliente");
        } else {
        person =  new Person(personList.size(), firstName.getEditText().getText().toString().trim(), "" +
                lastName.getEditText().getText().toString().trim(), userName.getEditText().getText().toString().trim(), password.getEditText().getText().toString(), "Cliente");
        }
        loadDataBasePerson(person);
        return person;
    }

    public void loadDataBasePerson(Person person){
        Map<String,String> datos = new HashMap<>();
//        datos.put("id",""+person.getId());
        datos.put("firstName",person.getFirstName());
        datos.put("lastName",person.getLastName());
        datos.put("userName",person.getUserName());
        datos.put("password",person.getPassword());
        datos.put("photo",person.getPhoto());
        datos.put("type",person.getTypeUser());

        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post("https://polar-plains-39256.herokuapp.com/SQLPerson_INSERT.php")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .setContentType("application/json")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String   state = response.getString("estado");
                            Log.d("Estado","blablabla"+state);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error","blablabla");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error",anError.getErrorDetail());
                    }
                });
    }
}