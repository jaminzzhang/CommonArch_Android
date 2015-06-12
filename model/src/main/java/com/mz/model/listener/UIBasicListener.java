package com.mz.model.listener;

import android.os.Looper;

/**
 * Created by jaminzzhang on 6/10/15.
 */
public abstract class UIBasicListener<T> extends BasicListener<T> {

    @Override
    public Looper getLooper() {
        return Looper.getMainLooper();
    }

}
