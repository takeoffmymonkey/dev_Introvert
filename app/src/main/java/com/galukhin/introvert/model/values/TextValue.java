package com.galukhin.introvert.model.values;

public class TextValue implements Value {
    String text;

    public TextValue(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}