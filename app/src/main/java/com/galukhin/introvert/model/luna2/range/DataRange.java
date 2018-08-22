package com.galukhin.introvert.model.luna2.range;

import com.galukhin.introvert.model.luna2.data.Data;

import java.util.Set;

/**
 * Defines permitted range for data and ways of manipulating it.
 */

public interface DataRange<T> {
    boolean inRange(Data<T> value);

    void add(Data<T> value);

    void remove(Data<T> value);

    Set<Data<T>> getRange();
}