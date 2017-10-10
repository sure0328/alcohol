package com.sinolease.service;

import com.sinolease.base.core.AbstractDaoService;
import com.sinolease.config.Constants;
import com.sinolease.dao.BiDepartmentDao;
import com.sinolease.model.po.BiDepartmentPo;
import com.sinolease.model.vo.BiDepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Huake.Tu on 2016/11/29.
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BiDepartmentService extends AbstractDaoService<BiDepartmentPo, Long> {

    @Autowired
    private BiDepartmentDao biDepartmentDao;

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
        super.setBaseDao(biDepartmentDao);
    }

    /**
     * 返回所有有效的部门
     *
     * @return
     */
//    @Cacheable(value = "orgCache", key = "targetClass + methodName")
    public Map<Long, BiDepartmentPo> getDepartments(Integer departmentType) {
        Map<Long, BiDepartmentPo> biDepartmentPoMap = new LinkedHashMap<>();
        BiDepartmentPo biDepartmentPo = new BiDepartmentPo();
        biDepartmentPo.setState(Constants.VALID);
        biDepartmentPo.setLevel(1);
        List<BiDepartmentPo> biDepartmentPoList = biDepartmentDao.selectByEntity(biDepartmentPo);
        for (BiDepartmentPo model : biDepartmentPoList) {
            if(departmentType.equals(Constants.ALL_OFFICE)) {
                biDepartmentPoMap.put(model.getDepartId(), model);
            }else if(departmentType.equals(Constants.BACK_OFFICE)){
                if(model.getDepartId()<12){
                    biDepartmentPoMap.put(model.getDepartId(), model);
                }
            }else if(departmentType.equals(Constants.MIDDLE_OFFICE)){

            }else if(departmentType.equals(Constants.FRONT_OFFICE)){
                if(model.getDepartId()>=12){
                    biDepartmentPoMap.put(model.getDepartId(), model);
                }
            }
        }

        return biDepartmentPoMap;
    }

    public BiDepartmentPo getDepartmentInfoByDepartName(String departName) {
        BiDepartmentPo biDepartmentPo = new BiDepartmentPo();
        biDepartmentPo.setDepartName(departName);
        biDepartmentPo.setState(Constants.VALID);
        List<BiDepartmentPo> titleList = biDepartmentDao.selectByEntity(biDepartmentPo);
        if (titleList == null || titleList.size() != 1)
            return null;
        return titleList.get(0);
    }

    public BiDepartmentPo getDepartmentInfoByDepartId(Long departId) {
        BiDepartmentPo biDepartmentPo = new BiDepartmentPo();
        biDepartmentPo.setDepartId(departId);
        biDepartmentPo.setState(Constants.VALID);
        List<BiDepartmentPo> titleList = biDepartmentDao.selectByEntity(biDepartmentPo);
        if (titleList == null || titleList.size() != 1)
            return null;
        return titleList.get(0);
    }

    public List<BiDepartmentPo> getDepartments(BiDepartmentVo biDepartmentVo) {
        List<BiDepartmentPo> list = biDepartmentDao.selectByEntityVo(biDepartmentVo);
        return list;
    }

    public int getCount() {
        return biDepartmentDao.getCount();
    }
}
