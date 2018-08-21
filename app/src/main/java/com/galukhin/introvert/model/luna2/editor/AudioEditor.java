package com.galukhin.introvert.model.luna2.editor;

import android.content.Context;

import com.galukhin.introvert.model.luna2.data.AudioData;
import com.galukhin.introvert.model.luna2.data.Data;

public class AudioEditor extends Editor {
    AudioData data;
    Context context;

    public AudioEditor(Context context) {
    }

    @Override
    Data getData() {
        return data;
    }
}
