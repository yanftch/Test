package com.yanftch.test.calendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanftch.test.R;

/**
 * User : yanftch
 * Date : 2017/7/5
 * Time : 17:11
 * Desc :
 */

public class ItemViewLinearLayout extends LinearLayout {
    private static final String TAG = "dah_ItmViewLinearLayout";
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private View rootView;
    private TextView tv_left, tv_middle, tv_right;
    private String leftText, middleText, rightText;
    private float leftTextSize, middleTextSize, rightTextSize;
    private int leftTextColor, middleTextColor, rightTextColor;
    private int leftBgShape,middleBgShape,rightBgShape;


    private void init(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        rootView = mLayoutInflater.inflate(R.layout.layout_view_itemview, this, false);
        tv_left = (TextView) rootView.findViewById(R.id.tv_left);
        tv_middle = (TextView) rootView.findViewById(R.id.tv_middle);
        tv_right = (TextView) rootView.findViewById(R.id.tv_right);
    }

    public ItemViewLinearLayout setLeftText(String leftText, float textSize, int textColor, int bgShape) {
        this.leftText = leftText;
        this.leftTextSize = textSize;
        this.leftTextColor = textColor;
        this.leftBgShape = bgShape;
        tv_left.setText(TextUtils.isEmpty(leftText) ? "" : leftText);
        tv_left.setTextSize(TypedValue.COMPLEX_UNIT_SP, (leftTextSize == 0) ? 12 : leftTextSize);
        tv_left.setTextColor(mContext.getResources().getColor((leftTextColor == 0) ? android.R.color.black : leftTextColor));
        tv_left.setBackgroundResource(leftBgShape);
        return this;
    }

    public ItemViewLinearLayout setMiddleText(String middleText, float textSize, int textColor, int bgShape) {
        this.middleText = middleText;
        this.middleTextSize = textSize;
        this.middleTextColor = textColor;
        this.middleBgShape = bgShape;
        tv_middle.setText(TextUtils.isEmpty(middleText) ? "" : middleText);
        tv_middle.setTextSize(TypedValue.COMPLEX_UNIT_SP, (middleTextSize == 0) ? 12 : middleTextSize);
        tv_middle.setTextColor(mContext.getResources().getColor((middleTextColor == 0) ? android.R.color.black : leftTextColor));
        tv_middle.setBackgroundResource(middleBgShape);
        return this;
    }

    public ItemViewLinearLayout setRightText(String rightText, float textSize, int textColor, int bgShape) {
        this.rightText = rightText;
        this.rightTextSize = textSize;
        this.rightTextColor = textColor;
        this.rightBgShape = bgShape;

        tv_right.setText(TextUtils.isEmpty(rightText) ? "" : rightText);
        tv_right.setTextSize(TypedValue.COMPLEX_UNIT_SP, (rightTextSize == 0) ? 12 : rightTextSize);
        tv_right.setTextColor(mContext.getResources().getColor((rightTextColor == 0) ? android.R.color.black : leftTextColor));
        tv_right.setBackgroundResource(rightBgShape);
        return this;
    }

    public ItemViewLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public ItemViewLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ItemViewLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

}
