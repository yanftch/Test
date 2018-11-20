package com.yanftch.test.mvp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author : yanftch
 * Date : 2018/2/5
 * Time : 10:15
 * Desc :
 */

public interface ApiService {

    @GET("query")
    Call<Bean> getBeanData(@Query("key") String key,@Query("id") String id);

}
