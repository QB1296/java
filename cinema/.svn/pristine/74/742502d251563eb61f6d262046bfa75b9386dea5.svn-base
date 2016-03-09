/**
 * 文件名： ValidateMeta.java
 *  
 * 版本信息：  
 * 日期：2015年3月28日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.vion.core.poi;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年3月28日 上午9:59:22
 */
public class ValidateMeta {
	
	public Map<Integer, String> columnNumFieldValidateMapper = new HashMap<Integer, String>();
	public Map<Integer, Field> columnNumFieldMapper = new HashMap<Integer, Field>();
	public Map<Integer, PoiFieldRemoteValidate> columnNumRemoteValidateMapper = new HashMap<Integer, PoiFieldRemoteValidate>();

	/**
	 * 获得columnNumFieldValidateMapper
	 * 
	 * @return columnNumFieldValidateMapper columnNumFieldValidateMapper
	 */
	public Map<Integer, String> getColumnNumFieldValidateMapper() {
		return columnNumFieldValidateMapper;
	}

	/**
	 * 设置columnNumFieldValidateMapper
	 * 
	 * @param columnNumFieldValidateMapper
	 *            columnNumFieldValidateMapper
	 */
	public void setColumnNumFieldValidateMapper(
			Map<Integer, String> columnNumFieldValidateMapper) {
		this.columnNumFieldValidateMapper = columnNumFieldValidateMapper;
	}

	public void putColumnNumFiledValidateMapper(Integer columnNum,
			String columnNumFieldValidate) {
		columnNumFieldValidateMapper.put(columnNum, columnNumFieldValidate);
	}

	/**
	 * 获得columnNumFieldMapper
	 * 
	 * @return columnNumFieldMapper columnNumFieldMapper
	 */
	public Map<Integer, Field> getColumnNumFieldMapper() {
		return columnNumFieldMapper;
	}

	/**
	 * 设置columnNumFieldMapper
	 * 
	 * @param columnNumFieldMapper
	 *            columnNumFieldMapper
	 */
	public void setColumnNumFieldMapper(Map<Integer, Field> columnNumFieldMapper) {
		this.columnNumFieldMapper = columnNumFieldMapper;
	}

	public void putColumnNumFieldMapper(Integer columnNum, Field field) {
		columnNumFieldMapper.put(columnNum, field);
	}

	/**
	 * 获得columnNumRemoteValidateMapper
	 * 
	 * @return columnNumRemoteValidateMapper columnNumRemoteValidateMapper
	 */
	public Map<Integer, PoiFieldRemoteValidate> getColumnNumRemoteValidateMapper() {
		return columnNumRemoteValidateMapper;
	}

	public void putColumnNumRemoteValidateMapper(Integer columnNum,
			PoiFieldRemoteValidate poiFieldRemoteValidate) {
		columnNumRemoteValidateMapper.put(columnNum, poiFieldRemoteValidate);
	}

	/**
	 * 设置columnNumRemoteValidateMapper
	 * 
	 * @param columnNumRemoteValidateMapper
	 *            columnNumRemoteValidateMapper
	 */
	public void setColumnNumRemoteValidateMapper(
			Map<Integer, PoiFieldRemoteValidate> columnNumRemoteValidateMapper) {
		this.columnNumRemoteValidateMapper = columnNumRemoteValidateMapper;
	}
}
