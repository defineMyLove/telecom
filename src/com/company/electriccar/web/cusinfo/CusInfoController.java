package com.company.electriccar.web.cusinfo;

import com.company.electriccar.common.syscontext.Const;
import com.company.electriccar.common.syscontext.ResponseMes;
import com.company.electriccar.domain.CUS_CARD_INFO;
import com.company.electriccar.domain.CUS_INFO;
import com.company.electriccar.domain.SALEMAN;
import com.company.electriccar.service.CusCardService;
import com.company.electriccar.service.CusInfoService;
import com.company.electriccar.service.SaleManService;
import com.company.electriccar.service.solution.SolutionService;
import com.company.modules.utils.JsonUtil;
import com.company.modules.utils.StringUtil;
import com.company.modules.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 发展人控制器
 * Created by zxl on 14-6-17.
 */
@Controller
@RequestMapping("/maintain/cusinfo")
public class CusInfoController {
    @Autowired
    CusInfoService zhuanLanService;
    @Autowired
    CusCardService cusCardService;
    @Autowired
    SolutionService solutionService;

    /**
     * 添加动作
     *
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "update")
    public ModelAndView update(HttpServletRequest request, CUS_INFO user) {
        zhuanLanService.updateCusInfo(user);
        return WebUtil.goSysInfoPage(request, "", "window.top.refreshGrid();window.top.closeDialog();");
    }

    /**
     * 完善信息
     *
     * @return
     */
    @RequestMapping(value = "addInfo")
    public ModelAndView addInfo(String id, ResponseMes res) {
        ModelAndView view = new ModelAndView("/maintain/cusinfo/cusinfoAdd");
        view.addObject("msg", ResponseMes.parseMsg(res));
        if (StringUtil.isNotBlank(id)) {
            view.addObject("info", zhuanLanService.selectByPk(id));
        } else {
            //view.addObject("info",new FANGAN_FENLEI());
        }
        return view;
    }

    /**
     * 跳转到办理套餐界面(未归档的套餐数据)
     *
     * @param id  套餐实例ID
     * @param res
     * @return
     */
    @RequestMapping(value = "banliUI")
    public ModelAndView banliUI(String id, ResponseMes res) {
        ModelAndView view = new ModelAndView("/maintain/cusinfo/cardAdd");
        view.addObject("msg", ResponseMes.parseMsg(res));
        if (StringUtil.isNotBlank(id)) {
            view.addObject("info", cusCardService.selectByPk(id));
            view.addObject("productList", solutionService.findAll());
            view.addObject("phontListJson", JsonUtil.obj2Json(cusCardService.selectViceList(id)));
        } else {
            //view.addObject("info",new FANGAN_FENLEI());
        }
        return view;
    }

    /**
     * 套餐详情
     *
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "detail")
    public void detail(String id,HttpServletResponse response) {
        Map resMap = new HashMap();
        if (StringUtil.isNotBlank(id)) {
            resMap.put("info", zhuanLanService.find(id));
            resMap.put("viceList", JsonUtil.obj2Json(cusCardService.selectViceList(id)));
        }
        WebUtil.writeJson(response, JsonUtil.obj2JsonObj(resMap));
    }

    // 保存套餐办理信息(主卡、背卡)并修改套餐数据,修改状态为已办理
    @RequestMapping(value = "productUpdate")
    public ModelAndView productUpdate(String vice_card_str, CUS_CARD_INFO card_info, HttpServletRequest request) {
        zhuanLanService.updateProduct(vice_card_str, card_info);
        return WebUtil.goSysInfoPage(request, "", "window.top.refreshGrid();window.top.closeDialog();");
    }


    // 删除
    @RequestMapping(value = "del")
    public void del(String id, HttpServletResponse response) {
        WebUtil.writeJson(response, JsonUtil.obj2JsonObj(zhuanLanService.deleteById(id)));
    }

    // 修改状态为不处理
    @RequestMapping(value = "buchuli")
    public void buchuli(String id, HttpServletResponse response) {
        WebUtil.writeJson(response, JsonUtil.obj2JsonObj(zhuanLanService.updateState(id, Const.CUS_STATE_BUCHULI)));
    }

    // 修改状态为处理
    @RequestMapping(value = "chuli")
    public void chuli(String id, HttpServletResponse response) {
        WebUtil.writeJson(response, JsonUtil.obj2JsonObj(zhuanLanService.updateState(id, Const.CUS_STATE_NEW)));
    }

    // 归档
    @RequestMapping(value = "guidang")
    public void guidang(String id, HttpServletResponse response) {
        WebUtil.writeJson(response, JsonUtil.obj2JsonObj(zhuanLanService.updateState(id, Const.CUS_STATE_GUIDANG)));
    }

    // 查询
    @RequestMapping(value = "list")
    public void list(CUS_INFO zhuan, HttpServletRequest request, HttpServletResponse response) {
        WebUtil.writeJson(response, JsonUtil.map2Json(zhuanLanService.find(zhuan, request)));
    }
}
