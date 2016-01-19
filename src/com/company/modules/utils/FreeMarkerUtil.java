package com.company.modules.utils;

import freemarker.ext.beans.HashAdapter;
import freemarker.ext.beans.MapModel;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FreeMarkerUtil {

	public static Configuration getConfiguration(Class clazz, String templatePath){
		Configuration cfg = new Configuration();
		try {
//			File templateDir = new File(templatePath);
//			cfg.setDirectoryForTemplateLoading(templateDir);
			cfg.setClassForTemplateLoading(clazz, templatePath);
			cfg.setLocale(Locale.CHINA);
			cfg.setDefaultEncoding("utf-8");
			cfg.setClassicCompatible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cfg;
	}

	
	/**
	 * 判断给定的类，是否为SimpleHash
	 * @param object
	 * @return
	 * 作者：杨凯
	 */
	public static boolean isSimpleHash(Object object){
		if (object instanceof HashAdapter) {
			return true;
		}
		return false;
	}
	
	

	/**
	 * 功能描述：将SimpleHash转换为Map<BR>
	 * @param sh
	 * @return
	 * @author:杨凯<BR>
	 * 时间：Jul 29, 2009 11:14:33 AM<BR>
	 */
	public static Map simpleHash2Map(SimpleHash sh){
		if (sh == null) {
			return null;
		}
		Map map = null;
		try {
			map = sh.toMap();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return map;
		}
	}
	
	/**
	 * 功能描述：将MapModel转换为Map<BR>
	 * @param mapModel
	 * @return
	 * @author:杨凯<BR>
	 * 时间：Aug 10, 2009 11:28:35 AM<BR>
	 */
	public static Map mapModel2Map(MapModel mapModel){
		return (Map)mapModel.getWrappedObject();
	}
	
	/**
	 * @Description：list.get(i)<BR>
	 * @Title: getMapFromList
	 * @param list
	 * @param i
	 * @return Map
	 * @author huxiao ybhuxiao@gmail.com<BR>
	 * Time：2009-8-19  下午04:02:07<BR>
	 */
	public static Map getMapFromList(List<Map> list, int i){
		return list.get(i);
	}
}
