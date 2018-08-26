package com.galukhin.introvert.model.luna2.editor;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.data.Data;

import java.util.List;

public class CatEditor<T> extends Editor<T> {
    private Spinner catSp;
    private Spinner subCatSp;
    List<String> catList;
    List<String> subCatList;

    private CatEditor(Activity activity, ViewGroup root,
                      List<Data<T>> data) {
        super(activity, root, R.layout.cats_editor, data);

        catSp = this.editor.findViewById(R.id.cat_editor_cat_sp);
        subCatSp = this.editor.findViewById(R.id.cat_editor_subcat_sp);
    }

    public CatEditor(Activity activity, ViewGroup root, List<Data<T>> data,
                     List<String> catList, List<String> subCatList) {
        this(activity, root, data);

        this.catList = catList;
        this.subCatList = subCatList;

        catSp.setAdapter(adapter(catList));
        subCatSp.setAdapter(adapter(subCatList));
    }

    private ArrayAdapter adapter(List<String> list) {
        return new ArrayAdapter<>(activity,
                android.R.layout.simple_dropdown_item_1line,
                list);
    }
}