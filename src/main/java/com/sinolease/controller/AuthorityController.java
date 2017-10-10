package com.sinolease.controller;

import com.sinolease.base.util.IdGenerator;
import com.sinolease.model.po.*;
import com.sinolease.model.vo.DictionaryVo;
import com.sinolease.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by sure on 2017/6/21.
 */
@Controller
@RequestMapping("/authorityManagement")
public class AuthorityController {
    @Autowired
    AuthorityService authorityService;


    @RequestMapping(value = "")
    public String index(DictionaryVo dictionaryVo, Model model) {
        dictionaryVo.setNeedLimit(true);
        dictionaryVo.setKind("authority");
        List<DictionaryPo> list = authorityService.getDictionary(dictionaryVo);
        model.addAttribute("searchText", dictionaryVo.getSearchText());
        model.addAttribute("list", list);
        return "authorityManagement";
    }


    @RequestMapping(value = "/addAuthrity", method = RequestMethod.POST)
    public String addTitle(HttpServletRequest request,String value,String feature1,@AuthenticationPrincipal SinoLeaseUser activeUser) {
        DictionaryPo dictionaryVo =new DictionaryPo();
        try {
            dictionaryVo.setKind("authority");
            dictionaryVo.setCode(new Long(1));
            dictionaryVo.setValue(value);
            dictionaryVo.setFeature1(feature1);
            dictionaryVo.setCuser(activeUser.getUserId());
            dictionaryVo.setId(IdGenerator.next());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        authorityService.insert(dictionaryVo);
        return "redirect:/authorityManagement";
    }

    @RequestMapping(value = {"/deleteAuthority"})
    public String deleteTitle(DictionaryVo dictionaryVo, Model model) {
        authorityService.deleteByPrimaryKey(dictionaryVo.getId());
        dictionaryVo.setNeedLimit(true);
        dictionaryVo.setKind("authority");
        List<DictionaryPo> list = authorityService.getDictionary(dictionaryVo);
        model.addAttribute("list", list);
        return "authorityManagement";
    }
}
