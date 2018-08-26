package com.galukhin.introvert.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.data.Categories;
import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.editor.CatEditor;
import com.galukhin.introvert.model.luna2.editor.TextEditor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ll = findViewById(R.id.test_layout_ll);

        createAddTextButton();
        createAddCatButton();

    }

    private void createAddTextButton() {
        Button addTextBt = findViewById(R.id.add_text);
        addTextBt.setOnClickListener(view -> {
            Data<String> d = new Data<>("test");
            List<Data<String>> data = new ArrayList<>();
            data.add(d);
            TextEditor textEditor = new TextEditor(this, ll, data, "the", false);
            ll.addView(textEditor.getEditor());
        });
    }

    private void createAddCatButton() {
        Button addCatsBt = findViewById(R.id.add_cats);
        addCatsBt.setOnClickListener(view -> {
            List<Data<String>> data = new ArrayList<>();
            List<String> cats = Arrays.asList("1", "2", "3");
            List<String> subCats = Arrays.asList("11", "22", "33");
            Categories categories = new Categories(cats, subCats);

            CatEditor<String> catEditor = new CatEditor<>
                    (this, ll, data, categories);
            ll.addView(catEditor.getEditor());
        });
    }
}