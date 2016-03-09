/**
 * 文件名：SpiElTagContext.java  
 *  
 * 版本信息：  
 * 日期：2015年1月21日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.CellRangeAddress;



/**
 * <b>功能描述</b> <br>
 * SpiEl上下文环境
 * @author YUJB
 * @date 2015年1月21日 下午5:37:05
 */
public class SpiElTagContext {
	
	/** 当前的上下文值  */
	private Map<String, Object> contextMapping = new HashMap<String,Object>();
	
	/** 监听器   */
	private List<ExcelExportListener> listeners;
	
	/** 记录总数 */
	private Integer count;
	
	/** 当前处理行  */
	private Integer currentRow;
	
	/** 锁定的单元格范围  */
	private List<CellRangeAddress> fixedRanges;
	
	
	public void addFixedRange(CellRangeAddress range){
		if (fixedRanges == null) {
			fixedRanges = new ArrayList<CellRangeAddress>();
		}
		fixedRanges.add(range);
	}


	/**
	 * 获得锁定的单元格范围 
	 * @return  fixedRanges 锁定的单元格范围
	 */
	public List<CellRangeAddress> getFixedRanges() {
		return fixedRanges;
	}
	
	/**
	 * 获得count 
	 * @return  count count
	 */
	public Integer getCount() {
		return count;
	}
	
	/** 
	 * 设置count 
	 * @param count count 
	 */
	public void setCount(Integer count) {
		this.count = count;
		this.currentRow = 0;
	}
	
	
	/**
	 * 获得currentRow 
	 * @return  currentRow currentRow
	 */
	public Integer getCurrentRow() {
		return currentRow;
	}
	
	
	/** 
	 * 设置contextMapping 
	 * @param contextMapping contextMapping 
	 */
	public void setContextMapping(Map<String, Object> contextMapping) {
		this.contextMapping = contextMapping;
	}
	
	
	public void addValue(String key, Object value) {
		contextMapping.put(key, value);
	}
	
	public Object getValue(String key){
		return contextMapping.get(key);
	}
	
	public void clear(){
		contextMapping.clear();
	}
	
	public void addListener(ExcelExportListener listener){
		if (listeners == null) {
			listeners = new ArrayList<ExcelExportListener>();
		}
		listeners.add(listener);
	}
	
	/**
	 * 获得导出监听器 
	 * @return  listeners 导出监听器
	 */
	public List<ExcelExportListener> getListeners() {
		return listeners;
	}


	/** 
	 * 设置导出监听器 
	 * @param listeners 导出监听器 
	 */
	public void setListeners(List<ExcelExportListener> listeners) {
		this.listeners = listeners;
	}

	public  void  visitorImportOneRow(String sheetName){
		this.currentRow ++;
		if(listeners != null){
			for (ExcelExportListener listener : listeners) {
				listener.importing(this.currentRow, count,sheetName);
			}
		}
	}
	
	
	public  void  visitorStart( String sheetName){
		if(listeners != null){
			for (ExcelExportListener listener : listeners) {
				listener.importStart(sheetName);
			}
		}
	}
	
	public  void visitorEnd( String sheetName){
		if(listeners != null){
			for (ExcelExportListener listener : listeners) {
				listener.importEnd(sheetName);
			}
		}
	}
	
	
}
