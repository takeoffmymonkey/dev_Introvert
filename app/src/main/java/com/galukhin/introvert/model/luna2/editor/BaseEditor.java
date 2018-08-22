package com.galukhin.introvert.model.luna2.editor;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.InputType;
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

public class BaseEditor {
    private Data<String> currentData;
    private View editor;
    private EditText textEt;
    private TextView nameTv;


    public BaseEditor(Activity activity, ViewGroup root,
                      String name, @Nullable Data data, boolean numeric) {
        editor = LayoutInflater.from(activity).inflate(R.layout.base_editor, root, false);
        textEt = editor.findViewById(R.id.base_editor_text_et);
        nameTv = editor.findViewById(R.id.base_editor_name_tv);

        if (name == null) nameTv.setVisibility(View.GONE);
        else nameTv.setText(name);

        if (data == null) {
            currentData = new Data<>("");
            textEt.setHint("Enter you note");
        } else {
            currentData = new Data<>(data.toString());
            textEt.setText(data.toString());
        }

        if (numeric) textEt.setInputType(InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    public Data getCurrentData() {
        currentData.setData(textEt.getText().toString());
        return currentData;
    }

    public View getEditor() {
        return editor;
    }
}
