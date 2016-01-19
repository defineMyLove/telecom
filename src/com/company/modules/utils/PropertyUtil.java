package com.company.modules.utils;

import java.io.*;
import java.util.Properties;

/**
 * 读取配置文件
 */
public class PropertyUtil {

	private static String fileName = "application.properties";
	
	/**
	 * 返回jdbc.properties的指定值
	 * @param key
	 * @return
	 * 作者：杨凯
	 */
	public static String get(String key){
		return get(fileName, key);
	}
	
	/**
	 * 加载指定的配置文件，返回key所代表的value值
	 * @param fileName property文件的名字（该文件最好放在根目录下）
	 * @param key 键值
	 * @return
	 * 作者：杨凯
	 */
	public static String get(String fileName,String key){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String value = "";
		try {
			InputStream in = classLoader.getResource(fileName).openStream();
			Properties props = new Properties();
			props.load(in);
			value = props.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取配置文件失败");
		}
		return value;
	}
	
	public static void write(String fileName, String key, String value) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String filePath =  classLoader.getResource(fileName).getPath();
		Properties prop = new Properties();
		try {
			InputStream fis = new FileInputStream(filePath);
			// 从输入流中读取属性列表（键和元素对）
			prop.load(fis);
			// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(filePath);
			prop.setProperty(key, value);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			prop.store(fos, "");
			fis.close();
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Visit " + fileName + " for updating " + key + " value error");

		}
	}

}
