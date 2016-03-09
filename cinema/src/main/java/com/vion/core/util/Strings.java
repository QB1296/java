/**
 * 文件名：Strings.java  
 *  
 * 版本信息：  
 * 日期：2013-5-31  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */

package com.vion.core.util;

import java.util.LinkedList;
import java.util.List;

/**
 * <b>功能描述</b> <br>
 * 
 * @author YUJB
 * @date 2013-5-31 下午01:42:07
 */
public abstract class Strings {

	/**
	 * 判断是否为null或 "",忽略空格
	 * 
	 * @param charSequence
	 *            char序列,常用的StringBuffer String都试下了charSequence方法
	 * @return true:为空；false:不为空
	 */
	public static boolean isEmpty(CharSequence charSequence) {
		return charSequence == null || charSequence.toString().trim().equals("");
	}

	/**
	 * 将字符串按半角逗号，拆分成数组，空元素将被忽略
	 * 
	 * @param s
	 *            字符串
	 * @return 字符串数组
	 */
	public static String[] splitIgnoreBlank(String s) {
		return Strings.splitIgnoreBlank(s, ",");
	}

	/**
	 * 根据一个正则式，将字符串拆分成数组，空元素将被忽略
	 * 
	 * @param s
	 *            字符串
	 * @param regex
	 *            正则式
	 * @return 字符串数组
	 */
	public static String[] splitIgnoreBlank(String s, String regex) {
		if (null == s)
			return null;
		String[] ss = s.split(regex);
		List<String> list = new LinkedList<String>();
		for (String st : ss) {
			if (isEmpty(st)) {
				continue;
			}
			list.add(trim(st));
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 去除前后空格
	 * 
	 * @param cs
	 * @return
	 */
	public static String trim(CharSequence cs) {
		if (null == cs) {
			return null;
		}
		if (cs instanceof String) {
			return ((String) cs).trim();
		}
		int length = cs.length();
		if (length == 0) {
			return cs.toString();
		}
		int l = 0;
		int last = length - 1;
		int r = last;
		for (; l < length; l++) {
			if (!Character.isWhitespace(cs.charAt(l))) {
				break;
			}
		}
		for (; r > l; r--) {
			if (!Character.isWhitespace(cs.charAt(r))) {
				break;
			}

		}
		if (l > r) {
			return "";
		}
		else if (l == 0 && r == last) {
			return cs.toString();
		}
		return cs.subSequence(l, r + 1).toString();
	}

	/**
	 * 去除所有的空白符
	 * 
	 * @param str
	 * @return
	 */
	public static String trimAllWhitespace(String str) {
		if (isEmpty(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		int index = 0;
		while (sb.length() > index) {
			if (Character.isWhitespace(sb.charAt(index))) {
				sb.deleteCharAt(index);
			}
			else {
				index++;
			}
		}
		return sb.toString();
	}

	public static String copyMulti(CharSequence cs, int num) {
		if (isEmpty(cs) || num <= 0)
			return "";
		StringBuilder sb = new StringBuilder(cs.length() * num);
		for (int i = 0; i < num; i++)
			sb.append(cs);
		return sb.toString();
	}

	/**
	 * 首字母小写,例如 "Fon" to "fon" 但是 "URI" to "URI"
	 * 
	 * @param name
	 * @return
	 */
	public static String decapitalize(String name) {

		if (name == null || name.length() == 0) {
			return name;
		}
		if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) && Character.isUpperCase(name.charAt(0))) {
			return name;
		}
		char chars[] = name.toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);

		return new String(chars);
	}

	/**
	 * 首字母大写,例如 "fon" to "Fon"
	 * 
	 * @param name
	 * @return
	 */
	public static String capitalize(String name) {

		if (name == null || name.length() == 0) {
			return name;
		}
		if (name.length() > 1 && Character.isUpperCase(name.charAt(0))) {
			return name;
		}
		char chars[] = name.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);

		return new String(chars);
	}

	/**
	 * 变成大写字母
	 * 
	 * @param name
	 * @return
	 */
	public static String upperCase(String name) {
		char chars[] = name.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			chars[i] = Character.toUpperCase(chars[i]);
		}

		return new String(chars);
	}

	/**
	 * <b>将前台输入的模糊匹配字符转换成SQL所能认识的</b> <br>
	 * 
	 * @author tramp
	 * @date 2015年6月12日 下午5:41:45
	 * @param source
	 * @return
	 * @throws
	 */
	public static String sqlFuzzy(String source) {
		
		if (source == null || source.length() == 0){
			return null;
		}
		if (source.contains("*") || source.contains("_")) {
			source = source.replace('*', '%');
		}
		else {
			source = "%" + source + "%";
		}

		return source;
	}

}
