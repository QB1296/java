/**
 * 文件名：ISpiElTag.java  
 *  
 * 版本信息：  
 * 日期：2015年1月21日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import org.apache.poi.ss.usermodel.Cell;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年1月21日 下午5:15:38
 */
public interface ISpiElTag {
	
	public InflConfig parseTag(SpiElTagContext context, Cell curCell);
	
	public boolean hasEndTag();

    public String getTagName();
    
    public Integer recordCount(SpiElTagContext context, Cell curCell);
    
    public boolean isSupport(String spiEl);
	
}
