/**
 * 文件名：AsyncExcelImportProcessor.java  
 *  
 * 版本信息：  
 * 日期：2015年1月27日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vion.core.poi.ExcelImportsService.ValidateResult;
import com.vion.core.util.Collections;

/**
 * <b>功能描述</b> <br>
 * 异步的导出处理器
 * @author YUJB
 * @date 2015年1月27日 下午3:27:53sss
 */
@Component
public class SyncExcelRecordProcessor implements ExcelRecordProcessor{

	public <T extends PoiBaseBo> void processRecords(List<ValidateResult<T>> validateResults,BatchProcessor<T> processor){
		List<T> successRecords = new ArrayList<T>();
		List<T> errorList = new ArrayList<T>();
		for (ValidateResult<T> validateResult : validateResults) {
			if (validateResult.isSuccess()) {
				if (!errorList.contains(validateResult.getObject())
						&& !successRecords.contains(validateResult.getObject())) {
					successRecords.add(validateResult.getObject());
				}
			}else {
				int index = successRecords.indexOf(validateResult.getObject());
				if (index >= 0) {
					successRecords.remove(index);
				}
				if (!errorList.contains(validateResult.getObject())) {
					errorList.add(validateResult.getObject());
				}
			}
		}
		Collections.removeDuplicate(successRecords);
		if (successRecords.size() > 0) {
			try {
				processor.processor(successRecords) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
