package com.galukhin.introvert.model.luna2;

import android.app.Activity;
import android.view.ViewGroup;

import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.data.TextData;
import com.galukhin.introvert.model.luna2.editors.CatsEditor;
import com.galukhin.introvert.model.luna2.editors.Editor;
import com.galukhin.introvert.model.luna2.editors.TagsEditor;
import com.galukhin.introvert.model.luna2.editors.TextEditor;
import com.galukhin.introvert.model.luna2.managers.CatsManager;
import com.galukhin.introvert.model.luna2.managers.TagsManager;

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

        addCatsEditor();
        addTagsEditor();
    }

    private void addCatsEditor() {
        List<String> cats = Arrays.asList("1", "2", "3");
        List<String> subCats = Arrays.asList("11", "22", "33");
        CatsManager catz = new CatsManager(cats, subCats);
        Editor editor = new CatsEditor(activity, root, note.cats, catz);
        editors.add(editor);
        root.addView(editor.getEditor());
    }

    private void addTagsEditor() {
        TagsManager tagsManager = new TagsManager(Arrays.asList("ad", "try", "yyyyy"));
        Editor editor = new TagsEditor(activity, root, note.tags, tagsManager);
        editors.add(editor);
        root.addView(editor.getEditor());
    }

    public void add(Data data) {
        addEditor(data);
    }

    private void addEditor(Data data) {
        Editor editor = createEditor(data);
        editors.add(editor);
        root.addView(editor.getEditor());
    }

    private Editor createEditor(Data data) {
        Editor editor = null;

        if (data instanceof TextData) {
            editor = new TextEditor(activity, root, (TextData) data,
                    "the", false);
        }

        return editor;
    }
}