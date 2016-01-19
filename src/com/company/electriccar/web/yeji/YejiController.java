package com.company.electriccar.web.yeji;

import com.company.electriccar.common.syscontext.ResponseMes;
import com.company.electriccar.domain.YEJI_INFO;
import com.company.electriccar.service.yeji.YejiClassifyService;
import com.company.electriccar.service.yeji.YejiService;
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
@RequestMapping("/maintain/yeji")
public class YejiController {
    @Autowired
    YejiService yejiService;
    @Autowired
    YejiClassifyService yejiClassifyService;

    @RequestMapping(value = "add")
    public ModelAndView add(MultipartHttpServletRequest request, YEJI_INFO user) {
        yejiService.add(user,request);
        return WebUtil.goSysInfoPage(request,"","window.top.refreshGrid();window.top.closeDialog();");
    }

    // 进入 新增用户界面
    @RequestMapping(value = "addUI")
    public ModelAndView addUI(String fenlei_id,String id,ResponseMes res) {
        ModelAndView view = new ModelAndView("/maintain/yeji/yejiAdd");
        view.addObject("msg", ResponseMes.parseMsg(res));
        if (StringUtil.isNotBlank(id)) {
            view.addObject("info", yejiService.selectByPk(id));
        }else {
        }
        view.addObject("classify", yejiClassifyService.selectByPk(fenlei_id));
        view.addObject("fenlei_id", fenlei_id);
        return view;
    }

    // 删除 用户
    @RequestMapping(value = "del")
    public void del(String id,HttpServletResponse response) {
        yejiService.deleteById(id);
        WebUtil.write(response,"1");
    }

    //
    @RequestMapping(value = "list")
    public void list(YEJI_INFO zhuan,HttpServletRequest request,HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(yejiService.find(zhuan, request)));;
    }

    @RequestMapping(value = "detail")
    public ModelAndView detail(String id,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/maintain/yeji/yejiDetail");
        view.addObject("info", yejiService.selectByPk(id));
        return view;
    }
}
