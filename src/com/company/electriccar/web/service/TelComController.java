package com.company.electriccar.web.service;

import com.company.electriccar.domain.CUS_INFO;
import com.company.electriccar.domain.PRO_FLAG;
import com.company.electriccar.service.CusInfoService;
import com.company.electriccar.service.TelComService;
import com.company.electriccar.service.reader.FlagService;
import com.company.modules.utils.JsonUtil;
import com.company.modules.utils.WebUtil;
import com.company.modules.web.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * User: zxl
 * Date: 13-10-22
 * Time: 下午4:20
 * 手机服务接口
 *
 */
@Controller
@RequestMapping("service/telcom")
public class TelComController {
    @Autowired
    TelComService zhuanLanService;
    @Autowired
    CusInfoService cusInfoService;

    /**
     * 宽带产品展示
     * @param request
     * @param response
     */
    @RequestMapping(value = "productList")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        List<Map> map = zhuanLanService.productList();
        WebUtil.writeJson(response, JsonUtil.list2Json(map));
    }

    @RequestMapping(value = "detail")
    public ModelAndView detail(String id,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/phone/detail");
        view.addObject("info", zhuanLanService.selectByPk(id));
        return view;
    }

    @RequestMapping(value = "input")
    public ModelAndView input(String chanpin_id,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/phone/input");
        view.addObject("chanpin_id",chanpin_id);
        return view;
    }

    /**
     * 添加用户信息(微信用户中心没有确定前用户信息不判断唯一性)
     * 1.如果没有用户ID直接添加用户信息
     * 2.如果有则不添加而只是添加套餐信息
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "add")
    public ModelAndView add(HttpServletRequest request, CUS_INFO user) {
       ServiceResponse response= cusInfoService.add(user);
        return WebUtil.goSysInfoPage(request,"","alert('"+response.getMsg()+"');history.go(-1);");
    }

    /**
     * 手机显示在首页的产品（最多十个）
     */
    @RequestMapping(value = "index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/phone/newindex");
        view.addObject("list",zhuanLanService.queryList());
        List<Map> telMap = zhuanLanService.queryTelList();
        if (telMap!=null&telMap.size()!=0) {
            view.addObject("tel",telMap.get(0));
        }
        return view;
    }
}