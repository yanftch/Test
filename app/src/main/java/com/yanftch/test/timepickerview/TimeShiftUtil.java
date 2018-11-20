package com.yanftch.test.timepickerview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeShiftUtil {

    /**
     * 格式yyyy-MM
     *
     * @param time 要转换的时间
     * @return
     */
    public static Date timeToDate(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException("时间格式不正确或时间不能为空");
        }
        return date;
    }

    /**
     * 转换时间格式yyyy-MM-dd hh:mm:ss
     *
     * @param mill 毫秒值 单位ms
     * @return 根据传入的毫秒值返回相应的时间
     */
    public static String millToDate(long mill) {
        Date date = new Date(mill);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = format.format(date);
        return time;
    }

}