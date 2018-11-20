package com.yanftch.test.status;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanftch.test.R;


/**
 * User : yanftch
 * Date : 2017/4/18
 * 进度条～
 */

public class ProgressStatusLayout extends FrameLayout {
    private Context context;
    private TextView tv_line_left, tv_line_right;//进度线
    private ImageView iv_icon_left, iv_icon_middle, iv_icon_right;//3个图标
    private TextView title_left, title_middle, title_right;//底部文本
    private TextView title_left_bottom, title_middle_bottom, title_right_bottom;//底部时间
    private int progress_color;//进度颜色
    private static final String DEFAULT_GRAY_COLOR = "#CFCFCF";

    public ProgressStatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(attrs);
    }

    public ProgressStatusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(attrs);
    }

    /**
     * 初始化
     *
     * @param attrs
     */
    private void initView(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.progressStatus);
        //三个图标
        int iconLeft = typedArray.getResourceId(R.styleable.progressStatus_ivLeft, -1);
        int iconMiddle = typedArray.getResourceId(R.styleable.progressStatus_ivMiddle, -1);
        int iconRight = typedArray.getResourceId(R.styleable.progressStatus_ivRight, -1);
        //三个图标背景色----
        int iconLeftBg = typedArray.getResourceId(R.styleable.progressStatus_ivLeft_bg, -1);
        int iconMiddleBg = typedArray.getResourceId(R.styleable.progressStatus_ivMiddle_bg, -1);
        int iconRightBg = typedArray.getResourceId(R.styleable.progressStatus_ivRight_bg, -1);

        //底部上边文本
        String text_left = typedArray.getString(R.styleable.progressStatus_text_left);
        String text_mid = typedArray.getString(R.styleable.progressStatus_text_middle);
        String text_right = typedArray.getString(R.styleable.progressStatus_text_right);
        //底部下部文本
        String text_left_bottom = typedArray.getString(R.styleable.progressStatus_text_left_bottom);
        String text_mid_bottom = typedArray.getString(R.styleable.progressStatus_text_middle_bottom);
        String text_right_bottom = typedArray.getString(R.styleable.progressStatus_text_right_bottom);        // 进度值 1 2 3
        int schedule = typedArray.getInteger(R.styleable.progressStatus_progress_schedule, 0);
        //进度颜色值
        progress_color = typedArray.getColor(R.styleable.progressStatus_progress_color, Color.parseColor(DEFAULT_GRAY_COLOR));
        typedArray.recycle();
        //加载布局
        View view = LayoutInflater.from(context).inflate(R.layout.view_progressstatus_layout, null, false);
        this.addView(view);
        //进度条
        tv_line_left = (TextView) view.findViewById(R.id.tv_line_left);
        tv_line_right = (TextView) view.findViewById(R.id.tv_line_right);
        //图标
        iv_icon_left = (ImageView) view.findViewById(R.id.iv_icon_left);
        iv_icon_middle = (ImageView) view.findViewById(R.id.iv_icon_middle);
        iv_icon_right = (ImageView) view.findViewById(R.id.iv_icon_right);
        //底部文案
        title_left = (TextView) view.findViewById(R.id.title_left);
        title_middle = (TextView) view.findViewById(R.id.title_middle);
        title_right = (TextView) view.findViewById(R.id.title_right);
        //底部文案-下
        title_left_bottom = (TextView) view.findViewById(R.id.title_left_bottom);
        title_middle_bottom = (TextView) view.findViewById(R.id.title_middle_bottom);
        title_right_bottom = (TextView) view.findViewById(R.id.title_right_bottom);

        /**………………………………………………………………………………………………………………………………………………**/
        setIcon(iconLeft, iconMiddle, iconRight);
        setIconBackground(iconLeftBg, iconMiddleBg, iconRightBg);//设置背景色
        //进度线的颜色
        setSchedule(schedule);
        setText(text_left, text_mid, text_right);
        setTextBottom(text_left, text_mid, text_right);
    }

    /**
     * 设置图片和进度状态
     *
     * @param ivIcon_left  左侧图片id
     * @param ivIcon_mid   中间图片id
     * @param ivIcon_right 右侧图片id
     */
    public ProgressStatusLayout setIcon(int ivIcon_left, int ivIcon_mid, int ivIcon_right) {
        if (ivIcon_left != -1) {
            iv_icon_left.setVisibility(View.VISIBLE);
            iv_icon_left.setImageResource(ivIcon_left);
        } else {
            iv_icon_left.setVisibility(View.INVISIBLE);
        }
        if (ivIcon_mid != -1) {
            iv_icon_middle.setVisibility(View.VISIBLE);
            iv_icon_middle.setImageResource(ivIcon_mid);
        } else {
            iv_icon_middle.setVisibility(View.INVISIBLE);
        }
        if (ivIcon_left != -1) {
            iv_icon_right.setVisibility(View.VISIBLE);
            iv_icon_right.setImageResource(ivIcon_right);
        } else {
            iv_icon_right.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    /**
     * 不设置图片，设置背景色值
     *
     * @param ivIcon_left_bg
     * @param ivIcon_left_bg
     * @param ivIcon_left_bg
     * @return
     */
    public ProgressStatusLayout setIconBackground(int ivIcon_left_bg, int ivIcon_mid_bg, int ivIcon_right_bg) {
        if (ivIcon_left_bg != -1) {
            iv_icon_left.setVisibility(View.VISIBLE);
            iv_icon_left.setBackgroundResource(ivIcon_left_bg);
        } else {
            iv_icon_left.setVisibility(View.INVISIBLE);
        }
        if (ivIcon_mid_bg != -1) {
            iv_icon_middle.setVisibility(View.VISIBLE);
            iv_icon_middle.setBackgroundResource(ivIcon_mid_bg);
        } else {
            iv_icon_middle.setVisibility(View.INVISIBLE);
        }
        if (ivIcon_right_bg != -1) {
            iv_icon_right.setVisibility(View.VISIBLE);
            iv_icon_right.setBackgroundResource(ivIcon_right_bg);
        } else {
            iv_icon_right.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    /**
     * 设置进度值
     *
     * @param schedule 0全部灰色 1第一条变色 2第二条变色
     */
    public void setSchedule(int schedule) {
        if (schedule == 1) {
            tv_line_left.setBackgroundColor(progress_color);
            tv_line_right.setBackgroundColor(Color.parseColor(DEFAULT_GRAY_COLOR));
        } else if (schedule == 2) {
            tv_line_left.setBackgroundColor(progress_color);
            tv_line_right.setBackgroundColor(progress_color);
        } else if (schedule == 0) {
            tv_line_left.setBackgroundColor(Color.parseColor(DEFAULT_GRAY_COLOR));
            tv_line_right.setBackgroundColor(Color.parseColor(DEFAULT_GRAY_COLOR));
        }
    }

    /**
     * 设置底部文案
     *
     * @param text_left  左侧描述
     * @param text_mid   中间描述
     * @param text_right 右侧描述
     */
    public ProgressStatusLayout setText(String text_left, String text_mid, String text_right) {
        title_left.setText(TextUtils.isEmpty(text_left) ? "" : text_left);
        title_middle.setText(TextUtils.isEmpty(text_mid) ? "" : text_mid);
        title_right.setText(TextUtils.isEmpty(text_right) ? "" : text_right);
        return this;
    }

    /**
     * 设置底部文案的下半部分文案
     *
     * @param text_left_bottom  left
     * @param text_mid_bottom   middle
     * @param text_right_bottom right
     * @return
     */
    public ProgressStatusLayout setTextBottom(String text_left_bottom, String text_mid_bottom, String text_right_bottom) {
        title_left_bottom.setText(TextUtils.isEmpty(text_left_bottom) ? "" : text_left_bottom);
        title_middle_bottom.setText(TextUtils.isEmpty(text_mid_bottom) ? "" : text_mid_bottom);
        title_right_bottom.setText(TextUtils.isEmpty(text_right_bottom) ? "" : text_right_bottom);
        return this;
    }

    /**
     * 设置底部文案字体颜色
     */
    public ProgressStatusLayout setTextColor(int text_leftColor, int text_middleColor, int text_rightColor) {
        title_left.setTextColor(getResources().getColor(text_middleColor));
        title_middle.setTextColor(getResources().getColor(text_middleColor));
        title_right.setTextColor(getResources().getColor(text_rightColor));
        return this;
    }

    /**
     * 设置底部文案字体颜色
     */
    public ProgressStatusLayout setTextColorBottom(int text_leftColor, int text_middleColor, int text_rightColor) {
        title_left_bottom.setTextColor(getResources().getColor(text_middleColor));
        title_middle_bottom.setTextColor(getResources().getColor(text_middleColor));
        title_right_bottom.setTextColor(getResources().getColor(text_rightColor));
        return this;
    }

}
