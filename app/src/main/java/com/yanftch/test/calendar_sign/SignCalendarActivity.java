package com.yanftch.test.calendar_sign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yanftch.test.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignCalendarActivity extends AppCompatActivity {
    private SignCalendar calendar;
    private String date;
    private int years;
    private String months;
    private Button btn_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_calendar);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        date = formatter.format(curDate);
        calendar = (SignCalendar) findViewById(R.id.sc_main);
        btn_sign = (Button) findViewById(R.id.btn_sign);
        btn_sign.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                List<String> list = new ArrayList<String>();
                list.add("2016-06-30");
                list.add(date);
                // calendar.setCalendarDaysBgColor(list,
                // R.drawable.bg_sign_today);
                calendar.addMarks(list, 0);
            }
        });
    }
}
