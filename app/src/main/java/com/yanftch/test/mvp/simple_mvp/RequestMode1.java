package com.yanftch.test.mvp.simple_mvp;

import com.yanftch.test.mvp.Api;
import com.yanftch.test.mvp.ApiService;
import com.yanftch.test.mvp.Bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @description M层 数据层
 * 整理网络请求
 *
 */
public class RequestMode1 {
    private String url = Api.base_url;
    //http://www.weather.com.cn/data/cityinfo/101010100.html
    public void http_getBean(String key , String id, Callback<Bean> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        //请求数据
        Call<Bean> call = apiService.getBeanData(key, id);
        //回调请求结果
        call.enqueue(callback);
    }
}