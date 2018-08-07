package com.galukhin.introvert.model.luna;

public class TextValue extends Value {
    String value;

    public TextValue(Types type, String value) {
        super(type);
        if (value != null) {
            value = "default text value";
            super.setDefault(true);
        }

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
