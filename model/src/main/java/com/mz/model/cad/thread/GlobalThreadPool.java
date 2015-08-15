package com.mz.model.cad.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jaminzzhang on 6/10/15.
 */
public class GlobalThreadPool {

    private ExecutorService mExecutorService;
    private HandlerThread mHandlerThread;
    private Handler mHandler;

//    public HandlerThread getHandlerThread() {
//        return mHandlerThread;
//    }

    public static GlobalThreadPool getThreadPool() {
        return sThreadPool;
    }


    private static GlobalThreadPool sThreadPool = new GlobalThreadPool();
    public static GlobalThreadPool sharedPool() {
        return sThreadPool;
    }

    private GlobalThreadPool() {
        mExecutorService = Executors.newCachedThreadPool();

        mHandlerThread = new HandlerThread("GlobalThread") {
            @Override
            protected void onLooperPrepared() {
                mHandler = new Handler() {
                    @Override
                    public void dispatchMessage(Message msg) {
                        if (msg.getCallback() != null) {
                            mExecutorService.submit(msg.getCallback());
                        } else {
                            super.dispatchMessage(msg);
                        }
                    }
                };

            }
        };
        mHandlerThread.start();
    }

    public Handler getHandler() {
        return mHandler;
    }


    //    static public Handler getHandler() {
//        return sThreadPool.mHandler;
//    }





}
