package com.company.electriccar.web.reader;

import com.company.electriccar.common.syscontext.ResponseMes;
import com.company.electriccar.domain.PRO_FLAG;
import com.company.electriccar.service.reader.FlagService;
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
 *
 */
@Controller
@RequestMapping("/maintain/flag")
public class FlagController {
    @Autowired
    FlagService zhuanLanService;

    @RequestMapping(value = "add")
    public ModelAndView add(HttpServletRequest request, PRO_FLAG user) {
        zhuanLanService.add(user);
        return WebUtil.goSysInfoPage(request,"","window.top.refreshGrid();window.top.closeDialog();");
    }

    // 进入 新增用户界面
    @RequestMapping(value = "addUI")
    public ModelAndView addUI(String id,ResponseMes res) {
        ModelAndView view = new ModelAndView("/maintain/flag/flagAdd");
        view.addObject("msg", ResponseMes.parseMsg(res));
        if (StringUtil.isNotBlank(id)) {
            view.addObject("info",zhuanLanService.selectByPk(id));
        }else {
            //view.addObject("info",new PRO_FLAG());
        }
        return view;
    }

    // 删除 用户
    @RequestMapping(value = "del")
    public void del(String id,HttpServletResponse response) {
        zhuanLanService.deleteById(id);
        WebUtil.write(response,"1");
    }

    // 查询列表
    @RequestMapping(value = "list")
    public void list(PRO_FLAG zhuan,HttpServletRequest request,HttpServletResponse response) {
        WebUtil.writeJson(response, JsonUtil.map2Json(zhuanLanService.find(zhuan,request)));
    }

    // 删除 用户
    @RequestMapping(value = "query")
    public void query(PRO_FLAG zhuan,String startTime,String endTime,HttpServletRequest request,HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(zhuanLanService.query(zhuan,startTime,endTime,request)));
    }

    // 标签月份分析
    @RequestMapping(value = "analysis")
    public void analysis(String raftId,String month,HttpServletRequest request,HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(zhuanLanService.analysis(raftId,month,request)));
    }

    @RequestMapping(value = "detail")
    public ModelAndView detail(String id,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/maintain/flag/flagDetail");
        view.addObject("info", zhuanLanService.selectByPk(id));
        return view;
    }
}
