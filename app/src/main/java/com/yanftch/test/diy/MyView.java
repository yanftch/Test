package com.yanftch.test.diy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Author : yanftch
 * Date : 2018/3/29
 * Time : 21:21
 * Desc :
 */

public class MyView extends View {

    private static final String TAG = "dah_MyView";


    private Paint mPaint;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure: ");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "onLayout: changed=" + changed + "  left=" + left + "  top=" + top + "  right=" + right + "  bottom=" + bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: ");
        canvas.drawLine(100, 100, 300, 300, getPaint());
    }

    private Paint getPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(false);
        mPaint.setColor(Color.parseColor("#ff0000"));
        mPaint.setStrokeWidth(10);
        return mPaint;
    }

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
