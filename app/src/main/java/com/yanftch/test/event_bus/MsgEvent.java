package com.yanftch.test.event_bus;

/**
 * User : yanftch
 * Date : 2017/4/19
 */

public class MsgEvent {
    public Object object;
    public String type;
    public static final String MSG_TEST_01="msg_0001";

    public MsgEvent(Object object) {
        this.object = object;
    }

    public MsgEvent(String type) {
        this.type = type;
    }

    public MsgEvent(Object object, String type) {
        this.object = object;
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
