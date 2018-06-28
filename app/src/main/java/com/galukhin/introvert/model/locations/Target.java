package com.galukhin.introvert.model.locations;

import com.galukhin.introvert.model.data.Data;


/**
 * Represents a target of data, where to copy it to
 */

public interface Target {
    void setData(Data data);
}
