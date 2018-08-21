package com.galukhin.introvert.model.luna2.data;

import com.galukhin.introvert.model.luna2.value.NumericValue;

public class NumericData extends Data implements NumericValue {
    Number value;

    public NumericData(Number value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public void setValue(Number value) {
        this.value = value;
    }
}
