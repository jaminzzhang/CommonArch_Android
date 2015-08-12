package com.mz.model.request.client;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.loopj.android.http.AsyncHttpClient;
import com.mz.model.request.IBaseHTTPRequest;

/**
 * Created by Jamin on 8/6/15.
 */
public class AsyncRequestClient implements IRequestClient {

    private AsyncHttpClient mHttpClient;
    private Context mContext;
    private TaskHandlerThread mTaskThread;

    public AsyncRequestClient(Context context) {
        mContext = context;

        this.mTaskThread = new TaskHandlerThread();
        this.mTaskThread.start();
    }

    @Override
    public void sendRequest(final IBaseHTTPRequest request) {

        if (null == this.mTaskThread.mHandler) {
            this.mTaskThread.waitUntilReady();
        }

        this.mTaskThread.mHandler.post(new Runnable() {

            @Override
            public void run() {
                AsyncHttpRequestAdapter requestAdapter = new AsyncHttpRequestAdapter(request);
                if (request.getHttpMethod().equalsIgnoreCase("GET")) {

                    mHttpClient.get(mContext,
                            request.getURL(),
                            requestAdapter.getHeaders(),
                            requestAdapter.getRequestParams(),
                            requestAdapter.getResponseHandler());


                } else if (request.getHttpMethod().equalsIgnoreCase("POST"))  {

                    mHttpClient.post(mContext,
                            request.getURL(),
                            requestAdapter.getHeaders(),
                            requestAdapter.getRequestParams(),
                            request.getContentType(),
                            requestAdapter.getResponseHandler());

                }
            }
        });

    }



    //=======================================TaskHandlerThread======================================
    static class TaskHandlerThread extends HandlerThread {
        public Handler mHandler;
        private TaskHandlerThread() {
            super("TaskThread");
        }

        public synchronized void waitUntilReady() {
            mHandler = new Handler(this.getLooper());
        }
    }


}
