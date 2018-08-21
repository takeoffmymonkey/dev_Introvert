package com.galukhin.introvert.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.editor.TextEditor;

public class TestActivity extends AppCompatActivity {
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        linearLayout = findViewById(R.id.test_layout);

        createAddButton();

    }

    private void createAddButton() {
        Button addBt = findViewById(R.id.add);
        addBt.setOnClickListener(view -> {
            TextEditor textEditor = new TextEditor(this, "Enter text");
            linearLayout.addView(textEditor.getEditText());
        });
    }
}
