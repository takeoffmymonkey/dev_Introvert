package com.galukhin.introvert.model.luna2.range;

import com.galukhin.introvert.model.luna2.data.Data;

import java.util.Set;

/**
 * Defines permitted range for data and ways of manipulating it.
 */

public class DataRange<T> {
    private Set<Data<T>> range;

    public DataRange(Set<Data<T>> range) {
        this.range = range;
    }

    public Set<Data<T>> getRange() {
        return range;
    }

    public boolean inRange(Data<T> value) {
        return range.contains(value);
    }

    public void add(Data<T> value) {
        range.add(value);
    }

    public void remove(Data<T> value) {
        range.remove(value);
    }
}