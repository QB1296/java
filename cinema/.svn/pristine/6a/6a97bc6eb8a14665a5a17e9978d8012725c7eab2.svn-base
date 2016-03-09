/**
 * 文件名：PoiAnnotationResolver.java  
 *  
 * 版本信息：  
 * 日期：2014年11月24日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.lang.reflect.Field;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年11月24日 上午10:50:00
 */
public class PoiAnnotationResolver {


	public static int getSheetAt(Class<?> clazz){
		int sheet = 0;
		PoiSheet poiSheet = clazz.getAnnotation(PoiSheet.class);
		if (poiSheet != null) {
			sheet = poiSheet.value();
		}
		return sheet;
	}
	
	public static int getRowStart(Class<?> clazz){
		int start = 0;
		PoiRow poiRow = clazz.getAnnotation(PoiRow.class);
		if (poiRow != null) {
			start = poiRow.start();
		}
		return start;
	}
	
	public static int getRowEnd(Class<?> clazz){
		int end = 0;
		PoiRow poiRow = clazz.getAnnotation(PoiRow.class);
		if (poiRow != null) {
			end = poiRow.end();
		}
		return end;
	}
	
	public static int getColumnStart(Class<?> clazz){
		int start = 0;
		PoiColumn poiColumn = clazz.getAnnotation(PoiColumn.class);
		if (poiColumn != null) {
			start = poiColumn.start();
		}
		return start;
	}
	
	public static int getColumnEnd(Class<?> clazz){
		int end = 0;
		PoiColumn poiColumn = clazz.getAnnotation(PoiColumn.class);
		if (poiColumn != null) {
			end = poiColumn.end();
		}
		return end;
	}
	
	static Field getFieldByColumn(Class<?> clazz,int column){
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			PoiField poiField = field.getAnnotation(PoiField.class);
			if (poiField != null) {
				if (column == poiField.column()) {
					return field;
				}
			}
		}
		return null;
	}
	
	static Integer getColumnByField(Class<?> clazz,Field field){
		PoiField poiField = field.getAnnotation(PoiField.class);
		if (poiField != null) {
			return poiField.column();
		}
		return null;
	}
	
	static String getFieldValidate(Field field){
		PoiFieldValidate poiField = field.getAnnotation(PoiFieldValidate.class);
		if (poiField != null) {
			return poiField.validate();
		}
		return null;
	}
	
	static PoiFieldRemoteValidate getFieldRemoteValidate(Field field){
		PoiFieldRemoteValidate poiField = field.getAnnotation(PoiFieldRemoteValidate.class);
		return poiField;
	}
	
	static String getFieldValidateMsg(Field field){
		PoiFieldValidate poiField = field.getAnnotation(PoiFieldValidate.class);
		if (poiField != null) {
			return poiField.msg();
		}
		return null;
	}
	
	static String getFieldRemoteValidateMsg(Field field){
		PoiFieldRemoteValidate fieldRemoteValidate = getFieldRemoteValidate(field);
		if (fieldRemoteValidate != null) {
			return fieldRemoteValidate.msg();
		}
		return null;
	}
	
	
	
}
