package com.company.electriccar.web.cusinfo;

import com.company.electriccar.common.syscontext.ResponseMes;
import com.company.electriccar.domain.CUS_INFO;
import com.company.electriccar.domain.SALEMAN;
import com.company.electriccar.service.CusInfoService;
import com.company.electriccar.service.SaleManService;
import com.company.modules.utils.JsonUtil;
import com.company.modules.utils.StringUtil;
import com.company.modules.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 发展人控制器
 * Created by zxl on 14-6-17.
 */
@Controller
@RequestMapping("/maintain/cusinfo")
public class CusInfoController {
    @Autowired
    CusInfoService zhuanLanService;

    /**
     * 添加动作
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "add")
    public ModelAndView add(HttpServletRequest request, CUS_INFO user) {
       zhuanLanService.add(user);
        return WebUtil.goSysInfoPage(request,"","window.top.refreshGrid();window.top.closeDialog();");
    }

    // 进入新增、修改界面
    @RequestMapping(value = "addUI")
    public ModelAndView addUI(String id,ResponseMes res) {
        ModelAndView view = new ModelAndView("/maintain/saleman/saleAdd");
        view.addObject("msg", ResponseMes.parseMsg(res));
        if (StringUtil.isNotBlank(id)) {
            view.addObject("info",zhuanLanService.selectByPk(id));
        }
        return view;
    }

    // 删除
    @RequestMapping(value = "del")
    public void del(String id,HttpServletResponse response) {
        WebUtil.writeJson(response,JsonUtil.obj2JsonObj(zhuanLanService.deleteById(id)));
    }

    // 查询
    @RequestMapping(value = "list")
    public void list(CUS_INFO zhuan,HttpServletRequest request,HttpServletResponse response) {
        WebUtil.writeJson(response, JsonUtil.map2Json(zhuanLanService.find(zhuan, request)));
    }
}
