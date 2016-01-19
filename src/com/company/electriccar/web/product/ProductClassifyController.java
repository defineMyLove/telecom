package com.company.electriccar.web.product;

import com.company.electriccar.common.syscontext.Const;
import com.company.electriccar.common.syscontext.PromptType;
import com.company.electriccar.common.syscontext.ResponseMes;
import com.company.electriccar.domain.CHANPIN_FENLEI;
import com.company.electriccar.domain.CHANPIN_INFO;
import com.company.electriccar.service.product.ProductClassifyService;
import com.company.electriccar.service.product.ProductService;
import com.company.modules.utils.JsonUtil;
import com.company.modules.utils.WebUtil;
import net.sf.ehcache.util.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;


/**
 * @author zxl
 *
 */
@Controller
@RequestMapping("maintain/productclassify")
public class ProductClassifyController {
    public static final String INFO_PAGE = "maintain/product/productMain";
    public static final String REDI_INFO_VIEW ="redirect:/maintain/productclassify/getTreeSelected/";
    @Autowired
    private ProductClassifyService productClassifyService;
    @Autowired
    private ProductService productService;

    /**菜单管理界面*/
    @RequestMapping(value = "toMain")
    public void getXzqhTree(HttpServletResponse response) {
        WebUtil.write(response, JsonUtil.list2Json(productClassifyService.getAll()));
    }

    /** 点击时进入所选节点详细信息页面*/
    @RequestMapping(value = "getTreeSelected/{id}")
    public ModelAndView getXzqhTreeSelected(@PathVariable
    String id,ResponseMes res) {
        id = (id == null) ? String.valueOf(Const.TOP_ID) : id;
        ModelAndView view = new ModelAndView(INFO_PAGE);
        CHANPIN_FENLEI xzqh = productClassifyService.selectByPK(id);
        if(xzqh==null){
            xzqh=new CHANPIN_FENLEI();
            xzqh.setId(String.valueOf(Const.TOP_ID));
        }
        view.addObject("product", xzqh);
        view.addObject("fenlei_id", id);
        view.addObject("isLoadTree",res.getIsLoadTree());
        view.addObject("message", ResponseMes.parseMsg(res));
        return view;
    }

    @RequestMapping(value = "update")
    public String update(CHANPIN_FENLEI xzqh) {
        productClassifyService.updateByPk(xzqh);
        return ResponseMes.addPromptTypeForPath(REDI_INFO_VIEW + xzqh.getId(), PromptType.update);
    }

    @RequestMapping(value = "del/{code}")
    public String del(@PathVariable
    String code) {
        String path = REDI_INFO_VIEW;
        CHANPIN_FENLEI xzqh = null;
        if (!code.equals(Const.TOP_ID)) {
            Boolean boo = productClassifyService.isNoChildren(code);
            int proCount = productService.countProduct(code);
            if (!boo||proCount>=0) {
                //先把要删除的xzqh给查出来
                path+=code;
                path= ResponseMes.addPromptTypeForPath(path, PromptType.moduleDelFail);
            } else {
                xzqh= productClassifyService.selectByPK(code);
                path += xzqh.getUp_id();
                productClassifyService.delete(code);
                path= ResponseMes.addIsLoadTreeForPath(path, true);
                path=ResponseMes.addPromptTypeForPath(path,PromptType.del);
            }
        }
        return path;
    }

    @RequestMapping(value = "add")
    public String insert(CHANPIN_FENLEI xzqh) {
        productClassifyService.add(xzqh);
        String path=ResponseMes.addIsLoadTreeForPath(REDI_INFO_VIEW +
                xzqh.getId(), true);
        return ResponseMes.addPromptTypeForPath(path, PromptType.add);
    }
    @RequestMapping("checkId")
    public void checkId(String code,HttpServletResponse response){
       WebUtil.write(response,"true");
    }
}