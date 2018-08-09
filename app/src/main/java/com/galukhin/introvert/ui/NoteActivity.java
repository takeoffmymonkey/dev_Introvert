package com.galukhin.introvert.ui;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna.Field;
import com.galukhin.introvert.model.luna.Fields;
import com.galukhin.introvert.model.luna.Note;
import com.galukhin.introvert.model.luna.Template;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    String TAG = "LUNA:" + getClass().getSimpleName();
    public static String NOTE_ID = "NoteId";

    LinearLayout linearLayout;

    Note note;
    Template template;
    SQLiteDatabase database = MainActivity.dbHelper.getWritableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        linearLayout = findViewById(R.id.note_layout);

        createNote();

        Button addBt = findViewById(R.id.add);
        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Field> field = Fields.createEditTextFields
                        (1, "added", NoteActivity.this);
                addFieldsToLayout(field);
                note.addField(field);
            }
        });

        Button saveBt = findViewById(R.id.save);
        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
                finish();
            }
        });
    }

    void saveNote() {
        note.save();
        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
    }

    void deleteNote() {

    }

    private void createNote() {
        Log.i(TAG, "createNote");
        Log.i(TAG, "Creating note object");

        int code = getCode();
        if (code == 0) { // This is a fresh note
            template = new Template(Fields.createEditTextFields
                    (3, "Text from template", this));
            note = new Note(template);
            addFieldsToLayout(note.getFields());
        } else { // Existing note
            note = new Note(code);
        }

    }


    private void addFieldsToLayout(List<Field> fields) {
        Log.i(TAG, "Adding fields");

        for (Field field : fields) {
            linearLayout.addView(field.getView());
        }
    }


    private int getCode() {
        return getIntent().getIntExtra(NOTE_ID, 0);
    }

    @Override
    public void finish() {
        database.close();
        super.finish();
    }
}
