package com.galukhin.introvert.model.luna2.managers;

import android.content.ContentValues;

import com.galukhin.introvert.model.luna2.db.DbHelper;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents available categories and their subcategories for CatsEditor.
 */

public class CatsManager extends Manager {
    private Set<String> cats;
    private Set<String> subCats;
    private String catsTable = DbHelper.CATS_TABLE;
    private String catsColumn = DbHelper.CATS_CAT_COLUMN;
    private String subCatsTable = dbHelper.subCatsTableNameByCat(null, null);
    private String subCatsColumn = DbHelper.SUBCATS_SUBCAT_COLUMN;

    public CatsManager() {
        cats = (HashSet<String>) dbHelper.getColumnAsStringCollection(
                null, catsTable, catsColumn, new LinkedHashSet<>());

        subCats = (HashSet<String>) dbHelper.getColumnAsStringCollection(
                null, subCatsTable, subCatsColumn, new LinkedHashSet <>());
    }


    public void addCat(String tag) {
        if (cats.add(tag)) {
            ContentValues values = new ContentValues();
            values.put(catsColumn, tag);
            dbHelper.insertRow(null, catsTable, values);
            notifyObservers();
        }
    }

    public String[] getCats() {
        return cats.toArray(new String[0]);
    }

    public String[] getSubCags() {
        return subCats.toArray(new String[0]);
    }
}