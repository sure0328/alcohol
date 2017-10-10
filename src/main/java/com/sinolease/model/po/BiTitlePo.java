package com.sinolease.model.po;

import com.sinolease.base.entity.BasePo;

/**
 * Created by AlbertLy on 16/11/17.
 */
public class BiTitlePo extends BasePo {

    private Long id;

    private Long titleId;

    private String titleName;

    private Integer state;

    public Long getId() {
        return id;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
