package com.galukhin.introvert.model.luna2.editors;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galukhin.introvert.model.luna2.data.Data;

public abstract class Editor {
    Activity activity;
    private ViewGroup root;
    View editor;
    Data data;

    Editor(Activity activity, ViewGroup root, int editor,
           Data data) {
        this.activity = activity;
        this.root = root;
        this.editor = LayoutInflater.from(activity).inflate(editor, root, false);
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public View getEditor() {
        return editor;
    }

    public void disable() {
        editor.setVisibility(View.GONE);
    }
}