package com.galukhin.introvert.model.locations;

import android.content.Context;
import android.widget.EditText;

import com.galukhin.introvert.model.PresentationType;
import com.galukhin.introvert.model.data.Data;
import com.galukhin.introvert.model.values.TextValue;

public class EditTextField implements Source, Target {
    EditText editText;

    public EditTextField(Context context) {
        editText = new EditText(context);
    }

    public EditTextField(EditText editText) {
        this.editText = editText;
    }

    @Override
    public Data getData(RelativePath relativePath) {
        return new Data(new TextValue(editText.getText().toString()), PresentationType.TEXT, this);
    }

    @Override
    public void setData(Data data) {
        editText.setText(data.getValue().toString());
    }

    public EditText getEditText() {
        return editText;
    }
}
