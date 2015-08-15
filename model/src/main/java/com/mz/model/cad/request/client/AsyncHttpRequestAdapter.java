package com.mz.model.cad.request.client;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.mz.model.cad.request.IBaseHTTPRequest;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jamin on 8/6/15.
 */
public class AsyncHttpRequestAdapter {

    private RequestParams mRequestParams;
    private Header[] mHeaders;
    private IBaseHTTPRequest mHTTPRequest;



    public AsyncHttpRequestAdapter(IBaseHTTPRequest request) {
        this.mHTTPRequest = request;
        this.mRequestParams = new RequestParams(request.getHttpBody());
//            this.
        HashMap<String, String> headerMap = request.getHttpHeader();
        if (null != headerMap) {
            ArrayList<Header> headerArrayList = new ArrayList<>();
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headerArrayList.add(new BasicHeader(entry.getKey(), entry.getValue()));
            }
            this.mHeaders = (Header[]) headerArrayList.toArray();
        }
    }

    public String getURL() {
        return mHTTPRequest.getURL();
    }


    public String getContentType() {
        return mHTTPRequest.getContentType();
    }

    public Header[] getHeaders() {
        return mHeaders;
    }

    public RequestParams getRequestParams() {
        return mRequestParams;
    }


    public ResponseHandlerInterface getResponseHandler() {

        return new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                mHTTPRequest.handleResponse(response);
                mHTTPRequest.complete();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                mHTTPRequest.handleFailure(statusCode, throwable.getLocalizedMessage());
                mHTTPRequest.complete();
            }
        };


    };

}
