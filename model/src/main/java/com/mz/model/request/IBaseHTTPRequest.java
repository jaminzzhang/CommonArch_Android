package com.mz.model.request;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Jamin on 8/5/15.
 */
public interface IBaseHTTPRequest {

    /**
     * Get Listener of the request
     * @return
     */
    public RequestListener getRequestListener();


    /**
     * Set Listener of the request
     */
    public void setRequestListener(RequestListener listener);

    /**
     * The time when request was sent
     * @return
     */
    public long getSendTimeMillis();


    /**
     * Return if this request can be retried
     * @return
     */
    public Boolean canRetry();


    /**
     * Send the request
     */
    public void send();


    /**
     * request url
     * @return
     */
    public String getURL();

    /**
     * Get HTTP Method
     * @return
     */
    public String getHttpMethod();

    /**
     * Return the content type of the payload you are sending.
     * @return
     */
    public String getContentType();


    public HashMap<String, String> getRequestHeader();
    public HashMap<String, Object> getRequestBody();


    /**
     * Handle http response
     * @param response
     */
    public void handleResponse(JSONObject response);


    /**
     * Handle http failure
     * @param statusCode
     */
    public void handleFailure(int statusCode, String errorMsg);



}
