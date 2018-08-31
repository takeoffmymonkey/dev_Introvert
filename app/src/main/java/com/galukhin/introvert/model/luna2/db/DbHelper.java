package com.galukhin.introvert.model.luna2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.galukhin.introvert.model.luna2.Note;
import com.galukhin.introvert.model.luna2.data.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbHelper extends SQLiteOpenHelper {
    // TODO: 031 31 Aug 18 check cursors closure

    private SQLiteDatabase db;

    private static String TAG = "LUNA:" + "DbHelper2";

    private static final String ID_COLUMN = BaseColumns._ID;

    /*DATABASE*/
    private static final String DATABASE_NAME = "luna";
    private static final int DATABASE_VERSION = 1;

    /*NOTES/TEMPLATES TABLES*/
    public static final String NOTES_TABLE = "Notes";
    public static final String TEMPLATES_TABLE = "Templates";
    private static final String NOTES_NAME_COLUMN = "Name";
    private static final String NOTES_CREATED_COLUMN = "Created";
    private static final String NOTES_EDITED_COLUMN = "Edited";
    private static final String NOTES_CAT_COLUMN = "Cat";
    private static final String NOTES_SUBCAT_COLUMN = "Subcat";
    private static final String NOTES_TAGS_COLUMN = "Tags";

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

    /*NOTE_/TEMPLATE_ TABLE*/
    public static String NOTE_TABLE_PT1 = "Note_";
    public static String TEMPLATE_TABLE_PT1 = "Template_";
    private static final String NOTE_TYPE_COLUMN = "Type";
    private static final String NOTE_VALUE_COLUMN = "Value";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "OnCreate");
        createTable(NOTES_TABLE);
        createTable(TEMPLATES_TABLE);
        createTable(TAGS_TABLE);
        createTable(TYPES_TABLE);
        createTable(CATS_TABLE);
        createTable(SUBCATS_TABLE_PT1, 1);

        initContent(NOTES_TABLE);
        initContent(TAGS_TABLE);
        initContent(TYPES_TABLE);
        initContent(CATS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    private void createTable(String table) {
        createTable(table, 0);
    }


    private void createTable(String table, int index) {
        Log.i(TAG, "createTable");
        if (!ensureReadiness(table, false)) return;

        String SQL_CREATE_TABLE = createTableCommand(table, index);
        db.execSQL(SQL_CREATE_TABLE);
    }


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
                                + NOTES_SUBCAT_COLUMN + " INTEGER, "
                                + NOTES_TAGS_COLUMN + " TEXT);";
                break;
            case TEMPLATES_TABLE:
                SQL_CREATE_TABLE =
                        "CREATE TABLE " + TEMPLATES_TABLE + " ("
                                + ID_COLUMN + " INTEGER PRIMARY KEY, "
                                + NOTES_NAME_COLUMN + " TEXT, "
                                + NOTES_CREATED_COLUMN + " INTEGER, "
                                + NOTES_EDITED_COLUMN + " INTEGER, "
                                + NOTES_CAT_COLUMN + " INTEGER, "
                                + NOTES_SUBCAT_COLUMN + " INTEGER, "
                                + NOTES_TAGS_COLUMN + " TEXT);";
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


    private void initContent(String table) {
        Log.i(TAG, "initContent");
        if (!ensureReadiness(table, true)) return;

        insertRows(table, getInitValues(table));
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
                value.put(NOTES_SUBCAT_COLUMN, "1");
                values.add(value);
                break;
            case TEMPLATES_TABLE:
                ContentValues val = new ContentValues();
                val.put(NOTES_NAME_COLUMN, "Записка1");
                val.put(NOTES_CREATED_COLUMN, "33333333333");
                val.put(NOTES_EDITED_COLUMN, "44444444444");
                val.put(NOTES_CAT_COLUMN, "2");
                val.put(NOTES_SUBCAT_COLUMN, "2");
                values.add(val);
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


    /* ~~~~~~~~~~~~~~~~~~~~~ UTILITY METHODS ~~~~~~~~~~~~~~~~~~~~~ */
    /* ~~~~~~~CHECKER METHODS~~~~~~~ */
    private boolean ensureDbReadiness() { //
        return ensureReadiness(null, false);
    }


    /**
     * Ensures that db is ready, and specified table satisfies specified existence
     * condition
     */
    private boolean ensureReadiness(String table, boolean mustExist) {
        Log.i(TAG, "ensureReadiness");
        if (db == null) db = getWritableDatabase();

        if (table != null) {
            if (mustExist) {
                return isExisting(table);
            } else return !isExisting(table);
        }

        return true;
    }


    private boolean isExisting(String table) {
        Log.i(TAG, "isExisting");
        ensureDbReadiness();

        try (Cursor cursor = db.rawQuery("select DISTINCT tbl_name " +
                "from sqlite_master where tbl_name = '" + table + "'", null)) {
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public void dumpTable(String table) {
        Log.i(TAG, "dumpTable");
        if (!ensureReadiness(table, true)) return;

        try (Cursor c = db.query(table, null, null, null,
                null, null, null)) {
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
        }
    }


    /* ~~~~~~~INSERT METHODS~~~~~~~ */
    public boolean insertRow(String table, ContentValues values) {
        List<ContentValues> contentValues = new ArrayList<>();
        contentValues.add(values);
        return insertRows(table, contentValues);
    }


    public boolean insertRows(String table, List<ContentValues> values) {
        Log.i(TAG, "insertRows");
        if (!ensureReadiness(table, true)) return false;

        db.beginTransaction();
        try {
            for (ContentValues value : values) {
                db.insert(table, null, value);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return true;
    }


    /* ~~~~~~~READ METHODS~~~~~~~ */
    public Collection<String> getColumnAsStringCollection(String table, String column,
                                                          Collection<String> list) {
        Log.i(TAG, "getColumnAsStringCollection");
        if (!ensureReadiness(table, true)) return null;

        try (Cursor c = db.query(
                table, new String[]{column}, null, null, null,
                null, null)) {
            if (c.getCount() > 0) {
                while (c.moveToNext()) {
                    list.add(c.getString(c.getColumnIndex(column)));
                }
            }
        }

        return list;
    }


    public Map<String, String> getRowAsStringMap(String table, long row, String[] columns) {
        Log.i(TAG, "getRowAsStringMap");
        if (!ensureReadiness(table, true)) return null;

        Map<String, String> map = new HashMap<>();
        try (Cursor c = db.query(table, columns,
                ID_COLUMN + "=?", new String[]{Long.toString(row)},
                null, null, null)) {
            if (c.getCount() == 1) {
                c.moveToNext();
                for (String s : columns) {
                    map.put(s, c.getString(c.getColumnIndex(s)));
                }
            }
        }

        return map;
    }


    /* ~~~~~~~ CATS/SUBCATS METHODS~~~~~~~ */
    private String subCatsTableNameById(int id) {
        return SUBCATS_TABLE_PT1 + id;
    }


    public String[] getSubCats() {
    }

    public String subCatsTableNameByCat(long id) {

    }

    public String

    public String subCatsTableNameByCat(String cat) {
        Log.i(TAG, "subCatsTableNameByCat");

        if (cat == null) return subCatsTableNameById(1);

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
            return subCatsTableNameById(c.getInt(c.getColumnIndex(ID_COLUMN)));
        } else return null;
    }


    /* ~~~~~~~ NOTE/TEMPLATE METHODS~~~~~~~ */
    public static String templateTableNameById(long id) {
        return TEMPLATE_TABLE_PT1 + id;
    }


    public static String noteTableNameById(long id) {
        return NOTE_TABLE_PT1 + id;
    }


    public long addTemlpate(Note note) {
        long id = addNoteToMainTable(db, note, true);
        addNoteTable(db, id, true, note);
        return id;
    }


    public long addNote(Note note) {
        long id = addNoteToMainTable(db, note, false);
        addNoteTable(db, id, false, note);
        return id;
    }


    private long addNoteToMainTable(Note note, boolean isTemplate) {
        Log.i(TAG, "addNoteToMainTable.. isTemplate: " + isTemplate);

        if (db == null) db = getWritableDatabase();

        String table;
        if (isTemplate) table = TEMPLATES_TABLE;
        else table = NOTES_TABLE;

        StringBuilder tags = new StringBuilder();
        List<String> tagz = note.getTags().getData();
        for (String tag : tagz) {
            tags.append(tag);
            tags.append(",");
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTES_NAME_COLUMN, note.getName());
        contentValues.put(NOTES_CREATED_COLUMN, System.currentTimeMillis());
        contentValues.put(NOTES_EDITED_COLUMN, System.currentTimeMillis());
        contentValues.put(NOTES_CAT_COLUMN, note.getCat().getData().get(0));
        contentValues.put(NOTES_SUBCAT_COLUMN, note.getCat().getData().get(1));
        contentValues.put(NOTES_TAGS_COLUMN, tags.toString());

        Log.i(TAG, "adding note to " + table);
        return db.insert(table, null, contentValues);
    }


    private boolean addNoteTable(long id, boolean isTemplate, Note note) {
        Log.i(TAG, "addNoteTable");

        String table;
        if (isTemplate) table = templateTableNameById(id);
        else table = noteTableNameById(id);

        if (db == null) db = getWritableDatabase();
        if (isExisting(table)) return false;

        Log.i(TAG, "Adding note " + id + " as template? " + isTemplate);

        List<Data> datas = note.getDatas();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Data data : datas) {
                values.put(NOTE_TYPE_COLUMN, Data.idByDataType(null, data));
                values.put(NOTE_VALUE_COLUMN, data.toString());
                db.insert(table, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        return true;
    }


    public Note getTemplateNote(long id) {
        return getNote(id, true);
    }


    public Note getNote(long id) {
        return getNote(id, false);
    }


    private Note getNote(long id, boolean isTemplate) {
        Log.i(TAG, "getNote");

        String table;
        String subTable;
        if (isTemplate) {
            table = TEMPLATES_TABLE;
            subTable = templateTableNameById(id);
        } else {
            table = NOTES_TABLE;
            subTable = noteTableNameById(id);
        }

        if (db == null) db = getWritableDatabase();
        if (!isExisting(db, subTable)) return null;

        Log.i(TAG, "Getting note from table " + subTable);
        Note note = new Note();

        Cursor c = db.query(
                table,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                ID_COLUMN + "=?",              // The columns for the WHERE clause
                new String[]{Long.toString(id)},          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );
        if (c.getCount() > 0) c.moveToFirst();

        note.setName(c.getString(c.getColumnIndex(NOTES_NAME_COLUMN)));
        note.setCreated(c.getLong(c.getColumnIndex(NOTES_CREATED_COLUMN)));
        note.setEdited(c.getLong(c.getColumnIndex(NOTES_EDITED_COLUMN)));
        note.setCat(c.getLong(c.getColumnIndex(NOTES_CAT_COLUMN)));
        note.setSubCat(c.getLong(c.getColumnIndex(NOTES_SUBCAT_COLUMN)));


        String tags = c.getString(c.getColumnIndex(NOTES_TAGS_COLUMN));


        return note;
    }

}