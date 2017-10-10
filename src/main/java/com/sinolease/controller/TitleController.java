package com.sinolease.controller;

import com.sinolease.base.core.BaseController;
import com.sinolease.base.entity.ErrorCode;
import com.sinolease.base.entity.ResponseObj;
import com.sinolease.base.util.IdGenerator;
import com.sinolease.config.Constants;
import com.sinolease.model.po.BiTitlePo;
import com.sinolease.model.vo.BiTitleVo;
import com.sinolease.service.BiTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by AlbertLy on 2016/11/23.
 */
@Controller
public class TitleController extends BaseController {

    @Autowired
    BiTitleService biTitleService;

    /**
     * 返回首页，首页包含列表
     *
     * @return
     */
    @RequestMapping(value = {"/titleManagement"})
    public String index(BiTitleVo biTitleVo, Model model) {
        biTitleVo.setNeedLimit(true);
        List<BiTitlePo> list = biTitleService.getTitles(biTitleVo);
        model.addAttribute("searchText", biTitleVo.getSearchText());
        model.addAttribute("list", list);
        return "titleManagement";
    }

    @RequestMapping(value = {"/deleteTitle"})
    public String deleteTitle(BiTitleVo biTitleVo, Model model) {
        biTitleService.deleteByPrimaryKey(biTitleVo.getId());
        biTitleVo.setNeedLimit(true);
        List<BiTitlePo> list = biTitleService.getTitles(biTitleVo);
        model.addAttribute("list", list);
        return "titleManagement";
    }


    @RequestMapping(value = "/addTitle", method = RequestMethod.POST)
    public String addTitle(HttpServletRequest request) {
        BiTitlePo biTitlePo = new BiTitlePo();
        String titlename = request.getParameter("titlename");
        ResponseObj responseObj = new ResponseObj();
        if (biTitleService.getTitleInfoByTtileName(titlename) != null) {
            responseObj.setErrno(ErrorCode.TITLE_NAME_EXIST_ERROR);
            responseObj.setErrmsg(ErrorCode.DES203ERROR);
        }
        try {
            biTitlePo.setId(IdGenerator.next());
            biTitlePo.setTitleId(1L + biTitleService.getCount());
            biTitlePo.setState(Constants.VALID);
            biTitlePo.setTitleName(request.getParameter("titlename"));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        biTitleService.insert(biTitlePo);
        return "redirect:/titleManagement";
    }
}
