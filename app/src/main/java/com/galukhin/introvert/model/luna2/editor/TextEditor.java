package com.galukhin.introvert.model.luna2.editor;

import android.content.Context;
import android.widget.EditText;

import com.galukhin.introvert.model.luna2.data.TextData;
import com.galukhin.introvert.model.luna2.value.Value;

public class TextEditor extends Editor {
    TextValue value;

    EditText editText;

    public TextEditor(Context context, String value) {
        editText = new EditText(context);
        editText.setText(value);
        this.value = new TextData(value);
    }

    public EditText getEditText() {
        return editText;
    }

    @Override
    Value getValue() {
        value.setValue(editText.getText().toString());
        return value;
    }

    void setValue(TextValue value) {
        this.value = value;
    }

    void setValue(String value) {
        this.value.setValue(value);
    }

}
