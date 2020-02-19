package com.dd.guerrerobuitrago.fotoAppDigital.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Booked;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        TextView tvDate;
        TextView tvHour;
        TextView tvType;
        private ImageButton btnDelete;

        public ServicesViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            tvNameOwner = itemView.findViewById(R.id.tv_name_person_service);
            tvNameUser = itemView.findViewById(R.id.tv_userName_person_service);
            tvDate = itemView.findViewById(R.id.tv_date_input_service);
            tvHour = itemView.findViewById(R.id.tv_hour_input_service);
            tvType = itemView.findViewById(R.id.tv_type_input_service);
            btnDelete = itemView.findViewById(R.id.btn_delete_booked);
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

    public ServicesRVAdapter(ArrayList<Booked> booked) {
        this.bookeds = booked;
    }

    @NonNull
    @Override
    public ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardViewServices = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_service, parent, false);
        ServicesViewHolder viewHolder = new ServicesViewHolder(cardViewServices, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesRVAdapter.ServicesViewHolder holder, int position) {
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