package com.mz.model.weather;

import com.mz.model.listener.IListener;

/**
 * Created by Jamin on 8/5/15.
 */
public class WeatherManager {

    private WeatherHandler mHandler = null;

    private static final WeatherManager sManager = new WeatherManager();
    public static WeatherManager getInstance() {
        return sManager;
    }
    private WeatherManager() {
        mHandler = new WeatherHandler();
    }

    public void fetchCurrentWeather(final IListener<WeatherInfo> listener) {
        mHandler.requestCurrentWeather(listener);
    }



}
