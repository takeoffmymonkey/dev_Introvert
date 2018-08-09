package com.galukhin.introvert.model.luna;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.galukhin.introvert.ui.MainActivity;

import java.util.List;

/**
 * Represents a note.
 * <p>
 * Note object may be created:
 * 1 - using a provided template - note is fresh and not in the db yet
 * 2 - using a provided id - this note already exists in the db
 */

public class Note {

    String TAG = "LUNA:" + getClass().getSimpleName();

    private long id;
    private List<Field> fields;
    private boolean exists;


    /**
     * For not yet existing notes. A provided template will be used
     */
    public Note(Template template) {
        fields = template.getFields();
        exists = false;
    }

    /**
     * For existing note
     */
    public Note(long id, Context context) {
        this.id = id;
        fields = Fields.getFieldsById(id, context);
        exists = true;
    }


    public long getId() {
        return id;
    }

    public List<Field> getFields() {
        return fields;
    }


    private boolean isExisting() {
        return exists;
    }

    public static boolean isExisting(int id) {
        return MainActivity.dbHelper.isExisting(null, DbHelper.NOTE_TABLE_PT1 + id);
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public void addField(List<Field> fields) {
        this.fields.addAll(fields);
    }

    public void removeField(int i) {
        fields.remove(i);
    }


    public void save() {
        Log.i(TAG, "save");

        prepareValues();

        Log.i(TAG, "Saving to note to db");
        MainActivity.dbHelper.addNote(null, this);
        // TODO: 007 07 Aug 18 add updating functionality
    }

    private void prepareValues() {
        Log.i(TAG, "prepareValues");
        Log.i(TAG, "Preparing values for storing to db");

        for (Field field : fields) {
            // TODO: 007 07 Aug 18 this should be more generic
            String value = ((TextView) field.getView()).getText().toString();
            ((TextValue) field.getValue()).setValue(value);
        }
    }
}