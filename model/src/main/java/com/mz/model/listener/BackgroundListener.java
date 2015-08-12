package com.mz.model.listener;

import com.mz.model.thread.GlobalThreadPool;

/**
 * Created by jaminzzhang on 6/10/15.
 */
public abstract class BackgroundListener<T> extends BasicListener<T> {


    public BackgroundListener() {
        super(GlobalThreadPool.sharedPool().getHandler());
    }



}
