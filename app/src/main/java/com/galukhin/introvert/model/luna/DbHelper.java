package com.galukhin.introvert.model.luna;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static String TAG = "LUNA:" + "DbHelper";

    /*DATABASE*/
    private static final String DATABASE_NAME = "luna";
    private static final int DATABASE_VERSION = 1;

    /*NOTES_TABLE*/
    public static final String NOTES_TABLE = "Notes";
    public static final String NOTES_ID_COLUMN = BaseColumns._ID;
    public static final String NOTES_NAME_COLUMN = "Name";
    public static final String NOTES_CREATED_COLUMN = "Created";

    /*NOTE_N TABLE*/
    public static String NOTE_TABLE_PT1 = "Note_";
    private static final String NOTE_ID_COLUMN = BaseColumns._ID;
    private static final String NOTE_TYPE_COLUMN = "Type";
    private static final String NOTE_VALUE_COLUMN = "Content";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        .i(TAG, "DbHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        .i(TAG, "OnCreate");
        createNotesTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public boolean isExisting(SQLiteDatabase db, String table) {
        .i(TAG, "isExisting");
        .i(TAG, "Checking " + table + " table existence");

        if (db == null) db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name " +
                        "from sqlite_master where tbl_name = '" + table + "'",
                null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                .i(TAG, "Table exists");
                return true;
            }
            cursor.close();
        }
        .i(TAG, "Table doesn't exists");
        return false;
    }

    public void createNotesTable(SQLiteDatabase db) {
        .i(TAG, "createNotesTable");

        if (db == null) db = getWritableDatabase();
        if (isExisting(db, NOTES_TABLE)) return;

        .i(TAG, "Creating NOTES table");

        String SQL_CREATE_NOTES_TABLE =
                "CREATE TABLE " + NOTES_TABLE + " ("
                        + NOTES_ID_COLUMN + " INTEGER PRIMARY KEY, "
                        + NOTES_NAME_COLUMN + " TEXT, "
                        + NOTES_CREATED_COLUMN + " INTEGER);";

        db.execSQL(SQL_CREATE_NOTES_TABLE);
    }

    public void deleteNotes(SQLiteDatabase db) {
        .i(TAG, "deleteNotes");

        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, NOTES_TABLE)) return;

        int n = notesCount(db);

        for (int i = 0; i < n; i++) {
            deleteNoteTable(db, n);
        }

        String SQL_DELETE_NOTES_TABLE =
                "DROP TABLE IF EXISTS " + NOTES_TABLE;

        db.execSQL(SQL_DELETE_NOTES_TABLE);
    }

    public void deleteNoteTable(SQLiteDatabase db, long id) {
        .i(TAG, "deleteNoteTable");

        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, noteName(id))) return;

        String SQL_DELETE_NOTES_TABLE =
                "DROP TABLE IF EXISTS " + noteName(id);

        db.execSQL(SQL_DELETE_NOTES_TABLE);
    }

    public void addNote(SQLiteDatabase db, Note note) {
        .i(TAG, "addNoteToMainTable");

        if (db == null) db = getWritableDatabase();
        int id = notesCount(db) + 1;

        .i(TAG, "Adding NOTE_" + id + " table to NOTES table");

        ContentValues values = new ContentValues();
        values.put(NOTES_NAME_COLUMN, id);
        values.put(NOTES_CREATED_COLUMN, System.currentTimeMillis());

        long newRowId = db.insert(NOTES_TABLE, null, values);
        .i(TAG, "Inserted new row to NOTES table: " + newRowId);

        createNoteTable(null, note, id);
    }

    public int notesCount(SQLiteDatabase db) {
        .i(TAG, "notesCount");
        if (db == null) db = getWritableDatabase();

        .i(TAG, "Counting notes in NOTES table");
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
        return n;
    }

    public void createNoteTable(SQLiteDatabase db, Note note, long id) {
        .i(TAG, "createNoteTable");

        if (db == null) db = getWritableDatabase();
        if (isExisting(db, noteName(id))) return;

        .i(TAG, "Creating " + noteName(id) + " table");

        String SQL_CREATE_NOTE_TABLE =
                "CREATE TABLE " + noteName(id) + " ("
                        + NOTE_ID_COLUMN + " INTEGER PRIMARY KEY, "
                        + NOTE_TYPE_COLUMN + " TEXT, "
                        + NOTE_VALUE_COLUMN + " TEXT);";
        db.execSQL(SQL_CREATE_NOTE_TABLE);

        addNoteFields(null, id, note.getFields());
    }

    private void addNoteFields(SQLiteDatabase db, long id, List<Field> fields) {
        .i(TAG, "addNoteFields");

        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, noteName(id))) return;

        .i(TAG, "Adding note fields...");

        ContentValues values;
        for (Field field : fields) {
            values = new ContentValues();
            values.put(NOTE_TYPE_COLUMN, field.getType().name());
            values.put(NOTE_VALUE_COLUMN, field.getValue().toString());
            long newRowId = db.insert(noteName(id), null, values);
            .i(TAG, "Inserted field to row " + newRowId);
        }
    }

    public List<Field> getNoteFields(SQLiteDatabase db, long id, Context context) {
        .i(TAG, "getNoteFields");

        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, noteName(id))) return null;

        String[] projection = {NOTE_TYPE_COLUMN, NOTE_VALUE_COLUMN};

        Cursor c = db.query(
                noteName(id),   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        List<Field> fields = new ArrayList<>();
        Types t;
        String v;

        // TODO: 009 09 Aug 18 hardcoded!
        while (c.moveToNext()) {
            t = Types.valueOf(c.getString(c.getColumnIndex(NOTE_TYPE_COLUMN)));
            v = c.getString(c.getColumnIndex(NOTE_VALUE_COLUMN));
            fields.add(Fields.createEditTextField(v, context));
        }

        c.close();
        return fields;
    }

    public String noteName(long id) {
        .i(TAG, "noteName");

        return NOTE_TABLE_PT1 + id;
    }

    public Cursor createNotesCursor(SQLiteDatabase db) {
        .i(TAG, "createNotesCursor");

        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, NOTES_TABLE)) return null;

        String[] projection = {NOTES_ID_COLUMN,
                NOTES_NAME_COLUMN,
                NOTES_CREATED_COLUMN};

        return db.query(
                NOTES_TABLE,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
    }

    /* ~~~~~~~ DUMPING METHODS ~~~~~~~ */
    public void dumpNotesTable(SQLiteDatabase db) {
        .i(TAG, "dumpNotesTable");

        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, NOTES_TABLE)) return;

        .i(TAG, "Performing NOTES table dump");
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
    }

    public void dumpNoteTable(SQLiteDatabase db, long id) {
        Log.i(TAG, "dumpNoteTable");

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
            Log.i(TAG, "TYPE: " + c.getString(c.getColumnIndex(NOTE_TYPE_COLUMN)));
            Log.i(TAG, "--------------------------------------------------------");
            Log.i(TAG, "VALUE: " + c.getString(c.getColumnIndex(NOTE_VALUE_COLUMN)));
            Log.i(TAG, "--------------------------------------------------------");
            c.moveToNext();
            Log.i(TAG, "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        Log.e(TAG, "========================================================");
        c.close();
    }
}