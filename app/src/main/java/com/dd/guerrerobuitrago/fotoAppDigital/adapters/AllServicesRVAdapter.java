package com.dd.guerrerobuitrago.fotoAppDigital.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Booked;

import java.util.ArrayList;

public class AllServicesRVAdapter extends RecyclerView.Adapter<AllServicesRVAdapter.AllServicesViewHolder>{
    private ArrayList<Booked> bookeds;
    private onItemClickListener listener;

    public interface onItemClickListener{
        void onDeleteClick(int position);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    static class AllServicesViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameOwner;
        TextView tvNameUser;
        TextView tvDate;
        TextView tvHour;
        TextView tvType;

        public AllServicesViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            tvNameOwner = itemView.findViewById(R.id.tv_name_person_service_all);
            tvNameUser = itemView.findViewById(R.id.tv_userName_person_service_all);
            tvDate = itemView.findViewById(R.id.tv_date_input_service_all);
            tvHour = itemView.findViewById(R.id.tv_hour_input_service_all);
            tvType = itemView.findViewById(R.id.tv_type_input_service_all);
        }
    }

    public AllServicesRVAdapter(ArrayList<Booked> booked) {
        this.bookeds = booked;
    }

    @NonNull
    @Override
    public AllServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardViewAllServices = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_all_service, parent, false);
        AllServicesViewHolder viewHolder = new AllServicesViewHolder(cardViewAllServices, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllServicesRVAdapter.AllServicesViewHolder holder, int position) {
        Booked booked = bookeds.get(position);
        holder.tvNameOwner.setText(booked.getOwner().getFirstName() +" "+ booked.getOwner().getLastName());
        holder.tvNameUser.setText(booked.getOwner().getUserName());
        holder.tvDate.setText(booked.getDay()+"/"+booked.getMonth()+"/"+booked.getYear());
        holder.tvHour.setText(booked.getHour());
        holder.tvType.setText(booked.getTypeBooked().getMyName());
    }

    @Override
    public int getItemCount() {
        return bookeds.size();
    }
}