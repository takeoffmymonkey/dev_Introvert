package com.galukhin.introvert.model.luna;

/**
 * Represents a value for the field.
 * Can be pre-defined.
 * Has a Type.
 */

public abstract class Value {
    private Types type;
    private boolean isDefault;

    public Value(Types type) {
        this.type = type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public void setDefault(boolean defaultValue) {
        isDefault = defaultValue;
    }


    public Types getType() {
        return type;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public abstract String toString();
}
