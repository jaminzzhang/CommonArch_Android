package com.mz.model.weather;

import android.util.Log;

import com.mz.model.cad.request.BaseHTTPRequest;
import com.mz.model.cad.request.RequestListener;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Jamin on 8/7/15.
 */
public class WeatherCurrentRequest extends BaseHTTPRequest {

    private String mCityId;
    private WeatherInfo mWeatherInfo;

    public WeatherCurrentRequest(RequestListener requestListener) {
        super(requestListener);
    }

    public WeatherCurrentRequest(String cityId, RequestListener requestListener) {
        super(requestListener);
        mCityId = cityId;
    }


    public String getCityId() {
        return mCityId;
    }

    public WeatherInfo getWeatherInfo() {
        return mWeatherInfo;
    }

    @Override
    public String getURL() {
        return "http://api.openweathermap.org/data/2.5/weather?id=" + mCityId + "&units=Metric&lang=zh";
    }


    @Override
    public String getHttpMethod() {
        return "GET";
    }

    @Override
    public HashMap<String, String> getHttpHeader() {
        return null;
    }

    @Override
    public HashMap<String, Object> getHttpBody() {

        return null;
//        HashMap<String, Object> httpBody = new HashMap<>();
//        httpBody.put("id", mCityId);
//        return httpBody;
    }

    @Override
    public void handleResponse(JSONObject response) {

        Log.d("Weather", response.toString());
        this.mWeatherInfo = new WeatherInfo(response);



    }
}
