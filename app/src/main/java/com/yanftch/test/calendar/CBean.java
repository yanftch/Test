package com.yanftch.test.calendar;

/**
 * User : yanftch
 * Date : 2017/6/29
 * Time : 15:46
 * Desc :
 */

public class CBean {
    private String dateNo;
    private boolean hasSign;//是否已经签到
    private boolean canBeSign;//是否可以签到--已经签到过的，就不能点击了

    public CBean(String dateNo, boolean hasSign, boolean canBeSign) {
        this.dateNo = dateNo;
        this.hasSign = hasSign;
        this.canBeSign = canBeSign;
    }

    public boolean isCanBeSign() {
        return canBeSign;
    }

    public CBean setCanBeSign(boolean canBeSign) {
        this.canBeSign = canBeSign;
        return this;
    }

    public CBean(String dateNo, boolean hasSign) {
        this.dateNo = dateNo;
        this.hasSign = hasSign;
    }

    public CBean(String dateNo) {
        this.dateNo = dateNo;
    }

    public String getDateNo() {
        return dateNo;
    }

    public CBean setDateNo(String dateNo) {
        this.dateNo = dateNo;
        return this;
    }

    public boolean isHasSign() {
        return hasSign;
    }

    @Override
    public String toString() {
        return "CBean{" +
                "dateNo='" + dateNo + '\'' +
                ", hasSign=" + hasSign +
                ", canBeSign=" + canBeSign +
                '}';
    }

    public CBean setHasSign(boolean hasSign) {
        this.hasSign = hasSign;
        return this;
    }


}
