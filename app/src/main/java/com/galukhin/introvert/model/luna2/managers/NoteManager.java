package com.galukhin.introvert.model.luna2.managers;

import android.app.Activity;
import android.view.ViewGroup;

import com.galukhin.introvert.model.luna2.Note;
import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.data.TextData;
import com.galukhin.introvert.model.luna2.db.DbHelper;
import com.galukhin.introvert.model.luna2.editors.CatsEditor;
import com.galukhin.introvert.model.luna2.editors.Editor;
import com.galukhin.introvert.model.luna2.editors.TagsEditor;
import com.galukhin.introvert.model.luna2.editors.TextEditor;

import java.util.ArrayList;
import java.util.List;

/**
 * A bridge between a note and corresponding to its data editors.
 * It represents the initial structure of a note, so any new note
 * should be initialized by its corresponding template.
 * A template holds a properly ordered list of editors, some
 * (or all) of which may be mandatory.
 * An edited template can be saved for further usages.
 */


public class NoteManager {
    private Activity activity;
    private ViewGroup root;
    private Note note;
    Editor catsEditor;
    Editor tagsEditor;
    List<Editor> dataEditors = new ArrayList<>();

    public NoteManager(Activity activity, ViewGroup root, int note, boolean template) {
        this.activity = activity;
        this.root = root;

        createNote(note, template);

        addCatsEditor();
        addTagsEditor();
        tagsEditor.hide();
    }


    private void createNote(int id, boolean isTemplate) {
        String mainTable;
        String subTable;

        if (isTemplate) {
            mainTable = DbHelper.TEMPLATES_TABLE;
            subTable = DbHelper.templateTableNameById(id);
        } else {
            mainTable = DbHelper.NOTES_TABLE;
            subTable = DbHelper.noteTableNameById(id);
        }

    }


    private void addCatsEditor() {
        CatsManager cats = Manager.getCatsManager();
        catsEditor = new CatsEditor(activity, root, note.getCat(), cats);
        root.addView(catsEditor.getEditor());
    }


    private void addTagsEditor() {
        TagsManager tagsManager = Manager.getTagsManager();
        tagsEditor = new TagsEditor(activity, root, note.getTags(), tagsManager);
        root.addView(tagsEditor.getEditor());
    }


    private void addEditor(Data data) {
        Editor editor = createEditor(data);
        dataEditors.add(editor);
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
