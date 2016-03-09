/**
 * 文件名：ExcelImportLinsitor.java  
 *  
 * 版本信息：  
 * 日期：2015年1月28日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;


/**
 * <b>功能描述</b> <br>
 * excel到入监听器
 * @author YUJB
 * @date 2015年1月28日 上午9:18:24
 */
public interface ExcelImportListener {
	
	/**
	 * 开始导入
	 * @param clazz
	 */
	<T extends PoiBaseBo> void importStart(Class<T> clazz);
	
	/**
	 * 正在导入
	 * @param start 起始
	 * @param end 截止
	 * @param count 总数
	 * @param clazz 数据类型
	 */
	<T extends PoiBaseBo> void importing(int start,int end,int count,Class<T> clazz);
	
	/**
	 * 导入结束
	 * @param clazz
	 */
	<T extends PoiBaseBo> void importEnd(Class<T> clazz);
}
