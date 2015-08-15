package com.mz.model.cad.basic;

/**
 * Created by Jamin on 8/6/15.
 */
public class MZError extends Error {

    private int mCode;

    public MZError(int code) {
        mCode = code;
    }

    public MZError(int code, String detailMessage) {
        super(detailMessage);
        mCode = code;
    }

    public MZError(int code, Throwable throwable) {
        super(throwable);
        mCode = code;
    }

    public MZError(int code, String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        mCode = code;
    }


    public int getCode() {
        return mCode;
    }
}
