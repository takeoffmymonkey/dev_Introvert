package com.galukhin.introvert.model.luna;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains utility methods for Field lists
 */

public class Fields {
    public static Field createEditTextField(String text,
                                            Context context) {
        EditText editText = new EditText(context);
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        if (text != null) editText.setText(text);
        else editText.setText("test");

        return new Field(editText, Types.TEXT,
                new TextValue(Types.TEXT, null));
    }


    public static List<Field> createEditTextFields(int number,
                                                   String text,
                                                   Context context) {
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            EditText editText = new EditText(context);
            editText.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            if (text != null) editText.setText(text);
            else editText.setText("test");
            fields.add(new Field(editText, Types.TEXT,
                    new TextValue(Types.TEXT, null)));
        }
        return fields;
    }

    public static List<Field> getFieldsById(int noteId) {
        List<Field> fields = new ArrayList<>();
        // TODO: 007 07 Aug 18 read table and make array of it
        return fields;
    }
}