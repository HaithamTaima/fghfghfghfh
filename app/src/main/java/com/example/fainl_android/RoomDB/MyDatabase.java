package com.example.fainl_android.RoomDB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fainl_android.RoomDB.DAO.ReviewDAO;
import com.example.fainl_android.RoomDB.DAO.UserDao;
import com.example.fainl_android.RoomDB.Tables.Review;
import com.example.fainl_android.RoomDB.Tables.UserInfo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Review.class, UserInfo.class}, version = 1, exportSchema = false)

public abstract class MyDatabase extends RoomDatabase {

    public abstract ReviewDAO reviewDAO();
    public abstract UserDao userDao();


    private static volatile MyDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 10;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "ReView")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // عند إنشاء وإستدعاء الDataBase لأول مرة
    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);


            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.


            });
        }
    };
}
