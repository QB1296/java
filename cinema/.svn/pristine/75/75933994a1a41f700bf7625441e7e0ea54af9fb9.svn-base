/**
 * 文件名：ExcelImports.java  
 *  
 * 版本信息：  
 * 日期：2014年11月24日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.vion.core.SystemContext;

/**
 * <b>功能描述</b> <br>
 * excel到入接口
 * @author YUJB
 * @date 2014年11月24日 上午10:08:13
 */
public class ExcelImports {
	
	private ExcelImportsService excelImportsService;
	
	/** 标识是xls还是 xlsx  */
	private ExcelType excelType;
	
	/** 导入监听器  */
	private List<ExcelImportListener> listeners;
	
	private ExcelImports() {
		super();
		excelImportsService = SystemContext.getApplicationContext().getBean(ExcelImportsService.class);
	}

	public static ExcelImports XLS(){
		ExcelImports excelImports = new ExcelImports();
		excelImports.setExcelType(ExcelType.xls);
		return excelImports;
	}
	
	public static ExcelImports XLSX(){
		ExcelImports excelImports = new ExcelImports();
		excelImports.setExcelType(ExcelType.xlsx);
		return excelImports;
	}
	
	
	public <T extends PoiBaseBo> ExcelImportResult importExcel(InputStream inputStream,Class<T> clazz,BatchProcessor<T> processor) {
		return excelImportsService.importExcel(excelType, inputStream, clazz, processor,listeners);
	}
	
	public void setBatchSize(int batchSize){
		this.excelImportsService.setBatchSize(batchSize);
	}
	
	/**
	 * 获得excelImportsService 
	 * @return  excelImportsService excelImportsService
	 */
	public ExcelImportsService getExcelImportsService() {
		return excelImportsService;
	}

	/** 
	 * 设置excelImportsService 
	 * @param excelImportsService excelImportsService 
	 */
	public void setExcelImportsService(ExcelImportsService excelImportsService) {
		this.excelImportsService = excelImportsService;
	}

	/**
	 * 获得标识是xls还是xlsx 
	 * @return  excelType 标识是xls还是xlsx
	 */
	public ExcelType getExcelType() {
		return excelType;
	}

	/** 
	 * 设置标识是xls还是xlsx 
	 * @param excelType 标识是xls还是xlsx 
	 */
	public void setExcelType(ExcelType excelType) {
		this.excelType = excelType;
	}
	
	
	/**
	 * 获得导入监听器 
	 * @return  listeners 导入监听器
	 */
	public List<ExcelImportListener> getListeners() {
		return listeners;
	}


	/** 
	 * 设置导入监听器 
	 * @param listeners 导入监听器 
	 */
	public void setListeners(List<ExcelImportListener> listeners) {
		this.listeners = listeners;
	}
	
	
	/**
	 * 增加一个监听器，让如到监听器链表中
	 * @param listener
	 */
	public void addListener(ExcelImportListener listener){
		if(this.listeners == null){
			listeners = new ArrayList<ExcelImportListener>();
		}
		listeners.add(listener);
	}

	
}
