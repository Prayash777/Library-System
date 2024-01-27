package com.example.otterlibrarysystem;

import static android.app.PendingIntent.getActivity;
import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.otterlibrarysystem.databinding.ActivityCreateBinding;
import com.example.otterlibrarysystem.databinding.ActivityHoldBinding;
import com.example.otterlibrarysystem.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Random;

public class HoldActivity extends AppCompatActivity {
    private ActivityHoldBinding binding;
    private LibraryDatabase db;
    private List<Books> booksList;
    private ListView booksListView;
    private ArrayAdapter<Books> bookAdapter;
    private List<Users> usernames;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        binding = ActivityHoldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = LibraryDatabase.getInstance(this);
        if(db.books().count() == 0){
            binding.nobook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.nobook.setText("Exit");
                    finish();
                }
            });
        }

            booksListView = binding.bookList;
            booksList = db.books().getAll();
        bookAdapter = new ArrayAdapter<>(this, R.layout.item_book,
                    R.id.book_item, booksList);
            booksListView.setAdapter(bookAdapter);
            booksListView.setOnItemClickListener((parent, view, position, id) -> {
                Books selectedBook = (Books) booksListView.getItemAtPosition(position);
                String book = selectedBook.getBookTitle();
                Toast.makeText(HoldActivity.this, "SELECTED Book:"+book,
                        Toast.LENGTH_LONG).show();
                binding.login.setOnClickListener(v -> {
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.putExtra("book", book);
                    intent.putExtra("bookobj",selectedBook);
                    startActivity(intent);
                    finish();
                });
            });
//            binding.computerButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    booksListView.setOnItemClickListener((parent, view, position, id) -> {
//                        Books selectedBook = (Books) booksListView.
//                    });
//                }
//
//                });
    }
}

