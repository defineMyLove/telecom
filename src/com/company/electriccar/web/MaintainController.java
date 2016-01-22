package com.company.electriccar.web;

import com.company.electriccar.common.syscontext.SystemContext;
import com.company.electriccar.domain.Account;
import com.company.electriccar.service.AccountService;
import com.company.modules.utils.PasswordUtil;
import com.company.modules.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wang.leq.sso.SSOConfig;
import wang.leq.sso.client.SSOHelper;
import wang.leq.sso.common.util.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * User: zxl
 * Date: 13-10-22
 * Time: 下午4:20
 */
@Controller
@RequestMapping("mainlogin")
public class MaintainController {
    @Autowired
    AccountService accountService;


   /* @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(Account account, ModelMap map, HttpSession session) {
        boolean isLogin = false;
        if (StringUtils.isBlank(account.getUserId()) || StringUtils.isBlank(account.getPwd())) {
            map.put("error", "用户或密码不能空,请重试!");
            return "maintain/login";
        }
        account.setPwd(account.getPwd());
        Map accounts = accountService.findEntity(account);
        if (accounts!=null&&!accounts.isEmpty()) {
            SystemContext.setCurrentAccount(session, account);
        } else {
            map.put("error", "用户或密码错误,请重试!");
            return "maintain/login";
        }
        return "maintain/main";
    }*/

    /**
     * 登录页面
     */
    /**
     * 登录 （注解跳过权限验证）
     */
    @RequestMapping(value = "login")
    public void login(HttpServletRequest request,HttpServletResponse response) {
        String basePath = getServerUrl(request);
        try {
            response.sendRedirect(HttpUtil.encodeRetURL(SSOConfig.getLoginUrl(), "ReturnURL", basePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "main")
    public String main() {
        return "maintain/main";
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

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping(value = "loginOut")
    public String login(HttpSession session,HttpServletResponse response,HttpServletRequest request) {
        SSOHelper.loginClear(request,response);
        SystemContext.clearCurrentAccount(session);
        return "redirect:/";
    }

    @RequestMapping
    public void home(HttpServletRequest request,HttpServletResponse response) {
        String basePath = getServerUrl(request);
        try {
            response.sendRedirect(HttpUtil.encodeRetURL(SSOConfig.getLoginUrl(), "ReturnURL", basePath+"mainlogin/main"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}