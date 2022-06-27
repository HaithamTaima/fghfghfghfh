package com.example.fainl_android.RoomDB.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fainl_android.RoomDB.Tables.UserInfo;


@Dao
public interface UserDao {

    @Insert
    void InsertUserInfo(UserInfo... userInfo);

    @Query("SELECT * FROM UserInfo WHERE email = (:email) and password = (:password)")
    UserInfo LoginInfo(String email, String password);

    @Query("SELECT * FROM UserInfo WHERE Email = (:email) and password = (:password)")
    boolean Login(String email, String password);

}
