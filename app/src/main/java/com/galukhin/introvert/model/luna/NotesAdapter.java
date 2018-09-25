/*
package com.galukhin.introvert.model.luna;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.galukhin.introvert.R;

public class NotesAdapter extends CursorAdapter {
    public NotesAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvCreated = view.findViewById(R.id.tvCreated);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.NOTES_NAME_COLUMN));
        long created = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.NOTES_CREATED_COLUMN));

        tvName.setText(name);
        tvCreated.setText(String.valueOf(created));
    }
}
*/
