package com.yanftch.test.mvp.leack_cancy;

import com.yanftch.test.mvp.Bean;

/**
 * @author 刘镓旗
 * @date 2017/11/16
 * @description V层接口，定义V层需要作出的动作的接口
 */
public interface RequestView2 {
    //请求时展示加载
    void requestLoading2();

    //请求成功
    void resultSuccess2(Bean result);

    //请求失败
    void resultFailure2(String result);
}