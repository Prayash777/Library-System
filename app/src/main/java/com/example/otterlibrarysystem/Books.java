package com.example.otterlibrarysystem;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Books")
public class Books implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Book Title")
    private String bookTitle;

    @ColumnInfo(name = "Genre")
    private String author;

    @ColumnInfo(name = "topic")
    private String genre;


    public Books(String bookTitle, String author, String genre) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
public String toString(){
        return bookTitle + " "+ author +" "+ genre;
}
}
