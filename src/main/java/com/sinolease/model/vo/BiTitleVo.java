package com.sinolease.model.vo;

import com.sinolease.base.entity.Page;

import java.util.Date;

/**
 * Created by AlbertLy on 2016/11/28.
 */
public class BiTitleVo extends Page {
    private Long id;

    private Long titleId;

    private String titleName;

    private Integer state;

    private Date ctime;

    private Integer cuser;

    private String searchText;

    public Long getId() {
        return id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
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
