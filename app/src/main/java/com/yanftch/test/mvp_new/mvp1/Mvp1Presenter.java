package com.yanftch.test.mvp_new.mvp1;

/**
 * Author : yanftch
 * Date : 2018/2/6
 * Time : 09:02
 * Desc :
 */

public class Mvp1Presenter implements Mvp1Contract.Presenter {
    private Mvp1Contract.Model mModel;
    private Mvp1Contract.View mView;

    public Mvp1Presenter(Mvp1Contract.Model model, Mvp1Contract.View view) {
        mModel = model;
        mView = view;
    }

    @Override
    public void handleData() {
        mView.showData(mModel.getData());
    }
}
