package com.yanftch.test.rxjava2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * User : yanftch
 * Date : 2017/7/11
 * Time : 10:16
 * Desc :
 */

public class BiView extends View {
    private static final String TAG = "dah_BiView";
    private int width, height;
    private Paint mPaint;
    private Path mPath;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        Log.e(TAG, "onSizeChanged: width = " + width + "     height = " + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.moveTo(0,0);
        mPath.quadTo(width/2,height/2,width,height);
        canvas.drawPath(mPath,mPaint);
    }

    public BiView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#dd3333"));
        mPath = new Path();

    }

    public BiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}
