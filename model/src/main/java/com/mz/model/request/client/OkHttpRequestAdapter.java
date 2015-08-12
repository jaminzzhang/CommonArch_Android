package com.mz.model.request.client;

import com.mz.model.request.IBaseHTTPRequest;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Jamin on 8/6/15.
 */
public class OkHttpRequestAdapter {

    private Request mOkRequest;
    private IBaseHTTPRequest mHTTPRequest;

    public OkHttpRequestAdapter(IBaseHTTPRequest request) {

        this.mHTTPRequest = request;

        MediaType mediaType = MediaType.parse(request.getContentType() + "; charset=utf-8");
        JSONObject jsonObject = new JSONObject(request.getRequestBody());
        RequestBody requestBody = RequestBody.create(mediaType, jsonObject.toString());

        Request.Builder builder = new Request.Builder()
                .url(request.getURL())
                .method(request.getHttpMethod(), requestBody);

        if (null != request.getRequestHeader()) {
            Headers headers = Headers.of(request.getRequestHeader());
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

    }

}
