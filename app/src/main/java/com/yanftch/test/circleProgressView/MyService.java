package com.yanftch.test.circleProgressView;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author : yanftch
 * Date : 2018/3/12
 * Time : 14:57
 * Desc :
 */

public class MyService extends Service {
    private static final String TAG = "dah_MyService";


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return new MyBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class MyBinder extends Binder {
        public String http_load() {
            Log.e(TAG, "http_load: 去下载事件。。。。。。。");
            return "1234567890";
        }
    }
}
