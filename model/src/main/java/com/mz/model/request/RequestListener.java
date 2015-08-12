package com.mz.model.request;

/**
 * Created by Jamin on 8/5/15.
 */
public abstract class RequestListener<T> {

    public boolean needRetry(int retryNo) { /* compiled code */ return false;}

    public void onCancel(T taskRequest) { /* compiled code */ }

    public void onSuccess(T taskRequest)  { /* compiled code */ }

    public void onFailure(T taskRequest)  { /* compiled code */ }


}
