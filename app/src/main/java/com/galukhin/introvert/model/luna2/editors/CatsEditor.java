package com.galukhin.introvert.model.luna2.editors;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.data.TextListData;
import com.galukhin.introvert.model.luna2.managers.CatsManager;
import com.galukhin.introvert.model.luna2.managers.interfaces.Observer;

public class CatsEditor extends Editor implements Observer {
    private Spinner catSp;
    private Spinner subCatSp;
    private CatsManager catsManager;

    public CatsEditor(Activity activity, ViewGroup root, TextListData data,
                      CatsManager catsManager) {
        super(activity, root, R.layout.cats_editor, data);
        this.catsManager = catsManager;
        catsManager.addObserver(this);

        catSp = this.editor.findViewById(R.id.cat_editor_cat_sp);
        catSp.setAdapter(catsAdapter());
        subCatSp = this.editor.findViewById(R.id.cat_editor_subcat_sp);
        subCatSp.setAdapter(subCatsAdapter());
    }


    private ArrayAdapter<String> catsAdapter() {
        return new ArrayAdapter<>(activity,
                android.R.layout.simple_dropdown_item_1line,
                catsManager.getCats());
    }


    private ArrayAdapter<String> subCatsAdapter() {
        return new ArrayAdapter<>(activity,
                android.R.layout.simple_dropdown_item_1line,
                catsManager.getSubCags());
    }


    @Override
    public void update() {

    }
}