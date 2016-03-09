/**
 * 文件名：Collections.java  
 *  
 * 版本信息：  
 * 日期：2013-6-3  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.util.Assert;

/**
 * <b>功能描述</b> <br>
 * 集合工具类
 * @author YUJB
 * @date 2013-6-3 下午12:16:26
 */
public class Collections {
	
	  
    /**
     * 将集合用指定的字符隔开,拼接成字符串
     * @param splitSymbol  分割符号
     * @param colls 带分割的集合
     * @return 按照分割符号分割的集合
     */
    public static String concat(String splitSymbol,Collection<String> colls){
    	StringBuilder sb = new StringBuilder();
        if (null == colls || colls.isEmpty()){
        	return splitSymbol;
        }
        Iterator<String> it = colls.iterator();
        sb.append(it.next());
        while (it.hasNext()){
        	sb.append(splitSymbol).append(it.next());
        }
        return sb.toString();
    }
    
    
    /**
     * 根据指定的偏移量和长度分割按照分割符号分割集合
     * @param offset 偏移量0标识集合中的第一个
     * @param len 长度 
     * @param splitSymbol  分割符号
     * @param colls 带分割的集合
     * @return 按照分割符号分割的集合
     */
    public static <T> StringBuilder concat(int offset, int len, String splitSymbol ,String[] colls) {
        StringBuilder sb = new StringBuilder();
        if (null == colls || len < 0 || 0 == colls.length)
            return sb;

        if (offset < colls.length) {
            sb.append(colls[offset]);
            for (int i = 1; i < len && i + offset < colls.length; i++) {
                sb.append(splitSymbol).append(colls[i + offset]);
            }
        }
        return sb;
    }
    
    
    /**
     * 删除重复集合
     * @param list 待去重的集合
     */
    public static <T> void removeDuplicate(List<T> list)      
    {      
    	LinkedHashSet<T> h = new LinkedHashSet<T>(list);      
	    list.clear();      
	    list.addAll(h);      
    }   
    
    
    /**
     * List列表拷贝
     * @param orginList 原始的拷贝列表
     * @param convert 转化器
     * @return 拷贝后的集合
     */
    public static <T,K> List<T> copyConvertList(List<K> orginList,Convert<T,K> convert){
    	Assert.notNull(orginList);
    	List<T> results = new ArrayList<T>();
    	for (K k : orginList) {
    		results.add(convert.convert(k));
		}
		return results;
    }
   
    /**
     * <b>功能描述</b> <br>
     * 集合拷贝转化器
     * @author YUJB
     * @date 2015年1月20日 上午11:36:56
     * @param <T>
     * @param <K>
     */
    public static interface Convert<T,K> {
    	public T convert(K orgin);
    }
    
}




