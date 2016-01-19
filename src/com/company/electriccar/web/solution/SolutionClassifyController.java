package com.company.electriccar.web.solution;

import com.company.electriccar.common.syscontext.ResponseMes;
import com.company.electriccar.domain.FANGAN_FENLEI;
import com.company.electriccar.service.solution.SolutionClassifyService;
import com.company.modules.utils.JsonUtil;
import com.company.modules.utils.StringUtil;
import com.company.modules.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zxl on 14-6-17.
 *知识库
 */
@Controller
@RequestMapping("/maintain/solutionclassify")
public class SolutionClassifyController {
    @Autowired
    SolutionClassifyService solutionService;

    @RequestMapping(value = "add")
    public ModelAndView add(HttpServletRequest request, FANGAN_FENLEI user) {
        solutionService.add(user);
        return WebUtil.goSysInfoPage(request,"","window.top.refreshGrid();window.top.closeDialog();");
    }

    // 进入 新增用户界面
    @RequestMapping(value = "addUI")
    public ModelAndView addUI(String id,ResponseMes res) {
        ModelAndView view = new ModelAndView("/maintain/solution/solutionClassifyAdd");
        view.addObject("msg", ResponseMes.parseMsg(res));
        if (StringUtil.isNotBlank(id)) {
            view.addObject("info", solutionService.selectByPk(id));
        }else {
            //view.addObject("info",new FANGAN_FENLEI());
        }
        return view;
    }

    // 删除 用户
    @RequestMapping(value = "del")
    public void del(String id,HttpServletResponse response) {
        solutionService.deleteById(id);
        WebUtil.write(response,"1");
    }

    // 删除 用户
    @RequestMapping(value = "tree")
    public void tree(String id,HttpServletResponse response) {
        solutionService.getAll(id);
        WebUtil.write(response,JsonUtil.list2Json(solutionService.getAll(id)));
    }

    // 删除 用户
    @RequestMapping(value = "list")
    public void list(FANGAN_FENLEI zhuan,HttpServletRequest request,HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(solutionService.find(zhuan, request)));;
    }

    @RequestMapping(value = "detail")
    public ModelAndView detail(String id,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/maintain/solution/solutionClassifyDetail");
        view.addObject("info", solutionService.selectByPk(id));
        return view;
    }


    /** 点击时进入所选节点详细信息页面*/
    @RequestMapping(value = "getTreeSelected/{id}")
    public String  getReceiveSiteTreeSelected(@PathVariable String id,ResponseMes res,ModelMap map) {
        //查询对应的行政区划
        map.put("fenlei_id", id);
        return "/maintain/solution/solutionMain";
    }

}
