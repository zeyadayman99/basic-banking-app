package com.example.bankingsystem_sparks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class all_transactions_screen extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<Users_transactions> u;
    private users_transactions_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_transactions_screen);
        rv = findViewById(R.id.recycler_transactions);

        u = showUsers_screen.db.getAllTransactions();
        if(u.get(0) == null)
            Toast.makeText(this, "no transactions yet", Toast.LENGTH_SHORT).show();
        else{
            adapter = new users_transactions_adapter(u, rv);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);
        }
    }
}