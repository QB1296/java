/**
 * 文件名：SimpleFilterRule.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;

import org.codehaus.jackson.annotate.JsonTypeName;
import org.springframework.util.Assert;

import com.vion.core.security.AliasProcessor;
import com.vion.core.security.FragmentSQL;
import com.vion.core.security.SQLStringValue;
import com.vion.core.security.meta.AliasConfig;
import com.vion.core.security.meta.AliasConfig.AliasConfigEntry;
import com.vion.core.util.Strings;



/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 下午2:06:20
 */
@JsonTypeName("sql")
public class SQLSimpleFilterRule extends RecordFilterRule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 对应rowRule value节点的sql  */
	private String sql;
	
	/** join关联表  */
	private List<String> joins;
	
	/** xml别名和表的对应关系  */
	private Map<String, String> aliasXMLMapper = new HashMap<String, String>();
	
	/** 强制jion的表和 join表达式onExpression的对应关系  */
	private Map<String, String> joinOnExpressionMapper = new HashMap<String,String>();
	
	/** 强制jion的表和 join连接类型的对应关系  */
	private Map<String, String> joinTypeMapper = new HashMap<String,String>();
	
	/** sql 参数值*/
	private Object value;

	
	/**
	 * 得到必须的表,不包含强制join表
	 * @return
	 */
	private List<String> getMustJoins() {
		List<String> mustJoins = new ArrayList<String>();
		if (joins != null) {
			for (String join : joins) {
				if (!joinOnExpressionMapper.containsKey(join)) {
					mustJoins.add(join);
				}
			}
		}
		return mustJoins;
	}
	
	
	/**
	 * 加入join表,join列表采用懒初始化模式,不用考虑NullPointException
	 * @param join
	 */
	public void addJoin(String join){
		if(joins == null){
			joins = new ArrayList<String>();
		}
		joins.add(join);
	}
	
	/**
	 * 增加join表和表达式的映射关系
	 * @param join  join表 
	 * @param expression 表达式
	 */
	public void putJoinExpressionMapper(String join,String expression){
		joinOnExpressionMapper.put(join, expression);
	}
	
	/**
	 * 增加join表和表达式的映射关系
	 * @param join  join表 
	 * @param expression 表达式
	 */
	public void putJoinTypeMapper(String join,String joinType){
		joinTypeMapper.put(join, joinType);
	}
	

	public List<String> getJoins() {
		return joins;
	}


	public void setJoins(List<String> joins) {
		this.joins = joins;
	}


	public String getSql() {
		return sql;
	}


	public void setSql(String sql) {
		this.sql = sql;
	}
	

	/**
	 * {@link #value}
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}


	/**
	 * {@link #value}	
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}


	@Override
	public Expression getWhereExpression(AliasConfig aliasConfig) {
 		if (Strings.isEmpty(sql)) {
			return  null;
		}
		AliasProcessor aliasProcessor = new AliasProcessor(aliasConfig);
		SQLStringValue sqlStringValue = new SQLStringValue(aliasProcessor.replacePlaceholderField(value,sql,aliasXMLMapper));
		return sqlStringValue;
	}
	
	
	/* (non-Javadoc)
	 * @see com.vion.core.security.rule.RecordFilterRule#convertPS(net.sf.jsqlparser.statement.select.PlainSelect)*/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void convertPS(PlainSelect ps) {
		AliasConfig aliasConfig = getAliasConfig(ps);
		
		Set<String> keySet = joinOnExpressionMapper.keySet();
		List allJoins = ps.getJoins();
		if (allJoins == null) {
			allJoins = new ArrayList<Object>();
		}
		for (String joinTable : keySet) {
			AliasConfigEntry aliasConfigEntry = aliasConfig.new AliasConfigEntry();
			aliasConfigEntry.setAlias(joinTable + "_alias");
			aliasConfigEntry.setEntity(joinTable);
			aliasConfig.addConfigEntry(aliasConfigEntry);
		}	
		for (String joinTable : keySet) {
			if (isContainsJoin(allJoins,joinTable)) {
				continue;
			}
			String onSQL = joinOnExpressionMapper.get(joinTable);
			Join join = new Join();
			String joinType = joinTypeMapper.get(joinTable);
			if (joinType != null) {
				if (joinType.equals("full")) {
					join.setFull(true);
				}
				if (joinType.equals("left")) {
					join.setLeft(true);
				}
				if (joinType.equals("inner")) {
					join.setInner(true);
				}
				if (joinType.equals("right")) {
					join.setRight(true);
				}
			}
			Table table = new Table();
			table.setName(joinTable);
			table.setAlias(joinTable + "_alias");
			join.setRightItem(table);
			AliasProcessor aliasProcessor = new AliasProcessor(aliasConfig);
			SQLStringValue sqlStringValue = new SQLStringValue(aliasProcessor.replacePlaceholderField(value,onSQL,aliasXMLMapper));
			join.setOnExpression(sqlStringValue);
			allJoins.add(join);
		}
		ps.setJoins(allJoins);
	}
	
	
	@SuppressWarnings("rawtypes")
	public boolean isContainsJoin(List joins,String tableName){
		boolean flag = false;
		if (joins != null) {
			for (Object oneJoinSegement : joins) {
				if (oneJoinSegement instanceof Join) {
					FromItem rightItem = ((Join)oneJoinSegement).getRightItem();
					if (rightItem instanceof Table) {
						if(((Table)rightItem).getName().equals(tableName)){
							flag = true;
						}					
					}
				}
			}
		}
		return flag;
	}
	
	/* (non-Javadoc)
	 * @see com.vion.core.security.rule.RecordFilterRule#convertJoin(net.sf.jsqlparser.statement.select.Join)*/
	@Override
	public void convertJoin(Join join) {
	}

	

	@Override
	public boolean isSupport(FragmentSQL fragmentSQL) {
		if (isNotJoinTable()) {
			if (fragmentSQL.isJoin()) {
				return false;
			}
		}
		String rootTable = fragmentSQL.getRootFrom();
		
		if(rootTable != null){
			List<String> allEntitys = getMustJoins();
			List<String> selectEntitys = fragmentSQL.getAllEntitys();
			
			if(rootTable.equals(getEntity()) && selectEntitys.containsAll(allEntitys)){
				return true;
			}
		}
		
		return false;
	}


	

	public void addXmlConfigMapper(String[] joinCodes, String[] joins) {
		if (joinCodes == null && joins == null) {
			return;
		}
		Assert.isTrue(joinCodes.length == joins.length);
		for (int i = 0; i < joinCodes.length; i++) {
			aliasXMLMapper.put(joinCodes[i], joins[i]);
			addJoin(joins[i]);
		}
	}


	public void addRootXmlConfigMapper(String code, String entity) {
		aliasXMLMapper.put(code, entity);
		setEntity(entity);
	}
	
	public String getEntityByAlias(String alias){
		return aliasXMLMapper.get(alias);
	}
	
	public void setAliasXMLMapper(Map<String, String> aliasXMLMapper) {
		this.aliasXMLMapper = aliasXMLMapper;
	}
	
	public Map<String, String> getAliasXMLMapper() {
		return aliasXMLMapper;
	}


	/**
	 * {@link #joinOnExpressionMapper}
	 * @return the joinOnExpressionMapper
	 */
	public Map<String, String> getJoinOnExpressionMapper() {
		return joinOnExpressionMapper;
	}


	

	

}
