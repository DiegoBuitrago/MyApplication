package com.dd.guerrerobuitrago.fotoAppDigital.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Product;

import java.util.ArrayList;

public class StoreRVAdapter extends RecyclerView.Adapter<StoreRVAdapter.StoreViewHolder>{
    private ArrayList<Product> products;
    private onItemClickListener listener;

    public interface onItemClickListener{
        void onDeleteClick(int position);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    static class StoreViewHolder extends RecyclerView.ViewHolder {

        private TextView tvProductName;
        private TextView tvProductDes;
        private ImageView imageProduct;
        private ImageButton btnDelete;

        public StoreViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tv_products_name);
            imageProduct = itemView.findViewById(R.id.iv_product);
            tvProductDes = itemView.findViewById(R.id.tv_products_des);
            btnDelete = itemView.findViewById(R.id.btn_delete_product);
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

    public StoreRVAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardViewStore = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_store, parent, false);
        StoreViewHolder viewHolder = new StoreViewHolder(cardViewStore, listener);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull StoreRVAdapter.StoreViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvProductDes.setText(product.getDescription());
        holder.imageProduct.setImageURI(Uri.parse(product.getPhoto()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}