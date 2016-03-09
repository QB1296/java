/**
 * 
 */
package com.vion.core.security;

import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.StringValue;

/**
 * @author Administrator
 *
 */
public class SQLStringValue extends StringValue{
	
	private String sqlVlaue;
	
	
	public SQLStringValue(String sqlVlaue) {
		super(sqlVlaue);
		this.sqlVlaue = sqlVlaue;
	}


	@Override
	public void accept(ExpressionVisitor expressionVisitor) {
		expressionVisitor.visit(this);
	}
	

	public String toString() {
		return sqlVlaue;
	}

}
