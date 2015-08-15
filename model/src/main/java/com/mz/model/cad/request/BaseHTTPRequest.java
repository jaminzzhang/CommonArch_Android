package com.mz.model.cad.request;

import com.mz.model.cad.basic.MZError;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Jamin on 8/5/15.
 */
public abstract class BaseHTTPRequest implements IBaseHTTPRequest {

    private static final int MAX_RETRY_COUNT = 3;
    protected int mRetryCount = 0;

    private static final long MAX_TIME_OUT = 60000; //60秒
    protected long mSendTimeMillis;
    protected RequestListener mRequestListener;
    protected MZError mError;



    public BaseHTTPRequest() {
        this.mRetryCount = 0;
    }

    public BaseHTTPRequest(RequestListener requestListener) {
        mRequestListener = requestListener;
    }

    @Override
    public RequestListener getRequestListener() {
        return mRequestListener;
    }


    @Override
    public void setRequestListener(RequestListener listener) {
        mRequestListener = listener;
    }


    public MZError getError() {
        return mError;
    }

    @Override
    public long getSendTimeMillis() {
        return mSendTimeMillis;
    }

    @Override
    public Boolean canRetry() {
        return this.mRetryCount < MAX_RETRY_COUNT;
    }

    @Override
    public void send() {

        if (this.mSendTimeMillis == 0) {
            this.mSendTimeMillis = System.currentTimeMillis();
        }
        this.mRetryCount++;
        RequestTaskManager.sharedInstance().sendRequest(this);
    }


    @Override
    public void cancel() {

    }

    @Override
    public void complete() {
        if (null != this.getError()) {
            this.getRequestListener().onFailure(this);
        } else {
            this.getRequestListener().onSuccess(this);
        }

    }

    @Override
    public abstract String getURL();

    @Override
    public String getHttpMethod() {
        return "POST";
    }


    @Override
    public String getContentType() {
        return "application/json";
    }

    @Override
    public abstract HashMap<String, String> getHttpHeader();

    @Override
    public abstract HashMap<String, Object> getHttpBody();


    @Override
    public abstract void handleResponse(JSONObject response);


    @Override
    public void handleFailure(int statusCode, String errorMsg) {
        MZError error = new MZError(statusCode, errorMsg);
        this.mError = error;
    }


}
