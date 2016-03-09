/**
 * 文件名：FilterRule.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.rule;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.vion.core.ResourcesHolder;
import com.vion.core.security.AssistTableHelper;
import com.vion.core.security.SQLStringValue;
import com.vion.core.security.SecureContextUtil;
import com.vion.core.security.meta.AliasConfig;
import com.vion.core.security.meta.AliasConfig.AliasConfigEntry;
import com.vion.core.util.Strings;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 下午2:02:59
 */
	
public abstract class RecordFilterRule  implements FilterRule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public abstract void convertPS(PlainSelect ps);
	public abstract void convertJoin(Join join);
	public abstract Expression getWhereExpression(AliasConfig aliasConfig);
	
	private String moudle;
	
	private String pointCut;
	
	private String excludePointCut;
	
	private boolean isCacheFilter;
	
	private boolean affectSQLFilter;
	
	private String entity;
	
	private boolean isAssistTable = false;
	
	private String assistColumnName;
	
	private String assistColumnCode;
	
	private boolean notJoinTable = true;
	
	
	/**
	 * {@link #assistColumnCode}
	 * @return the assistColumnCode
	 */
	public String getAssistColumnCode() {
		return assistColumnCode;
	}
	
	/**
	 * {@link #assistColumnCode}	
	 * @param assistColumnCode the assistColumnCode to set
	 */
	public void setAssistColumnCode(String assistColumnCode) {
		this.assistColumnCode = assistColumnCode;
	}
	
	/**
	 * {@link #isAssistTable}
	 * @return the isAssistTable
	 */
	public boolean isAssistTable() {
		return isAssistTable;
	}
	/**
	 * {@link #isAssistTable}	
	 * @param isAssistTable the isAssistTable to set
	 */
	public void setAssistTable(boolean isAssistTable) {
		this.isAssistTable = isAssistTable;
	}
	/**
	 * {@link #assistColumnName}
	 * @return the assistColumnName
	 */
	public String getAssistColumnName() {
		return assistColumnName;
	}
	/**
	 * {@link #assistColumnName}	
	 * @param assistColumnName the assistColumnName to set
	 */
	public void setAssistColumnName(String assistColumnName) {
		this.assistColumnName = assistColumnName;
	}
	/**
	 * {@link #entity}
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}
	
	/**
	 * {@link #entity}	
	 * @param entity the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	/**
	 * {@link #isCacheFilter}
	 * @return the isCacheFilter
	 */
	public boolean isCacheFilter() {
		return isCacheFilter;
	}


	/**
	 * {@link #isCacheFilter}	
	 * @param isCacheFilter the isCacheFilter to set
	 */
	public void setCacheFilter(boolean isCacheFilter) {
		this.isCacheFilter = isCacheFilter;
	}

	/**
	 * {@link #affectSQLFilter}
	 * @return the affectSQLFilter
	 */
	public boolean isAffectSQLFilter() {
		return affectSQLFilter;
	}
	/**
	 * {@link #affectSQLFilter}	
	 * @param affectSQLFilter the affectSQLFilter to set
	 */
	public void setAffectSQLFilter(boolean affectSQLFilter) {
		this.affectSQLFilter = affectSQLFilter;
	}
	
	
	/**
	 * {@link #pointCut}
	 * @return the pointCut
	 */
	public String getPointCut() {
		return pointCut;
	}
	/**
	 * {@link #pointCut}	
	 * @param pointCut the pointCut to set
	 */
	public void setPointCut(String pointCut) {
		this.pointCut = pointCut;
	}
	
	
	/**
	 * {@link #excludePointCut}
	 * @return the excludePointCut
	 */
	public String getExcludePointCut() {
		return excludePointCut;
	}
	/**
	 * @param excludePointCut the excludePointCut to set
	 */
	public void setExcludePointCut(String excludePointCut) {
		this.excludePointCut = excludePointCut;
	}
	
	
	public String getMoudle() {
		return moudle;
	}

	public void setMoudle(String moudle) {
		this.moudle = moudle;
	}
	
	
	/**
	 * 获得notJoinTable 
	 * @return  notJoinTable notJoinTable
	 */
	public boolean isNotJoinTable() {
		return notJoinTable;
	}
	/** 
	 * 设置notJoinTable 
	 * @param notJoinTable notJoinTable 
	 */
	public void setNotJoinTable(boolean notJoinTable) {
		this.notJoinTable = notJoinTable;
	}
	public void setWhereExpression(PlainSelect ps,Expression expression){
		if (expression == null) {
			return;
		}
		if (ps.getWhere() == null) {
			ps.setWhere(expression);
		}else {
			Expression expr = new Parenthesis(ps.getWhere());
			AndExpression and = new AndExpression(expression, expr);
			ps.setWhere(and);
		}
	}
	
	public void setWhereExpression(Join join,Expression expression){
		if (expression == null) {
			return;
		}
		Expression expr = join.getOnExpression();
		if (expr != null) {
			AndExpression and = new AndExpression(expression, expr);
			join.setOnExpression(and);
		}else {
			join.setOnExpression(expression);
		}
	}
	
	
	public  AliasConfig getAliasConfig(PlainSelect ps){
		
		AliasConfig aliasConfig = new AliasConfig();
		
		if(ps.getFromItem() instanceof Table){
			Table table = ((Table)ps.getFromItem());
        	AliasConfigEntry aliasConfigEntry = aliasConfig.new AliasConfigEntry();
        	aliasConfigEntry.setEntity(table.getName());
        	String alias = table.getName();
        			
        	if (!Strings.isEmpty(table.getAlias())) {
        		alias = table.getAlias();
			}
        	aliasConfigEntry.setAlias(alias);
        	aliasConfig.addConfigEntry(aliasConfigEntry);
        	aliasConfig.setRootEntity(table.getName());
        }

        //JOIN表的访问
        @SuppressWarnings("unchecked")
		List<Join>  joins = ps.getJoins();
        if (joins != null) {
            for (Join join : joins) {
            	AliasConfigEntry aliasConfigEntry = aliasConfig.new AliasConfigEntry();
            	FromItem rightItem = join.getRightItem();
            	if(rightItem instanceof Table){
            		Table table = ((Table)rightItem);
            		aliasConfigEntry.setEntity(table.getName());
            		String alias = table.getName();
            		
            		if (!Strings.isEmpty(table.getAlias())) {
            			alias = table.getAlias();
            		}
            		aliasConfigEntry.setAlias(alias);
            		aliasConfig.addConfigEntry(aliasConfigEntry);
            	}
            }
        }
        return aliasConfig;
	}
	
	
	public  AliasConfig getAliasConfig(Join join){
		
		AliasConfig aliasConfig = new AliasConfig();
		
		if(join.getRightItem() instanceof Table){
			Table table = ((Table)join.getRightItem());
        	AliasConfigEntry aliasConfigEntry = aliasConfig.new AliasConfigEntry();
        	aliasConfigEntry.setEntity(table.getName());
        	String alias = table.getName();
        			
        	if (!Strings.isEmpty(table.getAlias())) {
        		alias = table.getAlias();
			}
        	aliasConfigEntry.setAlias(alias);
        	aliasConfig.addConfigEntry(aliasConfigEntry);
        	aliasConfig.setRootEntity(table.getName());
        }

        return aliasConfig;
	}
	
	/**
	 * {@link #cacheFilterExpression}
	 * @return the cacheFilterExpression
	 */
	@JsonIgnore
	public Expression getCacheFilterExpression() {
		Serializable id = SecureContextUtil.getSecureContext().getSecureSession().getId();
		Expression whereExpression = ResourcesHolder.get(this.toString() + id,Expression.class);
		if (whereExpression == null) {
			if (isAssistTable()) {
				Column column = new Column();
				column.setColumnName(getAssistColumnName());
				column.setTable(new Table());
				ExpressionList items = new ExpressionList();
				List<Expression> itemsEx = new ArrayList<Expression>();
				List<String> assistResourceIds = AssistTableHelper.getAssistResourceIds(getAssistColumnCode());
				if (assistResourceIds != null) {
					for (String resourceId : assistResourceIds) {
						itemsEx.add(new StringValue("'" + resourceId + "'"));
					}
				}
				if (itemsEx.size() == 0) {
					itemsEx.add(new StringValue("'UNKNOW'"));
				}
				items.setExpressions(itemsEx);
				whereExpression = new InExpression(column,items);
			}else {
				Expression where = this.getWhereExpression(new AliasConfig());
				if (where instanceof SQLStringValue) {
					String sqlString = "select * from " + entity + " where " +  where.toString();
					CCJSqlParserManager parserManager = new CCJSqlParserManager();
					try {
						Statement statement = parserManager.parse(new StringReader(sqlString));
						if (statement instanceof Select) {
							Select select = (Select)statement;
							SelectBody selectBody = select.getSelectBody();
							whereExpression = ((PlainSelect)selectBody).getWhere();
						} 
					} catch (JSQLParserException e) {
					}
				}else {
					whereExpression = where;
				}
			}
			ResourcesHolder.bind(this.toString() + id, whereExpression);
		}
		return whereExpression;
	}

}
