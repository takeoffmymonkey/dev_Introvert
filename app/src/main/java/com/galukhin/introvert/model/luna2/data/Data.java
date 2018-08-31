package com.galukhin.introvert.model.luna2.data;

/**
 * A basic container for data.
 * Implies String if used as a raw type.
 */

public abstract class Data<T> {
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

    public static String dataTypeById(int id) {
        switch (id) {
            case 0:
            default:
                return "Data";
            case 1:
                return "TextData";
            case 2:
                return "TextListData";
        }

    }

    public static int idByDataType(String dataType, Data data) {
        int id = 0;
        if (dataType != null) {
            switch (dataType) {
                case "Data":
                default:
                    id = 0;
                    break;
                case "TextData":
                    id = 1;
                    break;
                case "TextListData":
                    id = 2;
                    break;
            }
        }

        if (data != null) {
            if (data instanceof TextListData) id = 2;
            else if (data instanceof TextData) id = 1;
            else id = 0;
        }

        return id;
    }
}