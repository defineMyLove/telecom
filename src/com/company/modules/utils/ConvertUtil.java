package com.company.modules.utils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;


public class ConvertUtil {
	
	public static Boolean obj2Boolean(Object obj){
		return obj == null ? false : Boolean.getBoolean(obj.toString());
	}
	
	public static Double obj2Double(Object obj){
		if (obj == null) {
			return null;
		}
		return StringUtil.isBlank(obj2Str(obj)) ? null : Double.valueOf(obj.toString());
	}
	
	public static void main(String[] args) {
		String str = "0.123";
		System.out.println(obj2Double(str));
	}
	
	
	/**
	 * List转Map
	 * @param dataList
	 * @param key
	 * @return
	 * 作者：
	 */
	public static Map list2Map(List<Map> dataList,Object key){
		Map resultMap = new HashMap();
		if (dataList != null && dataList.size() > 0) {
			for (Map map : dataList) {
				if (map.get(key) != null) {
					resultMap.put(map.get(key), map);
				}
			}
		}
		return resultMap;
	}
	
	/**
	 * List转Map
	 * @param dataList
	 * @param key
	 * @param value
	 * @return
	 * 作者：
	 */
	public static Map list2Map(List<Map> dataList,Object key,Object value){
		Map resultMap = new LinkedHashMap();
		if (dataList != null && dataList.size() > 0) {
			for (Map map : dataList) {
				resultMap.put(map.get(key), map.get(value));
			}
		}
		return resultMap;
	}
 	
	
	/**
	 * 如果为空，使用提供的值替换
	 * @param obj
	 * @param o
	 * @return
	 * 作者：
	 */
	public static Object defaultIfEmpty(Object obj,Object o){
		return obj == null || StringUtils.isBlank(obj.toString()) || StringUtils.equalsIgnoreCase(obj.toString(), "null") ? o : obj;
	}
	
	/**將GBK转化为GBK
	 * @param str
	 * @return
	 * 作者：
	 */
	public static String gbk2UTF8(String str){
		if (StringUtils.isBlank(str)) {
			return str;
		}
		try {
			str = URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String html2Text(String html){
		return html.replaceAll("<[^>]+>", "").replaceAll("&nbsp;","");
	}
	
	/**
	 * 功能描述：Object类转换为String，避免在Object为null时，直接toString()出错<BR>
	 * @param obj
	 * @return
	 * @author:<BR>
	 * 时间：Mar 22, 2009 10:17:29 PM<BR>
	 */
	public static String obj2Str(Object obj) {
		return obj == null ? null : obj.toString();
	}
	public static String obj2StrBlank(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	public static Integer obj2Integer(Object obj){
		return obj == null || obj.toString().trim().equals("")  ? null : Integer.parseInt(obj.toString()); 
	}
	
	public static int obj2Int(Object obj){
		return obj == null || obj.toString().trim().equals("")  ? null : Double.valueOf(obj.toString()).intValue(); 
	}
	
	public static Long obj2Long(Object obj){
		return obj == null || obj.toString().trim().equals("") ? null : Long.parseLong(obj.toString()); 
	}
	
	public static Long obj2Long(Object obj,boolean filter){
		if (!filter) {
			return obj == null || obj.toString().trim().equals("") ? null : Long.parseLong(obj.toString()); 
		} else {
			return obj == null || obj.toString().trim().equals("") ? null : Long.parseLong(obj.toString().replaceAll("[^0-9]", "")); 
		}
	}
	
	/**
	 * 功能描述：过滤用户输入的html、sql、javascript脚本<BR>
	 * @param map
	 * @return
	 * @author:<BR>
	 * 时间：Mar 23, 2009 3:41:12 PM<BR>
	 */
	public static Map filter(Map<String,Object> map) {
		Map resultMap = new HashMap();
		for (Map.Entry entry : map.entrySet()) {
			String value = StringEscapeUtils.escapeHtml(ConvertUtil.obj2Str(entry.getValue()));
			value = StringEscapeUtils.escapeSql(value);
			value = StringEscapeUtils.escapeJavaScript(value);
			resultMap.put(entry.getKey(), value);
		}
		return resultMap;
	}
}
