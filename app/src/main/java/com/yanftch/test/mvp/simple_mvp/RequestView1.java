package com.yanftch.test.mvp.simple_mvp;

import com.yanftch.test.mvp.Bean;

/**
 * @author 刘镓旗
 * @date 2017/11/16
 * @description V层接口，定义V层需要作出的动作的接口
 */
public interface RequestView1 {
    //请求时展示加载
    void requestLoading();
    //请求成功
    void resultSuccess(Bean result);
    //请求失败
    void resultFailure(String result);
}