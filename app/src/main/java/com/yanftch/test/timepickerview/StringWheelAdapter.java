package com.yanftch.test.timepickerview;


import com.yanftch.test.utils.S;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by liu on 17/3/2.
 */

public class StringWheelAdapter implements WheelAdapter<String> {

    private ArrayList<String> values;

    private StringWheelAdapter(){}

    /**
     * 根据开始以及结束年月计算显示的条数
     * @param startDate 起始年月(2016年8月)
     * @param endDate   结束年月(2017年12月)
     */
    public StringWheelAdapter(String startDate, String endDate) {
        values = S.getMonthBetween(startDate, endDate);
    }

    @Override
    public int getItemsCount() {
       return values.size();
    }

    @Override
    public String getItem(int index) {
        return values.get(index);
    }

    @Override
    public int indexOf(String o) {
        for (int i = 0; i < values.size(); i++) {
            if (o.equals(values.get(i))) {
                return i;
            }
        }
        return 0;
    }

    public int getIndex(Date date) {
        if (null != values) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");// 格式化为年月
            String dateStr = sdf.format(date);

            for (int i = 0; i < values.size(); i++) {
                String s = values.get(i);
                if (s.equals(dateStr)) {
                    return i;
                }
            }
        }
        return 0;
    }
}
