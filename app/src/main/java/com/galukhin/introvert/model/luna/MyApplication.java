package com.galukhin.introvert.model.luna;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class MyApplication extends Application {

    private static Context context;

    DbHelper dbHelper;
    private static SQLiteDatabase db;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
        dbHelper = new DbHelper(this);
        db = dbHelper.getReadableDatabase();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

    public static SQLiteDatabase getDB() {
        return MyApplication.db;
    }
}
