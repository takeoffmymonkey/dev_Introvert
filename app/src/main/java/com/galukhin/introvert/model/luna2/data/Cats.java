package com.galukhin.introvert.model.luna2.data;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Represents available categories for CatsEditor.
 */

public class Cats {
    private List<String> cats;
    private List<String> subCats;

    public Cats(List<String> cats, List<String> subCats) {
        this.cats = cats;
        this.subCats = subCats;
    }

    public Cats(String table) {
        // TODO: 026 26 Aug 18 get lists via db
    }

    private ArrayAdapter adapter(Activity activity, List<String> list) {
        return new ArrayAdapter<>(activity,
                android.R.layout.simple_dropdown_item_1line, list);
    }

    public ArrayAdapter catAdapter(Activity activity) {
        return adapter(activity, cats);
    }

    public ArrayAdapter subCatAdapter(Activity activity) {
        return adapter(activity, subCats);
    }
}