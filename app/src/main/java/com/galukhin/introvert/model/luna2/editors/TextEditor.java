/*
package com.galukhin.introvert.model.luna2.editors;

import android.app.Activity;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.data.TextData;

*/
/**
 * Presents and edits basic data (i.e. String)
 *//*


public class TextEditor extends Editor {
    private EditText textEt;
    private TextView nameTv;

    public TextEditor(Activity activity, ViewGroup root, TextData data,
                      String name, boolean numeric) {
        super(activity, root, R.layout.text_editor, data);

        textEt = this.editor.findViewById(R.id.base_editor_text_et);
        nameTv = this.editor.findViewById(R.id.base_editor_name_tv);

        if (name == null) nameTv.setVisibility(View.GONE);
        else nameTv.setText(name);

        this.data = data;

        textEt.setText(data.toString());

        if (numeric) textEt.setInputType(InputType.TYPE_CLASS_NUMBER
                | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }


    @Override
    public Data getData() {
        data = new TextData(textEt.getText().toString());
        return data;
    }

    @Override
    public void setData(Data data) {
        textEt.setText(data.toString());
        this.data = data;
    }
}*/
