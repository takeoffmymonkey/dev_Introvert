package com.galukhin.introvert.ui;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna.DbHelper;

public class MainActivity extends AppCompatActivity {

    String TAG = "LUNA:" + getClass().getSimpleName();

    public static DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        final SQLiteDatabase database = MainActivity.dbHelper.getWritableDatabase();


        Button addNoteBt = findViewById(R.id.add_note);
        addNoteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        NoteActivity.class);
                startActivity(intent);
            }
        });


        Button dumpNotesBt = findViewById(R.id.dump_notes);
        dumpNotesBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper.dumpNotesTable(database);
            }
        });


        Button deleteNotesBt = findViewById(R.id.delete_notes_table);
        deleteNotesBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteNotesTable(MainActivity.dbHelper.getWritableDatabase());
            }
        });

        Button createNotesBt = findViewById(R.id.create_notes_table);
        createNotesBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.createNotesTable(MainActivity.dbHelper.getWritableDatabase());
            }
        });


        Button dumpLastNoteBt = findViewById(R.id.delete_notes_table);
        dumpLastNoteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.dumpNoteTable(MainActivity.dbHelper.getWritableDatabase(), 0);
            }
        });

    }
}