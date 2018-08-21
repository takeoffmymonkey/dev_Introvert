package com.galukhin.introvert.model.luna2.range;

import com.galukhin.introvert.model.luna2.value.Value;

import java.util.Set;
// TODO: 021 21 Aug 18 incomplete

public class NumericRange implements ValueRange {
    Set<Number> range;

    public NumericRange(Set<Number> range) {
        this.range = range;
    }

    public NumericRange(double min, double max) {

    }

    @Override
    public boolean inRange(Value value) {
        return false;
    }

    @Override
    public void add(Value value) {

    }

    @Override
    public void remove(Value value) {

    }
}
