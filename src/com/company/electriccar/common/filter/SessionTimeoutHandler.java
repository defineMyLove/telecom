package com.company.electriccar.common.filter;

import com.company.electriccar.common.syscontext.SystemContext;
import com.company.electriccar.domain.Account;
import org.apache.commons.lang.StringUtils;

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
public class SessionTimeoutHandler implements Filter {

    private String handlePage;//默认超时处理页面 (在路径没有特别的超时处理页面时定位到此页面)
    private String filterExcludes;//排除验证的页面列表
    private Map<String, String> specialHandlePageMap = new HashMap<String, String>();  //特别的超时处理页面

    public void init(FilterConfig filterConfig) {
        handlePage = filterConfig.getInitParameter("handlePage");
        filterExcludes = filterConfig.getInitParameter("filterExcludes").replaceAll("\\s*|\t|\r|\n","");
        String[] arrs = filterConfig.getInitParameter("specialHandlePage").replaceAll("\\s*|\t|\r|\n","").split(";");
        for (String temp : arrs) {
            String[] t = temp.split(":");
            specialHandlePageMap.put(t[0], t[1]);
        }
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
        if (isLogon(request)) {
            filterChain.doFilter(request, response);
            return;
        } else if (isExcludeURL(request)) {//是不是maintain后台访问，如果不是则返回ture.
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        } else {
            String path = handlePage;
            String special = findSpecialHandlePage(request);
            if (special != null) {
                path = special;
            }
            response.sendRedirect(request.getContextPath() + path);
        }
    }

    /**
     * 判断路径有没有对应的超时处理页面，有则返回，没有则返回null.
     *
     * @param request
     * @return
     */
    private String findSpecialHandlePage(HttpServletRequest request) {
        String uri = request.getServletPath();
        for (String key : specialHandlePageMap.keySet()) {
            if (uri.contains(key)) {
                return specialHandlePageMap.get(key);
            } else {
                if (key.indexOf("(") !=-1){
                    String requestServletPath = key.substring(0, key.indexOf("("));
                    if (uri.contains(requestServletPath)) {
                        String[] arrs = key.substring(key.indexOf("(") + 1,key.indexOf(")")).split(",");
                        boolean isTrue=true;
                        for (String temp : arrs) {
                            String[] t = temp.split("=");
                            if (StringUtils.isBlank(request.getParameter(t[0]))||!request.getParameter(t[0]).contains(t[1])) {
                                isTrue=false;
                                break;
                            }
                        }
                        if(isTrue){
                            return specialHandlePageMap.get(key);
                        }
                    }
                }
            }
        }
        return null;
    }

    private boolean isURL(HttpServletRequest request) {
        String uri = request.getServletPath();
        if(uri.startsWith("/mainlogin"))
        return false;
        return true;
    }

    /**
     * 判断请求的URL是否是过滤器排除的URL，如果是则返回true<br>
     * 语法规则：servletPath1(paramName1=paramVal1&paramName2=paramVal2,paramName3=paramVal3);servletPath2(paramName1=paramVal1,paramName2=paramVal2) <br>
     * 解释: ";"和","是或的关系,"&"是并且的关系。servletPath不能重复配置<br>
     *
     * @return 是否是排除的url
     */
    private boolean isExcludeURL(HttpServletRequest request) {
        String uri = request.getServletPath();

        if (uri.equals("/")) {//请求登录页面
            return true;
        }
        if (uri.startsWith("/static/")) {//静态资源文件
            return true;
        }

        if (uri.startsWith("/resources/")) {//静态资源文件
            return true;
        }
        /*if(uri.equals(handlePage) || uri.endsWith(".js") || uri.endsWith(".css")
    			|| uri.endsWith(".gif") || uri.endsWith(".png") || uri.endsWith(".jpg")){//访问超时页面或资源时让其通过
    		return true;
    	}*/
        if (uri.equals(handlePage)) {//访问超时页面
            return true;
        }
        for(String value:specialHandlePageMap.values()){
            if(uri.equals(value)){
                return true;
            }
        }
        boolean ret = false;
        String[] excludeServlets = filterExcludes.split(";");
        boolean servletYes = false;//本次循环的servletPath，参数不匹配
        for (String excludeServlet : excludeServlets) {
            if(uri.startsWith(excludeServlet)){
                return true;
            }
            if (ret) {
                break;
            }
            if (servletYes) {
                break;
            }
            String requestServletPath = "";
            if (excludeServlet.indexOf("(") < 0) {
                requestServletPath = excludeServlet;
            } else {
                requestServletPath = excludeServlet.substring(0, excludeServlet.indexOf("("));
            }
            if(!requestServletPath.endsWith("/")){
                requestServletPath+="/";
            }
            if(!uri.endsWith("/")){
                uri+="/";
            }
            //1.判断是否满足servletPath
            //精确匹配
            if (!uri.equals(requestServletPath)) {
                continue;
            }
            if (excludeServlet.indexOf("(") < 0 || excludeServlet.indexOf(")") < 0) {
                ret = true;
                break;
            }
            String orExcludeParams_lang = excludeServlet.substring(excludeServlet.indexOf("(") + 1, excludeServlet.indexOf(")")).trim();
            if ("".equals(orExcludeParams_lang)) {
                ret = true;
                break;
            }
            String[] orExcludeParams = orExcludeParams_lang.split(",");
            for (String orExcludeParam : orExcludeParams) {//2是否满足任一个参数配置
                String[] andExcludeParams = orExcludeParam.split("&");
                boolean isParamsRequired = true;
                ;//是否符合所有参数配置
                for (String andExcludeParam : andExcludeParams) {//3是否满足某个参数配置
                    String[] requestParams = andExcludeParam.split("=");
                    if (requestParams.length != 2) {
                        continue;
                    }
                    String paramName = requestParams[0];
                    String paramValue = requestParams[1];
                    if ("".equals(paramName) || "".equals(paramValue)
                            || !paramValue.equals(request.getParameter(paramName))) {
                        isParamsRequired = false;
                        break;
                    }
                }
                if (isParamsRequired) {
                    ret = true;
                    break;
                }
            }
            servletYes = true;//本次循环的servletPath匹配，但是参数没有匹配
        }
        return ret;
    }

    /**
     * 判断是否正常登陆
     *
     * @return 是否是排除的url
     */
    private boolean isLogon(HttpServletRequest request) {
        boolean flag = false;

        Account user = SystemContext.getCurrentAccount(request);
        if (user != null && !"".equals(user.getUserId())) {
            flag = true;
        }

        return flag;
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
