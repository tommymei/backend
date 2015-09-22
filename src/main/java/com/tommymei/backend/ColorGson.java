package com.tommymei.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class ColorGson {

    @SerializedName("id")
    private final String id;
    
    @SerializedName("red")
    private final int red;
    
    @SerializedName("green")
    private final int green;
    
    @SerializedName("blue")
    private final int blue;

    ColorGson(String id, int red, int green, int blue) {
        this.id = id;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    private static Gson GSON = new GsonBuilder().create();

    public String toJson() {
        return GSON.toJson(this);
    }

}
