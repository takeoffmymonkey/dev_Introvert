package com.galukhin.introvert.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.editor.TagsEditor;
import com.galukhin.introvert.model.luna2.editor.TextEditor;
import com.galukhin.introvert.model.luna2.range.DataRange;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ll = findViewById(R.id.test_layout_ll);

        createAddTextButton();
        createAddTagsButton();

    }

    private void createAddTextButton() {
        Button addTextBt = findViewById(R.id.add_text);
        addTextBt.setOnClickListener(view -> {
            Data data = new Data("test");
            TextEditor textEditor = new TextEditor(this, ll, data, "the", false);
            ll.addView(textEditor.getEditor());
        });
    }

    private void createAddTagsButton() {
        Button addTagsBt = findViewById(R.id.add_tags);
        addTagsBt.setOnClickListener(view -> {
            List<Data<String>> list = new ArrayList<>();
            list.add(new Data<>("asda"));
            list.add(new Data<>("r4"));
            list.add(new Data<>("awe4da"));
            list.add(new Data<>("aaaaa"));
            DataRange<String> range = new DataRange<>(list);

            TagsEditor<String> tagsEditor = new TagsEditor<>(this, ll,
                    new Data<>("dfs"), range);
            ll.addView(tagsEditor.getEditor());
        });
    }
}
