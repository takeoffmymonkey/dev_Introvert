package com.galukhin.introvert.model.luna2.dataview;

import com.galukhin.introvert.model.luna2.data.Data;
import com.galukhin.introvert.model.luna2.data.interfaces.DataLocation;

public abstract class DataView implements DataLocation {
    Data data;

    @Override
    public Data getData() {
        return data;
    }

    @Override
    public void setData(Data data) {
        this.data = data;
    }
}
