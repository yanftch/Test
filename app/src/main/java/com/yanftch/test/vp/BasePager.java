package com.yanftch.test.vp;

import android.content.Context;
import android.view.View;

public abstract class BasePager<T> {
    protected Context context;
    public View rootView;

    public BasePager(Context context) {
        this.context = context;
        rootView = initView();
    }

    public abstract View initView();

    public abstract void initData(T t);
}
