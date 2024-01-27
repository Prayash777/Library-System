package com.example.otterlibrarysystem;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void addTransaction(Transaction transactions);
    @Query("SELECT COUNT(*) FROM transactions")
    int count();
    @Query("SELECT * FROM transactions")
    List<Transaction> getAll();

    @Delete
    void delete(Transaction transaction);
}
