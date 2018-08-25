package com.galukhin.introvert.model.luna2.data;

/**
 * A basic container for data.
 * Implies String if used as a raw type.
 */

public class Data<T> {
    private T data;

    public Data(T data) {
        this.data = data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}