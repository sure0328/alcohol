package com.sinolease.dao;

import com.sinolease.base.core.BaseDao;
import com.sinolease.model.po.BiTitlePo;
import com.sinolease.model.vo.BiTitleVo;

import java.util.List;

/**
 * Created by AlbertLy on 16/11/17.
 */
public interface BiTitleDao extends BaseDao<BiTitlePo, Long> {

    List<BiTitlePo> selectByEntity(BiTitlePo biTitlePo);

    List<BiTitlePo> selectByEntityVo(BiTitleVo biTitleVo);

    int getCount();
}
