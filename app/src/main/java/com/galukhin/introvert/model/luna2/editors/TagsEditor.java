package com.galukhin.introvert.model.luna2.editors;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.data.TextListData;
import com.galukhin.introvert.model.luna2.managers.Observer;
import com.galukhin.introvert.model.luna2.managers.TagsManager;

import java.util.ArrayList;
import java.util.List;

public class TagsEditor extends Editor implements Observer {
    private ViewGroup tagsLl;
    private Button addBt;
    private AutoCompleteTextView tagAcTv;

    private TagsManager tagsManager;

    private List<TextView> tagsTvs = new ArrayList<>();
    private List<Button> removeBts = new ArrayList<>();

    public TagsEditor(Activity activity, ViewGroup root,
                      TextListData data, TagsManager tagsManager) {
        super(activity, root, R.layout.tags_editor, data);
        this.tagsManager = tagsManager;
        tagsManager.addObserver(this);

        tagsLl = editor.findViewById(R.id.tags_editor_tags_ll_vert);
        addBt = editor.findViewById(R.id.tags_editor_add_bt);
        tagAcTv = editor.findViewById(R.id.tags_editor_tag_actv);

        addBt.setOnClickListener(view -> {
            addTag(tagAcTv.getText().toString());
            tagAcTv.setText("");
        });

        prepareAcTv();
        prepareTags(data);
    }


    private void prepareAcTv() {
        tagAcTv.setThreshold(1);
        tagAcTv.setOnItemClickListener((adapterView, view, i, l) -> {
            String selected = (String) adapterView.getItemAtPosition(i);
            addTag(selected);
            tagAcTv.setText("");
        });
        tagAcTv.setAdapter(adapter());
    }


    private void prepareTags(TextListData data) {
        List<String> list = data.getData();
        for (String s : list) {
            addTag(s);
        }
    }


    private void addTag(String selected) {
        View tagsZone = LayoutInflater.from(activity).inflate
                (R.layout.tags_editor_tag, tagsLl, false);

        TextView tagTv = tagsZone.findViewById(R.id.tags_editor_tag_tv);
        Button removeBt = tagsZone.findViewById(R.id.tags_editor_tag_remove_bt);
        tagsTvs.add(tagTv);
        removeBts.add(removeBt);

        tagTv.setText(selected);

        tagsLl.addView(tagsZone);

        tagsManager.addTag(selected);
    }


    private ArrayAdapter<String> adapter() {
        return new ArrayAdapter<>(activity,
                android.R.layout.simple_dropdown_item_1line,
                tagsManager.getTags());
    }


    @Override
    public void update() {
        tagAcTv.setAdapter(adapter());
    }
}