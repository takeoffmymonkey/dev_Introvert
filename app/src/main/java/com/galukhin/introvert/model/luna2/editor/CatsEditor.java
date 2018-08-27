package com.galukhin.introvert.model.luna2.editor;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.Cats;
import com.galukhin.introvert.model.luna2.data.Data;

import java.util.List;

public class CatsEditor extends Editor<String> {
    private Spinner catSp;
    private Spinner subCatSp;
    private Cats cats;

    public CatsEditor(Activity activity, ViewGroup root, List<Data<
            String>> data,
                      Cats cats) {
        super(activity, root, R.layout.cats_editor, data);
        this.cats = cats;

        catSp = this.editor.findViewById(R.id.cat_editor_cat_sp);
        catSp.setAdapter(cats.catAdapter(activity));
        subCatSp = this.editor.findViewById(R.id.cat_editor_subcat_sp);
        subCatSp.setAdapter(cats.subCatAdapter(activity));
    }
}