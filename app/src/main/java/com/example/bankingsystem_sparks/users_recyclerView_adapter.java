package com.example.bankingsystem_sparks;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class users_recyclerView_adapter extends RecyclerView.Adapter<users_recyclerView_adapter.usersViewHolder> {

    ArrayList<Users> users;
    Users u;
    onRecyclerViewItemClickedListener listener;
    RecyclerView rv;

    public users_recyclerView_adapter(ArrayList<Users> users, RecyclerView rv, onRecyclerViewItemClickedListener listener) {
        this.users = users;
        this.listener = listener;
        this.rv = rv;
    }

    @NonNull
    @Override
    public usersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_card_view, parent, false);
        usersViewHolder user_vh = new usersViewHolder(v);
        return user_vh;
    }


    @Override
    public void onBindViewHolder(@NonNull usersViewHolder holder, int position) {
        u = users.get(position);
        holder.id_tv.setText(String.valueOf(u.getUser_id()));
        holder.name_tv.setText(u.getUser_name());
        holder.name_tv.setTag(u.getUser_id());
        holder.email_tv.setText(u.getUser_email());
        holder.job_tv.setText(u.getUser_job());
        holder.balance_tv.setText(String.valueOf(u.getUser_balance()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class usersViewHolder extends RecyclerView.ViewHolder {
        TextView name_tv, email_tv, job_tv, balance_tv, id_tv;

        public usersViewHolder(@NonNull View itemView) {
            super(itemView);
            name_tv = itemView.findViewById(R.id.cardView_user_name);
            email_tv = itemView.findViewById(R.id.cardView_user_email);
            job_tv = itemView.findViewById(R.id.cardView_user_job);
            balance_tv = itemView.findViewById(R.id.cardView_user_balance);
            id_tv = itemView.findViewById(R.id.cardView_user_id);
            name_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = (int) name_tv.getTag();
                    listener.onRecyclerItemClick(id);
                }
            });
        }
    }
}