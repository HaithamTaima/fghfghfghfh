package com.example.fainl_android.RoomDB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fainl_android.Interface.UserInfoBoolean;
import com.example.fainl_android.Interface.UserInfoObject;
import com.example.fainl_android.RoomDB.Tables.Review;
import com.example.fainl_android.RoomDB.Tables.UserInfo;

import java.util.List;

public class MyViewModel extends AndroidViewModel {


    private Repository repository;
    public MyViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
    }

    public void Insert(Review... reviews){
        repository.Insert(reviews);
    }

    public void UpdateProducts(Review reviews){

        repository.UpdateProducts(reviews);
    }
    public LiveData<List<Review>> getAll(){
        return repository.getAll();
    }

    public LiveData<Review> getItem(int id){
        return repository.getItem(id);
    }

    public void DeleteProducts(int id){

        repository.DeleteProducts(id);
    }

    public void InsertUserInfo(UserInfo... userInfo){

        repository.InsertUserInfo(userInfo);
    }

    public void LoginInfo(String email, String password, UserInfoObject user){

        repository.LoginInfo(email,password,user);
    }

    public void Login(String email, String password, UserInfoBoolean infoBoolean){

        repository.Login(email,password,infoBoolean);
    }

}
