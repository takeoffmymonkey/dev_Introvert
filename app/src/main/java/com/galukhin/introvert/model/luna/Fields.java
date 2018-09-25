/*
package com.galukhin.introvert.model.luna;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.galukhin.introvert.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * This class contains utility methods for Field lists
 *//*


public class Fields {
    public static Field createEditTextField(String value,
                                            Context context) {
        EditText editText = new EditText(context);
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        if (value != null) editText.setText(value);
        else editText.setText("test");

        return new Field(editText, SignalTypes.TEXT,
                new TextValue(SignalTypes.TEXT, null));
    }


    public static List<Field> createEditTextFields(int number,
                                                   String value,
                                                   Context context) {
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            EditText editText = new EditText(context);
            editText.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            if (value != null) editText.setText(value);
            else editText.setText("test");
            fields.add(new Field(editText, SignalTypes.TEXT,
                    new TextValue(SignalTypes.TEXT, null)));
        }
        return fields;
    }

//    public static List<Field> getFieldsById(long noteId, Context context) {
//        return MainActivity.dbHelper.getNoteFields(null, noteId, context);
//    }
}*/
