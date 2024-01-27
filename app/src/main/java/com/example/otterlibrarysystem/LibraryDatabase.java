package com.example.otterlibrarysystem;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Books.class, Users.class, Transaction.class}, version = 11, exportSchema = false)

public abstract class LibraryDatabase extends RoomDatabase {
    public abstract BooksDao books();
    public abstract UserDao customers();
    public abstract TransactionDao transactions();
    private static LibraryDatabase sInstance;
    public static synchronized LibraryDatabase getInstance(Context context){
        if(sInstance == null) {
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            LibraryDatabase.class,"library.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return sInstance;
    }
    public void populateInitialData(){
        if(books().count() == 0){
            runInTransaction(() -> {
                books().addBooks(new Books("A Heartbreaking Work of Staggering Genius", "Dave Eggers", "Memoir"));
                books().addBooks(new Books("The IDA Pro Book", "Chris Eagle","Computer Science"));
                books().addBooks(new Books("Frankenstein","Mary Shelley","fiction"));
            });
        }
        if(customers().count() == 0){
            runInTransaction(() -> {
                customers().addCustomers(new Users("hShuard","m@thl3t3"));
                customers().addCustomers(new Users("bMishra","bioN@no"));
                customers().addCustomers(new Users("shirleyBee","Carmel2Chicago"));
            });
        }
    }
}
