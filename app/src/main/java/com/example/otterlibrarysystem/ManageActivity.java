package com.example.otterlibrarysystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrarysystem.databinding.ActivityMainBinding;
import com.example.otterlibrarysystem.databinding.ActivityManageBinding;

import java.util.List;

public class ManageActivity extends AppCompatActivity {
    private ActivityManageBinding binding;
    private LibraryDatabase db;
    List<Transaction> transactionList;
    private ListView transactionListView;
    private ArrayAdapter<Transaction> transactionAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityManageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LibraryDatabase.getInstance(this);
        transactionList = db.transactions().getAll();
        transactionListView = binding.transactionListView;
        binding.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(binding.qUsername.getText());
                String password = String.valueOf(binding.qPassword.getText());

                if ((username.equals("!admin2") && password.equals("!admin2"))){

                    transactionAdapter = new ArrayAdapter<>(ManageActivity.this,R.layout.item_transaction, R.id.transaction_item,transactionList);
                    transactionListView.setAdapter(transactionAdapter);
                }
                binding.menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        });
        binding.confirm.setOnClickListener(v -> startActivity(new Intent(this, LibraryloginActivity.class)));

    }
}
