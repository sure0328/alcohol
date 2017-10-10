package com.sinolease.base.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AlbertLy on 2016/5/17.
 */
public class Page<T> implements Serializable {

    public final static int PAGE_SHOW_COUNT = 10;

    /**
     * 分页大小: 每页多少条
     */
    private int pageSize = PAGE_SHOW_COUNT;

    /**
     * 当前第几页
     */
    private Long curPage = 1L;

    /**
     * 用于sql的物理分页语句
     */
    private String page;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 总记录数
     */
    private Long totalCount;

    /**
     * 返回的数据
     */
    private List<T> list;

    private boolean needLimit = false;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(Long curPage) {
        this.curPage = curPage;
    }

    public Long getTotalPage() {
        totalPage = totalCount != null ? (totalCount / getPageSize() + (totalCount % getPageSize() == 0 ? 0 : 1)) : 0L;
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        totalPage = totalCount != null ? (totalCount / getPageSize() + (totalCount % getPageSize() == 0 ? 0 : 1)) : 0L;
        this.totalCount = totalCount;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        //分页计算
        Long pageStartNum = 0L;
        if (null != curPage && curPage > 0) {
            pageStartNum = (curPage - 1) * pageSize;
            page = " limit " + String.valueOf(pageStartNum) + "," + String.valueOf(pageSize);
        } else {
            page = "";
        }
        return page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void buildTotalPage(int pageNo, int pageSize, int totalCount) {
        this.curPage = Long.valueOf(pageNo);
        this.pageSize = pageSize;
        this.totalCount = Long.valueOf(totalCount);
        this.totalPage = Long.valueOf((totalCount + pageSize - 1) / pageSize);
    }

    public boolean getNeedLimit() {
        return needLimit;
    }

    public void setNeedLimit(boolean needLimit) {
        this.needLimit = needLimit;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
