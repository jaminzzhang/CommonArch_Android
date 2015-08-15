package com.mz.model.cad.listener;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by jaminzzhang on 6/10/15.
 */
public interface IListener<T> {


    /*
     * The handler to be notified
     */
    Handler getHandler();

    /*
     * Callback Looper
     */
    Looper getLooper();


    /**
     * Callback
     * @param resultObj
     */
    void onFinish(T resultObj);

    /*
     * Callback
     *
     * @param error   cause of task failure
     */
    void onFailure(Error error);

    /*
     * Callback
     */
    void onCancel();



    /**
     * Notifies callback, that task was completed
     */
    void sendFinishMessage(final T obj);


    /**
     * Notifies callback, that task was cancelled
     */
    void sendCancelMessage();


    /**
     * Returns if task was completed with error code or failure of implementation
     *
     * @param error        cause of task failure
     */
    void sendFailureMessage(Error error);



}
