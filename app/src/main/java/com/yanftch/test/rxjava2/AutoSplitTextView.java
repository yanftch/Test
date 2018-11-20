package com.yanftch.test.rxjava2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class AutoSplitTextView extends View {
    private static final String TAG = "dah_AutoSplitTextView";
    private Paint mPaint;
    private Path mPath;
    private boolean mEnabled = true;
    private int width, height;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        Log.e(TAG, "onSizeChanged: " + width + "      " + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.moveTo(200, 200);//移动到起始点
        mPath.quadTo(300, 200, 400, 400);//x轴拉伸了

        canvas.drawPath(mPath, mPaint);

        super.onDraw(canvas);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#FF0000"));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPath = new Path();
    }

    public AutoSplitTextView(Context context) {
        super(context);
        init();
    }

    public AutoSplitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoSplitTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


}