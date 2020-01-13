package com.dd.guerrerobuitrago.fotoAppDigital.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dd.guerrerobuitrago.fotoAppDigital.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View servicesView = inflater.inflate(R.layout.fragment_services, container, false);
        init(servicesView);
        return servicesView;
    }

    private void init(View view) {
        View cvSearch = view.findViewById(R.id.cv_search_services);
        View cvBooked = view.findViewById(R.id.cv_booked_services);

        cvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearch();
            }
        });

        cvBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBooked();
            }
        });
    }

    private void onSearch() {

    }

    private void onBooked() {
    }

    public static ServicesFragment newInstance(){

        Bundle args = new Bundle();

        ServicesFragment fragment = new ServicesFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
