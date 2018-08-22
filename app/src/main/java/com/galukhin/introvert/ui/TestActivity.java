package com.galukhin.introvert.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.editor.BaseEditor;

public class TestActivity extends AppCompatActivity {
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ll = findViewById(R.id.test_layout_ll);

        createAddButton();
    }

    private void createAddButton() {
        Button addBt = findViewById(R.id.add);
        addBt.setOnClickListener(view -> {
            Data data = new Data("test");
            BaseEditor textEditor = new BaseEditor(this, ll, data);
            ll.addView(textEditor.getEditor());
        });
    }
}
