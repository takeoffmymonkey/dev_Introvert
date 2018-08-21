package com.galukhin.introvert.model.luna2.range;

import com.galukhin.introvert.model.luna2.value.Value;

import java.util.Set;

public class TextRange implements ValueRange {
    Set<String> range;

    public TextRange(Set<String> range) {
        this.range = range;
    }

    @Override
    public boolean inRange(Value value) {
        return range.contains(value.toString());
    }

    @Override
    public void add(Value value) {
        range.add(value.toString());
    }

    @Override
    public void remove(Value value) {
        range.remove(value.toString());
    }
}
