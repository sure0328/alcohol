package com.sinolease.dao;

import com.sinolease.base.core.BaseDao;
import com.sinolease.model.po.DictionaryPo;
import com.sinolease.model.vo.DictionaryVo;

import java.util.List;

/**
 * Created by sure on 2017/6/21.
 */
public interface AuthorityDao extends BaseDao<DictionaryPo, Long> {
    List<DictionaryPo> selectByEntity(DictionaryPo dictionaryPo);
    List<DictionaryPo> selectByEntityVo(DictionaryVo dictionaryPo);
    int getCount();
}
