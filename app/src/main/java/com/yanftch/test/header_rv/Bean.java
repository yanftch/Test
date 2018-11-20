package com.yanftch.test.header_rv;

/**
 * User : yanftch
 * Date : 2017/7/21
 * Time : 08:38
 * Desc :
 */

public class Bean {
    private String name;
    private String time;

    public Bean(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public Bean setTime(String time) {
        this.time = time;
        return this;
    }

    public Bean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Bean setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
