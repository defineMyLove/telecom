package com.company.electriccar.web.news;

import com.company.electriccar.common.syscontext.ResponseMes;
import com.company.electriccar.domain.NEW_INFO;
import com.company.electriccar.service.news.NewsService;
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
@RequestMapping("/maintain/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @RequestMapping(value = "add")
    public ModelAndView add(HttpServletRequest request, NEW_INFO user) {
        newsService.add(user);
        return WebUtil.goSysInfoPage(request,"","window.top.refreshGrid();window.top.closeDialog();");
    }

    // 进入 新增用户界面
    @RequestMapping(value = "addUI")
    public ModelAndView addUI(String id,ResponseMes res) {
        ModelAndView view = new ModelAndView("/maintain/news/newsAdd");
        view.addObject("msg", ResponseMes.parseMsg(res));
        if (StringUtil.isNotBlank(id)) {
            view.addObject("info",newsService.selectByPk(id));
        }else {
            //view.addObject("info",new NEW_INFO());
        }
        return view;
    }

    // 删除 用户
    @RequestMapping(value = "del")
    public void del(String id,HttpServletResponse response) {
        newsService.deleteById(id);
        WebUtil.write(response,"1");
    }

    // 删除 用户
    @RequestMapping(value = "list")
    public void list(NEW_INFO zhuan,HttpServletRequest request,HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(newsService.find(zhuan,request)));;
    }

    @RequestMapping(value = "detail")
    public ModelAndView detail(String id,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/maintain/news/newsDetail");
        view.addObject("info", newsService.selectByPk(id));
        return view;
    }
}
