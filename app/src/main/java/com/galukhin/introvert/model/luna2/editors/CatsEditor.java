package com.galukhin.introvert.model.luna2.editors;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.managers.CatsManager;
import com.galukhin.introvert.model.luna2.data.TextListData;

public class CatsEditor extends Editor {
    private Spinner catSp;
    private Spinner subCatSp;
    private CatsManager catsManager;

    public CatsEditor(Activity activity, ViewGroup root, TextListData data,
                      CatsManager catsManager) {
        super(activity, root, R.layout.cats_editor, data);
        this.catsManager = catsManager;

        catSp = this.editor.findViewById(R.id.cat_editor_cat_sp);
        catSp.setAdapter(catsManager.catAdapter(activity));
        subCatSp = this.editor.findViewById(R.id.cat_editor_subcat_sp);
        subCatSp.setAdapter(catsManager.subCatAdapter(activity));
    }
}