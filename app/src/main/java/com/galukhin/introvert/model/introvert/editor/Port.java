package com.galukhin.introvert.model.introvert.editor;

import com.galukhin.introvert.model.introvert.editor.Address;
import com.galukhin.introvert.model.introvert.editor.interfaces.Source;
import com.galukhin.introvert.model.introvert.editor.interfaces.Target;

public class Port<T> implements Source<T>, Target<T> {
    Address address;
    T data;

    @Override
    public T get() {
        return data;
    }

    @Override
    public T set() {
        return data;
    }

    @Override
    public Address getAddress() {
        return address;
    }
}
