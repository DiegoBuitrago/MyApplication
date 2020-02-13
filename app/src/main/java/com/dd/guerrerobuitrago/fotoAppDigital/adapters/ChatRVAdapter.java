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
import com.dd.guerrerobuitrago.fotoAppDigital.models.Message;

import java.util.ArrayList;

public class ChatRVAdapter extends RecyclerView.Adapter<ChatRVAdapter.ChatViewHolder>{
    private ArrayList<Message> messages;
    private ChatRVAdapter.onItemClickListener listener;

    public interface onItemClickListener{
        //intefaz de listener
    }

    public void setListener(ChatRVAdapter.onItemClickListener listener) {
        this.listener = listener;
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPersonName;
        private TextView tvPersonType;
        private ImageView imagePerson;
        private Button btnUpdate;
        private Spinner spinner;
        private Context context;

        public ChatViewHolder(@NonNull View itemView, final ChatRVAdapter.onItemClickListener listener, Context context) {
            super(itemView);
            tvPersonName = itemView.findViewById(R.id.tv_name_person);
            tvPersonType = itemView.findViewById(R.id.tv_type_person);
            imagePerson = itemView.findViewById(R.id.iv_image_person);
            btnUpdate = itemView.findViewById(R.id.update_person);
            spinner = itemView.findViewById(R.id.sp_type_user);
            this.context = context;
        }
    }

    public ChatRVAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ChatRVAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardViewChat = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_person, parent, false);
        ChatRVAdapter.ChatViewHolder viewHolder = new ChatRVAdapter.ChatViewHolder(cardViewChat, listener, parent.getContext());
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ChatRVAdapter.ChatViewHolder holder, int position) {
        Message message = messages.get(position);
//        holder.tvPersonName.setText(person.getFirstName()+"  "+person.getLastName());
//        holder.tvPersonType.setText(person.getTypeUser());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
