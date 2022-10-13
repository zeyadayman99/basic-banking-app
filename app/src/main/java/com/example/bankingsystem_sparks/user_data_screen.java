package com.example.bankingsystem_sparks;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class user_data_screen extends AppCompatActivity {
    private TextView name_tv, id_tv, email_tv, balance_tv, job_tv;
    private Button transfer_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_screen);
        name_tv = findViewById(R.id.user_data_screen_name);
        id_tv = findViewById(R.id.user_data_screen_id);
        email_tv = findViewById(R.id.user_data_screen_email);
        job_tv = findViewById(R.id.user_data_screen_job);
        balance_tv = findViewById(R.id.user_data_screen_balance);
        transfer_btn = findViewById(R.id.transfer_balance_btn);

        int id = 0;
        if(getIntent() != null && getIntent().getIntExtra("showUsers request code", -1) == showUsers_screen.SHOW_USERS_SCREEN_REQ_CODE){
            Intent intent = getIntent();
            id = intent.getIntExtra("user id", -1);
        }
        Users u = showUsers_screen.db.getUser(id);
        id_tv.setText(Integer.toString(u.getUser_id()));
        name_tv.setText(u.getUser_name());
        email_tv.setText(u.getUser_email());
        job_tv.setText(u.getUser_job());
        balance_tv.setText(String.valueOf(u.getUser_balance()));

        transfer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_data_screen.this, transaction_screen.class);
                intent.putExtra("user name", u.getUser_name()+"");
                intent.putExtra("user balance", u.getUser_balance());
                intent.putExtra("user id", u.getUser_id());
                startActivity(intent);
                }
        });
    }
}