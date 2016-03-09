/**
 * 文件名：asdf.java  
 *  
 * 版本信息：  
 * 日期：2015年2月28日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */

package com.vion.core.security;

import java.util.List;

import net.sf.jsqlparser.expression.AllComparisonExpression;
import net.sf.jsqlparser.expression.AnyComparisonExpression;
import net.sf.jsqlparser.expression.CaseExpression;
import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.InverseExpression;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.WhenClause;
import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import net.sf.jsqlparser.expression.operators.arithmetic.Division;
import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.Between;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.Matches;
import net.sf.jsqlparser.expression.operators.relational.MinorThan;
import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;
import bsh.ParserConstants;

import com.vion.core.hibernate.HibernateEntityUtils;
import com.vion.core.util.BeanUtils;
import com.vion.core.util.Strings;

/**
 * <b>功能描述</b> <br>
 * sqlwhere 表达式观察值,用于过滤对象使用{@link #eval(Object)}方法
 * @author YUJB
 * @date 2015年2月28日 下午12:24:37
 */
public class WhereExpressionVisitor implements ExpressionVisitor,
		ParserConstants {

	/** 结果 true:false  */
	private Object result;

	/** where表达式  */
	private Expression expression;

	/** 记录  */
	private Object rowInfo;

	public WhereExpressionVisitor(Expression expression) {
		this.expression = expression;
	}

	public Object eval(Object rowInfo) {
		this.rowInfo = rowInfo;
		expression.accept(this);
		return result;
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.NullValue)*/
	public void visit(NullValue nullValue) {
		this.result = null;
	}
	
	
	/**
	 * @param nullExpression
	 */
	public void visit(NullExpression nullExpression) {
		this.result = true;
	}
	

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.Function)*/
	public void visit(Function function) {
		throw new UnsupportedOperationException("不支持!");
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.InverseExpression)*/
	public void visit(InverseExpression inverseExpression) {
		throw new UnsupportedOperationException("不支持!");
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.JdbcParameter)*/
	public void visit(JdbcParameter jdbcParameter) {
		throw new UnsupportedOperationException("不支持!");
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.DoubleValue)*/
	public void visit(DoubleValue doubleValue) {
		result = doubleValue.getValue();
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.LongValue)*/
	public void visit(LongValue longValue) {
		this.result = longValue.getValue();
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.DateValue)*/
	public void visit(DateValue dateValue) {
		result = dateValue.getValue();
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.TimeValue)*/
	public void visit(TimeValue timeValue) {
		result = timeValue.getValue();
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.TimestampValue)*/
	public void visit(TimestampValue timestampValue) {
		result = timestampValue.getValue();
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.Parenthesis)*/
	public void visit(Parenthesis parenthesis) {
		parenthesis.getExpression().accept(this);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.StringValue)*/
	public void visit(StringValue stringValue) {
		result = stringValue.getValue();
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.arithmetic.Addition)*/
	public void visit(Addition equalsTo) {
		Expression left = equalsTo.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression right = equalsTo.getRightExpression();
		right.accept(this);

		Object rightValue = result;

		result = BinaryOperation.eval(leftValue, rightValue, PLUS);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.arithmetic.Division)*/
	public void visit(Division division) {
		Expression left = division.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression right = division.getRightExpression();
		right.accept(this);

		Object rightValue = result;

		result = BinaryOperation.eval(leftValue, rightValue, SLASH);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.arithmetic.Multiplication)*/
	public void visit(Multiplication multiplication) {
		Expression left = multiplication.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression right = multiplication.getRightExpression();
		right.accept(this);

		Object rightValue = result;
		result = BinaryOperation.eval(leftValue, rightValue, STAR);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.arithmetic.Subtraction)*/
	public void visit(Subtraction subtraction) {
		Expression left = subtraction.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression right = subtraction.getRightExpression();
		right.accept(this);

		Object rightValue = result;
		result = BinaryOperation.eval(leftValue, rightValue, MINUS);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.conditional.AndExpression)*/
	public void visit(AndExpression andExpression) {
		Expression left = andExpression.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression right = andExpression.getRightExpression();
		right.accept(this);

		Object rightValue = result;

		result = BinaryOperation.eval(leftValue, rightValue, BOOL_AND);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.conditional.OrExpression)*/
	public void visit(OrExpression orExpression) {
		Expression left = orExpression.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression right = orExpression.getRightExpression();
		right.accept(this);

		Object rightValue = result;

		result = BinaryOperation.eval(leftValue, rightValue, BOOL_OR);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.relational.Between)*/
	public void visit(Between between) {
		Expression left = between.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression start = between.getBetweenExpressionStart();
		start.accept(this);

		Object startValue = result;

		Expression end = between.getBetweenExpressionStart();
		end.accept(this);

		Object endValue = result;

		if (!between.isNot()) {
			Object ge = BinaryOperation.eval(leftValue, startValue, GE);
			Object le = BinaryOperation.eval(leftValue, endValue, LE);

			result = BinaryOperation.eval(ge, le, BOOL_AND);
		} else {
			Object ge = BinaryOperation.eval(leftValue, startValue, LT);
			Object le = BinaryOperation.eval(leftValue, endValue, GT);

			result = BinaryOperation.eval(ge, le, BOOL_OR);
		}
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.relational.EqualsTo)*/
	public void visit(EqualsTo equalsTo) {
		Expression left = equalsTo.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression right = equalsTo.getRightExpression();
		right.accept(this);

		Object rightValue = result;

		result = BinaryOperation.eval(leftValue, rightValue, EQ);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.relational.GreaterThan)*/
	public void visit(GreaterThan greaterThan) {
		Expression left = greaterThan.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression right = greaterThan.getRightExpression();
		right.accept(this);

		Object rightValue = result;

		result = BinaryOperation.eval(leftValue, rightValue, GT);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals)*/
	public void visit(GreaterThanEquals greaterThanEquals) {
		Expression left = greaterThanEquals.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression right = greaterThanEquals.getRightExpression();
		right.accept(this);

		Object rightValue = result;

		result = BinaryOperation.eval(leftValue, rightValue, GE);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.relational.InExpression)*/
	@SuppressWarnings("unchecked")
	public void visit(InExpression inExpression) {
		Expression left = inExpression.getLeftExpression();
		ItemsList itemsList = inExpression.getItemsList();
		Object retFlag = false;
		if (itemsList instanceof ExpressionList) {
			List<Expression> expressions = ((ExpressionList) itemsList)
					.getExpressions();
			if (expressions != null) {
				left.accept(this);
				Object leftValue = result;
				for (Expression expression : expressions) {
					expression.accept(this);
					Object rightValue = result;
					result = BinaryOperation.eval(leftValue, rightValue, EQ);
					retFlag = BinaryOperation.eval(retFlag, result, BOOL_OR);
					if (retFlag.equals(true)) {
						break;
					}
				}
			}
		}
		result = retFlag;
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.relational.IsNullExpression)*/
	public void visit(IsNullExpression isNullExpression) {
		Expression left = isNullExpression.getLeftExpression();
		left.accept(this);

		boolean isnull = result == null;

		if (isNullExpression.isNot()) {
			result = !isnull;
		} else {
			result = isnull;
		}
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.relational.LikeExpression)*/
	public void visit(LikeExpression likeExpression) {
		throw new UnsupportedOperationException("不支持!");
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.relational.MinorThan)*/
	public void visit(MinorThan minorThan) {
		Expression left = minorThan.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression right = minorThan.getRightExpression();
		right.accept(this);

		Object rightValue = result;

		result = BinaryOperation.eval(leftValue, rightValue, LT);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.relational.MinorThanEquals)*/
	public void visit(MinorThanEquals minorThanEquals) {
		Expression left = minorThanEquals.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression right = minorThanEquals.getRightExpression();
		right.accept(this);

		Object rightValue = result;

		result = BinaryOperation.eval(leftValue, rightValue, LE);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.relational.NotEqualsTo)*/
	public void visit(NotEqualsTo notEqualsTo) {
		Expression left = notEqualsTo.getLeftExpression();
		left.accept(this);

		Object leftValue = result;

		Expression right = notEqualsTo.getRightExpression();
		right.accept(this);

		Object rightValue = result;

		result = BinaryOperation.eval(leftValue, rightValue, NE);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.schema.Column)*/
	public void visit(Column tableColumn) {
		String columnName = tableColumn.getColumnName();
		String[] split = columnName.split("\\.");
		columnName = split[split.length - 1];
		String field = HibernateEntityUtils.getField(rowInfo.getClass(),
				columnName);
		if (Strings.isEmpty(field)) {
			field = columnName;
		}
		result = BeanUtils.getFieldValue(rowInfo, field);
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.statement.select.SubSelect)*/
	public void visit(SubSelect subSelect) {
		throw new UnsupportedOperationException("不支持!");
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.CaseExpression)*/
	public void visit(CaseExpression caseExpression) {
		throw new UnsupportedOperationException("不支持!");
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.WhenClause)*/
	public void visit(WhenClause whenClause) {
		throw new UnsupportedOperationException("不支持!");
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.operators.relational.ExistsExpression)*/
	public void visit(ExistsExpression existsExpression) {
		throw new UnsupportedOperationException("不支持!");
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.AllComparisonExpression)*/
	public void visit(AllComparisonExpression allComparisonExpression) {
		throw new UnsupportedOperationException("不支持!");
	}

	/* (non-Javadoc)
	 * @see net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser.expression.AnyComparisonExpression)*/
	public void visit(AnyComparisonExpression anyComparisonExpression) {
		throw new UnsupportedOperationException("不支持!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.Concat)
	 */
	@Override
	public void visit(Concat arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.relational.Matches)
	 */
	@Override
	public void visit(Matches arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.BitwiseAnd)
	 */
	@Override
	public void visit(BitwiseAnd arg0) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.BitwiseOr)
	 */
	@Override
	public void visit(BitwiseOr arg0) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
	 * .expression.operators.arithmetic.BitwiseXor)
	 */
	@Override
	public void visit(BitwiseXor arg0) {

	}
}
