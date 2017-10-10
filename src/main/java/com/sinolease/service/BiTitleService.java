package com.sinolease.service;

import com.sinolease.base.core.AbstractDaoService;
import com.sinolease.config.Constants;
import com.sinolease.dao.BiTitleDao;
import com.sinolease.model.po.BiTitlePo;
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
public class BiTitleService extends AbstractDaoService<BiTitlePo, Long> {

    @Autowired
    private BiTitleDao biTitleDao;

    /**
     * 实现类 必须用一下代码来实现
     *
     * @Autowired private XxxDao xxxDao;
     * @Override
     * @Autowired public void setBaseDao() {
     * super.setBaseDao(xxxDao);
     * }
     */
    @Override
    @Autowired
    public void setBaseDao() {
        super.setBaseDao(biTitleDao);
    }

    /**
     * 返回所有有效的部门
     *
     * @return
     */
    @Cacheable(value = "orgCache", key = "targetClass + methodName")
    public Map<Long, BiTitlePo> getTitles() {
        Map<Long, BiTitlePo> biTitleMap = new LinkedHashMap<>();
        BiTitlePo biTitlePo = new BiTitlePo();
        biTitlePo.setState(Constants.VALID);
        List<BiTitlePo> biTitlePoList = biTitleDao.selectByEntity(biTitlePo);
        for (BiTitlePo model : biTitlePoList) {
            biTitleMap.put(model.getTitleId(), model);
        }

        return biTitleMap;
    }

    public BiTitlePo getTitleInfoByTtileName(String titleName) {
        BiTitlePo biTitlePo = new BiTitlePo();
        biTitlePo.setTitleName(titleName);
        biTitlePo.setState(Constants.VALID);
        List<BiTitlePo> titleList = biTitleDao.selectByEntity(biTitlePo);
        if (titleList == null || titleList.size() != 1)
            return null;
        return titleList.get(0);
    }

    public List<BiTitlePo> getTitles(BiTitleVo biTitleVo) {
        List<BiTitlePo> list = biTitleDao.selectByEntityVo(biTitleVo);
        return list;
    }

    public int getCount() {
        return biTitleDao.getCount();
    }
}
