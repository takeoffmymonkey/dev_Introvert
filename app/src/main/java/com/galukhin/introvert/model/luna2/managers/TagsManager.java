package com.galukhin.introvert.model.luna2.managers;

import android.content.ContentValues;

import com.galukhin.introvert.model.luna2.db.DbHelper;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents available tags for TagsEditor.
 */

public class TagsManager extends Manager {
    private Set<String> tags;
    private String table = DbHelper.TAGS_TABLE;
    private String column = DbHelper.TAGS_TAG_COLUMN;

    TagsManager() {
        tags = (HashSet<String>) dbHelper.getColumnAsCollection(
                null, table, column, new HashSet<>());
    }

    public String[] getTags() {
        return tags.toArray(new String[0]);
    }

    public void addTag(String tag) {
        if (tags.add(tag)) {
            ContentValues values = new ContentValues();
            values.put(column, tag);
            dbHelper.insertRow(null, table, values);
            notifyObservers();
        }
    }

    public static void cleanUnused() {
        // TODO: 028 28 Aug 18 remove tags that have no note containing them
    }
}