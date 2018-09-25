package com.galukhin.introvert.model.introvert.editor;

import android.view.ViewGroup;

import com.galukhin.introvert.model.introvert.editor.elements.Element;

import java.util.ArrayList;
import java.util.List;

public class Template {
    ViewGroup root;
    List<Element> elements = new ArrayList<>();
    List<Connection> connections = new ArrayList<>();


    public Template(ViewGroup root) {
        this.root = root;
    }


    public void addElement(Element element) {
        elements.add(element);
//        root.addView(element.getUiView());
    }
}
