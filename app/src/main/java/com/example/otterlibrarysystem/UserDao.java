package com.example.otterlibrarysystem;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void addCustomers(Users customer);

    @Query("SELECT COUNT(*) FROM Credentials")
    int count();

    @Query("SELECT * FROM credentials")
    List<Users> getAll();
    @Query("SELECT * FROM credentials WHERE id = :id")
    Users findById(int id);
    @Delete
    void delete(Users customer);
}
