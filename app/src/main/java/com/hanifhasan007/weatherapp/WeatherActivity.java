package com.hanifhasan007.weatherapp;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.hanifhasan007.weatherapp.data.Astronomy;
import com.hanifhasan007.weatherapp.data.Atmosphere;
import com.hanifhasan007.weatherapp.data.Channel;
import com.hanifhasan007.weatherapp.data.Forecast;
import com.hanifhasan007.weatherapp.data.Item;
import com.hanifhasan007.weatherapp.data.Wind;
import com.hanifhasan007.weatherapp.fragments.fragmentForecast;
import com.hanifhasan007.weatherapp.service.WeatherServiceCallback;
import com.hanifhasan007.weatherapp.service.YahooWeatherService;

public class WeatherActivity extends AppCompatActivity implements WeatherServiceCallback {

    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private TextView sunriseTextView;
    private TextView sunsetTextView;
    private TextView humidityTextView;
    private TextView pressureTextView;
    private TextView visibilityTextView;
    private ImageView weatherIconImageView;
    private TextView windTextView;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    private int temperature;
    private boolean flag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);



        //Autocomplete Field**********

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                String placeName = place.getName().toString();
                //Toast.makeText(WeatherActivity.this, ""+placeName,Toast.LENGTH_SHORT).show();
                service.refreshWeather(placeName);
            }

            @Override
            public void onError(Status status) {

            }
        });

        temperatureTextView = (TextView)findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView)findViewById(R.id.conditionTextView);
        locationTextView = (TextView)findViewById(R.id.locationTextView);
        sunriseTextView = (TextView)findViewById(R.id.sunriseTextView);
        sunsetTextView = (TextView)findViewById(R.id.sunsetTextView);
        humidityTextView = (TextView)findViewById(R.id.humidityTextView);
        pressureTextView = (TextView)findViewById(R.id.pressureTextView);
        visibilityTextView = (TextView)findViewById(R.id.visibilityTextView);
        weatherIconImageView = (ImageView)findViewById(R.id.weatherIconImageView);
        windTextView = (TextView) findViewById(R.id.windTextView);


        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();
        service.refreshWeather("Dhaka");


        //**********Temperature Format Change


    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();



        //Item item = channel.getItem();
        Astronomy astronomy = channel.getAstronomy();
        Atmosphere atmosphere = channel.getAtmosphere();

        Item item = channel.getItem();
        int reasourceId =  getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(),null, getPackageName());
        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(reasourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawable);

        temperatureTextView.setText(""+item.getCondition().getTemperature()  + "\u00B0"+ channel.getUnits().getTemperature());
        conditionTextView.setText((CharSequence) item.getCondition().getDescription());
        locationTextView.setText((CharSequence) service.getLocation());
        sunriseTextView.setText("" + (CharSequence) astronomy.getSunrise());
        sunsetTextView.setText("" + (CharSequence) astronomy.getSunset());
        humidityTextView.setText("" + (CharSequence) atmosphere.getHumidity() + "%");
        pressureTextView.setText("" + (CharSequence) atmosphere.getPressure() + " hPa");
        visibilityTextView.setText("" + (CharSequence) atmosphere.getVisibility() + " mi");

        Wind wind = channel.getWind();
        windTextView.setText(""+ wind.getSpeed() + "mi/hr");


        Forecast[] forecast= channel.getItem().getForecast();
        //Forecast Curentforecast = forecast[1];


        for (int day = 0; day < forecast.length; day++) {
            if (day >= 7) {
                break;
            }

            Forecast currentCondition = forecast[day];

            int viewId = getResources().getIdentifier("forecast_" + day, "id", getPackageName());
            fragmentForecast fragment = (fragmentForecast) getSupportFragmentManager().findFragmentById(viewId);

            if (fragment != null) {
                fragment.loadForecast(currentCondition, channel.getUnits());
            }
        }

        temperature = item.getCondition().getTemperature();






    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this,exception.getMessage(), Toast.LENGTH_LONG).show();

    }

    public void tempFormat(View view){
        if(flag) {
            String s = String.valueOf(Math.round((temperature - 32) * .5556));
            temperatureTextView.setText(s+ "\u00B0"+"C");
            flag=false;
        }
        else{
            temperatureTextView.setText(temperature+ "\u00B0"+"F");
            flag=true;
        }

    }
}
