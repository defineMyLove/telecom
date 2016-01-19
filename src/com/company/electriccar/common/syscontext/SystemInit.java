package com.company.electriccar.common.syscontext;

import com.company.modules.spring.SpringContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 系统初始化，服务器启动时运行
 * 
 * @author gengzi
 */
public class SystemInit extends HttpServlet {

	private static final long serialVersionUID = 7690685052350540376L;
	static final Logger logger = LogManager.getLogger(SystemInit.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			SpringContext.setApplicationContext(WebApplicationContextUtils
                    .getRequiredWebApplicationContext(config
                            .getServletContext()));
			SystemContext.setServletContext(config.getServletContext());
		} catch (Exception e) {
			SpringContext.closeContext();
			logger.error("启动失败："+e.getMessage());
			throw new ServletException(e);
		}
		logger.info("业务系统启动成功！");
	}
}