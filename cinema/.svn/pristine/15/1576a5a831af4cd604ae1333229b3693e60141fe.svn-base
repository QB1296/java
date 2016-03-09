/**
 * 
 */
package com.vion.core.security;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.Union;

import com.vion.core.hibernate.HibernateEntityUtils;
import com.vion.core.security.meta.SecureSubject;
import com.vion.core.security.rule.FilterRule;
import com.vion.core.security.rule.RecordFilterRule;
import com.vion.core.util.Strings;

/**
 * <b>功能描述</b> <br>
 * HQL数据权限控制
 * @author YUJB
 * @date 2014年8月8日 下午3:34:05
 */
public class SQLRuleFilterProcessor implements RuleFilterProcessor{

	
	private SecureSubjectHolder secureSubjectHolder;
	
	private ValueProcessor[] valueProcessors;
	
	public SQLRuleFilterProcessor() {
		super();
	}
	
	public SecureSubjectHolder getSecureSubjectHolder() {
		return secureSubjectHolder;
	}


	public void setSecureSubjectHolder(SecureSubjectHolder secureSubjectHolder) {
		this.secureSubjectHolder = secureSubjectHolder;
	}


	public ValueProcessor[] getValueProcessors() {
		return valueProcessors;
	}


	public void setValueProcessors(ValueProcessor[] valueProcessors) {
		this.valueProcessors = valueProcessors;
	}
	
	
	@SuppressWarnings("rawtypes")
	public class IterableBag implements Iterable{
		private Object object;
		
		
		public IterableBag(Object object) {
			super();
			this.object = object;
		}

		/* (non-Javadoc)
		 * @see java.lang.Iterable#iterator()*/
		@Override
		public Iterator iterator() {
			if (object instanceof Iterable) {
				return ((Iterable) object).iterator();
			}
			return null;
		}
		
		public int size(){
			if (object instanceof Collection) {
				return ((Collection) object).size();
			}
			return 0;
		}
		
		public boolean isCollectoin(){
			return object instanceof Collection;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.vion.core.security.RuleFilterProcessor#process(java.lang.Object, java.lang.Object)*/
	@Override
	public void cacheFilter(Object rowInfos, Object ticket) {
		IterableBag iterableBag = new IterableBag(rowInfos);
		cacheFilter(iterableBag,ticket);
	}

	
	public void cacheFilter(IterableBag rowInfos, Object ticket) {
		if (rowInfos == null || rowInfos.size() == 0) {
			return;
		}
		SecureSubject secureSubject = secureSubjectHolder
				.getSecureSubject(ticket);
		WhereExpressionVisitor visitor = null;
		Map<RecordFilterRule, WhereExpressionVisitor> mapper = new HashMap<RecordFilterRule, WhereExpressionVisitor>(); 
		if (secureSubject != null) {
			List<FilterRule> filterRules = secureSubject.getDataPermissions();
			if (secureSubject != null) {
				for (Iterator<?> iterator = rowInfos.iterator(); iterator
						.hasNext();) {
					Object rowInfo = iterator.next();
					boolean isFilter = false;
					boolean isProcess = false;
					for (FilterRule filterRule : filterRules) {
						if (filterRule instanceof RecordFilterRule) {
							RecordFilterRule recordFilterRule = (RecordFilterRule) filterRule;
							boolean cacheFilter = recordFilterRule.isCacheFilter();
							String entity = recordFilterRule.getEntity();
							if (cacheFilter && entity.equals(HibernateEntityUtils.getTable(rowInfo.getClass()))) {
								String pointCut = recordFilterRule.getPointCut();
								String excludePointCut = recordFilterRule.getExcludePointCut();
								/*不可访问pointCut*/
								if(!SecureMetaModelHolder.getSecureMetaModel().isAccessMoudle(pointCut)){
									continue;
								}
								/*可访问excludePointCut*/
								if (!Strings.isEmpty(excludePointCut) && SecureMetaModelHolder.getSecureMetaModel().isAccessMoudle(excludePointCut)) {
									continue;
								}
								if (mapper.get(recordFilterRule) == null) {
									Expression expression = recordFilterRule.getCacheFilterExpression();
									visitor = new WhereExpressionVisitor(expression);
									mapper.put(recordFilterRule, visitor);
									if(expression instanceof NullExpression){
										continue; 
									}
								}else {
									visitor = mapper.get(recordFilterRule);
								}
								if (!isProcess) {
									isProcess = true;
								}
								isFilter = isFilter || cacheFilter(recordFilterRule, visitor, rowInfo);
								if (isFilter) {
									break;
								}
							}
						}
					}
					if (!isFilter && isProcess) {
						iterator.remove();
					}
				}
			}
		}
	}
	
	
	public boolean cacheFilter(RecordFilterRule recordFilterRule,WhereExpressionVisitor vistitor,Object rowInfo) {
		Object eval = vistitor.eval(rowInfo);
		return Boolean.valueOf(eval.toString());
	}

	@Override
	public String process(String sql,Object ticket) {
		CCJSqlParserManager parserManager = new CCJSqlParserManager();
		Select select = null;
		try {
			Statement statement = parserManager.parse(new StringReader(sql));
			if (statement instanceof Select) {
				select = (Select) parserManager.parse(new StringReader(sql));
			}else {
				return sql;
			}
		} catch (JSQLParserException e) {
			return sql;
		}
		
		SelectBody body = select.getSelectBody();
		
		SecureSubject secureSubject = secureSubjectHolder.getSecureSubject(ticket);
		List<FilterRule> filterRules = null;
		if (secureSubject != null) {
			filterRules = secureSubject.getDataPermissions();
		}
		body.accept(new SelectVisitorImpl(filterRules));
		
		return select.toString();
	}
	
	
	public static class SelectVisitorImpl implements SelectVisitor {
		
		private List<FilterRule> filterRules;
		

	    public SelectVisitorImpl(List<FilterRule> filterRules) {
			super();
			this.filterRules = filterRules;
		}

		@SuppressWarnings("unchecked")
		public void visit(PlainSelect ps) {
	    	if(ps.getFromItem() instanceof Table){
	    		if (filterRules != null) {
	    			List<Expression> expressions = new ArrayList<Expression>();
	    			for (FilterRule filterRule : filterRules) {
		    			if (filterRule instanceof RecordFilterRule) {
							RecordFilterRule recordFilterRule = (RecordFilterRule)filterRule;
							if(notOnlyCacheFilter(recordFilterRule) && recordFilterRule.isSupport(new FragmentSQL(ps))){
								String pointCut = recordFilterRule.getPointCut();
								String excludePointCut = recordFilterRule.getExcludePointCut();
								/*不可访问pointCut*/
								if(!SecureMetaModelHolder.getSecureMetaModel().isAccessMoudle(pointCut)){
									continue;
								}
								/*可访问excludePointCut*/
								if (!Strings.isEmpty(excludePointCut) && SecureMetaModelHolder.getSecureMetaModel().isAccessMoudle(excludePointCut)) {
									continue;
								}
								recordFilterRule.convertPS(ps);
								Expression expression = recordFilterRule.getWhereExpression(recordFilterRule.getAliasConfig(ps));
        						expressions.add(expression);
							}
						}
					}
	    			setWhereExpression(ps,expressions);
				}
	    		
	    	}
            
            /*form is 子查询*/
            if(ps.getFromItem() instanceof SubSelect){
            	SubSelect subSelect = ((SubSelect)ps.getFromItem());
            	subSelect.getSelectBody().accept(this);
            }

	        //JOIN表的访问
	        List<Join>  joins = ps.getJoins();
	        List<Join> realJoins = new ArrayList<Join>();
	        if (joins != null) {
	            for (Join join : joins) {
	            	if(join.getRightItem() instanceof SubSelect){
	                	SubSelect subSelect = ((SubSelect)join.getRightItem());
	                	subSelect.getSelectBody().accept(this);
	                	realJoins.add(join);
	                }
	            	if (join.getRightItem() instanceof Table) {
	            		boolean isProcess = false;
	            		if (filterRules != null) {
	            			for (FilterRule filterRule : filterRules) {
	            				if (filterRule instanceof RecordFilterRule) {
	            					RecordFilterRule recordFilterRule = (RecordFilterRule)filterRule;
	            					if(notOnlyCacheFilter(recordFilterRule) && recordFilterRule.isSupport(new FragmentSQL(join))){
	            						String pointCut = recordFilterRule.getPointCut();
	            						if(SecureMetaModelHolder.getSecureMetaModel().isAccessMoudle(pointCut)){
	            							isProcess = true;
	            						}
	            					}
	            				}
	            			}
						}
	            		if (isProcess){
	            			Table table = (Table)join.getRightItem();
	            			Join subSelectJoin = new Join();
	            			SubSelect subSelect = new SubSelect();
	            			subSelect.setAlias(table.getAlias());
	            			PlainSelect plainSelect = new PlainSelect();
	            			Table subTable = new Table();
	            			subTable.setName(table.getName());
	            			subTable.setAlias("vion_sub_" +table.getName() + "_alias" );
	            			plainSelect.setFromItem(subTable);
	            			Table subTableAlias = new Table();
	            			subTableAlias.setName(subTable.getAlias());
	            			plainSelect.setSelectItems(Arrays.asList(new AllTableColumns(subTableAlias)));
	            			subSelect.setSelectBody(plainSelect);
	            			subSelectJoin.setRightItem(subSelect);
	            			subSelectJoin.setOnExpression(join.getOnExpression());
	            			realJoins.add(subSelectJoin);
	            			subSelect.getSelectBody().accept(this);
	            		}else {
	            			realJoins.add(join);
						}
	            		
	            		
	            	}
	        }
	        ps.setJoins(realJoins);      
	      }

	    }

		public void visit(Union un) {
			List<?> plainSelects = un.getPlainSelects();
			for (Object object : plainSelects) {
				if (object instanceof PlainSelect) {
					((PlainSelect) object).accept(this);
				} else {
					((Union) object).accept(this);
				}
			}

		}


	}
	
	
	public static void setWhereExpression(PlainSelect ps,List<Expression> expression){
		if (expression == null || expression.size() == 0) {
			return;
		}
		Expression oldExpression = null;
		for (Expression one : expression) {
			if (oldExpression == null) {
				oldExpression = one;
			}else {
				oldExpression = new BracketOrExpression(one, oldExpression);
			}
		}
		
		if (ps.getWhere() == null) {
			ps.setWhere(oldExpression);
		}else {
			Expression expr = new Parenthesis(ps.getWhere());
			AndExpression and = new AndExpression(oldExpression, expr);
			ps.setWhere(and);
		}
	}
	
	public static void setWhereExpression(Join join,List<Expression> expression){
		if (expression == null || expression.size() == 0) {
			return;
		}
		Expression oldExpression = null;
		for (Expression one : expression) {
			if (oldExpression == null) {
				oldExpression = one;
			}else {
				oldExpression = new BracketOrExpression(one, oldExpression);
			}
		}
		Expression expr = join.getOnExpression();
		if (expr != null) {
			AndExpression and = new AndExpression(oldExpression, expr);
			join.setOnExpression(and);
		}else {
			join.setOnExpression(oldExpression);
		}
	}
	
	
	private static boolean notOnlyCacheFilter(RecordFilterRule recordFilterRule){
		boolean flag = true;
		if (recordFilterRule.isCacheFilter()) {
			flag = !recordFilterRule.isAffectSQLFilter();
		}else {
			flag = true;
		}
		return flag;
	}
	
	
	
	
}
