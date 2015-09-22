package com.tommymei.backend;

import static com.google.common.base.Preconditions.checkNotNull;

public class Color {

    private final Rgb red;
    private final Rgb green;
    private final Rgb blue;

    public Color(Rgb red, Rgb green, Rgb blue) {
        checkNotNull(red, "Undefined red");
        checkNotNull(green, "Undefined green");
        checkNotNull(blue, "Undefined blue");
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Rgb getRed() {
        return red;
    }

    public Rgb getGreen() {
        return green;
    }

    public Rgb getBlue() {
        return blue;
    }

}
