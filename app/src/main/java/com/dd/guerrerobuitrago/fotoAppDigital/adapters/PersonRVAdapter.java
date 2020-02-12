package com.dd.guerrerobuitrago.fotoAppDigital.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.guerrerobuitrago.fotoAppDigital.R;
import com.dd.guerrerobuitrago.fotoAppDigital.models.Person;

import java.io.IOException;
import java.util.ArrayList;

public class PersonRVAdapter extends RecyclerView.Adapter<PersonRVAdapter.PersonViewHolder>{
    private ArrayList<Person> persons;
    private onItemClickListener listener;

    public interface onItemClickListener{
//        void onDeleteClick(int position);
        void onUpdateClick(int position, String textNew);
    }

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPersonName;
        private TextView tvPersonType;
        private ImageView imagePerson;
        private Button btnUpdate;
        private Spinner spinner;
        private Context context;

        public PersonViewHolder(@NonNull View itemView, final onItemClickListener listener, Context context) {
            super(itemView);
            tvPersonName = itemView.findViewById(R.id.tv_name_person);
            tvPersonType = itemView.findViewById(R.id.tv_type_person);
            imagePerson = itemView.findViewById(R.id.iv_image_person);
            btnUpdate = itemView.findViewById(R.id.update_person);
            spinner = itemView.findViewById(R.id.sp_type_user);
            this.context = context;
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        String st = spinner.getSelectedItem().toString();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onUpdateClick(position, st);
                        }
                    }
                }
            });
        }
    }

    public PersonRVAdapter(ArrayList<Person> persons) {
        this.persons = persons;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardViewPerson = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_person, parent, false);
        PersonViewHolder viewHolder = new PersonViewHolder(cardViewPerson, listener, parent.getContext());
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull PersonRVAdapter.PersonViewHolder holder, int position) {
        Person person = persons.get(position);
        holder.tvPersonName.setText(person.getFirstName()+"  "+person.getLastName());
        holder.tvPersonType.setText(person.getTypeUser());
        try {
            if(person.getPhoto() != null) {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(holder.context.getContentResolver(), Uri.parse(person.getPhoto()));
                holder.imagePerson.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}