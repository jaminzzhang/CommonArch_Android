package com.mz.model.request.client;

import com.mz.model.request.IBaseHTTPRequest;

/**
 * Created by Jamin on 8/6/15.
 */
public interface IRequestClient {

    /**
     * Send Http request to the server
     * @param request
     */
    public void sendRequest(IBaseHTTPRequest request);

}
