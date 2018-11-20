package com.yanftch.test.vp;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * User : yanftch
 * Date : 2017/5/11
 * Time : 13:41
 * Desc :
 */


public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
//
//	@Override
//	public boolean dispatchTouchEvent(MotionEvent ev) {
//		// 自己处理触摸事件不需要父布局（listview）的相同事件覆盖
//		getParent().requestDisallowInterceptTouchEvent(true);
//		return super.dispatchTouchEvent(ev);
//	}

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
