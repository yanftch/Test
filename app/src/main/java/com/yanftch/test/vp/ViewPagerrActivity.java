package com.yanftch.test.vp;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.yanftch.test.R;
import com.yanftch.test.vp.pager.Pager1;
import com.yanftch.test.vp.pager.Pager2;
import com.yanftch.test.vp.pager.Pager3;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ViewPagerrActivity extends AppCompatActivity {
    private SimplePagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private static final String TAG = "dah_ViewPagerrActivity";
    private ViewPager viewPager;
    private ArrayList<BasePager> datas = new ArrayList<>();
    private LinearLayout mLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pagerr);
        Pager1 pager1 = new Pager1(this);
        Pager2 pager2 = new Pager2(this);
        Pager3 pager3 = new Pager3(this);
        datas.add(pager1);
        datas.add(pager2);
        datas.add(pager3);
        fbcListener();

        int i = 9;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list1.add("①" + i);
        }
        datas.get(0).initData(list1);

        ArrayList<String> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list2.add("②" + i);
        }
        datas.get(1).initData(list2);

        ArrayList<String> list3 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list3.add("③" + i);
        }
        datas.get(2).initData(list3);
    }

    private void fbcListener() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        settings();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new SimplePagerAdapter(datas, this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(tabLayout,60,60);
//            }
//        });
    }

    private void settings() {
        mLinearLayout = (LinearLayout) tabLayout.getChildAt(0);
        // 在所有子控件的中间显示分割线（还可能只显示顶部、尾部和不显示分割线）
        mLinearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
// 设置分割线的距离本身（LinearLayout）的内间距
        mLinearLayout.setDividerPadding(20);
// 设置分割线的样式
        mLinearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.divider_vertical));

    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
