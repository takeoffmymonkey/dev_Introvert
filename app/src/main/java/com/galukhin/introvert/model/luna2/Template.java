package com.galukhin.introvert.model.luna2;

import android.app.Activity;
import android.view.ViewGroup;

import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.data.DataTypes;
import com.galukhin.introvert.model.luna2.editor.CatsEditor;
import com.galukhin.introvert.model.luna2.editor.Editor;
import com.galukhin.introvert.model.luna2.editor.TagsEditor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A bridge between a note and corresponding to its data editors.
 * It represents the initial structure of a note, so any new note
 * should be initialized by its corresponding template.
 * A template holds a properly ordered list of editors, some
 * (or all) of which may be mandatory.
 * An edited template can be saved for further usages.
 */

public class Template {
    private Activity activity;
    private ViewGroup root;
    private Note note;

    List<Editor> editors = new ArrayList<>();

    public Template(Activity activity, ViewGroup root, Note note) {
        this.activity = activity;
        this.root = root;
        this.note = note;

        addEditor(DataTypes.CATEGORY, null);
        addEditor(DataTypes.TAG, null);
    }

    public <T> void add(DataTypes type, List<Data<T>> datas) {
        addEditor(type, datas);
    }

    private <T> void addEditor(DataTypes type, List<Data<T>> datas) {
        Editor<?> editor = createEditor(type, datas);
        editors.add(editor);
        root.addView(editor.getEditor());
    }

    private <T> Editor<?> createEditor(DataTypes type, List<Data<T>> datas) {
        Editor<?> editor = null;
        switch (type) {
            case CATEGORY:
                List<String> cats = Arrays.asList("1", "2", "3");
                List<String> subCats = Arrays.asList("11", "22", "33");
                Cats catz = new Cats(cats, subCats);
                editor = new CatsEditor(activity, root, note.cats, catz);
                break;
            case TAG:
                Tags tags = new Tags(Arrays.asList("ad", "try", "yyyyy"));
                editor = new TagsEditor(activity, root, note.tags, tags);
                break;
            case TEXT:
                if (datas == null) datas = new ArrayList<>();
//                TextEditor textEditor = new TextEditor
//                        (activity, root, datas, "the", false);
                break;
        }
        return editor;
    }
}