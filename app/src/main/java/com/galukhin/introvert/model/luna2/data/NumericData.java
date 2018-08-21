package com.galukhin.introvert.model.luna2.data;

public abstract class NumericData extends TextData {
    long aLong;

    public NumericData(String data) {
        super(data);
        aLong = Long.parseLong(data);
    }

    public long getaLong() {
        return aLong;
    }

    public void setaLong(long aLong) {
        this.aLong = aLong;
    }
}