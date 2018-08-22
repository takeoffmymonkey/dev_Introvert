package com.galukhin.introvert.model.luna2.data;

public class Data<T> {
    T data;

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
