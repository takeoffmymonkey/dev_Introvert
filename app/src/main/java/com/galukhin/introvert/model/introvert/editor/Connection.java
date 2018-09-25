package com.galukhin.introvert.model.introvert.editor;

import com.galukhin.introvert.model.introvert.editor.interfaces.Source;
import com.galukhin.introvert.model.introvert.editor.interfaces.Target;

public class Connection<T> {
    Source<T> source;
    Target<T> target;
    TriggeringEvent event;
}