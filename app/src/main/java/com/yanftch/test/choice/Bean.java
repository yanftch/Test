package com.yanftch.test.choice;

/**
 * User : yanftch
 * Date : 2017/5/14
 * Time : 16:08
 * Desc :
 */

public class Bean {
    private String name;
    private boolean option;

    public Bean(String name) {
        this.name = name;
    }

    public Bean() {
    }

    public String getName() {
        return name;
    }

    public Bean setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isOption() {
        return option;
    }

    public Bean setOption(boolean option) {
        this.option = option;
        return this;
    }
}

