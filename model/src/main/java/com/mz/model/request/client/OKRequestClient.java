package com.mz.model.request.client;

import com.mz.model.request.IBaseHTTPRequest;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Jamin on 8/6/15.
 */
public class OKRequestClient implements IRequestClient {

    private static OkHttpClient mHttpClient = new OkHttpClient();

    public OKRequestClient() {

    }

    @Override
    public void sendRequest(IBaseHTTPRequest request) {

        OkHttpClient httpClient = this.mHttpClient;
        OkHttpRequestAdapter requestAdapter = new OkHttpRequestAdapter(request);
        try {
            Response response = httpClient.newCall(requestAdapter.getOkRequest()).execute();
            requestAdapter.handleResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
