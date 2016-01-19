package com.company.modules.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取Spring配置文件中的bean
 */
public class SpringBeanUtil implements ApplicationContextAware {


	private static ApplicationContext staticContext;
	
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		staticContext = context;
	}
	
	/**
	 * ȡ������Bean Names
	 * @return
	 * @version V1.0.0
	 * @author yk
	 * @date Sep 7, 2013 4:24:28 PM
	 */
	public static String[] getSpringBeanNames() {
		return staticContext.getBeanDefinitionNames();
	}
	

	/**
	 * ͨ��bean��name��ȡbean������
	 * @param strBeanName beanName
	 * @return
	 */
	public static Object getBean(String strBeanName) {
		if (staticContext.containsBean(strBeanName)) {
			return staticContext.getBean(strBeanName);
		}
		return null;
	}
	
	public static <T> T getBean(Class<T> clazz){
		return staticContext.getBean(clazz);
	}
	
}
