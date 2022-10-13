package com.example.bankingsystem_sparks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class users_transactions_adapter extends RecyclerView.Adapter<users_transactions_adapter.usersViewHolder>{
    ArrayList<Users_transactions> transactions;
    Users_transactions u;
    RecyclerView rv;

    public users_transactions_adapter(ArrayList<Users_transactions> transactions, RecyclerView rv) {
        this.transactions = transactions;
        this.rv = rv;
    }

    @NonNull
    @Override
    public users_transactions_adapter.usersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item_card_view, parent, false);
        users_transactions_adapter.usersViewHolder user_vh = new users_transactions_adapter.usersViewHolder(v);
        return user_vh;
    }


    @Override
    public void onBindViewHolder(@NonNull users_transactions_adapter.usersViewHolder holder, int position) {
        u = transactions.get(position);
        holder.from_tv.setText(u.getFrom_user());
        holder.to_tv.setText(u.getTo_user());
        holder.amount_tv.setText(String.valueOf(u.getAmount()));
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class usersViewHolder extends RecyclerView.ViewHolder {
        TextView from_tv, to_tv, amount_tv;

        public usersViewHolder(@NonNull View itemView) {
            super(itemView);
            from_tv = itemView.findViewById(R.id.allTransactionsScreen_user_from);
            to_tv = itemView.findViewById(R.id.allTransactionsScreen_user_to);
            amount_tv = itemView.findViewById(R.id.allTransactionsScreen_amount);
        }
    }
}