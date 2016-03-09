/**
 * 文件名：BracketOrExpression.java  
 *  
 * 版本信息：  
 * 日期：2014年12月24日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;

/**
 * <b>功能描述</b> <br>
 * 包裹的Or表达式,用"()"包裹Or表达式
 * @author YUJB
 * @date 2014年12月24日 下午5:45:12
 */
public class BracketOrExpression extends OrExpression{

	/**
	 * @param leftExpression
	 * @param rightExpression
	 */
	public BracketOrExpression(Expression leftExpression,
			Expression rightExpression) {
		super(leftExpression, rightExpression);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.BinaryExpression#toString()*/
	@Override
	public String toString() {
		return "(" + super.toString() + ")";
	}
	
	

}
