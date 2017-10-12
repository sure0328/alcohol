package com.sinolease.model.po;

import com.sinolease.base.entity.BasePo;

import java.util.Date;

/**
 * Created by sure on 2017/6/21.
 */
public class AlWineJarPo extends BasePo {
    private Integer id;
    private Integer wineId;
    private Double maxCapacity;
    private Double curCapacity;
    private Date creatTime;

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

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}
