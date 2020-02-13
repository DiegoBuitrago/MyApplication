package com.dd.guerrerobuitrago.fotoAppDigital.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.StoreRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Product;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Promotion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreFragment extends Fragment {

    private RecyclerView rvStore;
    private StoreRVAdapter rvAdapter;
    private FloatingActionButton btnFloatStore;

    private EditText txtInputNameProduct;
    private EditText txtInputDesProduct;

    private ImageView imageCustomDialog;
    private Uri uri;

    private boolean isImage;

    private Person person;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View storeView = inflater.inflate(R.layout.fragment_store, container, false);
        AndroidNetworking.initialize(getContext());
        initComponents(storeView);
        return storeView;
    }

    private void initComponents(View view) {
        btnFloatStore = view.findViewById(R.id.btn_float_store);
        rvStore = view.findViewById(R.id.rv_store);

        initRecyclerView();
        btnFloatStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProductToList(v);
            }
        });
    }

    private void initRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,
                false);
        rvAdapter = new StoreRVAdapter(Manager.getProductList());
        rvAdapter.setListener(new StoreRVAdapter.onItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                Manager.removeProduct(position);
                rvAdapter.notifyItemRemoved(position);
            }
        });
        rvStore.setLayoutManager(layoutManager);
        rvStore.setAdapter(rvAdapter);
        rvStore.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && Manager.getSizeProductList() > 3) {
                    btnFloatStore.hide();
                } else {
                    btnFloatStore.show();
                }
            }
        });
    }

    private void addProductToList(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog_add_store, null);

        txtInputNameProduct = mView.findViewById(R.id.txt_input_name_product);
        txtInputDesProduct = mView.findViewById(R.id.txt_input_desc_product);
        imageCustomDialog = mView.findViewById(R.id.imageView4);
        Button btnAddImage = mView.findViewById(R.id.btn_choose_img_product);
        Button btnCancel = mView.findViewById(R.id.btn_cancel_add_product);
        Button btnAccept = mView.findViewById(R.id.btn_add_product_to_list);

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(true);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isImage){
                    Product product = new Product(Manager.getSizeProductList(),txtInputNameProduct.getText().toString(),txtInputDesProduct.getText().toString(), uri);
                    if(product.getPhoto() != null) {
                        Manager.addProduct(product);
                        //loadDataBaseProduct(product);
                        rvAdapter.notifyItemInserted(Manager.getSizeProductList());
                    }
                    alertDialog.dismiss();
                }else{
                    Toast.makeText(getContext(), "Ingrese una imagen", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });
        alertDialog.show();
    }

    private void loadImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            uri = data.getData();
            if (uri != null) {
                try {
                    Bitmap one = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                    Bitmap bitmap = Bitmap.createScaledBitmap(one, 400, 400, true);
                    imageCustomDialog.setImageBitmap(bitmap);
                    isImage = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadDataBaseProduct(Product product){
        Map<String,String> datos = new HashMap<>();
        datos.put("name",product.getName());
        datos.put("description",product.getDescription());
        datos.put("photo",product.getPhoto().toString());

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

    public static StoreFragment newInstance(Person person){
        Bundle args = new Bundle();
        StoreFragment fragment = new StoreFragment();
        fragment.person = person;
        fragment.setArguments(args);
        return fragment;
    }
}