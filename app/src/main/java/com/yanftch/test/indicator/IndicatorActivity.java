package com.yanftch.test.indicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yanftch.test.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndicatorActivity extends AppCompatActivity {

    private ViewPager mViewPager;//设置关联的Viewpager
    private ViewPagerIndicator mIndicator;//设置关联的Viewpager指示器
    private List<Fragment> mTabs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);
        initView();
        initData();
    }
    private void initData() {
        List<String> mTitles = Arrays.asList("产品收藏","发现收藏");
//List<String> mTitles = Arrays.asList("测试1", "测试2", "测试3","测试4", "测试5", "测试6","测试7", "测试8", "测试9","测试10");
        final int size = mTitles.size();
        final Fragment[] mFragments = new Fragment[size];
        int i;
        for (i = 0; i < size; i++){
            mFragments[i] = TabFragment.newInstance(mTitles.get(i));
        }
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return size;
            }
        };
        mViewPager.setAdapter(adapter);
//给tab加上标题
        mIndicator.setTabItemTitles(mTitles);
//设置关联的ViewPager，默认显示第一个
        mIndicator.setViewPager(mViewPager,0);
    }
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_collection);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.vp_indicator);
    }
}
