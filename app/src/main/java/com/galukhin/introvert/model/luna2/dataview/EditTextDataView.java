package com.galukhin.introvert.model.luna2.dataview;

import android.content.Context;
import android.widget.EditText;

public class EditTextDataView extends DataView {
    EditText editText;

    public EditTextDataView(Context context) {
        editText = new EditText(context);
    }
}
