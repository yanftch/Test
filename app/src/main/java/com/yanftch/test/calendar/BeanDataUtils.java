package com.yanftch.test.calendar;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * User : yanftch
 * Date : 2017/6/30
 * Time : 11:34
 * Desc : 生成假的后台接口数据
 * 情景模拟：
 * 当前是7月份，
 * 1，2，3，4已经签到了
 * 5、6没签到
 * 今天是7号
 * 本月共有31天
 * 7月份第一天是周六
 */

public class BeanDataUtils {
    private static String beginWeekStr = "6";

    /**
     * 获取日历实体类的集合
     *
     * @return 集合
     */
    public static ArrayList<CBean> getCalendarListDatas() {
        ArrayList<CBean> list = new ArrayList<>();

        // TODO: 2017/6/30 生成自己的测试数据
        //按照7月份来设计吧
//        for (int i = 0; i < Integer.parseInt(beginWeekStr); i++) {
//            CBean cBean = new CBean("", false, false);
//            list.add(0, cBean);
//        }
        //已未签到&不可以点击
        for (int i = 1; i < 5; i++) {
            CBean cBean = new CBean(String.valueOf(i), true, false);
            list.add(cBean);
        }
        //未签到&不可以点击
        for (int i = 5; i < 7; i++) {
            CBean cBean = new CBean(String.valueOf(i), false, false);
            list.add(cBean);
        }
        //未签到&可以点击
        for (int i = 7; i < 8; i++) {
            CBean cBean = new CBean(String.valueOf(i), false, true);
            list.add(cBean);
        }
        //未签到&可以点击
        for (int i = 8; i < 32; i++) {
            CBean cBean = new CBean(String.valueOf(i), false, true);
            list.add(cBean);
        }
        return list;
    }

    public static TotalCBean getTotalCBeanDatas() {
        TotalCBean totalCBean = new TotalCBean();
        totalCBean.beginWeekStr = "6";//7月的第一天是周六
        totalCBean.currentDateStr = "2017-07-07";//加入今天是0706日
        totalCBean.mCBeanArrayList = getCalendarListDatas();
        return totalCBean;
    }

    private int judgeFlag(String week) {
        if (TextUtils.isEmpty(week)) return 0;
        if (week.contains("日")) {
            return 7;
        } else if (week.contains("一")) {
            return 1;
        } else if (week.contains("二")) {
            return 2;
        } else if (week.contains("三")) {
            return 3;
        } else if (week.contains("四")) {
            return 4;
        } else if (week.contains("五")) {
            return 5;
        } else if (week.contains("六")) {
            return 6;
        } else {
            return 0;
        }
    }
}
