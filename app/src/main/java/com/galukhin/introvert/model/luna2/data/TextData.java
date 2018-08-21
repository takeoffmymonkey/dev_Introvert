package com.galukhin.introvert.model.luna2.data;

import com.galukhin.introvert.model.luna2.value.TextValue;

public class TextData extends Data implements TextValue {
    String value;

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}