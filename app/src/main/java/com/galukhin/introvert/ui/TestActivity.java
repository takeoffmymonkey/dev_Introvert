package com.galukhin.introvert.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.galukhin.introvert.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        createAddButton();

    }

    private void createAddButton() {
        Button addBt = findViewById(R.id.add);
        addBt.setOnClickListener(view -> {
        });
    }
}
