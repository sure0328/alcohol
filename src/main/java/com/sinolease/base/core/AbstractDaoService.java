package com.sinolease.base.core;

import java.io.Serializable;

/**
 * Created by AlbertLy on 2016/10/18.
 */
public abstract class AbstractDaoService<T, ID extends Serializable> implements BaseDaoService<T, ID> {

    private BaseDao<T, ID> baseDao;

    public void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public int insert(T record) {
        return baseDao.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return baseDao.insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(ID id) {
        return baseDao.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKey(T record) {
        return baseDao.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return baseDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(ID id) {
        return baseDao.deleteByPrimaryKey(id);
    }
}

