package com.galukhin.introvert.model.luna2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbHelper extends SQLiteOpenHelper {
    private static String TAG = "LUNA:" + "DbHelper2";
    private static final String ID_COLUMN = BaseColumns._ID;

    /*DATABASE*/
    private static final String DATABASE_NAME = "luna";
    private static final int DATABASE_VERSION = 1;

    /*NOTES_TABLE*/
    public static final String NOTES_TABLE = "Notes";
    private static final String NOTES_NAME_COLUMN = "Name";
    private static final String NOTES_CREATED_COLUMN = "Created";
    private static final String NOTES_EDITED_COLUMN = "Edited";
    private static final String NOTES_CAT_COLUMN = "Cat";
    private static final String NOTES_SUBCAT_COLUMN = "Subcat";

    /*TAGS_TABLE*/
    public static final String TAGS_TABLE = "Tags";
    private static final String TAGS_TAG_COLUMN = "Tag";
    private static final String TAGS_NOTES_COLUMN = "Notes";

    /*TYPES_TABLE*/
    public static final String TYPES_TABLE = "Types";
    private static final String TYPES_TYPE_COLUMN = "Type";

    /*CATS_TABLE*/
    public static final String CATS_TABLE = "Cats";
    private static final String CATS_CAT_COLUMN = "Cat";
    private static final String CATS_SUBCATS_COLUMN = "Subcats";

    /*NOTE_ TABLE*/
    public static String NOTE_TABLE_PT1 = "Note_";
    private static final String NOTE_TYPE_COLUMN = "Type";
    private static final String NOTE_VALUE_COLUMN = "Value";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "OnCreate");
        createTable(db, NOTES_TABLE);
        createTable(db, TAGS_TABLE);
        createTable(db, TYPES_TABLE);
        createTable(db, CATS_TABLE);

        initContent(db, NOTES_TABLE);
        initContent(db, TAGS_TABLE);
        initContent(db, TYPES_TABLE);
        initContent(db, CATS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


    private void createTable(SQLiteDatabase db, String table) {
        Log.i(TAG, "createTable");

        if (db == null) db = getWritableDatabase();
        if (isExisting(db, table)) return;

        Log.i(TAG, "Creating " + table + "table");

        String SQL_CREATE_TABLE = createTableCommand(table);

        db.execSQL(SQL_CREATE_TABLE);
    }


    private void initContent(SQLiteDatabase db, String table) {
        Log.i(TAG, "initContent");

        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, table)) return;

        Log.i(TAG, "Initializing " + table + "table's content");

        List<ContentValues> values = getInitValues(table);

        for (ContentValues value : values) {
            db.insert(table, null, value);
        }
    }


    /* ~~~~~~~ UTILITY METHODS ~~~~~~~ */
    private String createTableCommand(String table) {
        Log.i(TAG, "createTableCommand");
        String SQL_CREATE_TABLE = null;

        switch (table) {
            case NOTES_TABLE:
                SQL_CREATE_TABLE =
                        "CREATE TABLE " + NOTES_TABLE + " ("
                                + ID_COLUMN + " INTEGER PRIMARY KEY, "
                                + NOTES_NAME_COLUMN + " TEXT, "
                                + NOTES_CREATED_COLUMN + " INTEGER, "
                                + NOTES_EDITED_COLUMN + " INTEGER, "
                                + NOTES_CAT_COLUMN + " INTEGER, "
                                + NOTES_SUBCAT_COLUMN + " INTEGER);";
                break;
            case TAGS_TABLE:
                SQL_CREATE_TABLE =
                        "CREATE TABLE " + TAGS_TABLE + " ("
                                + ID_COLUMN + " INTEGER PRIMARY KEY, "
                                + TAGS_TAG_COLUMN + " TEXT, "
                                + TAGS_NOTES_COLUMN + " TEXT);";
                break;
            case TYPES_TABLE:
                SQL_CREATE_TABLE =
                        "CREATE TABLE " + TYPES_TABLE + " ("
                                + ID_COLUMN + " INTEGER PRIMARY KEY, "
                                + TYPES_TYPE_COLUMN + " TEXT);";
                break;
            case CATS_TABLE:
                SQL_CREATE_TABLE =
                        "CREATE TABLE " + CATS_TABLE + " ("
                                + ID_COLUMN + " INTEGER PRIMARY KEY, "
                                + CATS_CAT_COLUMN + " TEXT, "
                                + CATS_SUBCATS_COLUMN + " TEXT);";
                break;
        }

        return SQL_CREATE_TABLE;
    }


    private List<ContentValues> getInitValues(String table) {
        List<ContentValues> values = new ArrayList<>();

        switch (table) {
            case NOTES_TABLE:
                ContentValues value = new ContentValues();
                value.put(NOTES_NAME_COLUMN, "Записка1");
                value.put(NOTES_CREATED_COLUMN, "1111111111");
                value.put(NOTES_EDITED_COLUMN, "2222222222");
                value.put(NOTES_CAT_COLUMN, "1");
                value.put(NOTES_SUBCAT_COLUMN, "11");
                values.add(value);
                break;
            case TAGS_TABLE:
                String[] tags = {"Хуита", "Норм", "Заебок"};
                for (String tag : tags) {
                    ContentValues v = new ContentValues();
                    v.put(TAGS_TAG_COLUMN, tag);
                    values.add(v);
                }
                break;
            case TYPES_TABLE:
                String[] types = {"Data", "TextData", "TextListData"};
                for (String type : types) {
                    ContentValues v = new ContentValues();
                    v.put(TYPES_TYPE_COLUMN, type);
                    values.add(v);
                }
                break;
            case CATS_TABLE:
                Map<String, String> map = new HashMap<>();
                map.put("Шутки", "Туалетные, Черные");
                map.put("Дневник", "");
                map.put("Покупки", "Продукты, Одежда, Лекарства");
                for (String s : map.keySet()) {
                    ContentValues v = new ContentValues();
                    v.put(CATS_CAT_COLUMN, s);
                    v.put(CATS_SUBCATS_COLUMN, map.get(s));
                    values.add(v);
                }
                break;
        }
        return values;
    }


    private boolean isExisting(SQLiteDatabase db, String table) {
        Log.i(TAG, "isExisting");
        Log.i(TAG, "Checking " + table + " table existence");

        if (db == null) db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name " +
                        "from sqlite_master where tbl_name = '" + table + "'",
                null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                Log.i(TAG, "Table exists");
                return true;
            }
            cursor.close();
        }
        Log.i(TAG, "Table doesn't exists");
        return false;
    }


    public void dumpTable(SQLiteDatabase db, String table) {
        Log.i(TAG, "dumpTable");

        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, table)) return;

        Log.i(TAG, "Performing " + table + " table dump");

        Cursor c = db.query(
                table, null, null, null,
                null, null, null);
        String[] columns = c.getColumnNames();
        Log.i(TAG, "========================================================");
        Log.i(TAG, table + " table");
        Log.i(TAG, "Number of rows: " + c.getCount());
        Log.i(TAG, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) { // Moving through rows

            for (String column : columns) { // Moving through columns
                Log.i(TAG, column + ": " + c.getString(c.getColumnIndex(column)));
            }

            Log.i(TAG, "---------------------------------");
            c.moveToNext();
        }

        Log.i(TAG, "========================================================");
        c.close();
    }
}