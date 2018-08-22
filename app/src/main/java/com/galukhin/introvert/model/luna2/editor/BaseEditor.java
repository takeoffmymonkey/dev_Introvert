package com.galukhin.introvert.model.luna2.editor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.data.Data;

/**
 * Hierarchy for presenting and editing data
 */

public class BaseEditor<T> {
    Data<T> data;
    View editor;
    Activity activity;

    public BaseEditor(Activity activity, ViewGroup root, Data<T> data) {
        this.data = data;
        this.activity = activity;
        editor = LayoutInflater.from(activity).inflate(R.layout.base_editor, root, false);
    }

    public Data<T> getData() {
        return data;
    }

    public void setData(Data<T> data) {
        this.data = data;
    }

    public View getEditor() {
        return editor;
    }
}
