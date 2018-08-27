package com.galukhin.introvert.model.luna2;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Represents available tags for TagsEditor.
 */

public class Tags {
    private List<String> tags;

    public Tags(List<String> tags) {
        this.tags = tags;
    }

    public Tags(String table) {
        // TODO: 026 26 Aug 18 get list via db
    }

    public ArrayAdapter tagsAdapter(Activity activity) {
        return new ArrayAdapter<>(activity,
                android.R.layout.simple_dropdown_item_1line, tags);
    }
}