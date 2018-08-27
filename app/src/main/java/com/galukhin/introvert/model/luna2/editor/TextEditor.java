package com.galukhin.introvert.model.luna2.editor;

import android.app.Activity;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.data.TextData;

import java.util.List;

/**
 * Presents and edits basic data (i.e. String)
 */

public class TextEditor extends Editor<String> {
    private EditText textEt;
    private TextView nameTv;


    public TextEditor(Activity activity, ViewGroup root, List<Data<String>> data,
                      String name, boolean numeric) {
        super(activity, root, R.layout.text_editor, data);

        textEt = this.editor.findViewById(R.id.base_editor_text_et);
        nameTv = this.editor.findViewById(R.id.base_editor_name_tv);

        if (name == null) nameTv.setVisibility(View.GONE);
        else nameTv.setText(name);

        this.data = data;
        textEt.setText(data.get(0).toString());

        if (numeric) textEt.setInputType(InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }


    @Override
    public List<Data<String>> getData() {
        String current = textEt.getText().toString();
        data.clear();
        data.add(new TextData(current));
        return data;
    }

    @Override
    public void setData(List<Data<String>> data) {
        String val = data.get(0).toString();
        textEt.setText(val);
        this.data = data;
    }
}