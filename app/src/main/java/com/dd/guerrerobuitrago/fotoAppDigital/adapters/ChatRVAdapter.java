package com.dd.guerrerobuitrago.fotoAppDigital.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        private TextView tvUserName;
        private TextView tvMessage;
        private Context context;

        public ChatViewHolder(@NonNull View itemView, final ChatRVAdapter.onItemClickListener listener, Context context) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_name_person_chat);
            tvMessage = itemView.findViewById(R.id.tv_message);
            this.context = context;
        }
    }

    public ChatRVAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ChatRVAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardViewChat = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_chat, parent, false);
        ChatRVAdapter.ChatViewHolder viewHolder = new ChatRVAdapter.ChatViewHolder(cardViewChat, listener, parent.getContext());
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ChatRVAdapter.ChatViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.tvUserName.setText(message.getNameUser());
        holder.tvMessage.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}