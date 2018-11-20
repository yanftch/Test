package com.yanftch.test.mvp_new.mvp1;

/**
 * Author : yanftch
 * Date : 2018/2/6
 * Time : 08:59
 * Desc : 所有MVP的接口
 */

public interface Mvp1Contract {
     interface View{
         //显示数据
         void showData(String data);
     }

     interface Presenter{
         void handleData();//通知M层去获取数据，并且要将数据传递给V层展示
     }

     interface Model{
         String getData();//获取数据的吧
     }
}
