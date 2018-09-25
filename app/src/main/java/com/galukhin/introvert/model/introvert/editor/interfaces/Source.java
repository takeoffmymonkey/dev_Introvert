package com.galukhin.introvert.model.introvert.editor.interfaces;

import com.galukhin.introvert.model.introvert.editor.Address;

public interface Source<T> {
    Address getAddress();

    T get();
}
