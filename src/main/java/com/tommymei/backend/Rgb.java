package com.tommymei.backend;

import static com.google.common.base.Preconditions.checkArgument;

public class Rgb {

    private final int value;

    Rgb(int value) {
        checkArgument(value >= 0 && value <= 255, "invalid value %s", value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
