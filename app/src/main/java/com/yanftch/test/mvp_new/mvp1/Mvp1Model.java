package com.yanftch.test.mvp_new.mvp1;

/**
 * Author : yanftch
 * Date : 2018/2/6
 * Time : 09:04
 * Desc :
 */

public class Mvp1Model implements Mvp1Contract.Model {
    private static Mvp1Model mMvp1Model;

    public static Mvp1Model getInstanct() {
        if (mMvp1Model == null) {
            mMvp1Model = new Mvp1Model();
        }
        return mMvp1Model;
    }

    @Override
    public String getData() {
        return "啥玩意";
    }
}
