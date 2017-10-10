package com.sinolease.base.entity;

import java.util.Date;

/**
 * Created by AlbertLy on 2016/11/17.
 */
public class BasePo {

    private Integer cuser;

    private Date ctime;

    private Integer muser;

    private Date mtime;

    public Integer getCuser() {
        return cuser;
    }

    public void setCuser(Integer cuser) {
        this.cuser = cuser;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getMuser() {
        return muser;
    }

    public void setMuser(Integer muser) {
        this.muser = muser;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}
