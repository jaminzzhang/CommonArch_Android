package com.mz.model.weather;

import android.os.Build;
import android.util.Log;

import com.mz.model.request.BaseHTTPRequest;
import com.mz.model.request.RequestListener;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Jamin on 8/7/15.
 */
public class WeatherCurrentRequest extends BaseHTTPRequest {


    public WeatherCurrentRequest(RequestListener requestListener) {
        super(requestListener);
    }

    @Override
    public String getURL() {
        return "http://api.woqu.com/mobile";
    }

    @Override
    public HashMap<String, String> getRequestHeader() {
        return null;
    }

    @Override
    public HashMap<String, Object> getRequestBody() {

        HashMap<String, Object> reqHeader = new HashMap<>();
        reqHeader.put("appid", "WQVISAAndroid");
        reqHeader.put("bundle_id", "com.woqu.woqu");  //即Package Name
        reqHeader.put("app_version", "1.3.4");
        reqHeader.put("dev_mode", Build.DEVICE);
        reqHeader.put("dev_uuid", "1");
        reqHeader.put("network_type", 1);
        reqHeader.put("client_ip", "192.168.0.1");
        reqHeader.put("msg_seq", 1);
        reqHeader.put("cmd", "list_activities");

        HashMap<String, Object> reqBody = new HashMap<>();
        reqBody.put("page_index", 0);
        reqBody.put("page_size", 100);
        reqBody.put("thumb_size", "500x300");



        HashMap<String, Object> httpBody = new HashMap<>();
        httpBody.put("Req_Header", reqHeader);
        httpBody.put("Req_Body", reqBody);



        return httpBody;
    }

    @Override
    public void handleResponse(JSONObject response) {
        Log.d("Weather", response.toString());
    }
}
