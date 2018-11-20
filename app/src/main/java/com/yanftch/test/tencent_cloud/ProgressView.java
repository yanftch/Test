package com.yanftch.test.tencent_cloud;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yanftch.test.R;

/**
 * User : yanftch
 * Date : 2017/8/28
 * Time : 10:32
 * Desc :
 */

public class ProgressView extends LinearLayout {
    private static final String TAG = "dah_ProgressView";
    private View rootView;
    private ProgressBar progress_view;
    private TextView tv_speed, tv_total;

    public void setData(int progress, String speed, String total) {
        progress_view.setProgress(progress);
        tv_speed.setText(speed);
        tv_total.setText(total);
    }

    private void init(Context context) {
        this.setOrientation(VERTICAL);
        this.setBackgroundColor(Color.parseColor("#00ffff"));
        rootView = LayoutInflater.from(context).inflate(R.layout.layout_progress_view, this, false);
        progress_view = (ProgressBar) rootView.findViewById(R.id.progress_view);
        tv_speed = (TextView) rootView.findViewById(R.id.tv_speed);
        tv_total = (TextView) rootView.findViewById(R.id.tv_total);
        this.addView(rootView);
    }

    public ProgressView(Context context) {
        super(context);
        init(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
}
