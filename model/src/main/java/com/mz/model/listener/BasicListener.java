package com.mz.model.listener;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by jaminzzhang on 6/10/15.
 */
public abstract class BasicListener<T> implements IListener<T> {

    protected Handler mHandler;


    /**
     * Init Listener with handler
     * @param handler
     */
    public BasicListener(Handler handler) {
        this.mHandler = handler;
    }


    /**
     * Init listener & handler
     */
    public BasicListener() {
        if (null == getLooper()) {
            this.mHandler = new Handler();
        } else {
            this.mHandler = new Handler(getLooper());
        }
    }

    @Override
    public abstract void onFinish(T resultObj);

    @Override
    public abstract void onFailure(Error error);

    @Override
    public abstract void onCancel();

    @Override
    public Handler getHandler() {
        return this.mHandler;
    }

    @Override
    public Looper getLooper() {
        return null;
    }

    @Override
    public void sendFinishMessage(final T obj) {
        if (Thread.currentThread() == this.mHandler.getLooper().getThread()) {
            onFinish(obj);
        } else {
            this.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onFinish(obj);
                }
            });
        }
    }

    @Override
    public void sendCancelMessage() {
        if (Thread.currentThread() == this.mHandler.getLooper().getThread()) {
            onCancel();
        } else {
            this.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onCancel();
                }
            });
        }
    }

    @Override
    public void sendFailureMessage(final Error error) {

        if (Thread.currentThread() == this.mHandler.getLooper().getThread()) {
            onFailure(error);
        } else {
            this.mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure(error);
                }
            });
        }
    }
}
