/**
 * 文件名：IJoin.java  
 *  
 * 版本信息：  
 * 日期：2015年1月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao.finder;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年1月12日 上午11:37:18
 */
public interface IJoin {
	
	/**
	 * {@link #joinTable}
	 * @return the joinTable
	 */
	public String getJoinTable();
	
	/**
	 * {@link #joinType}
	 * @return the joinType
	 */
	public JoinType getJoinType();
	
	
	public boolean isFetch();
	
	
	public static enum JoinType{
		left,
		right,
		inner,
	}
}
