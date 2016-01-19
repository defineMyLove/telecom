package com.company.electriccar.web.solution;

import com.company.electriccar.common.syscontext.ResponseMes;
import com.company.electriccar.domain.FANGAN_INFO;
import com.company.electriccar.service.solution.SolutionClassifyService;
import com.company.electriccar.service.solution.SolutionService;
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
@RequestMapping("/maintain/solution")
public class SolutionController {
    @Autowired
    SolutionService solutionService;
    @Autowired
    SolutionClassifyService solutionClassifyService;

    @RequestMapping(value = "add")
    public ModelAndView add(MultipartHttpServletRequest request, FANGAN_INFO user) {
        solutionService.add(user, request);
        return WebUtil.goSysInfoPage(request,"","window.top.refreshGrid();window.top.closeDialog();");
    }

    // 进入 新增用户界面
    @RequestMapping(value = "addUI")
    public ModelAndView addUI(String fenlei_id,String id,ResponseMes res) {
        ModelAndView view = new ModelAndView("/maintain/solution/solutionAdd");
        view.addObject("msg", ResponseMes.parseMsg(res));
        if (StringUtil.isNotBlank(id)) {
            view.addObject("info", solutionService.selectByPk(id));
        }else {
        }
        view.addObject("classify", solutionClassifyService.selectByPk(fenlei_id));
        view.addObject("fenlei_id", fenlei_id);
        return view;
    }

    // 删除 用户
    @RequestMapping(value = "del")
    public void del(String id,HttpServletResponse response) {
        solutionService.deleteById(id);
        WebUtil.write(response,"1");
    }

    // 删除 用户
    @RequestMapping(value = "list")
    public void list(FANGAN_INFO zhuan,HttpServletRequest request,HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(solutionService.find(zhuan, request)));;
    }

    @RequestMapping(value = "detail")
    public ModelAndView detail(String id,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/maintain/solution/solutionDetail");
        view.addObject("info", solutionService.selectByPk(id));
        return view;
    }
}
