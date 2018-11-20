package com.yanftch.test.mvp.base;

/**
 * Author : yanftch
 * Date : 2018/2/5
 * Time : 11:29
 * Desc :
 */

public abstract class BasePresenter<V extends BaseView> {
    private V mMvpView;

    /**
     * 绑定V层
     *
     * @param view
     */
    public void attachMvpView(V view) {
        this.mMvpView = view;
    }

    /**
     * 解除绑定V层
     */
    public void detachMvpView() {
        mMvpView = null;
    }

    /**
     * 获取V层
     *
     * @return
     */
    public V getmMvpView() {
        return mMvpView;
    }
}
