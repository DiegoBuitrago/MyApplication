package com.dd.guerrerobuitrago.fotoAppDigital.fragments;

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
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private View cardEdit;
    private View cardServices;
    private View cardPurchases;
    private View cardLogout;
    private TextView txtNameUser;
    private TextView txtDescUser;
    private TextView txtUserName;
    private ImageView imageUser;
    private Uri photoUri;

    private String firstName;
    private String lastName;
    private String password;
    private String typeUser;
    private String userName;
    private String photo;


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
        imageUser = (ImageView) view.findViewById(R.id.image_user_change);
        Bundle bun = getArguments();
        firstName = bun.getString("firstName");
        lastName = bun.getString("lastName");
        password = bun.getString("password");
        typeUser = bun.getString("typeUser");
        userName = bun.getString("userName");
        photo = bun.getString("photo");
        loadPathImage(photo);
        Toast.makeText(getContext(), photo, Toast.LENGTH_LONG).show();

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
                closeSession();
            }
        });
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

    private void loadPathImage(String pathString){
        if(!(pathString == null || pathString =="")) {
            this.photoUri = Uri.parse(pathString);
            this.imageUser.setImageURI(photoUri);
        }
    }
}