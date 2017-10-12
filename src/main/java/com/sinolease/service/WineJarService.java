package com.sinolease.service;

import com.sinolease.base.core.AbstractDaoService;
import com.sinolease.config.Constants;
import com.sinolease.dao.BiTitleDao;
import com.sinolease.dao.WineJarDao;
import com.sinolease.model.po.AlWineJarPo;
import com.sinolease.model.po.BiTitlePo;
import com.sinolease.model.vo.AlWineJarVo;
import com.sinolease.model.vo.BiTitleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlbertLy on 2016/11/17.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WineJarService extends AbstractDaoService<AlWineJarPo, Long> {

    @Autowired
    private WineJarDao wineJarDao;

    @Override
    @Autowired
    public void setBaseDao() {
        super.setBaseDao(wineJarDao);
    }

    public List<AlWineJarPo> getWineJars(AlWineJarVo alWineJarVo) {
        List<AlWineJarPo> list = wineJarDao.selectByEntityVo(alWineJarVo);
        return list;
    }

    public int getCount() {
        return wineJarDao.getCount();
    }
}
