package com.dd.guerrerobuitrago.fotoAppDigital.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.guerrerobuitrago.fotoAppDigital.EditUser;
import com.dd.guerrerobuitrago.fotoAppDigital.LogIn;
import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.Services;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private View cardEdit;
    private View cardServices;
    private View cardLogout;
    private View cardRemoveUser;
    private TextView txtNameUser;
    private TextView txtDescUser;
    private TextView txtUserName;
    private ImageView imageUser;

    private Person person;
    private Bitmap pathBitmap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        cardEdit = view.findViewById(R.id.cv_edit_user_set);
        txtNameUser = view.findViewById(R.id.txt_name_person_set);
        txtDescUser = view.findViewById(R.id.txt_desc_user_set);
        txtUserName = view.findViewById(R.id.txt_userName_set);
        imageUser = view.findViewById(R.id.image_user_change);
        //
        pathBitmap = null;
        try {
            if(person.getPhoto() != null){
                pathBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), person.getPhoto());
                loadPathImage();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        txtNameUser.setText(person.getFirstName() + " " + person.getLastName());
        txtUserName.setText(person.getUserName());
        txtDescUser.setText(person.getTypeUser());
        cardEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser();
            }
        });
        //cardPurchases = view.findViewById(R.id.cv_purchase_set);
        cardServices = view.findViewById(R.id.cv_services_set);
        cardLogout = view.findViewById(R.id.cv_logout_set);
        cardRemoveUser = view.findViewById(R.id.cv_remove_user_set);

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(v, "Salir de la cuenta", "¿Desea salir de la cuenta?");
            }
        });

        cardRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(v, "Eliminar Usuario", "¿Desea eliminar el usuario de manera permanente?");
            }
        });

        cardServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicesAct();
            }
        });
    }

    private void removeUser() {
        Manager.removePerson(person);
        Toast.makeText(getContext(), "Usuario borrado con exito.", Toast.LENGTH_LONG).show();
    }

    private void showAlert(View view, final String titleMesage, String descMessage) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        alert.setMessage(descMessage);
        alert.setCancelable(false);
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(titleMesage.equals("Eliminar Usuario")){
                    removeUser();
                }
                closeSession();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog title = alert.create();
        title.setTitle(titleMesage);
        title.show();
    }

    public void editUser(){
        Intent editUserIntent = new Intent(getActivity(), EditUser.class);
        editUserIntent.putExtra("user", person);
        editUserIntent.putExtra("photo", person.getPhoto());
        startActivity(editUserIntent);
    }

    public void servicesAct(){
        Intent servicesIntent = new Intent(getActivity(), Services.class);
        servicesIntent.putExtra("userr", person.getUserName());
//        servicesIntent.putExtra("user", person);
        startActivity(servicesIntent);
    }

    public void closeSession(){
        Intent loginIntent = new Intent(getActivity(), LogIn.class);
        startActivity(loginIntent);
    }

    private void loadPathImage(){
        if(pathBitmap != null) {
            imageUser.setImageBitmap(pathBitmap);
        }
    }

    public static SettingsFragment newInstance(Person person){
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.person = person;
        fragment.setArguments(args);
        return fragment;
    }
}