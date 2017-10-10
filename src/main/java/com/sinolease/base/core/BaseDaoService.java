package com.sinolease.base.core;

import java.io.Serializable;

/**
 * Created by AlbertLy on 2016/10/18.
 */
public interface BaseDaoService<T, ID extends Serializable> {

    /**
     * 实现类 必须用一下代码来实现
     *
     * @Autowired private XxxDao xxxDao;
     * @Override
     * @Autowired public void setBaseDao() {
     * super.setBaseDao(xxxDao);
     * }
     */
    void setBaseDao();

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
