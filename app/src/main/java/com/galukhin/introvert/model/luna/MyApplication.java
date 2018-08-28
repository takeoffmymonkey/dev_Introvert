package com.galukhin.introvert.model.luna;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyApplication extends Application {

    private static Context context;

//    DbHelper dbHelper;
    com.galukhin.introvert.model.luna2.db.DbHelper dbHelper2;
//    private static SQLiteDatabase db;
    private static SQLiteDatabase db2;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
//        dbHelper = new DbHelper(this);
        dbHelper2 = new com.galukhin.introvert.model.luna2.db.DbHelper(this);
//        db = dbHelper.getReadableDatabase();
        db2 = dbHelper2.getReadableDatabase();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

    public static SQLiteDatabase getDB() {
        return MyApplication.db2;
    }
}
