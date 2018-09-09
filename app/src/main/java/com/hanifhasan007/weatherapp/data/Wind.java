package com.hanifhasan007.weatherapp.data;

import org.json.JSONObject;

public class Wind implements JSONPopulator {
    private int chill;
    private int direction;
    private int speed;

    public int getChill() {
        return chill;
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void populate(JSONObject data) {
        chill = data.optInt("city");
        direction = data.optInt("direction");
        speed = data.optInt("speed");

    }
}
