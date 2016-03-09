/**
 * 文件名：ExcelRecordProcessor.java  
 *  
 * 版本信息：  
 * 日期：2015年1月27日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.util.List;

import com.vion.core.poi.ExcelImportsService.ValidateResult;

/**
 * <b>功能描述</b> <br>
 * excel导出记录处理器
 * @author YUJB
 * @date 2015年1月27日 下午3:30:40
 */
public interface ExcelRecordProcessor {

	/**
	 * 处理记录
	 * @param validateResults 验证结果
	 * @param processor 批量处理器
	 */
	public <T extends PoiBaseBo> void processRecords(List<ValidateResult<T>> validateResults,BatchProcessor<T> processor);
}
