package com.galukhin.introvert.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.editor.MultiTextEditor;

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
//            TextEditor textEditor = new TextEditor(this, "Enter text");
//            NumericEditor textEditor = new NumericEditor(this, 3);
//            linearLayout.addView(textEditor.getEditText());

            MultiTextEditor textEditor = new MultiTextEditor(
                    new String[]{"s", "e"}, this);
//            linearLayout.addView(textEditor.mainLl);
            getLayoutInflater().inflate(R.layout.multi_text_editor, linearLayout);
        });
    }
}
