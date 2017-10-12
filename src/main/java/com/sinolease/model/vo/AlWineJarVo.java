package com.sinolease.model.vo;

import com.sinolease.base.entity.Page;

import java.util.Date;

/**
 * Created by AlbertLy on 2016/11/28.
 */
public class AlWineJarVo extends Page {
    private Integer id;
    private Integer wineId;
    private Double maxCapacity;
    private Double curCapacity;
    private Date creatTime;

    private Date ctime;
    private Integer cuser;
    private Date time;
    private Integer cmuser;
    private String searchText;

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWineId() {
        return wineId;
    }

    public void setWineId(Integer wineId) {
        this.wineId = wineId;
    }

    public Double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Double getCurCapacity() {
        return curCapacity;
    }

    public void setCurCapacity(Double curCapacity) {
        this.curCapacity = curCapacity;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getCuser() {
        return cuser;
    }

    public void setCuser(Integer cuser) {
        this.cuser = cuser;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getCmuser() {
        return cmuser;
    }

    public void setCmuser(Integer cmuser) {
        this.cmuser = cmuser;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
