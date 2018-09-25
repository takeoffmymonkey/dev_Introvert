package com.galukhin.introvert.model.introvert.editor.elements;

import android.widget.TextView;

import com.galukhin.introvert.model.introvert.editor.Port;

import java.util.ArrayList;

public class TextElement extends Element {
    TextView textView;

    public TextElement(TextView textView) {
        this.textView = textView;
        ports = new ArrayList<>();
        ports.add(new Port<String>());
    }
}
