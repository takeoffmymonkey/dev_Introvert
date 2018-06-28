package com.galukhin.introvert.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.PresentationType;
import com.galukhin.introvert.model.data.Data;
import com.galukhin.introvert.model.locations.EditTextField;
import com.galukhin.introvert.model.values.TextValue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.edit_text);
        EditTextField editTextField = new EditTextField(editText);
        editTextField.setData(new Data(new TextValue("yo"), PresentationType.TEXT));

    }
}
