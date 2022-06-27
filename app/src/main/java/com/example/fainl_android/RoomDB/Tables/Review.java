package com.example.fainl_android.RoomDB.Tables;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.fainl_android.RoomDB.DateConverter;

import org.jetbrains.annotations.NotNull;

@Entity
@TypeConverters(DateConverter.class)
public class Review {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    @NotNull
    private String NameBook;
    @NotNull
    private String Name;
    @NotNull
    private String Date;
    @NotNull
    private String Category;


    public Review(@NotNull String nameBook, @NotNull String name, @NotNull String date, @NotNull String category) {
        NameBook = nameBook;
        Name = name;
        Date = date;
        Category = category;
    }

    public Review() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @NotNull
    public String getNameBook() {
        return NameBook;
    }

    public void setNameBook(@NotNull String nameBook) {
        NameBook = nameBook;
    }

    @NotNull
    public String getName() {
        return Name;
    }

    public void setName(@NotNull String name) {
        Name = name;
    }

    @NotNull
    public String getDate() {
        return Date;
    }

    public void setDate(@NotNull String date) {
        Date = date;
    }

    @NotNull
    public String getCategory() {
        return Category;
    }

    public void setCategory(@NotNull String category) {
        Category = category;
    }
}
