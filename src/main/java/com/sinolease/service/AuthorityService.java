package com.sinolease.service;

import com.sinolease.base.core.AbstractDaoService;
import com.sinolease.dao.AuthorityDao;
import com.sinolease.model.po.DictionaryPo;
import com.sinolease.model.vo.DictionaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sure on 2017/6/21.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthorityService extends AbstractDaoService<DictionaryPo, Long> {

    @Autowired
    private AuthorityDao authorityDao;
    @Override
    @Autowired
    public void setBaseDao() {
        super.setBaseDao(authorityDao);
    }


    public List<DictionaryPo> getDictionary(DictionaryVo dictionaryVo) {
        List<DictionaryPo> list = authorityDao.selectByEntityVo(dictionaryVo);
        return list;
    }

}
