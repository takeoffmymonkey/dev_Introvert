/*
package com.galukhin.introvert.model.luna2.editor;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.range.DataRange;

import java.util.ArrayList;
import java.util.List;

public class TagsEditor<T> extends Editor<T> {
    private AutoCompleteTextView tagAcTv;
    private Button addBt;
    private LinearLayout tagsLl;
    private List<Spinner> tagSpinners = new ArrayList<>();
    private List<Button> removeBts = new ArrayList<>();

    private DataRange<T> range;
    ArrayAdapter<String> adapter;


    public TagsEditor(Activity activity, ViewGroup root,
                      Data<T> data, DataRange<T> range) {
        super(activity, root, R.layout.tags_editor, data);
        this.range = range;

        adapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_dropdown_item_1line,
                range.asStringArray());

        addAutoCompleteTv(adapter);
        addAddBt();
        addTagsZone();
    }

    private void addTagsZone() {
        tagsLl = editor.findViewById(R.id.tags_editor_tags_ll_vert);
        addTagSelector(tagsLl, adapter);
    }

    private void addAutoCompleteTv(ArrayAdapter<String> adapter) {
        tagAcTv = editor.findViewById(R.id.tags_editor_tag_actv);
        tagAcTv.setThreshold(1);
        tagAcTv.setAdapter(adapter);
    }

    private void addAddBt() {
        addBt = editor.findViewById(R.id.tags_editor_add_bt);
    }

    private void addTagSelector(LinearLayout ll, ArrayAdapter<String> adapter) {
        Spinner spinner = new Spinner(activity);
        spinner.setAdapter(adapter);
        tagSpinners.add(spinner);
        ll.addView(spinner);

        Button button = new Button(activity);
        button.setText("X");
        removeBts.add(button);
        ll.addView(button);
    }
}*/
