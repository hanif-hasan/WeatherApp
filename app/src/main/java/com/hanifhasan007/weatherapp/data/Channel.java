package com.hanifhasan007.weatherapp.data;

import org.json.JSONArray;
import org.json.JSONObject;

public class Channel implements JSONPopulator {
    private Units units;
    private Item item;
    private Astronomy astronomy;
    private Atmosphere atmosphere;
    private Wind wind;

    public Wind getWind() {
        return wind;
    }

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    @Override
    public void populate(JSONObject data) {

        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));

        astronomy = new Astronomy();
        astronomy.populate(data.optJSONObject("astronomy"));

        atmosphere = new Atmosphere();
        atmosphere.populate(data.optJSONObject("atmosphere"));

        wind = new Wind();
        wind.populate(data.optJSONObject("wind"));

    }
}
