package com.galukhin.introvert.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna.DbHelper;
import com.galukhin.introvert.model.luna.NotesAdapter;

public class MainActivity extends AppCompatActivity {

    String TAG = "LUNA:" + getClass().getSimpleName();

    public static DbHelper dbHelper;
    NotesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);

        createAddButton();

        adapter = new NotesAdapter(
                this, dbHelper.createNotesCursor(null));

        ListView listView = findViewById(R.id.notes_list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateCursor(adapter, dbHelper.createNotesCursor(null));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.debug, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        switch (item.getItemId()) {
            case R.id.create_notes_table:
                dbHelper.createNotesTable(null);
                return true;
            case R.id.delete_notes_table:
                dbHelper.deleteNotesTable(null);
                return true;
            case R.id.dump_notes_table:
                dbHelper.dumpNotesTable(null);
                return true;
            case R.id.dump_last_note:
                dbHelper.dumpNoteTable(null, dbHelper.notesCount(null));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createAddButton() {
        Button addNoteBt = findViewById(R.id.add_note);
        addNoteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        NoteActivity.class);
                intent.putExtra(NoteActivity.NOTE_ID, 0);
                startActivity(intent);
            }
        });
    }


    private void updateCursor(CursorAdapter adapter, Cursor cursor) {
        adapter.changeCursor(cursor);
    }

}