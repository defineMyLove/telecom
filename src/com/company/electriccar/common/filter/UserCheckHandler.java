package com.company.electriccar.common.filter;

import com.company.electriccar.common.syscontext.SystemContext;
import com.company.electriccar.domain.Account;
import com.gengzi.nuocommon.UserToken;
import org.apache.commons.lang.StringUtils;
import wang.leq.sso.client.SSOHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * session超时验证
 *
 * @author gengzi
 */
public class UserCheckHandler implements Filter {

    private String handlePage;//默认超时处理页面 (在路径没有特别的超时处理页面时定位到此页面)
    private String filterExcludes;//排除验证的页面列表
    private Map<String, String> specialHandlePageMap = new HashMap<String, String>();  //特别的超时处理页面

    public void init(FilterConfig filterConfig) {
     /*   handlePage = filterConfig.getInitParameter("handlePage");
        filterExcludes = filterConfig.getInitParameter("filterExcludes").replaceAll("\\s*|\t|\r|\n","");
        String[] arrs = filterConfig.getInitParameter("specialHandlePage").replaceAll("\\s*|\t|\r|\n","").split(";");
        for (String temp : arrs) {
            String[] t = temp.split(":");
            specialHandlePageMap.put(t[0], t[1]);
        }*/
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        if (servletRequest instanceof HttpServletRequest) {
            request = (HttpServletRequest) servletRequest;
            response = (HttpServletResponse) servletResponse;
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        UserToken loginUser = SystemContext.getUser(request);
        //1、先校验session
        if (loginUser==null){
            //2、校验cookies
            loginUser =(UserToken) SSOHelper.getToken(request);
            if (loginUser != null){
                SystemContext.setUser(request, loginUser);
            }
        }
        filterChain.doFilter(request, response);
    }


    @Override
    public void destroy() {
    }

    public String getFilterExcludes() {
        return filterExcludes;
    }

    public void setFilterExcludes(String filterExcludes) {
        this.filterExcludes = filterExcludes;
    }

    public String getHandlePage() {
        return handlePage;
    }

    public void setHandlePage(String handlePage) {
        this.handlePage = handlePage;
    }

}
