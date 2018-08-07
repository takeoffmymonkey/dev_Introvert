package com.galukhin.introvert.model.luna;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static String TAG = "LUNA:" + "DbHelper";

    /*DATABASE*/
    private static final String DATABASE_NAME = "luna";
    private static final int DATABASE_VERSION = 1;

    /*NOTES_TABLE*/
    private static final String NOTES_TABLE = "Notes";
    private static final String NOTES_ID_COLUMN = BaseColumns._ID;
    private static final String NOTES_NAME_COLUMN = "Name";
    private static final String NOTES_CREATED_COLUMN = "Created";

    /*NOTE_N TABLE*/
    public static String NOTE_TABLE_PT1 = "Note_";
    private static final String NOTE_ID_COLUMN = BaseColumns._ID;
    private static final String NOTE_TYPE_COLUMN = "Type";
    private static final String NOTE_VALUE_COLUMN = "Content";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createNotesTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean isExisting(SQLiteDatabase db, String table) {
        Log.i(TAG, "Checking NOTES table existence");

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
//        db.close();
        return false;
    }

    public void createNotesTable(SQLiteDatabase db) {
        if (db == null) db = getWritableDatabase();
        if (isExisting(db, NOTES_TABLE)) return;

        Log.i(TAG, "Creating NOTES table");

        String SQL_CREATE_NOTES_TABLE =
                "CREATE TABLE " + NOTES_TABLE + " ("
                        + NOTES_ID_COLUMN + " INTEGER PRIMARY KEY, "
                        + NOTES_NAME_COLUMN + " TEXT, "
                        + NOTES_CREATED_COLUMN + " INTEGER);";

        db.execSQL(SQL_CREATE_NOTES_TABLE);
//        db.close();
    }

    public void deleteNotesTable(SQLiteDatabase db) {
        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, NOTES_TABLE)) return;

        Log.i(TAG, "Deleting NOTES table");

        String SQL_DELETE_NOTES_TABLE =
                "DROP TABLE IF EXISTS " + NOTES_TABLE;

        db.execSQL(SQL_DELETE_NOTES_TABLE);
//        db.close();
    }

    public void addNote(SQLiteDatabase db, Note note) {
        if (db == null) db = getWritableDatabase();
        int id = notesCount(db) + 1;

        Log.i(TAG, "Adding NOTE_" + id + " table to NOTES table");

        ContentValues values = new ContentValues();
        values.put(NOTES_NAME_COLUMN, id);
        values.put(NOTES_CREATED_COLUMN, System.currentTimeMillis());

        long newRowId = db.insert(NOTES_TABLE, null, values);
        Log.i(TAG, "Inserted new row to NOTES table: " + newRowId);
//        db.close();

        createNoteTable(null, note, id);
    }

    public void dumpNotesTable(SQLiteDatabase db) {
        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, NOTES_TABLE)) return;

        Log.i(TAG, "Performing NOTES table dump");
        Cursor c = db.query(
                NOTES_TABLE, null, null, null,
                null, null, null);
        Log.e(TAG, "========================================================");
        Log.i(TAG, "NOTES TABLE");
        Log.i(TAG, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            Log.i(TAG, "ID: " + c.getInt(c.getColumnIndex(NOTES_ID_COLUMN)));
            Log.i(TAG, "--------------------------------------------------------");
            Log.i(TAG, "NAME: " + c.getInt(c.getColumnIndex(NOTES_NAME_COLUMN)));
            Log.i(TAG, "--------------------------------------------------------");
            Log.i(TAG, "MODIFIED: " + c.getLong(c.getColumnIndex(NOTES_CREATED_COLUMN)));
            Log.i(TAG, "--------------------------------------------------------");
            c.moveToNext();
            Log.i(TAG, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        Log.e(TAG, "========================================================");
        c.close();
//        db.close();
    }


    private int notesCount(SQLiteDatabase db) {
        if (db == null) db = getWritableDatabase();

        Log.i(TAG, "Counting notes in NOTES table");
        int n = -1;

        String[] projection = {NOTES_ID_COLUMN};

        Cursor c = db.query(
                NOTES_TABLE,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        n = c.getCount();
        c.close();
//        db.close();
        return n;
    }


    public void createNoteTable(SQLiteDatabase db, Note note, int id) {
        if (db == null) db = getWritableDatabase();
        if (isExisting(db, noteName(id))) return;

        Log.i(TAG, "Creating NOTE_" + id + " table");

        String SQL_CREATE_NOTE_TABLE =
                "CREATE TABLE " + noteName(id) + " ("
                        + NOTE_ID_COLUMN + " INTEGER PRIMARY KEY, "
                        + NOTE_TYPE_COLUMN + " TEXT, "
                        + NOTE_VALUE_COLUMN + " TEXT);";
        db.execSQL(SQL_CREATE_NOTE_TABLE);
//        db.close();

        addNoteFields(null, id, note.getFields());
    }

    private void addNoteFields(SQLiteDatabase db, int id, List<Field> fields) {
        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, noteName(id))) return;

        Log.i(TAG, "Adding note fields...");

        ContentValues values;
        for (Field field : fields) {
            values = new ContentValues();
            values.put(NOTE_TYPE_COLUMN, field.getType().name());
            values.put(NOTE_VALUE_COLUMN, field.getValue().toString());
            long newRowId = db.insert(noteName(id), null, values);
            Log.i(TAG, "Inserted field to row " + newRowId);
        }

//        db.close();
    }

    public String noteName(int id) {
        return NOTE_TABLE_PT1 + id;
    }

    public void dumpNoteTable(SQLiteDatabase db, int id) {
        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, noteName(id))) return;

        if (id == 0) id = notesCount(db);

        Log.i(TAG, "Performing " + noteName(id) + " table dump");
        Cursor c = db.query(
                noteName(id), null, null, null,
                null, null, null);
        Log.e(TAG, "========================================================");
        Log.i(TAG, "NOTE_" + id + " TABLE");
        Log.i(TAG, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            Log.i(TAG, "ID: " + c.getInt(c.getColumnIndex(NOTE_ID_COLUMN)));
            Log.i(TAG, "--------------------------------------------------------");
            Log.i(TAG, "TYPE: " + c.getInt(c.getColumnIndex(NOTE_TYPE_COLUMN)));
            Log.i(TAG, "--------------------------------------------------------");
            Log.i(TAG, "VALUE: " + c.getInt(c.getColumnIndex(NOTE_VALUE_COLUMN)));
            Log.i(TAG, "--------------------------------------------------------");
            c.moveToNext();
            Log.i(TAG, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        Log.e(TAG, "========================================================");
        c.close();
//        db.close();
    }
}