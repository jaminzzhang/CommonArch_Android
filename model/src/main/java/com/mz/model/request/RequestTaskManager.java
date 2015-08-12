package com.mz.model.request;


import com.mz.model.request.client.OKRequestClient;

import junit.framework.Assert;

/**
 * Created by Jamin on 8/5/15.
 */
public class RequestTaskManager {

//    private AsyncRequestClient mRequestClient = new AsyncRequestClient(null);
    private OKRequestClient mRequestClient = new OKRequestClient();

    private static final RequestTaskManager sTaskManager = new RequestTaskManager();
    public static RequestTaskManager sharedInstance() {
        return sTaskManager;
    }

    private RequestTaskManager() {

    }


    public void sendRequest(final IBaseHTTPRequest request) {

        Assert.assertNotNull(request);
        mRequestClient.sendRequest(request);
    }





}
