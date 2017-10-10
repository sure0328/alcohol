package com.sinolease.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinolease.base.core.BaseController;
import com.sinolease.base.entity.Page;
import com.sinolease.base.entity.ResponseObj;
import com.sinolease.config.Constants;
import com.sinolease.model.po.*;
import com.sinolease.model.vo.BiUserVo;
import com.sinolease.model.vo.DictionaryVo;
import com.sinolease.service.AuthorityService;
import com.sinolease.service.BiDepartmentService;
import com.sinolease.service.BiTitleService;
import com.sinolease.service.BiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by AlbertLy on 2016/11/23.
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    BiUserService biUserService;

    @Autowired
    AuthorityService authorityService;


    @Autowired
    BiDepartmentService biDepartmentService;

    @Autowired
    BiTitleService biTitleService;

    /**
     * 返回首页，首页包含列表
     *
     * @return
     */
    @RequestMapping(value = {"/userManagement"})
    public String index(BiUserVo biUserVo, Model model) {
        Map<Long, BiDepartmentPo> departmentMap = biDepartmentService.getDepartments(Constants.ALL_OFFICE);
        Map<Long, BiTitlePo> titleMap = biTitleService.getTitles();
        biUserVo.setNeedLimit(true);
        biUserVo.setState(Constants.VALID);
        Page<BiUserPo> list = biUserService.getUsers(biUserVo);
        model.addAttribute("departmentMap", departmentMap);
        model.addAttribute("selectedDepartId", biUserVo.getDepartId());
        model.addAttribute("selectedTitleId", biUserVo.getTitleId());
        model.addAttribute("searchText", biUserVo.getSearchText());
        model.addAttribute("titleMap", titleMap);
        model.addAttribute("list", list);
        return "userManagement";
    }

    @RequestMapping(value = {"/updateUserInfo"})
    public String updateUserInfo(BiUserVo biUserVo, Model model) {
        BiUserPo biUserPo =biUserService.selectByPrimaryKey(biUserVo.getId());
        Map<Long, BiDepartmentPo> departmentMap = biDepartmentService.getDepartments(Constants.ALL_OFFICE);
        Map<Long, BiTitlePo> titleMap = biTitleService.getTitles();
        biUserVo.setNeedLimit(true);
        biUserVo.setState(Constants.VALID);
        model.addAttribute("departmentMap", departmentMap);
        model.addAttribute("selectedDepartId", biUserPo.getDepartId());
        model.addAttribute("selectedTitleId", biUserPo.getTitleId());
        model.addAttribute("titleMap", titleMap);
        model.addAttribute("biUserPo", biUserPo);
        return "updateBaseInfo";
    }

    @RequestMapping(value = "/updateBaseInfo",method = RequestMethod.POST)
    public String updateBaseInfo(HttpServletRequest request ,BiUserVo biUserVo) {

        try {
            BiUserPo biUserPo =biUserService.selectByPrimaryKey(biUserVo.getId());
            int userId = Integer.parseInt(request.getParameter("userid"));
            String userName = request.getParameter("username").toString();
            biUserPo.setUserId(userId);
            biUserPo.setUserName(userName);
            biUserPo.setUserNameZh(request.getParameter("usernamezh").toString());
            biUserPo.setDepartId(Long.parseLong(request.getParameter("department").toString()));
            biUserPo.setTitleId(Long.parseLong(request.getParameter("title")));
            biUserPo.setEmail(request.getParameter("email").toString());
            biUserPo.setPhone(request.getParameter("phone").toString());
            biUserPo.setState(Constants.VALID);
            if (request.getParameter("role").toString().equals(Constants.ROLE_TYPE_U))
                biUserPo.setRole(Constants.ROLE_USER);
            else if (request.getParameter("role").toString().equals(Constants.ROLE_TYPE_A))
                biUserPo.setRole(Constants.ROLE_ADMIN);
            else if (request.getParameter("role").toString().equals(Constants.ROLE_TYPE_F))
                biUserPo.setRole(Constants.ROLE_FINANCE);
            else if (request.getParameter("role").toString().equals(Constants.ROLE_TYPE_M))
                biUserPo.setRole(Constants.ROLE_MARKET);
            else if (request.getParameter("role").toString().equals(Constants.ROLE_TYPE_Z))
                biUserPo.setRole(Constants.ROLE_ZONGHE);
            biUserService.updateByPrimaryKey(biUserPo);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return "redirect:/userManagement";
    }

    @RequestMapping(value = {"/deleteUser"})
    public String deleteUser(BiUserVo biUserVo, Model model) {
        biUserService.deleteByPrimaryKey(biUserVo.getId());
        Map<Long, BiDepartmentPo> departmentMap = biDepartmentService.getDepartments(Constants.ALL_OFFICE);
        Map<Long, BiTitlePo> titleMap = biTitleService.getTitles();
        biUserVo.setNeedLimit(true);
//        biUserVo.setState(1);
        Page<BiUserPo> list = biUserService.getUsers(biUserVo);
        model.addAttribute("departmentMap", departmentMap);
        model.addAttribute("titleMap", titleMap);
        model.addAttribute("list", list);
        return "redirect:/userManagement";
    }

    @RequestMapping(value = {"/resetPassword"})
    public String resetPassword(BiUserVo biUserVo, Model model) {
        BiUserPo biUserPo = new BiUserPo();
        biUserPo.setId(biUserVo.getId());
        biUserPo = biUserService.selectByEntity(biUserPo).get(0);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("123456");
        biUserPo.setPassword(pwd);
        biUserService.updateByPrimaryKey(biUserPo);
        Map<Long, BiDepartmentPo> departmentMap = biDepartmentService.getDepartments(Constants.ALL_OFFICE);
        Map<Long, BiTitlePo> titleMap = biTitleService.getTitles();
        biUserVo.setNeedLimit(true);
//        biUserVo.setState(1);
        Page<BiUserPo> list = biUserService.getUsers(biUserVo);
        model.addAttribute("departmentMap", departmentMap);
        model.addAttribute("titleMap", titleMap);
        model.addAttribute("list", list);
        return "redirect:/userManagement";
    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request) {
        BiUserPo biUserPo = new BiUserPo();
        int userId = Integer.parseInt(request.getParameter("userid"));
        String userName = request.getParameter("username").toString();

        if (biUserService.selectByUserId(userId) != null) {

        }

        if (biUserService.selectByUserName(userName) != null) {

        }

        try {
            biUserPo.setUserId(userId);
            biUserPo.setUserName(userName);
            biUserPo.setUserNameZh(request.getParameter("usernamezh").toString());
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String pwd = encoder.encode("123456");
            biUserPo.setPassword(pwd);
            biUserPo.setDepartId(Long.parseLong(request.getParameter("department").toString()));
            biUserPo.setTitleId(Long.parseLong(request.getParameter("title")));
            biUserPo.setEmail(request.getParameter("email").toString());
            biUserPo.setPhone(request.getParameter("phone").toString());
            biUserPo.setState(Constants.VALID);
            if (request.getParameter("role").toString().equals(Constants.ROLE_TYPE_U))
                biUserPo.setRole(Constants.ROLE_USER);
            else if (request.getParameter("role").toString().equals(Constants.ROLE_TYPE_A))
                biUserPo.setRole(Constants.ROLE_ADMIN);
            else if (request.getParameter("role").toString().equals(Constants.ROLE_TYPE_F))
                biUserPo.setRole(Constants.ROLE_FINANCE);
            else if (request.getParameter("role").toString().equals(Constants.ROLE_TYPE_M))
                biUserPo.setRole(Constants.ROLE_MARKET);
            else if (request.getParameter("role").toString().equals(Constants.ROLE_TYPE_Z))
                biUserPo.setRole(Constants.ROLE_ZONGHE);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        biUserService.insert(biUserPo);
        return "redirect:/userManagement";
    }

    @RequestMapping(value = "/changePasswd", method = RequestMethod.POST)

    public String changePassword(HttpServletRequest request, @AuthenticationPrincipal SinoLeaseUser activeUser) {
        BiUserPo biUserPo = new BiUserPo();
        try {
            biUserPo.setUserId(activeUser.getUserId());
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            BiUserPo temp = biUserService.selectByEntity(biUserPo).get(0);

            String newpassword = request.getParameter("newpassword");
            String confirm = request.getParameter("confirm");

            if (temp != null ) {
                if (newpassword.equals(confirm) && newpassword.length() >= 6) {
                    temp.setPassword(encoder.encode(request.getParameter("newpassword")));
                    biUserService.updateByPrimaryKey(temp);
                }

            } else {

            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "redirect:/index";
    }

    @RequestMapping(value = "/CiUserAuthority",method = RequestMethod.POST)
    @ResponseBody
    public ResponseObj authority(String id, Model model) {
            try {

                BiUserPo biUserPo =biUserService.selectByPrimaryKey(new Long(id));
                JSONArray array=new JSONArray();//
                JSONObject jsonObject = null;
                jsonObject = new JSONObject();
                jsonObject.put("id",biUserPo.getId());
                jsonObject.put("name",biUserPo.getUserNameZh());
                array.add(jsonObject);

                DictionaryVo dictionaryVo=new DictionaryVo();
                dictionaryVo.setKind("authority");
                List<DictionaryPo> list = authorityService.getDictionary(dictionaryVo);

                for(int i=0;i<list.size();i++)
                {
                    String key = list.get(i).getValue();
                    String value = list.get(i).getFeature1();
                    jsonObject = new JSONObject();
                    jsonObject.put("key",key);
                    jsonObject.put("value",value);

                    biUserPo.setRole(key);
                    BiUserPo biUserPo2=biUserService.selectByEntityRole(biUserPo);
                    if (biUserPo2==null){
                        jsonObject.put("selected",0);
                    }else{
                        jsonObject.put("selected",1);
                    }
                    array.add(jsonObject);
                }
//                model.addAttribute("authorityMap", Constants.AUTHORITY_TYPE_MAP);

                return getSuccessResponse(array.toString());
            } catch (Exception e) {
                return getSysErrorResponse();
            }
    }
    @RequestMapping(value = "/CiUserAuthorityAdd",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public ResponseObj authorityadd(String id,String role) {
        try {
            BiUserPo biUserPo =biUserService.selectByPrimaryKey(new Long(id));
            String[] roles=role.split(",");
            biUserService.deleteRole(biUserPo.getUserId());
            if (!role.equals("null")){
                for (int i=0;i<roles.length;i++){
                    biUserPo.setRole(roles[i]);
                    BiUserPo biUserPo2=biUserService.selectByEntityRole(biUserPo);
                    if (biUserPo2==null){
                        biUserService.insertRole(biUserPo);
                    }
                }
            }

            return getSuccessResponse(null);
        } catch (Exception e) {

            return getSysErrorResponse();
        }
    }
}
