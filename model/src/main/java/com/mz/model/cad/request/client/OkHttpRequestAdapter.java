package com.mz.model.cad.request.client;

import com.mz.model.cad.request.IBaseHTTPRequest;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Jamin on 8/6/15.
 */
public class OkHttpRequestAdapter {

    private Request mOkRequest;
    private IBaseHTTPRequest mHTTPRequest;

    public OkHttpRequestAdapter(IBaseHTTPRequest request) {

        this.mHTTPRequest = request;

        MediaType mediaType = MediaType.parse(request.getContentType() + "; charset=utf-8");
        HashMap<String, Object> body = request.getHttpBody();
        RequestBody requestBody = null;
        if (null != body) {

            JSONObject jsonObject = new JSONObject();
            requestBody = RequestBody.create(mediaType, jsonObject.toString());
        }

        Request.Builder builder = new Request.Builder()
                .url(request.getURL())
                .method(request.getHttpMethod(), requestBody);

        if (null != request.getHttpHeader()) {
            Headers headers = Headers.of(request.getHttpHeader());
            builder.headers(headers);
        }


        this.mOkRequest = builder.build();
    }


    //Getter
    public Request getOkRequest() {
        return mOkRequest;
    }

    public void handleResponse(Response response) {
        if (response.isSuccessful()) {
            JSONObject json = null;
            try {
                String responseString = response.body().string();
                json = new JSONObject(responseString);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            this.mHTTPRequest.handleResponse(json);

        } else {
            this.mHTTPRequest.handleFailure(response.code(), response.message());
        }

        this.mHTTPRequest.complete();

    }

}
