package com.galukhin.introvert.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.data.Cats;
import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.data.Tags;
import com.galukhin.introvert.model.luna2.editor.CatsEditor;
import com.galukhin.introvert.model.luna2.editor.TagsEditor;
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
        createAddTagButton();


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
            Cats categories = new Cats(cats, subCats);

            CatsEditor<String> catsEditor = new CatsEditor<>
                    (this, ll, data, categories);
            ll.addView(catsEditor.getEditor());
        });
    }

    private void createAddTagButton() {
        Button addTagsBt = findViewById(R.id.add_tags);
        addTagsBt.setOnClickListener(view -> {
            List<Data<String>> data = new ArrayList<>();
            List<String> tagss = Arrays.asList("ttt", "jjjjj", "gggg", "bbbbb");
            Tags tags = new Tags(tagss);
            TagsEditor<String> tagsEditor = new TagsEditor<>
                    (this, ll, data, tags);
            ll.addView(tagsEditor.getEditor());
        });

    }
}