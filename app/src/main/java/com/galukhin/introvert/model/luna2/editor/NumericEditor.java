package com.galukhin.introvert.model.luna2.editor;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

import com.galukhin.introvert.model.luna2.data.NumericData;
import com.galukhin.introvert.model.luna2.value.NumericValue;
import com.galukhin.introvert.model.luna2.value.Value;

public class NumericEditor extends Editor {
    NumericValue value;
    EditText editText;


    public NumericEditor(Context context, Number value) {
        editText = new EditText(context);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER |
        InputType.TYPE_NUMBER_FLAG_DECIMAL);
        editText.setText(value.toString());
        this.value = new NumericData(value);
    }


    public EditText getEditText() {
        return editText;
    }

    @Override
    Value getValue() {
        // TODO: 021 21 Aug 18 perform numeric check
        value.setValue(Double.parseDouble(editText.getText().toString()));
        return value;
    }

    void setValue(NumericValue value) {
        this.value = value;
    }
}
