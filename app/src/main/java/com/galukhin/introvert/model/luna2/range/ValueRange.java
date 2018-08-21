package com.galukhin.introvert.model.luna2.range;

import com.galukhin.introvert.model.luna2.value.Value;

public interface ValueRange {
    boolean inRange(Value value);

    void add(Value value);

    void remove(Value value);
}
