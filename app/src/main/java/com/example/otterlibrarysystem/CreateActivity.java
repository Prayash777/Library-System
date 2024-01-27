package com.example.otterlibrarysystem;

import static android.app.PendingIntent.getActivity;
import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrarysystem.databinding.ActivityCreateBinding;
import com.example.otterlibrarysystem.databinding.ActivityMainBinding;

import java.util.List;


public class CreateActivity extends AppCompatActivity {
    private ActivityCreateBinding binding;
    private LibraryDatabase db;
    private List<Users> usernames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LibraryDatabase.getInstance(this);
        usernames = db.customers().getAll();
        binding.createButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = String.valueOf(binding.qUsername.getText());
                String password = String.valueOf(binding.qPassword.getText());
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(CreateActivity.this, "Username and password blank. Try again",
                            Toast.LENGTH_LONG).show();
                    binding.createButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String username = String.valueOf(binding.qUsername.getText());
                            String password = String.valueOf(binding.qPassword.getText());
                            if (username.isEmpty() || password.isEmpty()) {
                                Toast.makeText(CreateActivity.this, "Error again blank going to main",
                                        Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                    });
                }
                String sameName = " ";
//                boolean status = false;
//                for (Users user : usernames) {
//                    if (user.getUsername().equals(username)) {
//                        sameName = username;
//                        Toast.makeText(CreateActivity.this, "Account already exists with that username. Try again",
//                                Toast.LENGTH_LONG).show();
//                        status = true;
//                        break;
//                    }
//                }
//                if (status) {
//                    binding.createButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            String username = String.valueOf(binding.qUsername.getText());
//                            String password = String.valueOf(binding.qPassword.getText());
//                            for (Users u : usernames) {
//                                if (u.getUsername().equals(username)) {
//                                    Toast.makeText(CreateActivity.this, "Error again same username, exiting to main",
//                                            Toast.LENGTH_LONG).show();
//                                    finish();
//                                }
//                            }
//                        }
//                    });
//                }
                for(Users user: usernames){
                    if(user.getUsername().equals(username)) {
                        sameName = user.getUsername();
                        Toast.makeText(CreateActivity.this, "Account already exists with that username. Try again",
                                Toast.LENGTH_LONG).show();
                        binding.createButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String user = String.valueOf(binding.qUsername.getText());
                                String pass = String.valueOf(binding.qPassword.getText());
                                for (Users u : usernames) {
                                    if (u.getUsername().equals(user)) {
                                        Toast.makeText(CreateActivity.this, "Error, again same username",
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
                if (!(username.equals(sameName))) {
                    if (!((username.isEmpty()) || (password.isEmpty()))) {
                        if (!(username.equals("!admin2"))) {
                            Users user1 = new Users(username, password);
                            db.customers().addCustomers(user1);
                            Toast.makeText(CreateActivity.this, "Account Creation Successful",
                                    Toast.LENGTH_LONG).show();
                            Transaction transaction = new Transaction("Transaction type: New Account\nCustomer's username: " + username);
                            db.transactions().addTransaction(transaction);
                            finish();
                        } else{
                            Toast.makeText(CreateActivity.this, "can't have !admin2 username",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }
}