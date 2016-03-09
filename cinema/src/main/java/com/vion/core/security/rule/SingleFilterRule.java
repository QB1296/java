/**
 * 文件名：FilterRule.java  
 *  
 * 版本信息：  
 * 日期：2014年8月8日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.rule;


import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;

import org.codehaus.jackson.annotate.JsonTypeName;

import com.vion.core.security.FragmentSQL;
import com.vion.core.security.SQLStringValue;
import com.vion.core.security.meta.AliasConfig;
import com.vion.core.security.meta.AliasConfig.AliasConfigEntry;


/**
 * <b>功能描述</b> <br>
 * 一条数据规律规则 {"field":"name","op":"in",value:"a,b,c"}
 * @author YUJB
 * @date 2014年8月8日 下午5:13:55
 */
@JsonTypeName("single")
public class SingleFilterRule extends RecordFilterRule{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String OP_EQUAL = "equals";
	public static final String OP_NOT_EQUAL = "unEquals";
	public static final String OP_LESS_THAN = "lessThan";
	public static final String OP_GREATER_THAN = "greaterThan";
	public static final String OP_LESS_OR_EQUAL = "lessOrEqual";
	public static final String OP_GREATER_OR_EQUAL = "GreaterOrEquals";
	public static final String OP_LIKE = "like";
	public static final String OP_IN = "in";
	public static final String OP_NOT_IN = "not in";
	public static final String OP_NULL = "is null";
	public static final String OP_NOT_NULL = "not null";
	
	private String field;
	
	private String op;
	
	private String value;
	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

	public Expression  getWhereExpression(AliasConfig aliasConfig) {
		
		String property = getField();
		String value = getValue();
		String operator = getOp();
		
		AliasConfigEntry aliasConfigEntry = aliasConfig.getAliasConfigEntryByEntity(getEntity());
		if (aliasConfigEntry == null) {
			return null;
		}
		String alias = aliasConfigEntry.getAlias();
		switch (operator) {
			case SingleFilterRule.OP_NULL:
				IsNullExpression isNullExpression = new IsNullExpression();
				isNullExpression.setLeftExpression(new SQLStringValue(alias + "." + property));
			case SingleFilterRule.OP_NOT_NULL:
				IsNullExpression isNotNullExpression = new IsNullExpression();
				isNotNullExpression.setNot(true);
				isNotNullExpression.setLeftExpression(new SQLStringValue(alias + "." + property));
			case SingleFilterRule.OP_IN:
				InExpression inExpression = new InExpression();
				inExpression.setLeftExpression(new SQLStringValue(alias + "." + property));
				ExpressionList itemsList = new ExpressionList();
				String[] values = value.split(",");
				List<Expression> inExpValues = new ArrayList<Expression>();
				for (String one : values) {
					inExpValues.add(new StringValue(one));
				}
				itemsList.setExpressions(inExpValues);
				inExpression.setItemsList(itemsList);
			case SingleFilterRule.OP_NOT_IN:
				InExpression notInExpression = new InExpression();
				notInExpression.setNot(true);
				notInExpression.setLeftExpression(new SQLStringValue(alias + "." + property));
				ExpressionList notInItems = new ExpressionList();
				String[] notInValues = value.split(",");
				List<Expression> notInExpValues = new ArrayList<Expression>();
				for (String one : notInValues) {
					notInExpValues.add(new StringValue(one));
				}
				notInItems.setExpressions(notInExpValues);
				notInExpression.setItemsList(notInItems);
			case SingleFilterRule.OP_EQUAL:
				EqualsTo equalsTo = new EqualsTo();
				equalsTo.setLeftExpression(new SQLStringValue(alias + "." + property));
				equalsTo.setRightExpression(new StringValue(value));
				return equalsTo;
			case SingleFilterRule.OP_NOT_EQUAL:
				EqualsTo notEqualsTo = new EqualsTo();
				notEqualsTo.setNot();
				notEqualsTo.setLeftExpression(new SQLStringValue(alias + "." + property));
				notEqualsTo.setRightExpression(new StringValue(value));
				return notEqualsTo;
			case SingleFilterRule.OP_GREATER_THAN:
				GreaterThan greaterThan = new GreaterThan();
				greaterThan.setLeftExpression(new SQLStringValue(alias + "." + property));
				greaterThan.setRightExpression(new StringValue(value));
			case SingleFilterRule.OP_LESS_THAN:
				MinorThan minorThan = new MinorThan();
				minorThan.setLeftExpression(new SQLStringValue(alias + "." + property));
				minorThan.setRightExpression(new StringValue(value));
			case SingleFilterRule.OP_LIKE:
				LikeExpression likeExpression = new LikeExpression();
				likeExpression.setLeftExpression(new SQLStringValue(alias + "." + property));
				likeExpression.setRightExpression(new StringValue(value));
				return likeExpression;
			default:
				throw new IllegalArgumentException("Filter 操作 ( " + operator + " ) is 无效的.");
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.vion.core.security.rule.RecordFilterRule#convertPS(net.sf.jsqlparser.statement.select.PlainSelect)*/
	@Override
	public void convertPS(PlainSelect ps) {
		Expression expression = getWhereExpression(getAliasConfig(ps));
		setWhereExpression(ps, expression);
		
	}

	/* (non-Javadoc)
	 * @see com.vion.core.security.rule.RecordFilterRule#convertJoin(net.sf.jsqlparser.statement.select.Join)*/
	@Override
	public void convertJoin(Join join) {
		Expression expression = getWhereExpression(getAliasConfig(join));
		setWhereExpression(join, expression);
	}
	
	@Override
	public boolean isSupport(FragmentSQL fragmentSQL) {
		String rootTable = fragmentSQL.getRootFrom();
		return rootTable.equals(getEntity());
	}
	
}
