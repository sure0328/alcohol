package com.sinolease.model.po;

import com.sinolease.base.entity.BasePo;

/**
 * Created by AlbertLy on 16/11/17.
 */
public class BiDepartmentPo extends BasePo {

    private Long id;

    private Long departId;

    private String departName;

    private Integer leaderId;

    private String leaderName;

    private Integer state;

    private Integer level;
    private Integer departClass;

    public Integer getDepartClass() {
        return departClass;
    }

    public void setDepartClass(Integer departClass) {
        this.departClass = departClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
