package com.mz.model.cad.basic;

import android.os.*;

import com.mz.model.cad.thread.GlobalThreadPool;

/**
 * Created by Jamin on 8/7/15.
 */
public class BaseLogicHandler {

    private Handler mHandler;
    private HandlerThread mHandlerThread;

    public Handler getHandler() {
        if (null == mHandler) {
            return GlobalThreadPool.sharedPool().getHandler();
        }

        return mHandler;
    }

    public BaseLogicHandler(Handler handler) {
        this.mHandler = handler;
    }


    public BaseLogicHandler(boolean customThread) {

        if (customThread) {
            this.mHandlerThread = new HandlerThread(this.getClass().getName(), android.os.Process.THREAD_PRIORITY_BACKGROUND) {
                @Override
                protected void onLooperPrepared() {
                    mHandler = new Handler();
                }
            };
            this.mHandlerThread.start();
        }
    }




    protected void finalize() throws Throwable {
        if (null != this.mHandlerThread) {
            this.mHandlerThread.quit();
        }
    }
}
