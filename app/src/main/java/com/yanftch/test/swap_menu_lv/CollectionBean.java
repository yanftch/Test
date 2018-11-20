package com.yanftch.test.swap_menu_lv;

/**
 * User : yanftch
 * Date : 2017/5/18
 * Time : 16:42
 * Desc :
 */

public class CollectionBean {
    private String name;//显示文本
    private boolean option;

    @Override
    public String toString() {
        return "CollectionBean{" +
                "name='" + name + '\'' +
                ", option=" + option +
                '}';
    }

    public CollectionBean(String name) {
        this.name = name;
    }

    public CollectionBean(String name, boolean option) {
        this.name = name;
        this.option = option;
    }

    public CollectionBean() {
    }

    public String getName() {
        return name;
    }

    public CollectionBean setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isOption() {
        return option;
    }

    public CollectionBean setOption(boolean option) {
        this.option = option;
        return this;
    }
}
