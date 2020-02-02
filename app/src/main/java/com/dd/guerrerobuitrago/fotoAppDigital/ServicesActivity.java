package com.dd.guerrerobuitrago.fotoAppDigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.ServicesRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Booked;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;

import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity {

    private RecyclerView rvServices;
    private ServicesRVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        initComponents();
    }

    private void initComponents() {
        rvServices = findViewById(R.id.rv_services);
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,
                false);
        rvAdapter = new ServicesRVAdapter(Manager.getBookedList());
        rvAdapter.setListener(new ServicesRVAdapter.onItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                Manager.removeBooked(position);
                rvAdapter.notifyItemRemoved(position);
            }
        });
        rvServices.setLayoutManager(layoutManager);
        rvServices.setAdapter(rvAdapter);
        rvAdapter.notifyItemInserted(Manager.getSizeBookedList());
    }

    public static void addProductToList(){
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
////        alertDialog.show();
    }

    public ServicesRVAdapter getServicesRVAdapter(){
        return this.rvAdapter;
    }
}