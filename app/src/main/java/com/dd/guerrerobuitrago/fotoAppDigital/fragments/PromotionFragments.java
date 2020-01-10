package com.dd.guerrerobuitrago.fotoAppDigital.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.adapters.PromotionRVAdapter;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Promotion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionFragments extends Fragment {

    private ArrayList<Promotion> promotionsList;

    private RecyclerView rvPromotion;
    private PromotionRVAdapter rvAdapter;
    private FloatingActionButton btnFloat;
    private int a;

    private Uri imagen;

//    private ImageView imagen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragmentloadImage();
        View promotionView = inflater.inflate(R.layout.fragment_promotion_fragments, container, false);
        initComponents(promotionView);
        return promotionView;
    }

    private void initComponents(View view) {
        btnFloat = view.findViewById(R.id.btn_float);
        rvPromotion = view.findViewById(R.id.rv_promotion);
        initRecyclerView();
//      imagen = (ImageView) view.findViewById(R.id.iv_promotion);

        btnFloat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadImage();
                countTest();
                promotionsList.add(new Promotion(String.valueOf(a), imagen));
                rvAdapter.notifyItemInserted(promotionsList.size());
            }
        });
    }

    private void initRecyclerView() {
        promotionsList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,
                false);
            rvAdapter = new PromotionRVAdapter(promotionsList);
            rvAdapter.setListener(new PromotionRVAdapter.onItemClickListener() {
                @Override
                public void onDeleteClick(int position) {
                    promotionsList.remove(position);
                    rvAdapter.notifyItemRemoved(position);
                }
            });
            rvPromotion.setLayoutManager(layoutManager);
            rvPromotion.setAdapter(rvAdapter);
            rvPromotion.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (!recyclerView.canScrollVertically(1) && promotionsList.size() > 2) {
                        btnFloat.hide();
                    } else {
                        btnFloat.show();
                    }
                }
            });
    }

    private boolean loadImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicacion"), 10);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
              this.imagen = data.getData();
//            setPathImage(data.getData());
//            Uri path = data.getData();
//            ImageView image = getView().findViewById(R.id.iv_promotion);
//            image.setImageURI(path);
        }
    }

    public static PromotionFragments newInstance(){
        Bundle args = new Bundle();
        PromotionFragments fragment = new PromotionFragments();
        fragment.setArguments(args);
        return fragment;
    }

    /*
    Metodo de prueba que ingresa por parametro un path(Uri) e inicializa una image para usarla en el objeto correspondiente.

    private void createImage(Uri path){
        this.imagen = getView().findViewById(R.id.iv_promotion);
        imagen.setImageURI(path);
    }
    */

//   private void setPathImage(Uri path){
//       this.imagen = path;
//   }

    private void countTest(){
        this.a++;
    }
}