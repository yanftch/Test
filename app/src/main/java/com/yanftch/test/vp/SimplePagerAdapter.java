package com.yanftch.test.vp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SimplePagerAdapter extends PagerAdapter {
    private ArrayList<BasePager> datas = new ArrayList<>();
    private Context mContext;
    private ArrayList<String> titles = new ArrayList<>();

    public SimplePagerAdapter(ArrayList<BasePager> datas, Context context) {
        this.datas = datas;
        mContext = context;
        titles.add("产品收藏");
        titles.add("发现收藏");
        titles.add("第三步就");
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager basePager = datas.get(position);
        View view = basePager.rootView;
        container.addView(view);
        return view;
    }
}