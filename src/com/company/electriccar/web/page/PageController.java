package com.company.electriccar.web.page;

import com.company.electriccar.domain.*;
import com.company.electriccar.service.lunwen.ZhishiService;
import com.company.electriccar.service.lunwen.ZhuanLanService;
import com.company.electriccar.service.mesboard.MesBoardService;
import com.company.electriccar.service.news.NewsService;
import com.company.electriccar.service.product.ProductClassifyService;
import com.company.electriccar.service.product.ProductService;
import com.company.electriccar.service.shipin.ShipinService;
import com.company.electriccar.service.solution.SolutionClassifyService;
import com.company.electriccar.service.solution.SolutionService;
import com.company.electriccar.service.work.WorkService;
import com.company.electriccar.service.yeji.YejiClassifyService;
import com.company.electriccar.service.yeji.YejiService;
import com.company.modules.displayTag.PaginationHelper;
import com.company.modules.utils.StringUtil;
import com.company.modules.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by zxl on 14-6-29.
 */
@Controller
@RequestMapping("/page")
public class PageController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private YejiClassifyService yejiClassifyService;
    @Autowired
    private YejiService yejiService;
    @Autowired
    private ShipinService shipinService;
    @Autowired
    private ZhuanLanService lunwenService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductClassifyService productClassifyService;
    @Autowired
    private SolutionClassifyService solutionClassifyService;
    @Autowired
    private MesBoardService mesBoardService;
    @Autowired
    private ZhishiService zhishiService;
    @Autowired
    private WorkService workService;
    @Autowired
    private SolutionService solutionService;


    /**
     * 新闻中心
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("news")
    public String news(ModelMap model,HttpServletRequest request) {
        NEW_INFO info = new NEW_INFO();
        PaginationHelper<Map> newList = PaginationHelper.createPagination(newsService.find(info,request));
        model.put("newList", newList);
        return "news";
    }

    /**
     * 公司资历
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("aboutque")
    public String aboutque(ModelMap model,HttpServletRequest request) {
        return "aboutque";
    }

    /**
     * 公司业绩-分类列表
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("aboutper")
    public String aboutper(ModelMap model,HttpServletRequest request) {
        YEJI_FENLEI fenlei = new YEJI_FENLEI();
        PaginationHelper<Map> classList = PaginationHelper.createPagination(yejiClassifyService.find(fenlei,request));
        model.put("classList", classList);
        return "aboutper";
    }

    /**
     * 公司业绩
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("yejilist")
    public String yejilist(ModelMap model,HttpServletRequest request) {
        String fenlei_id = request.getParameter("fenlei_id");
        YEJI_INFO info = new YEJI_INFO();
        info.setFenlei_id(fenlei_id);
        YEJI_FENLEI fenlei = new YEJI_FENLEI();
        fenlei.setId(fenlei_id);
        fenlei.queryForBean();
        PaginationHelper<Map> newList = PaginationHelper.createPagination(yejiService.find(info, request));
        model.put("newList", newList);
        model.put("fenlei", fenlei);
        return "aboutperlist";
    }

    /**
     * 公司业绩-详情
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("yejiDetail")
    public String yejiDetail(ModelMap model,HttpServletRequest request) {
        String id = request.getParameter("id");
        model.put("info", yejiService.selectByPk(id));
        model.put("proFile", yejiService.selectFiles(id));
        return "yejiDetail";
    }

    /**
     * 公司业绩
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("aboutvid")
    public String aboutvid(ModelMap model,HttpServletRequest request) {
        SHIPIN_INFO info = new SHIPIN_INFO();
        PaginationHelper<Map> newList = PaginationHelper.createPagination(shipinService.find(info, request));
        model.put("newList", newList);
        return "aboutvid";
    }

    /**
     * 论文专栏
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("paper")
    public String paper(ModelMap model,HttpServletRequest request) {
        TECH_ZHUANLAN info = new TECH_ZHUANLAN();
        PaginationHelper<Map> newList = PaginationHelper.createPagination(lunwenService.find(info, request));
        model.put("newList", newList);
        return "paper";
    }

    /**
     * 产品资料
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("data")
    public String data(ModelMap model,HttpServletRequest request) {
        return "redirect:/page/products";
    }

    @RequestMapping("products")
    public String products(ModelMap model,HttpServletRequest request) {
        model.put("newList", productClassifyService.getLevelClassify());
        return "products";
    }

    @RequestMapping("product")
    public String product(ModelMap model,String id ,HttpServletRequest request) {
        model.put("newList", productClassifyService.getLevelClassify());
        model.put("productList",productService.findProduct(id));
        model.put("info",productClassifyService.selectByPk(id));
        model.put("classfiyId",id);
        return "products";
    }
    @RequestMapping("prosearch")
    public String prosearch(ModelMap model,String name ,HttpServletRequest request) {
        model.put("newList", productClassifyService.getLevelClassify());
        model.put("pros", productService.selectByName(name));
        model.put("name",StringUtil.isBlank(name)?"请输入关键字":name);
        return "prosearch";
    }
    @RequestMapping("proDetail")
    public String proDetail(ModelMap model,String id ,HttpServletRequest request) {
        Map<String,String> product =productService.selectByPk(id);
        String pId = StringUtil.isNotBlank(request.getParameter("classfiyId")) ? request.getParameter("classfiyId") : product.get("fenlei_id");
        model.put("newList", productClassifyService.getLevelClassify());
        model.put("info",product);
        model.put("classifyInfo", productClassifyService.selectByPk(pId));
        model.put("proFile", productService.selectFiles(id));
        return "productDetail";
    }


    @RequestMapping("addMes")
    public void addMes(MES_BOARD mes, HttpServletResponse response) {
        mesBoardService.add(mes);
        WebUtil.write(response, "1");
    }


    /**
     * 知识库
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("know")
    public String know(ModelMap model,HttpServletRequest request) {
        TECH_ZHISHI info = new TECH_ZHISHI();
        PaginationHelper<Map> newList = PaginationHelper.createPagination(zhishiService.find(info, request));
        model.put("newList", newList);
        return "know";
    }
    /**
     * 知识库
     * @param model
     * @return
     */
    @RequestMapping("lunwenDetail")
    public String lunwenDetail(ModelMap model,String id) {
        model.put("info",lunwenService.selectByPk(id));
        return "lunwenDetail";
    }

    /**
     * 知识库
     * @param model
     * @return
     */
    @RequestMapping("newDetail")
    public String newDetail(ModelMap model,String id) {
        model.put("info",newsService.selectByPk(id));
        return "newDetail";
    }

    /**
     * 知识库
     * @param model
     * @return
     */
    @RequestMapping("zhishiDetail")
    public String zhishiDetail(ModelMap model,String id) {
        model.put("info",zhishiService.selectByPk(id));
        return "zhishiDetail";
    }

    /**
     * 知识库
     * @param model
     * @return
     */
    @RequestMapping("job")
    public String job(ModelMap model,String id,HttpServletRequest request) {
        WORK_INFO info = new WORK_INFO();
        info.setId(id);
        model.put("newList", workService.find(info,request).get("rows"));
        return "job";
    }
    /**
     * 解决方案界面
     * @param model
     * @return
     */
    @RequestMapping("solutions")
    public String solutions(ModelMap model,HttpServletRequest request) {
        model.put("solutionList", solutionClassifyService.findAll());
        return "solutions";
    }

    /**
     * 解决方案界面
     * @param model
     * @return
     */
    @RequestMapping("solution")
    public String solution(ModelMap model,String fenlei_id,HttpServletRequest request) {
        model.put("solutionList", solutionClassifyService.findAll());

        FANGAN_INFO info = new FANGAN_INFO();
        info.setFenlei_id(fenlei_id);
        model.put("infos", solutionService.find(info, request).get("rows"));
        model.put("currId",fenlei_id);
        return "solutions";
    }

    @RequestMapping("solutionDetail")
    public String solutionDetail(ModelMap model,String id,HttpServletRequest request) {
        model.put("solutionList", solutionClassifyService.findAll());
        model.put("info", solutionService.selectByPk(id));
        return "solutionDetail";
    }
}


