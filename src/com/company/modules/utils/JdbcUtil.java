package com.company.modules.utils;

public class JdbcUtil {
	
	
	/**
	 * 返回数组0：IP 1：端口；2：数据库名称
	 */
	private static String[] getJdbcConfig(){
		String url = PropertyUtil.get("jdbc.url");
		String strs = url.replaceAll(".+//([0-9.localhost]+):([0-9]+)/([^?]+)?.+", "$1&&$2&&$3");
		return strs.split("&&");
	}
	
	/**
	 * 取得数据库名称
	 * @return
	 * 作者：杨凯
	 */
	public static String getDbName(){
		return getJdbcConfig()[2];
	}
	
	/**
	 * 取得数据库链接的IP地址
	 * @return
	 * 作者：杨凯
	 */
	public static String getIpAddress(){
		return getJdbcConfig()[0];
	}
	
	/**
	 * 取得数据库连接的端口号
	 * @return
	 * 作者：杨凯
	 */
	public static String getPort(){
		return getJdbcConfig()[1];
	}
	/**
	 * 取得数据库链接的用户名
	 * @return
	 * 作者：杨凯
	 */
	public static String getUserName(){
		return PropertyUtil.get("jdbc.username");
	}
	
	/**
	 * 取得数据库链接的密码
	 * @return
	 * 作者：杨凯
	 */
	public static String getPassword(){
		return PropertyUtil.get("jdbc.password");
	}
}
