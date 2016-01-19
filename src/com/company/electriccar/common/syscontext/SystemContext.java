package com.company.electriccar.common.syscontext;


import com.company.electriccar.domain.Account;
import com.gengzi.nuocommon.UserToken;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;

public class SystemContext {
	
	private static ServletContext servletContext;
	
	/** 
	 * 通过　HttpServletRequest对象得到当前用户信息
	 * @param request
	 * @return　用户对象
	 */
	public static Account getCurrentAccount(HttpServletRequest request) {
		return (Account) request.getSession().getAttribute(
				Const.USER_SESSION_ID);
	}
	/**
	 * 通过HttpSession对象得到当前用户信息
	 * @param session
	 * @return　用户对象
	 */
	public static Account getCurrentAccount(HttpSession session) {
		return (Account) session.getAttribute(
				Const.USER_SESSION_ID);
	}
	/**
	 * 清除用户登录信息
	 * @param session
	 */
	public static void clearCurrentAccount(HttpSession session) {
		session.removeAttribute(Const.USER_SESSION_ID);
	}
	/**
	 * 通过HttpServletRequest对象设置当前用户信息
	 * @param request
	 * @param user
	 */
	public static void setCurrentAccount(HttpServletRequest request,Account user) {
		request.getSession().setAttribute(Const.USER_SESSION_ID, user);
	}
	public static void setCurrentAccount(HttpSession session,Account user) {
		session.setAttribute(Const.USER_SESSION_ID, user);
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}
	public static void setServletContext(ServletContext servletContext) {
		SystemContext.servletContext = servletContext;
	}

    /**
     * 用于取代ServletContext.getRealPath()，当项目运行在weblogic服务器上时此方法返回null。
     * 原因：weblogic是以war包的形式发布的，并没有realPath
     * @return
     */
    public static String getRealPath(){
        String path;
        try {
            if((path=servletContext.getRealPath("/"))==null){
                path=servletContext.getResource("/").getPath().substring(1);  //去掉第一个字符-"/"
            }
            return path;
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return "";
    }

    public static UserToken getUser(HttpServletRequest request) {
        return (UserToken) request.getSession().getAttribute(
                Const.USER_SESSION_ID);
    }

    public static void setUser(HttpServletRequest request, UserToken loginUser) {
        request.getSession().setAttribute(Const.USER_SESSION_ID, loginUser);
    }
}
