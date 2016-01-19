package com.company.electriccar.web.reader;

import com.company.electriccar.common.syscontext.ResponseMes;
import com.company.electriccar.domain.PRO_READER;
import com.company.electriccar.service.lunwen.ZhuanLanService;
import com.company.electriccar.service.reader.ReaderService;
import com.company.modules.utils.JsonUtil;
import com.company.modules.utils.StringUtil;
import com.company.modules.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by zxl on 14-6-17.
 */
@Controller
@RequestMapping("/maintain/reader")
public class ReaderController {
    @Autowired
    ReaderService zhuanLanService;

    @RequestMapping(value = "add")
    public ModelAndView add(HttpServletRequest request, PRO_READER user) {
        if (StringUtil.isBlank(user.getId())) {
            user.setCreate_time(new Date().getTime());
        }
        zhuanLanService.add(user);
        return WebUtil.goSysInfoPage(request, "", "window.top.refreshGrid();window.top.closeDialog();");
    }

    // 进入 新增用户界面
    @RequestMapping(value = "addUI")
    public ModelAndView addUI(String id, ResponseMes res) {
        ModelAndView view = new ModelAndView("/maintain/reader/readerAdd");
        view.addObject("msg", ResponseMes.parseMsg(res));
        if (StringUtil.isNotBlank(id)) {
            view.addObject("info", zhuanLanService.selectByPk(id));
        } else {
            //view.addObject("info",new TECH_ZHUANLAN());
        }
        return view;
    }

    // 删除 用户
    @RequestMapping(value = "del")
    public void del(String id, HttpServletResponse response) {
        zhuanLanService.deleteById(id);
        WebUtil.write(response, "1");
    }

    // 删除 用户
    @RequestMapping(value = "list")
    public void list(PRO_READER zhuan, HttpServletRequest request, HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(zhuanLanService.find(zhuan, request)));
    }

    @RequestMapping(value = "detail")
    public ModelAndView detail(String id, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/maintain/reader/readerDetail");
        view.addObject("info", zhuanLanService.selectByPk(id));
        return view;
    }
}
