package com.yanftch.test.header_rv;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * User : yanftch
 * Date : 2017/7/21
 * Time : 13:26
 * Desc :
 */

public class MyItemDecoration2 extends RecyclerView.ItemDecoration {
    private static final String TAG = "dah_MyItemDecoration";
    private Paint mPaint, mPaint2;
    private final int DIVIDER_BOTTOM = 2;

    // 在构造函数里进行绘制的初始化，如画笔属性设置等
    public MyItemDecoration2() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.parseColor("#00ffff"));
        // 画笔颜色设置为红色
    }

    // 重写getItemOffsets（）方法
    // 作用：设置矩形OutRect 与 Item 的间隔区域
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemPosition = parent.getChildAdapterPosition(view);
        // 获得每个Item的位置
        outRect.set(0, 0, 0, DIVIDER_BOTTOM);
        // 设置间隔区域为10px,即onDraw()可绘制的区域为10px
    }

    // 重写onDraw（）
    // 作用:在间隔区域里绘制一个矩形，即分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        // 获取RecyclerView的Child view的个数--是获取的一屏的数目
        int childCount = parent.getChildCount();

        // 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
        for (int i = 0; i < childCount; i++) {
            // 获取每个Item的位置
            final View child = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(child);

            // 获取布局参数
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            // 设置矩形(分割线)的宽度为10px
            final int mDivider = DIVIDER_BOTTOM;

            int left = 0;
            final int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            final int right = parent.getWidth() - parent.getPaddingRight();
            final int bottom = top + mDivider;
            if (i % 2 == 0) {
                left = 50;
                c.drawRect(left, top, right, bottom, mPaint);
                c.drawRect(0, top, left + parent.getLeft(), bottom, mPaint2);
            } else {
                left = 0;
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }
}