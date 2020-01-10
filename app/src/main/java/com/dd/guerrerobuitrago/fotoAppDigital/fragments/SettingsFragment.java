package com.dd.guerrerobuitrago.fotoAppDigital.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dd.guerrerobuitrago.fotoAppDigital.EditUser;
import com.dd.guerrerobuitrago.fotoAppDigital.LogIn;
import com.dd.guerrerobuitrago.fotoAppDigital.R;

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

    private String firstName;
    private String lastName;
    private String password;
    private String typeUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        cardEdit = view.findViewById(R.id.cv_edit_user_set);
        txtNameUser = view.findViewById(R.id.txt_name_person_edit);
        txtDescUser = view.findViewById(R.id.txt_desc_user_edit);
        Bundle bun = getArguments();
        firstName = bun.getString("firstName");
        lastName = bun.getString("lastName");
        password = bun.getString("password");
        txtNameUser.setText(firstName + " " + lastName);
        txtDescUser.setText(bun.getString("typeUser"));
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
        startActivity(editUserIntent);
    }

    public void closeSession(){
        Intent loginIntent = new Intent(getActivity(), LogIn.class);
        startActivity(loginIntent);
    }

    public static SettingsFragment newInstance(String firstName, String lastName, String password, String typeUser){
        Bundle args = new Bundle();
        args.putString("firstName", firstName);
        args.putString("lastName", lastName);
        args.putString("password", password);
        args.putString("typeUser", typeUser);
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}