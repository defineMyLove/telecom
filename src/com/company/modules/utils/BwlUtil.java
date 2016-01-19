package com.company.modules.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BwlUtil {

	public static List<Map> doFilter(List<Map> list,String primaryKey){
		List<Map> resultList = new ArrayList<Map>();
		Map<String,Object> bwlMap = new HashMap();
		
		for (Map map : list) {
			if (!bwlMap.containsKey(map.get(primaryKey))) {
				Map tempMap = new HashMap();
//				for (Object obj : map.keySet()) {
//					tempMap.put(ConvertUtil.obj2Str(obj), map.get(obj));
//				}
				tempMap.putAll(map);
				List<Map> tempList = new ArrayList<Map>();
				tempList.add(map);
				tempMap.put("DATALIST", tempList);
				resultList.add(tempMap);
				bwlMap.put(ConvertUtil.obj2Str(map.get(primaryKey)), tempMap);
			} else {
				List<Map> tempList = (List<Map>)((Map)bwlMap.get(map.get(primaryKey))).get("DATALIST");
				tempList.add(map);
			}
		}
		return resultList;
	}
	public static List<Map> doFilter(List<Map> list,String primaryKey,String subListNames){
		List<Map> resultList = new ArrayList<Map>();
		Map<String,Object> bwlMap = new HashMap();
		for (Map map : list) {
			if (!bwlMap.containsKey(map.get(primaryKey))) {
				Map tempMap = new HashMap();
				for (Object obj : map.keySet()) {
					tempMap.put(ConvertUtil.obj2Str(obj), map.get(obj));
				}
				List<Map> tempList = new ArrayList<Map>();
				tempList.add(map);
				tempMap.put(subListNames, tempList);
				resultList.add(tempMap);
				bwlMap.put(ConvertUtil.obj2Str(map.get(primaryKey)), tempMap);
			} else {
				List<Map> tempList = (List<Map>)((Map)bwlMap.get(map.get(primaryKey))).get(subListNames);
				tempList.add(map);
			}
		}
		return resultList;
	}
	/**
	 * 功能描述：备忘录算法<br>
	 * 
	 * @return List<Map<String,Object>> 作者：杨凯<BR>
	 *         时间：Aug 27, 2008 <br>
	 */
	public static List<Map<String, Object>> bwl(List list, String[][] keys, String[] subListNames) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		// 根据备忘录的层数声明备忘录
		Map[] bwlArrays = new HashMap[keys.length];
		for (int i = 0; i < bwlArrays.length; i++) {
			bwlArrays[i] = new HashMap<String, Map>();
		}

		if (list != null && list.size() > 0) {
			for (Map tempMap : (List<Map>) list) {
				for (int i = 0; i < keys.length; i++) {
					String keyValue = ConvertUtil.obj2Str(tempMap.get(keys[i][0]));
					if (!bwlArrays[i].containsKey(keyValue)) {
						Map<String, Object> subMap = new HashMap<String, Object>();
						for (int j = 0; j < keys[i].length; j++) {
							subMap.put(keys[i][j], ConvertUtil.obj2Str(tempMap.get(keys[i][j])));
						}
						if (i == 0) {
							subMap.put(subListNames[i], new ArrayList());
							bwlArrays[i].put(keyValue, subMap);
							resultList.add(subMap);
						} else {
							List subList = (List) ((Map) bwlArrays[i - 1].get(tempMap.get(keys[i - 1][0]))).get(subListNames[i - 1]);
							subList.add(subMap);
							subMap.put(subListNames[i], new ArrayList());
							bwlArrays[i].put(keyValue, subMap);
						}
					}
				}
				List subSubList = (List) ((Map) bwlArrays[bwlArrays.length - 1].get(tempMap.get(keys[keys.length - 1][0]))).get(subListNames[subListNames.length - 1]);
				subSubList.add(tempMap);
				for (int i = 0; i < keys.length; i++) {
					for (int j = 0; j < keys[i].length; j++) {
						tempMap.remove(keys[i][j]);
					}
				}
			}
		}
		return resultList;
	}
}
