package com.hanifhasan007.weatherapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanifhasan007.weatherapp.R;
import com.hanifhasan007.weatherapp.data.Forecast;
import com.hanifhasan007.weatherapp.data.Units;


public class fragmentForecast extends Fragment {

    private TextView dayTextView;
    private TextView highTempperatureTextView;
    private TextView lowTempperatureTextView;
    private ImageView weatherIconImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast,container,false);

        weatherIconImageView = (ImageView) view.findViewById(R.id.weatherIconImageView);
        dayTextView = (TextView) view.findViewById(R.id.dayTextView);
        highTempperatureTextView = (TextView)  view.findViewById(R.id.highTemperatureTextView);
        lowTempperatureTextView = (TextView) view.findViewById(R.id.lowTemperatureTextView);

        return view;
    }

    public void loadForecast(Forecast forecast, Units units){
        int weatherIconImageResource = getResources().getIdentifier("icon_" + forecast.getCode(), "drawable", getActivity().getPackageName());

        weatherIconImageView.setImageResource(weatherIconImageResource);
        dayTextView.setText(forecast.getDay());
        highTempperatureTextView.setText(""+ forecast.getHighTemperature());
        lowTempperatureTextView.setText(""+ forecast.getLowTemperature());

    }
}
