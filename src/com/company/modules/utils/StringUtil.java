package com.company.modules.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class StringUtil {

	public static String test(){
		return StringUtil.getUUID();
	}
	
	
	/**
	 * 验证是否为Email格式
	 * @param email
	 * @return
	 * @version V1.0.0
	 * @author
	 * @date Sep 7, 2013 12:06:34 PM
	 */
	public static boolean isEmail(String email){
        Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配   
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()){
            return true;
        }
        return false;
    }
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	public static String asc2gbk(String param){
		String ret = "";
		if (StringUtils.isNotBlank(param)) {
			try {
				ret = new String(param.getBytes("ISO-8859-1"),"GBK").trim();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				ret ="";
			}
		}
		return ret;
	}
	public static String asc2utf8(String param){
		String ret = "";
		if (StringUtils.isNotBlank(param)) {
			try {
				ret = new String(param.getBytes("ISO-8859-1"),"UTF-8").trim();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				ret ="";
			}
		}
		return ret;
	}
	/**
	 * 功能描述：阿拉伯数字转汉字<BR>
	 * 
	 * @param a
	 * @return
	 * @author:<BR>
	 *            时间：Apr 25, 2009 7:10:23 PM<BR>
	 */
	public static String translateNumToChinese(int a) {
		String[] units = { "", "十", "百", "千", "万", "十", "百", "千", "亿" };
		String[] nums = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
		String result = "";
		if (a < 0) {
			result = "负";
			a = Math.abs(a);
		}
		String t = String.valueOf(a);
		for (int i = t.length() - 1; i >= 0; i--) {
			int r = (int) (a / Math.pow(10, i));
			if (r % 10 != 0) {
				String s = String.valueOf(r);
				String l = s.substring(s.length() - 1, s.length());
				result += nums[Integer.parseInt(l) - 1];
				result += (units[i]);
			} else {
				if (!result.endsWith("零")) {
					result += "零";
				}
			}
		}
		return result;
	}

	/**
	 * 功能描述：取得32位UUID<BR>
	 * 
	 * @return
	 * @author:<BR>
	 *            时间：Feb 20, 2009 11:04:36 PM<BR>
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static void main(String[] args) {
		System.out.println(getUUID());
	}

	/**
	 * 功能描述：电话号码用××代替<BR>
	 * 
	 * @param photo
	 * @return
	 * @author:<BR>
	 *            时间：Apr 25, 2009 6:54:11 PM<BR>
	 */
	public static String dhhmreplaceByXX(String photo) {
		if (StringUtils.isBlank(photo)) {
			return null;
		}
		char[] strs = photo.toCharArray();
		for (int i = 3; i < 8; i++) {
			strs[i] = '*';
		}
		return new String(strs);
	}

	/**
	 * 去掉字符串中的回车换行符
	 * 
	 * @param str
	 * @return
	 */
	public static String removeNewline(String str) {
		String result = null;
		if (str != null) {
			result = str.replaceAll("\\r\\n", "");
			result = result.replaceAll(new String(new char[] { 10 }), "");
		}
		return result;
	}

	/**
	 * 功能描述：判断字符串是否为汉字<BR>
	 * @param str
	 * @return
	 * @author:<BR>
	 * 时间：Jul 8, 2009 9:02:48 AM<BR>
	 */
	public static boolean isNumber(String str) {
		if (str.matches("\\d*")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 功能描述：浮点型判断
	 * 
	 * @param str
	 * @return
	 * @author 胡晓<BR>
	 *         时间：2009-7-29<BR>
	 */
	public static boolean isFloat(String str) {
		if (str == null || "".equals(str))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 功能描述：整型判断
	 * 
	 * @param str
	 * @return
	 * @author 胡晓<BR>
	 *         时间：2009-7-29<BR>
	 */
	public static boolean isInteger(String str) {
		if (StringUtil.isBlank(str))
			return false;
		return str.matches("^-?\\d+");
	}

	/**
	 * 功能描述：获取子字符串的个数
	 * 
	 * @return
	 * @author 胡晓<BR>
	 *         时间：2009-7-16<BR>
	 */
	public static int getSubCount(String sub, String string) {
		if (StringUtils.isBlank(string)) {
			return 0;
		}
		int subLength = sub.length();
		int forLength = string.length() - sub.length() + 1; // 循环次数
		int count = 0; // 记录循环次数
		for (int i = 0; i < forLength; i++) {
			if (StringUtils.substring(string, i, i + subLength).equals(sub)) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 功能描述：接受一个List<String>，如果有任何一个为空，则返回true，否则返回false
	 * @param list
	 * @return
	 * @author 胡晓<BR>
	 * 时间：2009-8-8<BR>
	 */
	public static boolean isBlank(String... list){
		for (String string : list) {
			if(StringUtils.isBlank(string)){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 如果IsNull，就返回defalut
	 * @param str
	 * @param defalut
	 * @return
	 * @version V1.0.0
	 * @author
	 * @date Sep 19, 2013 8:59:22 AM
	 */
	public static String defaultIfNull(String str,String defalut){
		if (StringUtils.isBlank(str)) {
			return defalut;
		}
		return str;
	}
	
	/**
	 * 功能描述：都不为空，则返回true
	 * @param list
	 * @return
	 * @author 胡晓<BR>
	 * 时间：2009-9-25 上午09:04:47<BR>
	 */
	public static boolean isNotBlank(String... list){
		return !isBlank(list);
	}
	
	/**
	 * 功能描述：trim从前台获取的参数，包括null变成""，'变成''<BR>
	 * @param s
	 * @return
	 * @author 胡晓<BR>
	 * 时间：2009-10-28 下午03:00:52<BR>
	 */
	public static String trimParam(Object s){
		String str = ConvertUtil.obj2Str(s);
		if(StringUtils.isBlank(str)){
			return "";
		}else{
			return str.replaceAll("'", "''").trim();
		}
	}
	
	/**
	 * 描述：tapestry 比较一个字符串包含另一个字符串
	 * @param o1
	 * @param o2
	 * @return
	 * 作者：李坤
	 * 时间：Sep 9, 2010
	 */
	public static boolean Str1ContainsStr2(Object o1 , Object o2){
		if(ConvertUtil.obj2Str(o1).contains(ConvertUtil.obj2Str(o2))){
			return true;
		}
		return false;
	}
	
	/**
	 * 功能描述：截取指定长度字符串，一个汉字占两个字节，字符和数字占用一个<BR>
	 * @param value
	 * @param length
	 * @param flag 为真则后加"..."
	 * @return
	 * @author:<BR>
	 * 时间：Sep 12, 2009 3:53:36 PM<BR>
	 */
	public static String subStr(String value,int length,boolean flag){
		if (StringUtils.isBlank(value) || value.getBytes().length <= length) 
			return value;
		for (int i = 0; i <= value.length(); i++) {
			if (value.substring(0, i).getBytes().length > length) {
				if (i - 1 >= 0) {
					return value.substring(0, i - 1)+(flag ? "...":"");
				}
			}
		}
		return value;
	}
	/**
	 * 清除一个时间格式字符串中的"-" ":"和空格，返回一个格式化的String<br>
	 * 例如：将一个 2010-11-13 12:00 转换为 201011131200 <br>
	 * 
	 * 作者：<a href="mailto:winhunter@163.com">郝世博</a><br>
	 * 时间：2010-12-08 18:02
	 * 
	 * @param date
	 * @return
	 */
	public static String clearDateStrToLong(String date) throws NumberFormatException{
		String ret = "";
		if(date!=null) {
			ret = date.replaceAll("[-]*[\\s]*[:]*", "");
		}
		return ret;
	}
	/**
	 * 描述：小数格式化 
	 * @param value  (需要格式化的数字)
	 * @param length (保留几位小数)
	 * @return
	 * 作者：李坤
	 * 时间：Nov 5, 2010
	 */
	public static Double getDoubleFormat(Double value,Integer length){
		String str=".";
		for (int i = 0; i < length; i++) {
			str+="#";
		}
		return  Double.valueOf(new DecimalFormat(str).format(value));
	}
	
	public static  String decode(String str){
		String string = "";
		if(StringUtil.isNotBlank(str)){
			try {
				string = URLDecoder.decode(str, "gbk");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return  string;
	}
	
	public static  String decode_utf(String str){
		String string = "";
		if(StringUtil.isNotBlank(str)){
			try {
				string = URLDecoder.decode(str, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return  string;
	}
	
	public static String decode_Iso_utf(String str) throws UnsupportedEncodingException{
		if(StringUtil.isBlank(str)){
			return str;
		}
		return new String(str.getBytes("ISO-8859-1"),"GBK");
	}
	
	/**
	 *  将2010-1-1 转为 20100101.
	 * @author <a href="mailto:winhunter@163.com">郝世博</a>
	 * @time Apr 8, 2011 6:48:15 PM
	 */
	public static String datestr2long(String date){
		String[] time = date.split("-");
		StringBuffer dateBuf = new StringBuffer();
		for (int i = 0; i < time.length; i++) {
			if (i==0) {
				dateBuf.append(time[i]);
			}else {
				dateBuf.append(time[i].length()<2?"0"+time[i]:time[i]);
			}
		}
		return dateBuf.toString();
	}
	
	/**
	 *  将一个字符串根据给定的长度分隔成N个字符串的数组.
	 * @author <a href="mailto:winhunter@163.com">郝世博</a>
	 * @time Mar 30, 2011 11:06:59 PM
	 */
	public static String[] splitForLength(String str , int length){
		int len = str.length();
		int pageSize = (int) Math.ceil(len/70D);
		String[]  array = new String[pageSize];
		for (int i = 0; i < pageSize; i++) {
			int begin = i*70;
			int end = ( begin+70) >= len ? len : (begin+70 );
			array[i] = str.substring( begin, end);
		}
		return array;
	}
	
	
	/**
	 * 根据一个字符数组组成一个由指定字符分隔的字符串.
	 * @author <a href="mailto:winhunter@163.com">郝世博</a>
	 * @time Mar 31, 2011 1:42:17 PM
	 */
	public static String strArrayToStr(String[] arr,String sign){
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			buf.append(arr[i]);
			if ( i<arr.length-1 ) {
				buf.append(sign);
			}
		}
		return buf.toString();
	}
	

	/**
	 *  将一个字符串中的空格和换行转行成html代码.
	 * @author <a href="mailto:winhunter@163.com">郝世博</a>
	 * @time Apr 8, 2011 1:46:00 PM
	 */
	public static String str2Html(String str){
		String retStr = "";
		if(str!=null){
			retStr = str;
			retStr = retStr.replace("&", "&amp;");
			retStr = retStr.replace(" ", "&nbsp;");
			retStr = retStr.replace("<", "&lt;");
			retStr = retStr.replace(">", "&gt;");
			retStr = retStr.replaceAll(" ", "&nbsp;");
			retStr = retStr.replaceAll("\r\n", "<br>");
			retStr = retStr.replaceAll("\r", "<br>");
		}
		return retStr;
	}
	
	/**
	 *  .
	 * @author <a href="mailto:winhunter@163.com">郝世博</a>
	 * @time Jun 23, 2011 6:40:22 PM
	 */
	public static String num2Weekstr(int num){
		String result = "";
		 switch (num) {
			case 1:
				result = "一";
				break;
			case 2:
				result = "二";
				break;
			case 3:
				result = "三";
				break;
			case 4:
				result = "四";
				break;
			case 5:
				result = "五";
				break;
			case 6:
				result = "六";
				break;
			case 7:
				result = "日";
				break;
			default:
				break;
		}
		 return result;
	}
	
	/**
	 *  将字符串或路径中的 \ 转为  /.
	 * @author <a href="mailto:winhunter@163.com">郝世博</a>
	 * @time Jul 8, 2011 3:11:54 PM
	 */
	public  static String convert(String text){
		StringBuffer buf = new StringBuffer("");
		if ( null==text ) {
			return null;
		}
		char[] chars = text.toCharArray();
		for ( char tmp : chars ) {
			if (tmp=='\\') {
				tmp = '/';
			}
			buf.append(tmp);
		}
	
		return buf.toString();
	}
	/**
	 * 编码
	 * 描述：
	 * @param str
	 * @return
	 * 作者：李坤
	 * 时间：Aug 23, 2011
	 */
	public  static String base64Encode(String str){
		if(StringUtil.isBlank(str))
			return str;
		return new Base64Encoder().encode(str.getBytes());
	}
	/**
	 * 解码
	 * 描述：
	 * @param str
	 * @return
	 * @throws java.io.IOException
	 * 作者：李坤
	 * 时间：Aug 23, 2011
	 */
	public  static String base64Decode(String str) throws IOException{
		if(StringUtil.isBlank(str))
			return str;
		return new String(new BASE64Decoder().decodeBuffer(str));
	}
	/**
	 * 获取随机数
	 */
	public  static String getRandom(int length) {
		String yzm = StringUtils.right(Math.random()+"", length);
		while (yzm.length()!=length) {
			yzm = StringUtils.right(Math.random()+"", length);
		}
		return yzm;
	}
	public  static String trim(String str) {
		 if(StringUtil.isBlank(str)){
			 return str;
		 }
		 return str.trim();
	}
	public static String[]  split(String str, String regex) {
		return str.split(regex);
	}
	public static String  replace(String str, String regex,String replacement) {
		return str.replaceAll(regex, replacement);
	}
	/**
	 * 判断一个自己数组组成的字符串是否包含字符串
	 * @param strs 父字符串
	 * @param reg 用来拆分父字符串成为数组
	 * @param str 子字符串
	 * @return
	 */
	public static Boolean arrayContainsStr(String strs,String reg,String str) {
		if(isBlank(strs)){
			return false;
		}
		String array []  = strs.split(reg);
		for (String string : array) {
			if(string.equals(str)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 获取当前工程路径
	 * @return
	 */
	public static String getProjectPath() {
		return StringUtil.class.getResource("/").getPath().substring(1).split("WEB-INF")[0];
	}
	
	
	public static String getStringValue(String inStr,int pos){
		String retStr="";
		if(inStr!=null&!"".equals(inStr)){
			String[] tempS=inStr.split(",");
			 retStr= tempS[pos];
		}
		
		return retStr;
	}
	
	/**
	 * 分割字符串中间加字符
	 */
	public static String getStringSplitByStr(String inStr,String str,int j){
		if(inStr!=null&!"".equals(inStr)&str!=null&!"".equals(str)){
			StringBuilder sb=new StringBuilder(inStr);
			int length=sb.length();
			for(int i=1;i<=length/j;i++)
			{
				if(i==length/j  &&  (length%j)==0);
				else
					sb.insert(i*j+i-1,str);
			}
			inStr = sb.toString();
		}
		
		return inStr;
		
	}
}

