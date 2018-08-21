package com.galukhin.introvert.model.luna2.editor;

import android.content.Context;

import com.galukhin.introvert.model.luna2.value.AudioValue;
import com.galukhin.introvert.model.luna2.value.Value;

public class AudioEditor extends Editor {
    AudioValue value;
    Context context;

    public AudioEditor(Context context) {
    }

    @Override
    Value getValue() {
        return value;
    }
}
