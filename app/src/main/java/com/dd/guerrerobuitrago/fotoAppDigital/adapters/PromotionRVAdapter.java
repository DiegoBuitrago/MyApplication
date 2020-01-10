package com.dd.guerrerobuitrago.fotoAppDigital.adapters;

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

        TextView tvPromotionName;
        ImageView imagePromotion;
        ImageButton btnDelete;

        public PromotionViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            tvPromotionName = itemView.findViewById(R.id.tv_promotions_name);
            // image promotion para probar
            imagePromotion = itemView.findViewById(R.id.iv_promotion);
            btnDelete = itemView.findViewById(R.id.btn_delete);
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
        PromotionViewHolder viewHolder = new PromotionViewHolder(cardViewPromotions, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionRVAdapter.PromotionViewHolder holder, int position) {
        Promotion promotion = promotions.get(position);
        holder.tvPromotionName.setText(promotion.getName());
        holder.imagePromotion.setImageURI(promotion.getPhoto());
    }

    @Override
    public int getItemCount() {
        return promotions.size();
    }
}