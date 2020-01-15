package com.dd.guerrerobuitrago.fotoAppDigital.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Booked;

import java.util.ArrayList;

public class ServicesRVAdapter extends RecyclerView.Adapter<ServicesRVAdapter.ServicesViewHolder>{
    private ArrayList<Booked> bookeds;
    private onItemClickListener listener;

    public interface onItemClickListener{
        void onDeleteClick(int position);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    static class ServicesViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameOwner;
        TextView tvNameUser;
        TextView tvYear;
        TextView tvMonth;
        TextView tvDay;
        TextView tvHour;
        TextView tvType;

        public ServicesViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            tvNameOwner = itemView.findViewById(R.id.tv_products_name);
            tvNameUser = itemView.findViewById(R.id.iv_product);
            tvYear = itemView.findViewById(R.id.tv_products_des);
            tvMonth = itemView.findViewById(R.id.tv_products_des);
            tvDay = itemView.findViewById(R.id.tv_products_des);
            tvHour = itemView.findViewById(R.id.tv_products_des);
            tvType = itemView.findViewById(R.id.tv_products_des);
        }
    }

    public ServicesRVAdapter(ArrayList<Booked> bookeds) {
        this.bookeds = bookeds;
    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardViewServices = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_store, parent, false);
        ServicesViewHolder viewHolder = new ServicesViewHolder(cardViewServices, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesRVAdapter.ServicesViewHolder holder, int position) {
        Booked booked = bookeds.get(position);
        holder.tvNameOwner.setText(booked.getOwner().getFirstName() +" "+ booked.getOwner().getLastName());
        holder.tvNameUser.setText(booked.getOwner().getUserName());
        holder.tvYear.setText(booked.getYear());
        holder.tvMonth.setText(booked.getMonth());
        holder.tvDay.setText(booked.getDay());
        holder.tvHour.setText(booked.getHour());
        holder.tvType.setText(booked.getTypeBooked().getMyName());
    }

    @Override
    public int getItemCount() {
        return bookeds.size();
    }
}