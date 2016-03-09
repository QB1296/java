/**
 * 文件名：Join.java  
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
 * @date 2015年1月12日 上午11:39:14
 */
public class Join implements IJoin{

	private JoinType joinType;

	private String joinTable;
	
	private boolean isFetch = false;

	public Join(JoinType joinType, String joinTable,boolean isFetch) {
		this.joinTable = joinTable;
		this.joinType = joinType;
		this.isFetch = isFetch;
	}
	

	public static Join leftFetch(String joinTable) {
		return new Join(JoinType.left, joinTable,true);
	}
	
	public static Join rightFetch(String joinTable) {
		return new Join(JoinType.right, joinTable,true);
	}
	
	public static Join innerFetch(String joinTable) {
		return new Join(JoinType.inner, joinTable,true);
	}

	public static Join left(String joinTable) {
		return new Join(JoinType.left, joinTable,false);
	}
	
	public static Join right(String joinTable) {
		return new Join(JoinType.right, joinTable,false);
	}
	
	public static Join inner(String joinTable) {
		return new Join(JoinType.inner, joinTable,false);
	}
	
	/**
	 * {@link #joinTable}
	 * 
	 * @return the joinTable
	 */
	public String getJoinTable() {
		return joinTable;
	}

	/**
	 * {@link #joinType}
	 * 
	 * @return the joinType
	 */
	public JoinType getJoinType() {
		return joinType;
	}
	
	/* (non-Javadoc)
	 * @see com.vion.core.dao.finder.IJoin#isFetch()*/
	@Override
	public boolean isFetch() {
		return isFetch;
	}

}
