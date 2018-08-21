package com.galukhin.introvert.model.luna2.data;

public class TextData extends Data {
    String data;

    public TextData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}