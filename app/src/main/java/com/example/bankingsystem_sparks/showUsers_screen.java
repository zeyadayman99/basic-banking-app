package com.example.bankingsystem_sparks;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class showUsers_screen extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static MyDatabase db;
    private ArrayList<Users> users;
    private users_recyclerView_adapter adapter;
    private Toolbar tb;
    public static final int SHOW_USERS_SCREEN_REQ_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users_screen);
        recyclerView = findViewById(R.id.recycler_users);
        tb = findViewById(R.id.toolbar);
        tb.setTitle("All users");
        setSupportActionBar(tb);
        tb.inflateMenu(R.menu.show_all_users_screen_menu);

        Users u1 = new Users( 120000, "john smith", "accountant", "johnSmith12@gmail.com");
        Users u2 = new Users( 500000, "shawn parker", "engineer", "shawnparker88@gmail.com");
        Users u3 = new Users(91000, "maya adams", "designer", "mayaAdams32@gmail.com");
        Users u4 = new Users(24000, "steven daniel", "doctor", "stevendan45@gmail.com");
        Users u5 = new Users(50000, "rayan jack", "Business owner", "rayanjack67@gmail.com");
        Users u6 = new Users(325000, "emy nick", "teacher", "emyNick67@gmail.com");
        Users u7 = new Users(950000, "andrew adam", "marketing manager", "andrewAdam1@gmail.com");
        Users u8 = new Users(125900, "michel gordan", "basketball player", "michelGordan344@gmail.com");
        users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        users.add(u6);
        users.add(u7);
        users.add(u8);

        db = new MyDatabase(this);
        db.insertUser(u1);
        db.insertUser(u2);
        db.insertUser(u3);
        db.insertUser(u4);
        db.insertUser(u5);
        db.insertUser(u6);
        db.insertUser(u7);
        db.insertUser(u8);

        users = db.getAllUsers();

        adapter = new users_recyclerView_adapter(users, recyclerView, new onRecyclerViewItemClickedListener() {
            @Override
            public void onRecyclerItemClick(int user_id) {
                Intent i = new Intent(showUsers_screen.this, user_data_screen.class);
                i.putExtra("user id", user_id);
                i.putExtra("showUsers request code", SHOW_USERS_SCREEN_REQ_CODE);
                startActivity(i);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        if(getIntent() != null && getIntent().getIntExtra("transaction result code", -1) == transaction_screen.TRANSACTION_COMPLETE_RESULT_CODE){
            users = db.getAllUsers();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        users = db.getAllUsers();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.show_all_users_screen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case (R.id.transactions_icon):{
                Intent i = new Intent(showUsers_screen.this, all_transactions_screen.class);
                startActivity(i);
            }
        }
        return true;
    }
}