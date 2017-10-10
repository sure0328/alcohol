package com.sinolease.model.vo;

import com.sinolease.base.entity.Page;

import java.util.Date;

/**
 * Created by AlbertLy on 2016/11/28.
 */
public class BiDepartmentVo extends Page {
    private Long id;

    private Long departId;

    private String departName;

    private Integer leaderId;

    private String leaderName;

    private Date ctime;

    private Integer cuser;

    private String searchText;
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

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
