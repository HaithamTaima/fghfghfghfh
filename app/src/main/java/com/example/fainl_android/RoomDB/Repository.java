package com.example.fainl_android.RoomDB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.fainl_android.Interface.UserInfoBoolean;
import com.example.fainl_android.Interface.UserInfoObject;
import com.example.fainl_android.RoomDB.DAO.ReviewDAO;
import com.example.fainl_android.RoomDB.DAO.UserDao;
import com.example.fainl_android.RoomDB.Tables.Review;
import com.example.fainl_android.RoomDB.Tables.UserInfo;

import java.util.List;

public class Repository {

    private ReviewDAO reviewDAO;
    private UserDao userDao;

    public Repository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        reviewDAO=db.reviewDAO();
        userDao=db.userDao();
    }

    public void Insert(Review... reviews){
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                reviewDAO.InsertProducts(reviews);
            }
        });
    }
    public void UpdateProducts(Review reviews){
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                reviewDAO.UpdateProducts(reviews);
            }
        });
    }
    public LiveData<List<Review>> getAll(){
        return reviewDAO.getAll();
    }
    public LiveData<Review> getItem(int id){
        return reviewDAO.getItem(id);
    }

    public void DeleteProducts(int id){
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                reviewDAO.DeleteProducts(id);
            }
        });
    }

    public void InsertUserInfo(UserInfo... userInfo) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.InsertUserInfo(userInfo);
            }
        });
    }

    public void LoginInfo(String email, String password, UserInfoObject user) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                UserInfo u = userDao.LoginInfo(email,password);
                user.UserObject(u);
            }
        });
    }

    public void Login(String email, String password, UserInfoBoolean infoBoolean) {
        MyDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                boolean b = userDao.Login(email,password);
                infoBoolean.UserBoolean(b);
            }
        });
    }



}
