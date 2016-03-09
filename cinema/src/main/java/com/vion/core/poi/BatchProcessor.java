/**
 * 文件名：BatchProcessor.java  
 *  
 * 版本信息：  
 * 日期：2014年11月24日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.util.List;


/**
 * <b>功能描述</b> <br>
 * 批处理接口
 * @author YUJB
 * @date 2014年11月24日 下午3:14:45
 */
public interface BatchProcessor<T extends PoiBaseBo> {
	
	/**
	 * 处理数据
	 * @param data excel导出的一辆数据
	 * @throws Exception
	 */
	void processor(List<T> data) throws Exception;
}
