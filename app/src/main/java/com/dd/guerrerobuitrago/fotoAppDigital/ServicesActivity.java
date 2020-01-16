package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.ServicesRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Booked;

import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        initComponents();
    }

    private ArrayList<Booked> bookedList;

    private RecyclerView rvServices;
    private ServicesRVAdapter rvAdapter;


    private void initComponents() {
        rvServices = findViewById(R.id.rv_services);
        initRecyclerView();
//        btnFloatStore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addProductToList(v);
//            }
//        });
    }

    private void initRecyclerView(){
        bookedList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false);
        rvAdapter = new ServicesRVAdapter(bookedList);

        rvServices.setLayoutManager(layoutManager);
        rvServices.setAdapter(rvAdapter);
    }

    private void addProductToList(View view){
//        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
////        View mView = getLayoutInflater().inflate(R.layout.custom_dialog_add_store, null);
////
////        txtInputNameProduct = mView.findViewById(R.id.txt_input_name_product);
////        txtInputDesProduct = mView.findViewById(R.id.txt_input_desc_product);
////        imageCustomDialog = mView.findViewById(R.id.imageView4);
////        Button btnAddImage = mView.findViewById(R.id.btn_choose_img_product);
////        Button btnCancel = mView.findViewById(R.id.btn_cancel_add_product);
////        Button btnAccept = mView.findViewById(R.id.btn_add_product_to_list);
////
////        alert.setView(mView);
////        final AlertDialog alertDialog = alert.create();
////        alertDialog.setCanceledOnTouchOutside(true);
////
////        btnCancel.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                alertDialog.dismiss();
////            }
////        });
////
////        btnAccept.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                productList.add(new Product(txtInputNameProduct.getText().toString(),txtInputDesProduct.getText().toString(), imageUri.toString()));
////                rvAdapter.notifyItemInserted(productList.size());
////                alertDialog.dismiss();
////            }
////        });
////
////        btnAddImage.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                loadImage();
////            }
////        });
////        alertDialog.show();
    }
}