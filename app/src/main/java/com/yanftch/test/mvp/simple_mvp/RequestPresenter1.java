package com.yanftch.test.mvp.simple_mvp;

import android.os.Handler;

import com.yanftch.test.mvp.Bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @description P层
 * 特点:需要持有M层和V层
 */
public class RequestPresenter1 {
    /**
     * 持有M层  和  V层的引用
     */
    private final RequestView1 mRequestView;
    private final RequestMode1 mRequestMode;

    public RequestPresenter1(RequestView1 requestView) {
        this.mRequestView = requestView;
        this.mRequestMode = new RequestMode1();
    }


    /**
     * P 层去调用请求接口
     *
     * @param key
     * @param id
     */
    public void P_getData(final String key, final String id) {
        mRequestView.requestLoading();//请求之前
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRequestMode.http_getBean(key, id, new Callback<Bean>() {
                    @Override
                    public void onResponse(Call<Bean> call, Response<Bean> response) {
                        mRequestView.resultSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<Bean> call, Throwable t) {

                    }
                });
            }
        }, 1000);
    }
}