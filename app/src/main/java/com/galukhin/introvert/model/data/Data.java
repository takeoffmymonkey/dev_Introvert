package com.galukhin.introvert.model.data;


import com.galukhin.introvert.model.PresentationType;
import com.galukhin.introvert.model.locations.RelativePath;
import com.galukhin.introvert.model.locations.Source;
import com.galukhin.introvert.model.values.Value;

/**
 * Container with data
 */

public class Data {
    private Source source;
    private RelativePath relativePath;
    private PresentationType presentationType;
    private Value value;


    public Data(Value value, PresentationType presentationType, Source source, RelativePath
            relativePath) {
        this.source = source;
        this.relativePath = relativePath;
        this.presentationType = presentationType;
        this.value = value;
    }


    public Data(Value value, PresentationType presentationType, Source source) {
        this.source = source;
        this.presentationType = presentationType;
        this.value = value;
        relativePath = new RelativePath("");
    }

    public Data(Value value, PresentationType presentationType) {
        this.value = value;
        this.presentationType = presentationType;
        this.source = null;
        relativePath = new RelativePath("");
    }


    public Value getValue() {
        return value;
    }
}