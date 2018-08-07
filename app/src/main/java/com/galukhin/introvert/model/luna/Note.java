package com.galukhin.introvert.model.luna;

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

    private int id;
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
    public Note(int id) {
        this.id = id;
        fields = Fields.getFieldsById(id);
        exists = true;
    }


    public int getId() {
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
        fields.addAll(fields);
    }

    public void removeField(int i) {
        fields.remove(i);
    }


    public void save() {
        prepareValues();
        MainActivity.dbHelper.addNote(null, this);
        // TODO: 007 07 Aug 18 add updating functionality
    }

    private void prepareValues() {
        for (Field field : fields) {
            // TODO: 007 07 Aug 18 this should be more generic
            String value = ((TextView) field.getView()).getText().toString();
            ((TextValue) field.getValue()).setValue(value);
        }
    }
}