package com.mz.model;

import android.app.Application;

import com.mz.model.cad.thread.GlobalThreadPool;

/**
 * Created by Jamin on 8/10/15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        this.launch();
    }


    private void launch() {
        GlobalThreadPool.sharedPool();
    }

}
