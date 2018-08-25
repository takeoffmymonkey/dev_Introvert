package com.galukhin.introvert.model.luna2.range;

import com.galukhin.introvert.model.luna2.data.Data;

import java.util.List;

/**
 * Defines permitted range for data and ways of manipulating the range.
 */

public class DataRange<T> {
    List<Data<T>> range;

    public DataRange(List<Data<T>> range) {
        this.range = range;
    }

    public DataRange(String table, String row) {
        // TODO: 025 25 Aug 18 get data set from db
    }

    public List<Data<T>> getRange() {
        return range;
    }

    public String[] asStringArray() {
        String[] array = new String[range.size()];
        Object[] ar = range.toArray();

        for (int i = 0; i < ar.length; i++) {
            array[i] = ar[i].toString();
        }

        return array;
    }
}