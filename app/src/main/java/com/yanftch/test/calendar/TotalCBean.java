package com.yanftch.test.calendar;

import java.util.ArrayList;

/**
 * User : yanftch
 * Date : 2017/6/30
 * Time : 11:43
 * Desc :
 */

public class TotalCBean {
    public ArrayList<CBean> mCBeanArrayList;
    public String currentDateStr;//格式是：2011-11-11
    public String beginWeekStr;//4或者四，都行，最好是4，可以直接转换成Int的4了~~~
    public Object todoObj;//待定-保留字段
    public String productImgUrl;//签到成功推广活动图片的URL
    public String productFlagType;//跳转标志位？
    public String introduceInfo;//介绍性的文案
    /**
     * "code"：1，
     * "msg":null,
     * "content":{
     *     "currentDateStr":"2011-11-11",
     *     "beginWeekStr":"4",
     *     "productImgUrl":"",
     *     ...//其他需要的字段
     *     "CBean":[
     *              {
     *                  "dateNo":1,
     *                  "hasSign":true,
     *                  "canBeSign":false,
     *              },
     *               {
     *                  "dateNo":2,
     *                  "hasSign":true,
     *                  "canBeSign":false,
     *              },
     *              ...
     *     ]
     * }
     */
}
