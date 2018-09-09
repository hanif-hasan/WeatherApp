package com.hanifhasan007.weatherapp.service;

import com.hanifhasan007.weatherapp.data.Channel;

public interface WeatherServiceCallback {

    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
