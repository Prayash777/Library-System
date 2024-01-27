package com.example.otterlibrarysystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.otterlibrarysystem.databinding.ActivityLoginBinding;

import java.util.List;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private LibraryDatabase db;
    private List<Users> usernames;
    int resNum=0;
    String name = "";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //From Intent
        String bookTitle = getIntent().getStringExtra("book");
        Books bookobj =(Books) getIntent().getSerializableExtra("bookobj");
        db = LibraryDatabase.getInstance(this);
        usernames = db.customers().getAll();
        binding.okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = String.valueOf(binding.qUsername.getText());
                String password = String.valueOf(binding.qPassword.getText());
                for (Users u : usernames) {
                    if (!((u.getUsername().equals(username)) && (u.getPassword().equals(password)))){
                        Toast.makeText(LoginActivity.this, "Information not valid, Try again.",
                                Toast.LENGTH_LONG).show();
                        binding.okButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String user = String.valueOf(binding.qUsername.getText());
                                String pass = String.valueOf(binding.qPassword.getText());
                                for (Users u1 : usernames) {
                                    if (!((u1.getUsername().equals(username)) && (u1.getPassword().equals(password)))){
                                        Toast.makeText(LoginActivity.this, "Error, again invalid",
                                                Toast.LENGTH_LONG).show();
                                        finish();
                                        break;
                                    }
                                }
                            }
                        });
                        break;
                    }
                }
                for (Users user : usernames) {
                    if (user.getUsername().equals(username) && (user.getPassword().equals(password))) {
                        name = username;
                        Random rand = new Random();
                        resNum = rand.nextInt(100);
                        binding.confirm.setText("Customer Username: " + name + " \nBook Title: " + bookTitle +"\nReservation number: "+ resNum+ "\n\n Is the information correct?");
                        binding.yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String newTransaction = "PlaceHold: " +" Username:"+ username+" Resnum: "+resNum;
                                Transaction transaction = new Transaction(newTransaction);
                                db.transactions().addTransaction(transaction);
                                Toast.makeText(LoginActivity.this, "Hold placed",
                                        Toast.LENGTH_LONG).show();
                                db.books().delete(bookobj);
                                finish();
                            }
                        });
                    }}
                    }
        });
    }
}
