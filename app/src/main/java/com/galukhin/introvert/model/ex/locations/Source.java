package com.galukhin.introvert.model.ex.locations;

import com.galukhin.introvert.model.ex.data.Data;

/**
 * Represents a source of data, where to read it from
 */

public interface Source {
    Data getData(RelativePath relativePath);
}