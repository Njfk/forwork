package com.forwork.com.forwork.bean.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2018/11/1.
 */

public class Geek {

    /**
     * sec : 3
     * on : true
     * one : true
     * new : false
     * jd : true
     */

    private int sec;
    private boolean on;
    private boolean one;
    @SerializedName("new")
    private boolean newX;
    private boolean jd;

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean isOne() {
        return one;
    }

    public void setOne(boolean one) {
        this.one = one;
    }

    public boolean isNewX() {
        return newX;
    }

    public void setNewX(boolean newX) {
        this.newX = newX;
    }

    public boolean isJd() {
        return jd;
    }

    public void setJd(boolean jd) {
        this.jd = jd;
    }
}
