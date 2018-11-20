package com.yanftch.test.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yanftch.test.R;
import com.yanftch.test.calendar.dialog.DialogFragmentUtils;
import com.yanftch.test.calendar.dialog.Person;
import com.yanftch.test.utils.SpannableStrings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Author : yanftch
 * Date   : 2017/6/30
 * Time   : 08:56
 * Desc   : 第一：需要返回本月第一天是周几
 * 第二：需要返回本月是几月，如"2011-01"
 * 第三：需要返回，每天是一个实体类，每个类里边，需要标志是否已经签到、是否可以点击以签到、当前是第几天（1-31）、
 */
public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = "dah_CalendarActivity";
    private TextView tv_go2sign;
    private RecyclerView recyclerview;
    private ArrayList<CBean> datas;
    private MyAdapter mMyAdapter;
    private TextView tv_month_show;
    private int monthBeginFlag;//本月第一天的开始标志
    private String todayStr;
    private int todayInt;
    private TotalCBean totalCBeanDatas;
    private TextView tv_sp;
    private ItemViewLinearLayout lllllllllll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        totalCBeanDatas = BeanDataUtils.getTotalCBeanDatas();
        Log.e(TAG, "onCreate: " + totalCBeanDatas);
        View view  = View.inflate(this,R.layout.layout_view_itemview,null);
        Person person = new Person(this,view);
        init();
    }

    private void init() {
        tv_go2sign = (TextView) findViewById(R.id.tv_go2sign);
        datas = new ArrayList<>();
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        tv_month_show = (TextView) findViewById(R.id.tv_month_show);
        mMyAdapter = new MyAdapter(this, datas);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 7);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(mMyAdapter);
        recyclerview.addItemDecoration(new DividerGridItemDecoration(this));
        initListener();
        fillData();
        datas.addAll(totalCBeanDatas.mCBeanArrayList);
        for (int i = 0; i < monthBeginFlag; i++) {
            CBean cBean = new CBean("", false, false);
            datas.add(0, cBean);
        }
        for (int i = 0; i < datas.size(); i++) {
            Log.w(TAG, "init: " + datas.get(i).toString());
        }
//        initDatas();
        tv_sp = (TextView) findViewById(R.id.tv_sp);
        tv_sp.setText(SpannableStrings.setUnderLine("大家好我们好我不好",1,5));
    }

    // TODO: 2017/6/30 此处的数据，都要是后台给的 调接口
    private void fillData() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sDateFormatYM = new SimpleDateFormat("yyyy年MM月");
        SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE");
        Date date = new Date();
        String sysDate = sDateFormat.format(date);
        String yearMonth = sDateFormatYM.format(date);
        String week = weekFormat.format(getFirstdayofThisMonth());
        monthBeginFlag = judgeFlag(week);
        Log.e(TAG, "fillData: 月份：" + yearMonth);
        Log.e(TAG, "fillData: 本月的第一天开始是(monthBeginFlag)：周 " + monthBeginFlag);
        Log.e(TAG, "fillData: 系统时间：" + sysDate);
        todayStr = sysDate.substring(8, 10);
        todayInt = Integer.parseInt(todayStr);
        Log.e(TAG, "fillData: 今天的日子是：" + todayStr + "           int todayInt = " + todayInt);
        tv_month_show.setText(yearMonth + "-" + weekFormat.format(date));
        //------------------------------------使用JSON生成的假数据的测试--------------------------------------------
        Log.e(TAG, "fillData: --------------------有点真的         假数据------------------------------");
        monthBeginFlag = Integer.parseInt(totalCBeanDatas.beginWeekStr);
        Log.e(TAG, "fillData: " + "本月的第一天，是周几呢？ -----------> " + monthBeginFlag);
        todayStr = totalCBeanDatas.currentDateStr;
        todayInt = Integer.parseInt(todayStr.substring(8, 10));
        Log.e(TAG, "fillData: 当前月份是：   " + todayStr + "          todayInt = " + todayInt);
        tv_month_show.setText(todayStr + "-");
    }

    /**
     * 获取本月第一天
     *
     * @return
     */
    public static Date getFirstdayofThisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
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

    private void initListener() {
        mMyAdapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                todayInt = 24;//假设今天是24号
                ArrayList<CBean> fromAdapterGetDatas = mMyAdapter.getDatas();
//                CBean bean = datas.get(position);
                CBean bean = fromAdapterGetDatas.get(position);
                int dateNo = Integer.parseInt(bean.getDateNo());
                if (dateNo != todayInt) {
                    Toast.makeText(CalendarActivity.this, "onItemClick: 除了今天的日期，其余的时间，是不能签到的，望知悉~~~~~~", Toast.LENGTH_SHORT).show();
                    return;
                }
                bean.setHasSign(true);
                bean.setCanBeSign(false);
                mMyAdapter.notifyItemChanged(position);
                Toast.makeText(CalendarActivity.this, "签到成功，本月第" + fromAdapterGetDatas.get(position).getDateNo() + "天", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onItemClick: 点击的是：position = " + position + "   日历：" + fromAdapterGetDatas.get(position).getDateNo());
            }
        });
        tv_go2sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(CalendarActivity.this, "签到成功？？？", Toast.LENGTH_SHORT).show();
                String substring = totalCBeanDatas.currentDateStr.substring(8, 10);
                Log.e(TAG, "今天是 " + substring + " 号");
                int parseInt = Integer.parseInt(substring);
                int position = parseInt + monthBeginFlag - 1;//RV中，今天对应的数据的position
                ArrayList<CBean> getDatas = mMyAdapter.getDatas();
                CBean bean = getDatas.get(position);
                Log.e(TAG, "onClick: " + bean.toString());
                if (bean.isCanBeSign()) {
                    int dateNo = Integer.parseInt(bean.getDateNo());
                    if (dateNo != todayInt) {
                        Toast.makeText(CalendarActivity.this, "onItemClick: 除了今天的日期，其余的时间，是不能签到的，望知悉~~~~~~", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    bean.setHasSign(true);
                    bean.setCanBeSign(false);
                    mMyAdapter.notifyItemChanged(position);
                    Toast.makeText(CalendarActivity.this, "签到成功，本月第" + bean.getDateNo() + "天", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CalendarActivity.this, "已签到，不能重复签到~", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initDatas() {
        for (int i = 0; i < monthBeginFlag; i++) {
            CBean cBean = new CBean("", false, false);
            datas.add(0, cBean);
        }
        //假设1，2号签到了
        for (int i = 1; i < 3; i++) {
            CBean bean = new CBean(i + "", true, false);
            datas.add(bean);
        }
        //3号---23号   都没有签到
        for (int i = 3; i < 24; i++) {
            CBean bean = new CBean(i + "", false, false);
            datas.add(bean);
        }
        //假设今天是24号，那么就只有24号是可以签的，后边的日子，是不能响应点击事件的
        for (int i = 24; i < 25; i++) {
            CBean bean = new CBean(i + "", false, true);
            datas.add(bean);
        }
        for (int i = 25; i < 31; i++) {
            CBean bean = new CBean(i + "", false, true);
            datas.add(bean);
        }
        //只有最后一天可以签到了
//        for (int i = 30; i < 31; i++) {
//            CBean bean = new CBean(i + "", false, true);
//            datas.add(bean);
//        }
        for (int i = 0; i < datas.size(); i++) {
            Log.w(TAG, "initDatas: 打印一下最终传入适配器的数据集：" + datas.get(i));
        }
    }

    public void btnShowDialog(View view) {
        DialogFragmentUtils dialog = new DialogFragmentUtils(this, "Title", "this is content ,this is content , this is content...", "L", "R", "https://s3.cn-north-1.amazonaws.com.cn/uploadfiles/activityFile/2b1374fe079149e59db89a2047b5317b.png", new DialogFragmentUtils.DialogClickListener() {
            @Override
            public void onLeftClick() {
                Toast.makeText(CalendarActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(CalendarActivity.this, "right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCloseClick() {
                Toast.makeText(CalendarActivity.this, "点击了右上角的X号了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onImageClick() {
                Toast.makeText(CalendarActivity.this, "点击了推广图片", Toast.LENGTH_SHORT).show();
            }
        });
//        dialog.setButtonTextColor(R.color.color_999999,R.color.colorAccent);
        dialog.show(getFragmentManager(), "showInfo");
    }
}
