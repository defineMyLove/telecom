package com.company.electriccar.web;

import com.company.electriccar.domain.FANGAN_FENLEI;
import com.company.electriccar.domain.FANGAN_INFO;
import com.company.electriccar.domain.NEW_INFO;
import com.company.electriccar.service.news.NewsService;
import com.company.electriccar.service.product.ProductService;
import com.company.electriccar.service.solution.SolutionClassifyService;
import com.company.electriccar.service.solution.SolutionService;
import com.company.electriccar.service.yeji.YejiService;
import com.company.modules.displayTag.PaginationHelper;
import com.company.modules.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wang.leq.sso.SSOConfig;
import wang.leq.sso.common.util.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    NewsService newsService;
    @Autowired
    ProductService productService;

    @Autowired
    SolutionService solutionService;
    @Autowired
    SolutionClassifyService solutionClassifyService;

    @Autowired
    YejiService yejiService;
    private static final String INDEX_VIEW = "index";
    public static final String HOME = "redirect:/";

    /**
     * 登录
     * @param map
     * @param request
     * @param response
     */
    @RequestMapping
    public void home(ModelMap map,HttpServletRequest request,HttpServletResponse response){
        String basePath = getServerUrl(request);
        try {
            response.sendRedirect(HttpUtil.encodeRetURL(SSOConfig.getLoginUrl(), "ReturnURL", basePath + "mainlogin/main"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getServerUrl(HttpServletRequest request){
        String basePath = request.getScheme() + "://"+ request.getServerName();
        if(request.getServerPort()!=80){
            basePath+= ":" + request.getServerPort();
        }
        String path = request.getContextPath();
        if(!StringUtil.isBlank(path)){
            basePath+=path + "/";
        }else{
            basePath+="/";
        }
        return basePath;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String userId, String pwd,ModelMap map,HttpSession session) {
        return HOME;
    }
}