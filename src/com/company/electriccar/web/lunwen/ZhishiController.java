package com.company.electriccar.web.lunwen;

import com.company.electriccar.common.syscontext.ResponseMes;
import com.company.electriccar.domain.TECH_ZHISHI;
import com.company.electriccar.service.lunwen.ZhishiService;
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
 * Created by zxl on 14-6-17.
 *知识库
 */
@Controller
@RequestMapping("/maintain/zhishi")
public class ZhishiController {
    @Autowired
    ZhishiService zhishiService;

    @RequestMapping(value = "add")
    public ModelAndView add(HttpServletRequest request, TECH_ZHISHI user) {
        zhishiService.add(user);
        return WebUtil.goSysInfoPage(request,"","window.top.refreshGrid();window.top.closeDialog();");
    }

    // 进入 新增用户界面
    @RequestMapping(value = "addUI")
    public ModelAndView addUI(String id,ResponseMes res) {
        ModelAndView view = new ModelAndView("/maintain/zhishi/zhishiAdd");
        view.addObject("msg", ResponseMes.parseMsg(res));
        if (StringUtil.isNotBlank(id)) {
            view.addObject("info", zhishiService.selectByPk(id));
        }else {
            //view.addObject("info",new TECH_ZHISHI());
        }
        return view;
    }

    // 删除 用户
    @RequestMapping(value = "del")
    public void del(String id,HttpServletResponse response) {
        zhishiService.deleteById(id);
        WebUtil.write(response,"1");
    }

    // 删除 用户
    @RequestMapping(value = "list")
    public void list(TECH_ZHISHI zhuan,HttpServletRequest request,HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(zhishiService.find(zhuan, request)));;
    }

    @RequestMapping(value = "detail")
    public ModelAndView detail(String id,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/maintain/zhishi/zhishiDetail");
        view.addObject("info", zhishiService.selectByPk(id));
        return view;
    }
}
