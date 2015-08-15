package com.mz.model.weather;

import com.mz.model.cad.listener.IListener;

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

    public void fetchCurrentWeather(String cityId, final IListener<WeatherInfo> listener) {
        mHandler.requestCurrentWeather(cityId, listener);
    }



}
