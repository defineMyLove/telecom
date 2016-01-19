package com.company.electriccar.web.shipin;

import com.company.electriccar.common.syscontext.ResponseMes;
import com.company.electriccar.domain.SHIPIN_INFO;
import com.company.electriccar.service.shipin.ShipinService;
import com.company.modules.utils.JsonUtil;
import com.company.modules.utils.StringUtil;
import com.company.modules.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zxl on 14-6-17.
 *知识库
 */
@Controller
@RequestMapping("/maintain/shipin")
public class ShipinController {
    @Autowired
    ShipinService shipinService;

    @RequestMapping(value = "add")
    public ModelAndView add(MultipartHttpServletRequest request, SHIPIN_INFO user) {
        shipinService.add(user,request);
        return WebUtil.goSysInfoPage(request,"","window.top.refreshGrid();window.top.closeDialog();");
    }

    // 进入 新增用户界面
    @RequestMapping(value = "addUI")
    public ModelAndView addUI(String id,ResponseMes res) {
        ModelAndView view = new ModelAndView("/maintain/shipin/shipinAdd");
        view.addObject("msg", ResponseMes.parseMsg(res));
        if (StringUtil.isNotBlank(id)) {
            view.addObject("info", shipinService.selectByPk(id));
        }else {
            //view.addObject("info",new SHIPIN_INFO());
        }
        return view;
    }

    // 删除 用户
    @RequestMapping(value = "del")
    public void del(String id,HttpServletResponse response) {
        shipinService.deleteById(id);
        WebUtil.write(response,"1");
    }

    // 删除 用户
    @RequestMapping(value = "list")
    public void list(SHIPIN_INFO zhuan,HttpServletRequest request,HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(shipinService.find(zhuan, request)));;
    }

    @RequestMapping(value = "detail")
    public ModelAndView detail(String id,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/maintain/shipin/shipinDetail");
        view.addObject("info", shipinService.selectByPk(id));
        return view;
    }
}
