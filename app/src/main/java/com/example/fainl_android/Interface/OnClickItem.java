package com.example.fainl_android.Interface;

import com.example.fainl_android.RoomDB.Tables.Review;

public interface OnClickItem {

    void OnClick(Review review);
    void onDelete(Review review);
}
