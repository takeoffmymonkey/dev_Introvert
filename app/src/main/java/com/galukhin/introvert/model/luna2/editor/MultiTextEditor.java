package com.galukhin.introvert.model.luna2.editor;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.value.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MultiTextEditor extends MultiEditor {
    Set<Value> values;
    String[] vals;
    public LinearLayout mainLl;
    Spinner spinner;
    EditText newValueEt;
    Button addNewValueBt;
    LinearLayout choiceLl;


    public MultiTextEditor(String[] values, Activity activity) {
        vals = values;
        mainLl = activity.findViewById(R.id.multi_ll_main);
        spinner = activity.findViewById(R.id.spinner);
        newValueEt = activity.findViewById(R.id.add_et);
        addNewValueBt = activity.findViewById(R.id.add_bt);
        choiceLl = activity.findViewById(R.id.multi_text_item_ll);
    }

    @Override
    List<Value> getValues() {
        return new ArrayList<>(values);
    }
}
