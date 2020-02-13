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
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Promotion;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PromotionRVAdapter extends RecyclerView.Adapter<PromotionRVAdapter.PromotionViewHolder>{
    private ArrayList<Promotion> promotions;
    private onItemClickListener listener;
    private Person person;

    public interface onItemClickListener{
        void onDeleteClick(int position);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public static class PromotionViewHolder extends RecyclerView.ViewHolder {

        private ImageView imagePromotion;
        private ImageButton btnDelete;
        private Context context;
        private Person person;

        public PromotionViewHolder(@NonNull View itemView, final onItemClickListener listener, Context context, Person person) {
            super(itemView);
            this.person = person;
            imagePromotion = itemView.findViewById(R.id.iv_promotion);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            if(person.getTypeUser().equals("Cliente")) {
                btnDelete.setVisibility(View.INVISIBLE);
            }
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

    public PromotionRVAdapter(ArrayList<Promotion> promotions, Person person) {
        this.promotions = promotions;
        this.person = person;
    }

    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardViewPromotions = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_promotions, parent, false);
        PromotionViewHolder viewHolder = new PromotionViewHolder(cardViewPromotions, listener, parent.getContext(), person);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull PromotionRVAdapter.PromotionViewHolder holder, int position) {
        Promotion promotion = promotions.get(position);
        try {
            Bitmap one = MediaStore.Images.Media.getBitmap(holder.context.getContentResolver(), promotion.getPhoto());
            Bitmap bitmap = Bitmap.createScaledBitmap(one, 500, 500, true);
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