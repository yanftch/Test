package com.yanftch.test;

import android.app.Application;

import com.yanzhenjie.nohttp.NoHttp;

/**
 * User : yanftch
 * Date : 2017/4/19
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
    }
}
