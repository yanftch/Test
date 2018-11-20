package com.yanftch.test.title_bar;

import android.content.Context;
import android.widget.RelativeLayout;

import com.yanftch.test.R;

/**
 * Author : yanftch
 * Date : 2018/1/11
 * Time : 13:24
 * Desc :
 */

public class BaseTitleBarView extends RelativeLayout {

    public BaseTitleBarView(Context context) {
        super(context);
    }

    protected int getLayoutResId() {
        return R.layout.base_common_title_bar_layout;
    }
}
