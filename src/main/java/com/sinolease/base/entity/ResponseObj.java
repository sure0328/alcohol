package com.sinolease.base.entity;

import java.io.Serializable;

/**
 * Created by AlbertLy on 2016/11/15.
 */
public class ResponseObj implements Serializable{

    private String successmsg;

    private String errmsg;

    private int errno;

    private Object data;

    public String getSuccessmsg() {
        return successmsg;
    }

    public void setSuccessmsg(String successmsg) {
        this.successmsg = successmsg;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
