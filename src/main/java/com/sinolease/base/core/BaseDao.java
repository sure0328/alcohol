package com.sinolease.base.core;


import java.io.Serializable;

/**
 * Created by AlbertLy on 2016/10/18.
 */
public interface BaseDao<T, ID extends Serializable> {

    /**
     * 插入一条记录
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 可选择性地插入记录
     *
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     *
     *
     */
    T selectByPrimaryKey(ID id);

    /**
     * 根据id更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 根据id选择性地更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(ID id);
}
