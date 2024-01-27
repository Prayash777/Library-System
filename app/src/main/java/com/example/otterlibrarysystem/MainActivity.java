package com.example.otterlibrarysystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrarysystem.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private LibraryDatabase db;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LibraryDatabase.getInstance(this);
        db.populateInitialData();
        binding.createButton.setOnClickListener(v -> startActivity(new Intent(this,CreateActivity.class)));
        binding.holdButton.setOnClickListener(v -> startActivity(new Intent(this,HoldActivity.class)));
        binding.manageButton.setOnClickListener(v -> startActivity(new Intent(this,ManageActivity.class)));
    }
}
