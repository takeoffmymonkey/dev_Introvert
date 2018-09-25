package com.galukhin.introvert.model.introvert.editor.elements;

import com.galukhin.introvert.model.introvert.editor.Port;

import java.io.File;
import java.util.ArrayList;

public class FileElement extends Element {
    File file;

    public FileElement(File file) {
        this.file = file;
        ports = new ArrayList<>();
        ports.add(new Port<String>());
    }
}
