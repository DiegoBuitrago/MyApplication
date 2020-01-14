package com.dd.guerrerobuitrago.fotoAppDigital.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.guerrerobuitrago.fotoAppDigital.EditUser;
import com.dd.guerrerobuitrago.fotoAppDigital.LogIn;
import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private View cardEdit;
    private View cardServices;
    private View cardPurchases;
    private View cardLogout;
    private View cardRemoveUser;
    private TextView txtNameUser;
    private TextView txtDescUser;
    private TextView txtUserName;
    private ImageView imageUser;

    private String firstName;
    private String lastName;
    private String password;
    private String typeUser;
    private String userName;
    private String photo;

    private Uri pathUri;


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

        Bundle bun = getArguments();
        firstName = bun.getString("firstName");
        lastName = bun.getString("lastName");
        password = bun.getString("password");
        typeUser = bun.getString("typeUser");
        userName = bun.getString("userName");
        photo = bun.getString("photo");

        pathUri = Uri.parse(photo);
        loadPathImage();

        txtNameUser.setText(firstName + " " + lastName);
        txtUserName.setText(userName);
        txtDescUser.setText(typeUser);
        cardEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editUser();
            }
        });
        cardPurchases = view.findViewById(R.id.cv_purchase_set);
        cardServices = view.findViewById(R.id.cv_services_set);
        cardLogout = view.findViewById(R.id.cv_logout_set);

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(v, "Salir de la cuenta", "¿Desea salir de la cuenta?");
            }
        });

        cardRemoveUser = view.findViewById(R.id.cv_remove_user_set);
        cardRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(v, "Eliminar Usuario", "¿Desea eliminar el usuario de manera permanente?");
            }
        });
    }

    private void removeUser() {
        //Mejorar enviando el id
        for (int i= 0; i < Manager.getPersonList().size(); i++){
            if(Manager.getPersonList().get(i).getUserName().equals(userName)){
                Manager.removePerson(i);
            }
        }
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
                    Toast.makeText(getContext(), "Usuario borrado con exito.", Toast.LENGTH_LONG).show();
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
        editUserIntent.putExtra("firstName", firstName);
        editUserIntent.putExtra("lastName", lastName);
        editUserIntent.putExtra("password", password);
        editUserIntent.putExtra("typeUser", typeUser);
        editUserIntent.putExtra("userName", userName);
        editUserIntent.putExtra("photo", photo);
        startActivity(editUserIntent);
    }

    public void closeSession(){
        Intent loginIntent = new Intent(getActivity(), LogIn.class);
        startActivity(loginIntent);
    }

    public static SettingsFragment newInstance(String firstName, String lastName, String password, String typeUser, String userName, String photo){
        Bundle args = new Bundle();
        args.putString("firstName", firstName);
        args.putString("lastName", lastName);
        args.putString("password", password);
        args.putString("typeUser", typeUser);
        args.putString("userName", userName);
        args.putString("photo", photo);
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void loadPathImage(){
        if(pathUri != null) {
            imageUser.setImageURI(pathUri);
        }
    }
}