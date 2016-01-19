package com.company.electriccar.web.product;

import com.company.electriccar.common.syscontext.Const;
import com.company.electriccar.common.syscontext.ResponseMes;
import com.company.electriccar.domain.CHANPIN_FENLEI;
import com.company.electriccar.domain.CHANPIN_FILE;
import com.company.electriccar.domain.CHANPIN_INFO;
import com.company.electriccar.service.product.ProductClassifyService;
import com.company.electriccar.service.product.ProductService;
import com.company.electriccar.service.yeji.YejiClassifyService;
import com.company.modules.utils.JsonUtil;
import com.company.modules.utils.StringUtil;
import com.company.modules.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by zxl on 14-6-17.
 *产品
 */
@Controller
@RequestMapping("/maintain/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductClassifyService yejiClassifyService;
    public static final String INFO_PAGE = "maintain/product/proMain";

    @RequestMapping(value = "add")
    public ModelAndView add(MultipartHttpServletRequest request, CHANPIN_INFO user) {
        productService.add(user, request);
        return WebUtil.goSysInfoPage(request,"","window.top.refreshGrid();window.top.closeDialog();");
    }

    // 进入 新增用户界面
    @RequestMapping(value = "addUI")
    public ModelAndView addUI(String fenlei_id,String id,ResponseMes res) {
        ModelAndView view = new ModelAndView("/maintain/product/proAdd");
        view.addObject("msg", ResponseMes.parseMsg(res));
        if (StringUtil.isNotBlank(id)) {
            Map product = productService.selectByPk(id);
            List<Map> fileList = productService.selectFiles(id);
            view.addObject("info", product);
            view.addObject("fileList",fileList);
        }else {
        }
        view.addObject("classify", yejiClassifyService.selectByPk(fenlei_id));
        view.addObject("fenlei_id", fenlei_id);
        return view;
    }

    /** 点击时进入所选节点详细信息页面*/
    @RequestMapping(value = "getTreeSelected/{id}")
    public ModelAndView getXzqhTreeSelected(@PathVariable
                                            String id,ResponseMes res) {
        ModelAndView view = new ModelAndView(INFO_PAGE);
        view.addObject("fenlei_id", id);
        return view;
    }

    // 删除 用户
    @RequestMapping(value = "del")
    public void del(String id,HttpServletResponse response) {
        productService.deleteById(id);
        WebUtil.write(response,"1");
    }

    // 删除 用户
    @RequestMapping(value = "delFile")
    public void delFile(String id,HttpServletResponse response) {
        CHANPIN_FILE file = new CHANPIN_FILE();
        file.setId(id);
        file.deleteById();
        WebUtil.write(response, "1");
    }

    // 删除 用户
    @RequestMapping(value = "list")
    public void list(CHANPIN_INFO zhuan,HttpServletRequest request,HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.map2Json(productService.find(zhuan, request)));;
    }

    @RequestMapping(value = "detail")
    public ModelAndView detail(String id,HttpServletRequest request,HttpServletResponse response) {
        ModelAndView view = new ModelAndView("/maintain/product/proDetail");
        view.addObject("info", productService.selectByPk(id));
        return view;
    }
}
