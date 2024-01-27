package com.example.otterlibrarysystem;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BooksDao {
    @Insert
    void addBooks(Books books);

    @Query("SELECT COUNT(*) FROM books")
    int count();

    @Query("SELECT * FROM books")
    List<Books> getAll();
    @Query("SELECT * FROM books WHERE id = :id")
    Books findById(int id);
    //@Query("SELECT * FROM books WHERE genre = Genre ")
    //Books findByGenre(String genre);
    @Delete
    void delete(Books books);
}