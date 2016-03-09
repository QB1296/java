/**
 * 文件名：DataResourceGroupNode.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.meta;

import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 下午5:40:52
 */
public class DataResourceNode{
	
	private String column;
	
	private String operate;
	
	private String value;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void setValueList(List<String> values){
		this.value = StringUtils.join(values,",");
	}
	
	
}
