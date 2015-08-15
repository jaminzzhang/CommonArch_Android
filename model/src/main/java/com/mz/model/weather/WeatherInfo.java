package com.mz.model.weather;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Jamin on 8/5/15.
 */
public class WeatherInfo {

    private String mCityId;
    private String mCityName;
    private String mIconId;
    private String mMainDesc;     //主要描述
    private double mTemperature;
    private double mTempMin;
    private double mTempMax;
    private long mHumidity;     //湿度
    private long mPressure;     //气压
    private long mLatestUpdateTime;

    public WeatherInfo(JSONObject json) {

        JSONArray weatherArray = json.optJSONArray("weather");
        JSONObject brefJson = weatherArray.optJSONObject(0);

        this.mCityId = json.optString("id");
        this.mCityName = json.optString("name");
        if (null != brefJson) {
            this.mMainDesc = brefJson.optString("description");
            this.mIconId = brefJson.optString("icon");
        }

        JSONObject mainJson = json.optJSONObject("main");
        if (null != mainJson) {
            this.mTemperature = mainJson.optDouble("temp");
            this.mTempMax = mainJson.optDouble("temp_max");
            this.mTempMin = mainJson.optDouble("temp_min");
            this.mHumidity = mainJson.optLong("humidity");
            this.mPressure = mainJson.optLong("pressure");
        }

        this.mLatestUpdateTime = json.optLong("dt");
    }


    //========================Getter


    public String getCityId() {
        return mCityId;
    }

    public String getCityName() {
        return mCityName;
    }

    public String getIconId() {
        return mIconId;
    }

    public String getMainDesc() {
        return mMainDesc;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public double getTempMin() {
        return mTempMin;
    }

    public double getTempMax() {
        return mTempMax;
    }

    public long getHumidity() {
        return mHumidity;
    }

    public long getPressure() {
        return mPressure;
    }

    public long getLatestUpdateTime() {
        return mLatestUpdateTime;
    }
}
