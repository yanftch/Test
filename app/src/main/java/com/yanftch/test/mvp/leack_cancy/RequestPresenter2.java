package com.yanftch.test.mvp.leack_cancy;

import android.os.Handler;

import com.yanftch.test.mvp.Bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author : yanftch
 * Date : 2018/2/5
 * Time : 11:01
 * Desc : P 层
 * 放这内存泄漏的写法
 */

public class RequestPresenter2 {
    private RequestMode2 mRequestMode2;
    private RequestView2 mRequestView2;

    public RequestPresenter2() {
        mRequestMode2 = new RequestMode2();
    }

    public void attach(RequestView2 view2) {
        this.mRequestView2 = view2;
    }

    public void detach() {
        mRequestView2= null;
    }
    /**
     * 取消网络请求
     */
    public void interruptHttp(){
        mRequestMode2.interruptHttp();
    }

    public void P_getData(final String key, final String id) {
        if (null != mRequestView2) {
            mRequestView2.requestLoading2();//请求之前
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRequestMode2.http_getBean(key, id, new Callback<Bean>() {
                        @Override
                        public void onResponse(Call<Bean> call, Response<Bean> response) {
                            if (null != mRequestView2) {
                                mRequestView2.resultSuccess2(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<Bean> call, Throwable t) {
                            if (null != mRequestView2) {
                                mRequestView2.resultFailure2(t.getMessage());
                            }
                        }
                    });
                }
            }, 1000);
        }
    }
}
