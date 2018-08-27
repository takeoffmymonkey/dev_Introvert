package com.galukhin.introvert.model.luna2.editor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galukhin.introvert.model.luna2.data.Data;

import java.util.List;

public abstract class Editor<T> {
    Activity activity;
    private ViewGroup root;
    View editor;
    List<Data<T>> data;

    Editor(Activity activity, ViewGroup root, int editor, List<Data<T>> data) {
        this.data = data;
        this.activity = activity;
        this.root = root;
        this.editor = LayoutInflater.from(activity).inflate(editor, root, false);
    }

    public List<Data<T>> getData() {
        return data;
    }

    public void setData(List<Data<T>> data) {
        this.data = data;
    }

    public View getEditor() {
        return editor;
    }

    public void disable() {
        editor.setVisibility(View.GONE);
    }
}