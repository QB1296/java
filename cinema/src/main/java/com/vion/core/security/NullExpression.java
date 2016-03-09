/**
 * 文件名：NullExpression.java  
 *  
 * 版本信息：  
 * 日期：2015年3月6日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;

/**
 * <b>功能描述</b> <br>
 * 空表达式 
 * @author YUJB
 * @date 2015年3月6日 上午9:31:05
 */
public class NullExpression implements Expression{

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.Expression#accept(net.sf.jsqlparser.expression.ExpressionVisitor)*/
	@Override
	public void accept(ExpressionVisitor visitor) {
		if(visitor instanceof WhereExpressionVisitor){
			WhereExpressionVisitor whereVisitor = (WhereExpressionVisitor)visitor;
			whereVisitor.visit(this);
		}
		
	}

}
