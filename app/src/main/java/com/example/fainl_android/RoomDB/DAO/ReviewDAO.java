package com.example.fainl_android.RoomDB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fainl_android.RoomDB.Tables.Review;

import java.util.List;

@Dao
public interface ReviewDAO {

    @Insert
    void InsertProducts(Review... reviews);

    @Update
    void UpdateProducts(Review... reviews);

    @Query("SELECT * FROM Review ORDER BY  Name ASC ")
    LiveData<List<Review>> getAll();

    @Query("SELECT * FROM Review Where Id=:id ")
    LiveData<Review> getItem(int id);

    @Query("DELETE From Review Where Id=:id")
    void DeleteProducts(int id);


}
