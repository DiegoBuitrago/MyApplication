package com.dd.guerrerobuitrago.fotoAppDigital.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.StoreRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

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

    private Bitmap imageUri;

    private boolean isImage;

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
        rvStore = view.findViewById(R.id.rv_store);
        //this.imageUri = stringToBitMap("");

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
                if (!recyclerView.canScrollVertically(1) && Manager.getSizeProductList() > 2) {
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
                    Manager.addProduct(new Product(txtInputNameProduct.getText().toString(),txtInputDesProduct.getText().toString(), bitMapToString(imageUri)));
                    rvAdapter.notifyItemInserted(Manager.getSizeProductList());
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

    public String bitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public static StoreFragment newInstance(){
        Bundle args = new Bundle();
        StoreFragment fragment = new StoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void loadImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);
        //startActivityForResult(intent.createChooser(intent, "Seleccione la aplicacion"), 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final Bundle extras = data.getExtras();
        if (extras != null) {
            //Get image
            isImage = true;
            Bitmap newProfilePic = extras.getParcelable("data");
            this.imageUri = newProfilePic;
            this.imageCustomDialog.setImageBitmap(imageUri);
        }
    }
}