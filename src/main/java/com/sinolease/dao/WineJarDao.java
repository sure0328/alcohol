package com.sinolease.dao;

import com.sinolease.base.core.BaseDao;
import com.sinolease.model.po.AlWineJarPo;
import com.sinolease.model.po.BiTitlePo;
import com.sinolease.model.vo.AlWineJarVo;
import com.sinolease.model.vo.BiTitleVo;

import java.util.List;

/**
 * Created by AlbertLy on 16/11/17.
 */
public interface WineJarDao extends BaseDao<AlWineJarPo, Long> {

    List<AlWineJarPo> selectByEntity(AlWineJarPo biTitlePo);

    List<AlWineJarPo> selectByEntityVo(AlWineJarVo biTitleVo);

    int getCount();
}
