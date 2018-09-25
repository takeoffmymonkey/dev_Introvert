package com.galukhin.introvert.model.introvert.editor.interfaces;

import com.galukhin.introvert.model.introvert.editor.Address;

public interface Target<T> {
    Address getAddress();

    T set();
}