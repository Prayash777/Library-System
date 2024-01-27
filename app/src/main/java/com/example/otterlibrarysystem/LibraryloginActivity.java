package com.example.otterlibrarysystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrarysystem.databinding.ActivityLibraryloginBinding;
import com.example.otterlibrarysystem.databinding.ActivityMainBinding;

import java.util.List;

public class LibraryloginActivity extends AppCompatActivity {
    private ActivityLibraryloginBinding binding;
    private LibraryDatabase db;
    List<Books> books;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLibraryloginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=LibraryDatabase.getInstance(this);
        binding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookTitle = binding.bookTitle.getText().toString();
                String author = binding.author.getText().toString();
                String genre = binding.genre.getText().toString();

                binding.bookInfo.setText("Book title: "+bookTitle+"\nAuthor: "+author+"\nGenre: "+genre+"\nIs the info correct");
                binding.no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                binding.yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Books b = new Books(bookTitle,author,genre);
                        Toast.makeText(LibraryloginActivity.this, "Book added",
                                Toast.LENGTH_LONG).show();
                        db.books().addBooks(b);
                        finish();
                    }
                });
            }
        });
    }
}
