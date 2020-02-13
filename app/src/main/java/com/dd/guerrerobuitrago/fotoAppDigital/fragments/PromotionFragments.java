package com.dd.guerrerobuitrago.fotoAppDigital.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.PromotionRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Product;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Promotion;
import com.dd.guerrerobuitrago.fotoAppDigital.utilities.MyConexion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionFragments extends Fragment {

//  private ArrayList<Promotion> promotionsList;
    private static final int MY_PERMISSIONS_REQUEST_READ_MEDIA = 1;

    private RecyclerView rvPromotion;
    private PromotionRVAdapter rvAdapter;
    private FloatingActionButton btnFloat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragmentloadImage();
        View promotionView = inflater.inflate(R.layout.fragment_promotion_fragments, container, false);
        AndroidNetworking.initialize(getContext());
        initComponents(promotionView);
        return promotionView;
    }


    private void initComponents(View view) {
        btnFloat = view.findViewById(R.id.btn_float);
        rvPromotion = view.findViewById(R.id.rv_promotion);
        initRecyclerView();
        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,
                false);
            rvAdapter = new PromotionRVAdapter(Manager.getPromotionList());
            rvAdapter.setListener(new PromotionRVAdapter.onItemClickListener() {
                @Override
                public void onDeleteClick(int position) {
                    Manager.removePromotion(position);
                    MyConexion.deleteDataBasePromotion(position);
                    rvAdapter.notifyItemRemoved(position);
                }
            });
            rvPromotion.setLayoutManager(layoutManager);
            rvPromotion.setAdapter(rvAdapter);
            rvPromotion.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (!recyclerView.canScrollVertically(1) && Manager.getSizePromotionList() > 2) {
                        btnFloat.hide();
                    } else {
                        btnFloat.show();
                    }
                }
            });
    }

    private void loadImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            Uri uri = data.getData();
            Promotion promotion = new Promotion(Manager.getSizePromotionList(), "promo", uri);
            if (uri != null) {
                Manager.addPromotion(promotion);
                MyConexion.loadDataBasePromotion(promotion);
                rvAdapter.notifyItemInserted(Manager.getSizePromotionList());
            }
        }
    }

    public static PromotionFragments newInstance(){
        Bundle args = new Bundle();
        PromotionFragments fragment = new PromotionFragments();
        fragment.setArguments(args);
        return fragment;
    }
}