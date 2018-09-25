package com.galukhin.introvert.model.introvert.editor.elements;


import com.galukhin.introvert.model.introvert.editor.Port;

import java.util.List;

public abstract class Element {
    List<Port> ports;

    <T> void addPort(Port<T> port) {
        ports.add(port);
    }

    public List<Port> getPorts() {
        return ports;
    }
}