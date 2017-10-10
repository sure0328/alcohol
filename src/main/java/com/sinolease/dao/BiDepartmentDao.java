package com.sinolease.dao;

import com.sinolease.base.core.BaseDao;
import com.sinolease.model.po.BiDepartmentPo;
import com.sinolease.model.vo.BiDepartmentVo;

import java.util.List;

/**
 * Created by AlbertLy on 16/11/17.
 */
public interface BiDepartmentDao extends BaseDao<BiDepartmentPo, Long> {

    List<BiDepartmentPo> selectByEntity(BiDepartmentPo biDepartmentPo);

    List<BiDepartmentPo> selectByEntityVo(BiDepartmentVo biDepartmentVo);

    int getCount();
}
