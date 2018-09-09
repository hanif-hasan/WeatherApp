package com.hanifhasan007.weatherapp.data;

import org.json.JSONArray;
import org.json.JSONObject;

public class Forecast implements JSONPopulator {
    private int code;
    private String day;
    private int highTemperature;
    private int lowTemperature;
    private String description;

    public int getCode() {
        return code;
    }

    public String getDay() {
        return day;
    }

    public int getHighTemperature() {
        return highTemperature;
    }

    public int getLowTemperature() {
        return lowTemperature;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        day = data.optString("day");
        highTemperature = data.optInt("high");
        lowTemperature = data.optInt("low");
        description = data.optString("text");

    }
}
