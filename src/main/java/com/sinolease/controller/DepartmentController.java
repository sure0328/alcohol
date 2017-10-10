package com.sinolease.controller;

import com.sinolease.base.core.BaseController;
import com.sinolease.base.util.IdGenerator;
import com.sinolease.config.Constants;
import com.sinolease.model.po.BiDepartmentPo;
import com.sinolease.model.po.BiUserPo;
import com.sinolease.model.vo.BiDepartmentVo;
import com.sinolease.service.BiDepartmentService;
import com.sinolease.service.BiUserService;
import javafx.scene.effect.SepiaTone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by AlbertLy on 2016/11/23.
 */
@Controller
public class DepartmentController extends BaseController{

    @Autowired
    BiDepartmentService biDepartmentService;
    @Autowired
    BiUserService biUserService;

    /**
     * 返回首页，首页包含列表
     *
     * @return
     */
    @RequestMapping(value = {"/departmentManagement"})
    public String index(BiDepartmentVo biDepartmentVo, Model model) {
        BiUserPo biUserPo = new BiUserPo();
        biDepartmentVo.setNeedLimit(true);
        Map<Integer,BiUserPo> userMap = new HashMap<Integer,BiUserPo>();
        List<BiDepartmentPo> list = biDepartmentService.getDepartments(biDepartmentVo);
        biUserPo.setDepartId(Long.parseLong(String.valueOf(1)));
        List<BiUserPo> userList = biUserService.selectByEntity(biUserPo);
        for (BiUserPo temp : userList) {
            if (userList.contains(temp.getUserId()) || temp.getUserId().equals(0)) {

            } else {
                userMap.put(temp.getUserId(), temp);
            }
        }

        for (int i=0;i<list.size();i++) {
            for(int j=0;j<userList.size();j++){
                if(list.get(i).getLeaderId()!=null && list.get(i).getLeaderId().equals(userList.get(j).getUserId()))
                    list.get(i).setLeaderName(userList.get(j).getUserNameZh());
            }
        }

        model.addAttribute("searchText", biDepartmentVo.getSearchText());
        model.addAttribute("userMap",userMap);
        model.addAttribute("list", list);
        model.addAttribute("depClassMap",Constants.DEPT_CLASS_MAP);

        return "departmentManagement";
    }

    @RequestMapping(value = {"/deleteDepartment"})
    public String deleteDepartment(BiDepartmentVo biDepartmentVo, Model model) {
        biDepartmentService.deleteByPrimaryKey(biDepartmentVo.getId());
        biDepartmentVo.setNeedLimit(true);
        List<BiDepartmentPo> list = biDepartmentService.getDepartments(biDepartmentVo);
//        Map<Integer,BiUserPo> userMap = new HashMap<Integer,BiUserPo>();
//        BiUserPo biUserPo = new BiUserPo();
//        biUserPo.setDepartId(Long.parseLong(String.valueOf(1)));
//        List<BiUserPo> userList = biUserService.selectByEntity(biUserPo);for (BiUserPo temp : userList) {
//            if (userList.contains(temp.getUserId()) || temp.getUserId().equals(0)) {
//
//            } else {
//                userMap.put(temp.getUserId(), temp);
//            }
//        }
//
//        for (int i=0;i<list.size();i++) {
//            for(int j=0;j<userList.size();j++){
//                if(list.get(i).getLeaderId()!=null && list.get(i).getLeaderId().equals(userList.get(j).getUserId()))
//                    list.get(i).setLeaderName(userList.get(j).getUserNameZh());
//            }
//        }

        model.addAttribute("list", list);
        return "redirect:/departmentManagement";
    }

    @RequestMapping(value = "/addDepartment", method= RequestMethod.POST)
    public String addDepartment(HttpServletRequest request){
        BiDepartmentPo biDepartmentPo = new BiDepartmentPo();
        try {
            biDepartmentPo.setId(IdGenerator.next());
            biDepartmentPo.setState(Constants.VALID);
            if(request.getParameter("level").toString().equals("1"))
                biDepartmentPo.setLevel(Constants.LEVEL_1);
            else if(request.getParameter("level").toString().equals("2"))
                biDepartmentPo.setLevel(Constants.LEVEL_2);
            else if(request.getParameter("level").toString().equals("3"))
                biDepartmentPo.setLevel(Constants.LEVEL_3);
            biDepartmentPo.setDepartId(biDepartmentService.getCount()+1L);
            biDepartmentPo.setDepartName(request.getParameter("departmentname"));
            biDepartmentPo.setLeaderId(Integer.parseInt(request.getParameter("userId")));
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        biDepartmentService.insert(biDepartmentPo);
        return "redirect:/departmentManagement";
    }
}
