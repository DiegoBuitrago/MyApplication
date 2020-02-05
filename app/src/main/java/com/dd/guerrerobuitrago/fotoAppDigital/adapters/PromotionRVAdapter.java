package com.dd.guerrerobuitrago.fotoAppDigital.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Promotion;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PromotionRVAdapter extends RecyclerView.Adapter<PromotionRVAdapter.PromotionViewHolder>{
    private ArrayList<Promotion> promotions;
    private onItemClickListener listener;

    public interface onItemClickListener{
        void onDeleteClick(int position);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    static class PromotionViewHolder extends RecyclerView.ViewHolder {

//        private TextView tvPromotionName;
        private ImageView imagePromotion;
        private ImageButton btnDelete;
        private Context context;

        public PromotionViewHolder(@NonNull View itemView, final onItemClickListener listener, Context context) {
            super(itemView);
//            tvPromotionName = itemView.findViewById(R.id.tv_promotions_name);
            // image promotion para probar
            imagePromotion = itemView.findViewById(R.id.iv_promotion);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            this.context = context;
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public PromotionRVAdapter(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }

    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardViewPromotions = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_promotions, parent, false);
        PromotionViewHolder viewHolder = new PromotionViewHolder(cardViewPromotions, listener, parent.getContext());
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull PromotionRVAdapter.PromotionViewHolder holder, int position) {
        Promotion promotion = promotions.get(position);
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(holder.context.getContentResolver(), promotion.getPhoto2());
            holder.imagePromotion.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return promotions.size();
    }
}