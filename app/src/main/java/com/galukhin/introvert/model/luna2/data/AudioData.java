package com.galukhin.introvert.model.luna2.data;

import com.galukhin.introvert.model.luna2.value.AudioValue;

public class AudioData extends Data implements AudioValue {
    String path;

    @Override
    public void setValue(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}