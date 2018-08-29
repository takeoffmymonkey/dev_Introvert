package com.galukhin.introvert.model.luna2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public static final String TAGS_TAG_COLUMN = "Tag";
    private static final String TAGS_NOTES_COLUMN = "Notes";

    /*TYPES_TABLE*/
    public static final String TYPES_TABLE = "Types";
    private static final String TYPES_TYPE_COLUMN = "Type";

    /*CATS_TABLE*/
    public static final String CATS_TABLE = "Cats";
    public static final String CATS_CAT_COLUMN = "Cat";

    /*SUBCATS_TABLE*/
    public static final String SUBCATS_TABLE_PT1 = "Subcats_";
    public static final String SUBCATS_SUBCAT_COLUMN = "Subcat";

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
        createTable(db, NOTES_TABLE, 0);
        createTable(db, TAGS_TABLE, 0);
        createTable(db, TYPES_TABLE, 0);
        createTable(db, CATS_TABLE, 0);
        createTable(db, SUBCATS_TABLE_PT1, 1);

        initContent(db, NOTES_TABLE);
        initContent(db, TAGS_TABLE);
        initContent(db, TYPES_TABLE);
        initContent(db, CATS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


    private void createTable(SQLiteDatabase db, String table, int index) {
        Log.i(TAG, "createTable");

        if (db == null) db = getWritableDatabase();
        if (isExisting(db, table)) return;

        Log.i(TAG, "Creating " + table + "table");

        String SQL_CREATE_TABLE = createTableCommand(table, index);

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
    private String createTableCommand(String table, int index) {
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
                                + TAGS_TAG_COLUMN + " TEXT UNIQUE, "
                                + TAGS_NOTES_COLUMN + " TEXT);";
                break;
            case TYPES_TABLE:
                SQL_CREATE_TABLE =
                        "CREATE TABLE " + TYPES_TABLE + " ("
                                + ID_COLUMN + " INTEGER PRIMARY KEY, "
                                + TYPES_TYPE_COLUMN + " TEXT UNIQUE);";
                break;
            case CATS_TABLE:
                SQL_CREATE_TABLE =
                        "CREATE TABLE " + CATS_TABLE + " ("
                                + ID_COLUMN + " INTEGER PRIMARY KEY, "
                                + CATS_CAT_COLUMN + " TEXT UNIQUE);";
                break;
            case SUBCATS_TABLE_PT1:
                SQL_CREATE_TABLE =
                        "CREATE TABLE " + SUBCATS_TABLE_PT1 + index + " ("
                                + ID_COLUMN + " INTEGER PRIMARY KEY, "
                                + SUBCATS_SUBCAT_COLUMN + " TEXT UNIQUE);";
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
                String[] cats = {"General", "Jokes", "Diary", "Tracks"};
                for (String cat : cats) {
                    ContentValues v = new ContentValues();
                    v.put(CATS_CAT_COLUMN, cat);
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


    public Collection<String> getColumnAsCollection(SQLiteDatabase db, String table,
                                                    String column, Collection<String> list) {
        Log.i(TAG, "getColumnAsCollection");

        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, table)) return null;

        Log.i(TAG, "Getting list from table " + table + " of column " + column);

        Cursor c = db.query(
                table, new String[]{column}, null, null, null,
                null, null);

        while (c.moveToNext()) {
            list.add(c.getString(c.getColumnIndex(column)));
        }

        return list;
    }


    public long insertRow(SQLiteDatabase db, String table, ContentValues values) {
        Log.i(TAG, "insertRow");

        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, table)) return 0;

        Log.i(TAG, "Inserting new row to table " + table);

        return db.insert(table, null, values);
    }


    private String subCatsTableName(int index) {
        return SUBCATS_TABLE_PT1 + index;
    }


    public String subCatsTableByCat(SQLiteDatabase db, String cat) {
        Log.i(TAG, "subCatsTableByCat");

        if (cat == null) return subCatsTableName(1);

        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, CATS_TABLE)) return null;

        Log.i(TAG, "Searching for the category " + cat);

        Cursor c = db.query(
                CATS_TABLE,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                CATS_CAT_COLUMN + "=?",              // The columns for the WHERE clause
                new String[]{cat},          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            return subCatsTableName(c.getInt(c.getColumnIndex(ID_COLUMN)));
        } else return null;
    }
}