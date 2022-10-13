package com.example.bankingsystem_sparks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class transaction_screen extends AppCompatActivity {
    private TextView user_name_tv;
    private Button transaction_btn;
    private TextInputEditText balance_et;
    private AutoCompleteTextView users_list_et;
    public static final int TRANSACTION_COMPLETE_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_screen);
        user_name_tv = findViewById(R.id.from_user_name_transaction_screen);
        users_list_et = findViewById(R.id.users_list_transactions_screen);
        transaction_btn = findViewById(R.id.transfer_balance_btn);
        balance_et = findViewById(R.id.textInputEditText_balanceTransfer);

        Intent intent = getIntent();
        String sender_name = (String) intent.getCharSequenceExtra("user name");
        int sender_id = intent.getIntExtra("user id", -1);
        long sender_balance = intent.getLongExtra("user balance", -1);

        user_name_tv.setText(sender_name);
        ArrayList<Users> users = showUsers_screen.db.getAllUsers();
        ArrayAdapter<Users> adapter = new ArrayAdapter<Users>(this,
                android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        users_list_et.setAdapter(adapter);

        int[] user_id_to_transfer_to = new int[1];

        users_list_et.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapter.getItem(i).getUser_id() == sender_id)
                    Toast.makeText(transaction_screen.this, "invalid user, please select another user", Toast.LENGTH_SHORT).show();
                else{
                    user_id_to_transfer_to[0] = (int)adapter.getItem(i).getUser_id();
                }
            }
        });

        transaction_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(balance_et.getText()).equals(""))
                    Toast.makeText(transaction_screen.this, "please enter amount for transaction", Toast.LENGTH_SHORT).show();
                else{
                    long balance_entered = Long.parseLong(String.valueOf(balance_et.getText()));
                    if(sender_balance < balance_entered){
                        Toast.makeText(transaction_screen.this, "no enough balance, please enter a valid amount", Toast.LENGTH_SHORT).show();
                        balance_et.setText("");
                    }
                    else{
                        Users user_to_make_transaction_to = showUsers_screen.db.getUser(user_id_to_transfer_to[0]);
                        user_to_make_transaction_to.transfer_balance(balance_entered);
                        showUsers_screen.db.updateUserInfo(user_to_make_transaction_to);

                        Users user_to_make_transaction_from = showUsers_screen.db.getUser(sender_id);
                        user_to_make_transaction_from.withdraw_balance(balance_entered);
                        showUsers_screen.db.updateUserInfo(user_to_make_transaction_from);

                        Toast.makeText(transaction_screen.this, "transaction complete", Toast.LENGTH_SHORT).show();

                        String receiver_name = user_to_make_transaction_to.getUser_name();
                        Users_transactions u = new Users_transactions(sender_name, receiver_name, balance_entered);
                        showUsers_screen.db.insertTransaction(u);

                        Intent i = new Intent(transaction_screen.this, showUsers_screen.class);
                        i.putExtra("transaction result code", TRANSACTION_COMPLETE_RESULT_CODE);
                        startActivity(i);
                    }
                }
            }
        });
    }
}