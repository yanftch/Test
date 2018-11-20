package com.yanftch.test.other_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * User : yanftch
 * Date : 2017/4/19
 * 带锯齿形状的View
 */

public class SerratedView extends LinearLayout {
    private static final String TAG = "dah_SerratedView";

    private int circleNum;//圆圈的数量
    private int spaceNum;//间距的数量（间距数量= 圆圈数量+1）
    private int space = 5;//间距的宽度
    private float radius = 10;//圆圈的半径
    private int width;//View的宽度
    private int height;//View的高度
    private Paint paint;//默认画笔
    private final String BORDER_COLOR = "#ffdd44";//边框的颜色
    private float leftSp;

    private void init(AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor(BORDER_COLOR));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        if (leftSp == 0) {
            leftSp = (int) (w - space) % (2 * radius + space);
        }
        circleNum = (int) ((width - space) / (2 * radius + space));
        Log.e(TAG, "onSizeChanged:     circleNum ====  " + circleNum);
        Log.e(TAG, "onSizeChanged: width === " + width);
        Log.e(TAG, "onSizeChanged:      " + (circleNum * 15 + (circleNum + 1) * 15));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //循环绘制每个小圆圈
        for (int i = 0; i < circleNum; i++) {
            float x = space+ radius + leftSp / 2 + ((space + 2 * radius) * i);
            canvas.drawCircle(x, 0, radius, paint);
            canvas.drawCircle(x, height, radius, paint);
        }
    }

    public SerratedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public SerratedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
}
