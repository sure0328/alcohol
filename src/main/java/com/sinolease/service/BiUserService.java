package com.sinolease.service;

import com.sinolease.base.core.AbstractDaoService;
import com.sinolease.base.entity.Page;
import com.sinolease.config.Constants;
import com.sinolease.dao.BiUserDao;
import com.sinolease.model.po.BiUserPo;
import com.sinolease.model.vo.BiUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlbertLy on 2016/11/17.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BiUserService extends AbstractDaoService<BiUserPo, Long> {

    @Autowired
    private BiUserDao biUserDao;

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
        super.setBaseDao(biUserDao);
    }

    public BiUserPo selectByUserId(int userId) {
        return biUserDao.selectByUserId(userId);
    }

    public BiUserPo selectByUserName(String userName) {
        return biUserDao.selectByUserName(userName);
    }

    public List<BiUserPo> selectByEntity(BiUserPo biUserPo) {
        return biUserDao.selectByEntity(biUserPo);
    }

    public List<BiUserPo> selectRoleByUserId(int userId) {
        return biUserDao.selectRoleByUserId(userId);
    }

    public BiUserPo getUserInfoByUserName(String userName) {
        BiUserPo biUserPo = new BiUserPo();
        biUserPo.setUserName(userName);
        biUserPo.setState(Constants.VALID);
        List<BiUserPo> userList = biUserDao.selectByEntity(biUserPo);
        if (userList == null || userList.size() != 1)
            return null;
        return userList.get(0);
    }

    public Page<BiUserPo> getUsers(BiUserVo biUserVo) {
        Page<BiUserPo> result = new Page<>();
        List<BiUserPo> list = biUserDao.selectByEntityVo(biUserVo);
        Long count = biUserDao.countByEntity(biUserVo);
        result.setList(list);
        result.setTotalCount(count);
        result.setCurPage(biUserVo.getCurPage());
        return result;
    }



    @Transactional(rollbackFor = Exception.class)
    public int insertRole(BiUserPo record) {
        return biUserDao.insertRole(record);
    }
    public BiUserPo selectByEntityRole(BiUserPo biUserPo) {
        return biUserDao.selectByEntityRole(biUserPo);
    }
    public int deleteRole(Integer id) {
        return biUserDao.deleteRole(id);
    }
}
