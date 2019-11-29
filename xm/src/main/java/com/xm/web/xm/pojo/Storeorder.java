package com.xm.web.xm.pojo;

import java.util.Date;

public class Storeorder {
    private String olid;

    private Integer onlin;

    private Date time;

    public String getOlid() {
        return olid;
    }

    public void setOlid(String olid) {
        this.olid = olid;
    }

    public Integer getOnlin() {
        return onlin;
    }

    public void setOnlin(Integer onlin) {
        this.onlin = onlin;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}