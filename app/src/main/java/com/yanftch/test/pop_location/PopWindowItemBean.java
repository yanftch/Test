package com.yanftch.test.pop_location;

/**
 * Author : yanftch
 * Date : 2018/2/28
 * Time : 10:28
 * Desc :
 */

public class PopWindowItemBean {
    private String popName;//显示的名字
    private String popCode;//名字对应的code值
    private int popLeftDrawable;//左边的图标的res地址

    @Override
    public String toString() {
        return "PopWindowItemBean{" +
                "popName='" + popName + '\'' +
                ", popCode='" + popCode + '\'' +
                ", popLeftDrawable=" + popLeftDrawable +
                '}';
    }

    public int getPopLeftDrawable() {
        return popLeftDrawable;
    }

    public PopWindowItemBean setPopLeftDrawable(int popLeftDrawable) {
        this.popLeftDrawable = popLeftDrawable;
        return this;
    }

    public String getPopName() {
        return popName;
    }

    public PopWindowItemBean setPopName(String popName) {
        this.popName = popName;
        return this;
    }

    public String getPopCode() {
        return popCode;
    }

    public PopWindowItemBean setPopCode(String popCode) {
        this.popCode = popCode;
        return this;
    }
}
