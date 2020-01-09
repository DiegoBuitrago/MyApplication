package com.dd.guerrerobuitrago.fotoAppDigital.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.guerrerobuitrago.fotoAppDigital.Home;
import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment {

    private FloatingActionButton btnFloatStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View storeView = inflater.inflate(R.layout.fragment_store, container, false);
        initComponents(storeView);
        return storeView;
    }


    private void initComponents(View view) {
        btnFloatStore = view.findViewById(R.id.btn_float_store);
        btnFloatStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToList(v);
            }
        });
    }

    private void addProductToList(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog_add_store, null);

        EditText txtInputNameProduct = mView.findViewById(R.id.txt_input_name_product);
        EditText txtDescProduct = mView.findViewById(R.id.txt_input_desc_product);
        Button btnAddImage = mView.findViewById(R.id.btn_choose_img_product);
        Button btnCancel = mView.findViewById(R.id.btn_cancel_add_product);
        Button btnAccept = mView.findViewById(R.id.btn_add_product_to_list);

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "ddd", Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        });

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        alertDialog.show();

    }

    public static StoreFragment newInstance(){

        Bundle args = new Bundle();

        StoreFragment fragment = new StoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
