/**
 * 文件名： BeanUtils.java
 *  
 * 版本信息：  
 * 日期：2015年3月30日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.util;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.vion.core.util.Collections;
import com.vion.core.util.Collections.Convert;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年3月30日 下午1:22:40
 */
public class BeanUtil {

	/**
	 * @param orginList 原始集合
	 * @param clazz 目标类
	 * @return
	 */
	public static <T,K> List<T> copy(List<K> orginList,final Class<T> clazz){
		if(orginList == null){
			return null;
		}
		Convert<T, K> convert = new Convert<T, K>() {
			
			@Override
			public T convert(K orgin) {
				// TODO Auto-generated method stub
				T t = null;
				try {
					t = clazz.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BeanUtils.copyProperties(orgin, t);
				return t;
			}
		};
		return Collections.copyConvertList(orginList, convert);
	}
}
