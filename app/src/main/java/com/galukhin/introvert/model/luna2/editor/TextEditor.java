package com.galukhin.introvert.model.luna2.editor;

import android.content.Context;
import android.widget.EditText;

import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.data.TextData;

public class TextEditor extends Editor {
    TextData data;
    EditText editText;

    public TextEditor(Context context) {
        editText = new EditText(context);
    }

    @Override
    Data getData() {
        return data;
    }

    void setData(TextData data) {
        this.data = data;
    }
}
