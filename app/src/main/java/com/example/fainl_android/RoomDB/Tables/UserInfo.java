package com.example.fainl_android.RoomDB.Tables;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class UserInfo {

    @PrimaryKey
    @NonNull
    private String Email;
    @ColumnInfo(name = "Name")
    private String Name;
    @ColumnInfo(name = "Address")
    private String address;
    private String password;


    public UserInfo(@NonNull String email, String name, String address, String password) {
        this.Email = email;
        Name = name;
        this.address = address;
        this.password = password;
    }

    public UserInfo() {
    }

    @NonNull
    public String getEmail() {
        return Email;
    }

    public void setEmail(@NonNull String email) {
        this.Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
