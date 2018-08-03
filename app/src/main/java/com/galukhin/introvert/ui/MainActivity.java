package com.galukhin.introvert.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.luna.Fields;
import com.galukhin.introvert.model.luna.Template;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout main = findViewById(R.id.main_layout);

        Template template = new Template(Fields.createButtons(4, this), main);
    }
}
