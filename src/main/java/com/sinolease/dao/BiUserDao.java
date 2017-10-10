package com.sinolease.dao;

import com.sinolease.base.core.BaseDao;
import com.sinolease.model.po.BiUserPo;
import com.sinolease.model.vo.BiUserVo;

import java.util.List;

/**
 * Created by AlbertLy on 2016/11/16.
 */
public interface BiUserDao extends BaseDao<BiUserPo, Long> {

    List<BiUserPo> selectByEntity(BiUserPo biUserPo);

    List<BiUserPo> selectByEntityVo(BiUserVo biUserVo);

    Long countByEntity(BiUserVo biUserVo);

    BiUserPo selectByUserId(int userId);

    BiUserPo selectByUserName(String userName);

    List<BiUserPo> selectByTitles(List titleList);

    List<BiUserPo> selectRoleByUserId(int userId);
    int insertRole(BiUserPo record);
    BiUserPo selectByEntityRole(BiUserPo biUserPo);
    int deleteRole(Integer id);
}
