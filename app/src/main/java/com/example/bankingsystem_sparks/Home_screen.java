package com.example.bankingsystem_sparks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_screen extends AppCompatActivity {
    private Button showUsers_btn, showTransactions_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        showUsers_btn = findViewById(R.id.users_btn);
        showTransactions_btn = findViewById(R.id.transactions_btn);

        showUsers_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_screen.this, showUsers_screen.class);
                startActivity(intent);
            }
        });
        showTransactions_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_screen.this, all_transactions_screen.class);
                startActivity(intent);
            }
        });
    }
}