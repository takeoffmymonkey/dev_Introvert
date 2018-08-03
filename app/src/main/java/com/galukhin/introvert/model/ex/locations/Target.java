package com.galukhin.introvert.model.ex.locations;

import com.galukhin.introvert.model.ex.data.Data;


/**
 * Represents a target of data, where to copy it to
 */

public interface Target {
    void setData(Data data);
}
