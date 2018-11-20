package com.yanftch.test.calendar.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yanftch.test.R;

/**
 * User : yanftch
 * Date : 2017/7/6
 * Time : 08:52
 * Desc :
 */

public class Person {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private View rootView;
    private TextView tv_left, tv_middle, tv_right;
    private String leftText, middleText, rightText;
    private float leftTextSize, middleTextSize, rightTextSize;
    private int leftTextColor, middleTextColor, rightTextColor;
    private int leftBgShape, middleBgShape, rightBgShape;

    public Person(Context context, View view, String text, int textColor, float textSize, int bgShape) {
        rootView = view;
        init();
    }

    public Person(Context context, View view) {
    }

    private void init() {
        tv_left = (TextView) rootView.findViewById(R.id.tv_left);
        tv_middle = (TextView) rootView.findViewById(R.id.tv_middle);
        tv_right = (TextView) rootView.findViewById(R.id.tv_right);
        tv_left.setText(TextUtils.isEmpty(leftText) ? "" : leftText);
        tv_left.setTextSize(TypedValue.COMPLEX_UNIT_SP, (leftTextSize == 0) ? 12 : leftTextSize);
        tv_left.setTextColor(mContext.getResources().getColor((leftTextColor == 0) ? android.R.color.black : leftTextColor));
        tv_left.setBackgroundResource(leftBgShape);
        tv_middle.setText(TextUtils.isEmpty(middleText) ? "" : middleText);
        tv_middle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (middleTextSize == 0) ? 12 : middleTextSize);
        tv_middle.setTextColor(mContext.getResources().getColor((middleTextColor == 0) ? android.R.color.black : leftTextColor));
        tv_middle.setBackgroundResource(middleBgShape);
        tv_right.setText(TextUtils.isEmpty(rightText) ? "" : rightText);
        tv_right.setTextSize(TypedValue.COMPLEX_UNIT_SP, (rightTextSize == 0) ? 12 : rightTextSize);
        tv_right.setTextColor(mContext.getResources().getColor((rightTextColor == 0) ? android.R.color.black : leftTextColor));
        tv_right.setBackgroundResource(rightBgShape);
    }

}
