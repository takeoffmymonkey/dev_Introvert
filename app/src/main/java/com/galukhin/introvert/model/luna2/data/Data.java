package com.galukhin.introvert.model.luna2.data;

/**
 * A basic container for data.
 * Implies String if used as a raw type.
 */

public abstract class Data<T> {
    private T data;
    private DataTypes type;


    public Data(T data, DataTypes type) {
        this.data = data;
        this.type = type;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public DataTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}