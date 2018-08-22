package com.galukhin.introvert.model.luna2.editor;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.data.Data;

/**
 * Presents and edits basic data (i.e. String)
 */

public class BaseEditor<T> {
    private Data<T> data;
    private Activity activity;
    private View editor;
    private EditText textEt;
    private TextView nameTv;

    public BaseEditor(Activity activity, ViewGroup root,
                      @Nullable String name, @Nullable Data<T> data) {
        this.activity = activity;
        editor = LayoutInflater.from(activity).inflate(R.layout.base_editor, root, false);
        textEt = editor.findViewById(R.id.base_editor_text_et);
        nameTv = editor.findViewById(R.id.base_editor_name_tv);

        if (name == null) nameTv.setVisibility(View.GONE);
        else nameTv.setText(name);

        if (data == null) {
            this.data = new Data("");
            textEt.setHint("Enter you note");
        } else {
            this.data = data;
            textEt.setText(data.toString());
        }
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
