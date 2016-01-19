package com.company.modules.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class JsonUtil {
	
	/**
	 * 将JSON格式的字符串转化为Map对象，只进行第一层次的转换
	 * @param json
	 * @return
	 * 
	 */
	public static Map<String,String> json2SimpleMap(String json){
		JSONObject object = JSONObject.fromObject(json);
		Map map = new HashMap();
		Iterator it = object.keys();
		while (it.hasNext()) {
			String key = (String)it.next();
			map.put(key, ConvertUtil.obj2Str(object.get(key)));
		}
		return map;
	}
	
	/**
	 * 将JSON格式的字符串转化为Map对象，进行所有层次的转换
	 * @param json
	 * @return
	 * 
	 */
	public static Map<String, Object> json2Map(String json){
		JSONObject object = JSONObject.fromObject(json);
		Map map = new HashMap();
		Iterator it = object.keys();
		while (it.hasNext()) {
			String key = (String)it.next();
			String value = object.getString(key);
			//如果为Map结构
			if (JSONUtils.isObject(object.get(key))) {
				map.put(key, json2Map(value));
			//如果为List结构
			} else if (JSONUtils.isArray(object.get(key))) {
				List<Map> list = new ArrayList<Map>();
				JSONArray jArray = JSONArray.fromObject(value);
				for (int i = 0; i < jArray.size(); i++) {
					list.add(json2Map(jArray.getString(i)));
				}
				map.put(key, list);
			} else {
				map.put(key, ConvertUtil.obj2Str(object.get(key)));
			}
		}
		return map;
	}
	
	/**
	 * 将JSON格式的字符串转化为List对象，进行所有层次的转换
	 * @param json
	 * @return
	 * 
	 */
	public static List<Map> json2List(String json){
		if (StringUtils.isBlank(json)) {
			return new ArrayList<Map>();
		}
		
		JSONArray jArray = JSONArray.fromObject(json);
		List<Map> list = new LinkedList<Map>();
		for (int i = 0; i < jArray.size(); i++) {
			list.add(json2Map(jArray.getString(i)));
		}
		return list;
	}
	
	/**
	 * 将JSON格式的字符串转化为List对象，只进行第一层次的转换
	 * @param json
	 * @return
	 * 
	 */
	public static List<Map> json2SimpleList(String json){
		JSONArray jArray = JSONArray.fromObject(json);
		List<Map> list = new LinkedList<Map>();
		for (int i = 0; i < jArray.size(); i++) {
			list.add(json2SimpleMap(jArray.getString(i)));
		}
		return list;
	}
	
	public static String map2Json(Map map){
		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map);
		return jsonObject.toString();
	}
	
	public static String list2Json(List list){
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}
	
	/**
	 * 功能描述：将对象转换为JSON格式的字符串<BR>
	 * @param obj
	 * @return
	 * @author:<BR>
	 * 时间：May 22, 2009 12:18:26 PM<BR>
	 */
	public static String obj2Json(Object obj) {
		JSONArray jsonArray = JSONArray.fromObject(obj);
		return jsonArray.toString();
	}
    public static String obj2JsonObj(Object obj) {
        JSONObject jsonArray = JSONObject.fromObject(obj);
        return jsonArray.toString();
    }
	
	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("name", "yangkai");
		map.put("age", 20);
		
		List<Map> list = new ArrayList<Map>();
		Map tempMap = new HashMap();
		tempMap.put("key1", "value1");
		tempMap.put("key2", "value2");
		
		Map tempMap1 = new HashMap();
		tempMap1.put("key3", "value3");
		tempMap1.put("key4", "value4");
		list.add(tempMap);
		list.add(tempMap1);
		map.put("list", list);
		String json = JsonUtil.map2Json(map);
		System.out.println(json);
//		json2List(list2Json(list));
		
	}
}
