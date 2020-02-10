package com.dd.guerrerobuitrago.fotoAppDigital.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.PromotionRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Manager;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Product;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Promotion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionFragments extends Fragment {

//  private ArrayList<Promotion> promotionsList;

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
        //this.imageUri = stringToBitMap("");
        initRecyclerView();
        btnFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });
    }

    private void initRecyclerView() {
//      promotionsList = Manager.getSizePromotionList();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,
                false);
            rvAdapter = new PromotionRVAdapter(Manager.getPromotionList());
            rvAdapter.setListener(new PromotionRVAdapter.onItemClickListener() {
                @Override
                public void onDeleteClick(int position) {
                    Manager.removePromotion(position);
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
            Promotion promotion = new Promotion("", uri);
            if (promotion.getPhoto() != null) {
                Manager.addPromotion(promotion);
                loadDataBasePromotion(promotion);
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

    public void loadDataBasePromotion(Promotion promotion){
        Map<String,String> datos = new HashMap<>();
        datos.put("name",promotion.getName());
        datos.put("photo", promotion.getPhoto().toString());
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
                            Log.e("Error","blablablapromotion");
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error",anError.getErrorDetail());
                    }
                });
    }
}