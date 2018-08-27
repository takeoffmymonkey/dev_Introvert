package com.galukhin.introvert.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna2.Note;
import com.galukhin.introvert.model.luna2.Template;
import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.data.DataTypes;
import com.galukhin.introvert.model.luna2.data.TextData;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    LinearLayout ll;
    Template template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ll = findViewById(R.id.test_layout_ll);
        createAddTextButton();

        Note note = new Note();
        template = new Template(this, ll, note);

    }

    private void createAddTextButton() {
        Button addTextBt = findViewById(R.id.add_text);
        addTextBt.setOnClickListener(view -> {
            List<Data<String>> data = new ArrayList<>();
            data.add(new TextData(""));
            template.add(DataTypes.TEXT, data);
        });
    }
}