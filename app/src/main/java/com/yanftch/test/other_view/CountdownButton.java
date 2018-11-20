package com.yanftch.test.other_view;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.Button;

import com.yanftch.test.R;

public class CountdownButton extends Button {
    private int totalTime = 10 * 1000;
    private int interval = 1000;
    private boolean isRunning = false;//是否正在倒计时

    private String text = "获取验证码";

    private void init(int totalTime) {
        this.totalTime = totalTime;

    }

    public void start() {
        new CountDownTimer(totalTime, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                CountdownButton.this.setTextColor(getResources().getColor(R.color.color_cccccc));
                CountdownButton.this.setEnabled(false);
                CountdownButton.this.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                CountdownButton.this.setText(text);
                CountdownButton.this.setEnabled(true);
                CountdownButton.this.setTextColor(getResources().getColor(R.color.pickerview_timebtn_nor));
            }
        }.start();
    }

    public CountdownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}