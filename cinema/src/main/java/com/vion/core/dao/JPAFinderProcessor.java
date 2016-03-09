/**
 * 文件名：FinderProcessor.java  
 *  
 * 版本信息：  
 * 日期：2013-6-5  
 * Copyright(c)  http://www.vion.com <br>
 * 版权所有  
 */	
 
package com.vion.core.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.vion.core.dao.FinderContext.AliasNode;
import com.vion.core.dao.finder.Field;
import com.vion.core.dao.finder.Filter;
import com.vion.core.dao.finder.IFilter;
import com.vion.core.dao.finder.IFinder;
import com.vion.core.dao.finder.IGroup;
import com.vion.core.dao.finder.IJoin.JoinType;
import com.vion.core.dao.finder.Sort;
import com.vion.core.util.Strings;


/**
 * <b>功能描述</b> <br>
 * 基于JPA的Finder处理器,同时实现了FinderResult接口<br>
 * 通过使用{@link #generateQL()}生成查询语句
 * 
 * <pre>
 *  select _vion_t from Student _vion_t left join School t_1
 *  where _vion_t.age &gt; :p1 and _vion_t.name != :p2
 *  and t_1.name like lower(:p3)
 *   
 *  parameter list: [3, 'YUJB','北京大学']
 * <pre>
 * @author YUJB
 * @date 2014-6-14 下午02:21:04
 */
public abstract class JPAFinderProcessor implements FinderResult {
	
	/** 查询封装类  */
	protected IFinder finder;
	
	/** 查询语句  */
	protected String ql;
	
	/** JPQL查询语言中参数站位符前缀,最终胜出P1,P2....  */
	protected String PREFIX_PARAM = "p";
	
	/** JPQL查询占位符  */
	protected String PLACEHOLDER_PARAM = ":" + PREFIX_PARAM;
	
	
	private FinderContext ctx = null;
	
	private transient Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @param finder
	 */
	public JPAFinderProcessor(IFinder finder) {
		super();
		this.finder = finder;
		Assert.notNull(finder.getRootClass(), "root class 不能为null");
		ctx = new FinderContext(finder.getRootClass());
		ctx.addAliasNode(finder.getJoins());
	}
	
	/**
	 * @return the finder
	 */
	public IFinder getFinder() {
		return finder;
	}
	
	/**
	 * @param finder the finder to set
	 */
	public void setFinder(IFinder finder) {
		this.finder = finder;
	}
	

	/**
	 * 基于JPA标准生产ORM查询语句
	 * @return 查询语句
	 */
	protected String generateQL(){
		
		StringBuffer sb = new StringBuffer();
		
		if (ql == null) {
			String select = generateSelectClause(ctx,finder.getFields(), finder.isDistinct());
			String where = generateWhereClause(ctx,finder.getFilter(),false);
			String groupBy = generateGroupByClause(ctx,finder.getGroupBy());
			String orderBy = generateOrderByClause(ctx, finder.getSorts());
			String from = generateFromClause(ctx);
			sb.append(select);
			sb.append(from);
			sb.append(where);
			sb.append(groupBy);
			sb.append(orderBy);
			ql = sb.toString();
			
			logger.debug("query sql: {}",ql);
			
		}
		
		return ql;
	};
	
	/**
	 * @param ctx
	 * @param groupBy
	 * @return
	 */
	private String generateGroupByClause(FinderContext ctx, IGroup groupBy) {
		StringBuffer sb = new StringBuffer();
		if (groupBy != null) {
			sb.append(" group by ");
			String[] propertys = groupBy.getProperties();
			for (int i = 0; i < propertys.length; i++) {
				sb.append(ctx.getPathAlias(propertys[i]));
				if (i != propertys.length - 1) {
					sb.append(",");
				}
			}
			if(groupBy.getHaving() != null){
				sb.append(" having ");
				IFilter having = groupBy.getHaving();
				sb.append(filterToQL(ctx, having,true));
			}
			
		}
		return sb.toString();
	}

	public List<Object> getParamValues(){
		if (ql == null) {
			//TODO custom exception
			throw new RuntimeException("先调用generateQL方法");
		}
		return ctx.paramList;
	}
	
	
	/**
	 * 得到select field列集合
	 * @return
	 */
	public List<String> getSelectColumns(){
		
		List<String> retColumns = new ArrayList<String>();
		
		List<Field> fields = finder.getFields();
		for (Field field : fields) {
			if (!Strings.isEmpty(field.getAlias())) {
				retColumns.add(field.getAlias());
			}else {
				String property = field.getProperty();
				String column = property;
				int index = property.lastIndexOf(".");
				if (index > 0) {
					column = property.substring(index + 1, property.length());
				}
				retColumns.add(column);
			}
		}
		
		return retColumns;
	} 
	
	protected String generateFieldOperateClause(String property,Integer fieldOperator, FinderContext ctx){
		StringBuffer sb = new StringBuffer();
		if (fieldOperator != null) {
			switch (fieldOperator) {
			case Field.OP_AVG:
				sb.append("avg(");
				break;
			case Field.OP_COUNT:
				sb.append("count(");
				break;
			case Field.OP_COUNT_DISTINCT:
				sb.append("count(distinct ");
				break;
			case Field.OP_MAX:
				sb.append("max(");
				break;
			case Field.OP_MIN:
				sb.append("min(");
				break;
			case Field.OP_SUM:
				sb.append("sum(");
				break;
			default:
				break;
			}
		}
		sb.append(ctx.getPathAlias(property));
		if (fieldOperator != null) {
			sb.append(")");
		}
		return sb.toString();
	}
	
	/**
	 * 得到select　从句
	 * @param ctx       finder上下文信息
	 * @param fields　　查询的fields
	 * @param distinct　是否去除重复数据
	 * @return  select从句
	 */
	protected String generateSelectClause(FinderContext ctx,List<Field> fields, boolean distinct) {

		StringBuilder sb = null;
		boolean useOperator = false;
		boolean first = true;
		
		/*更具field拼装select语句*/
		for (Field field : fields) {
			if (first) {
				sb = new StringBuilder("select ");
				if (distinct) {
					sb.append("distinct ");
				}
				first = false;
			} else {
				sb.append(", ");
			}

			String prop = null;
			if (field.getProperty() == null || "".equals(field.getProperty())) {
				prop = ctx.rootAlias;
			} else {
				String alias = ctx.getPathAlias(field.getProperty());
				prop = alias;
			}
			if (field.getOperator() != null) {
				switch (field.getOperator()) {
				case Field.OP_AVG:
					sb.append("avg(");
					useOperator = true;
					break;
				case Field.OP_COUNT:
					sb.append("count(");
					useOperator = true;
					break;
				case Field.OP_COUNT_DISTINCT:
					sb.append("count(distinct ");
					useOperator = true;
					break;
				case Field.OP_MAX:
					sb.append("max(");
					useOperator = true;
					break;
				case Field.OP_MIN:
					sb.append("min(");
					useOperator = true;
					break;
				case Field.OP_SUM:
					sb.append("sum(");
					useOperator = true;
					break;
				default:
					break;
				}
			}
			sb.append(prop);
			if (useOperator) {
				sb.append(")");
			}
			if (!Strings.isEmpty(field.getAlias())) {
				sb.append(" as " + field.getAlias());
			}
		}
		/* 如果没有field 所有字段*/
		if (first) {
			if (distinct)
				return "select distinct " + ctx.getRootAlias();
			else
				return "select " + ctx.getRootAlias();
		}
		
		return sb.toString();
	}
	
	/**
	 * 得到from 从句,处理了多表关联
	 * @param ctx finder查询上下文
	 * @return from从句
	 */
	protected String generateFromClause(FinderContext ctx) {
		StringBuilder sb = new StringBuilder(" from ");
		
		sb.append(finder.getRootClass().getSimpleName());
		sb.append(" as ");
		sb.append(ctx.getRootAlias());
		sb.append(generateJoins(ctx));
		return sb.toString();
	}
	
	/**
	 * 处理多表关联
	 * @param ctx finder查询上下文信息
	 * @return 连接语句
	 */
	protected String generateJoins(FinderContext ctx) {
		StringBuilder sb = new StringBuilder();

		Queue<AliasNode> queue = new LinkedList<AliasNode>();
		queue.offer(ctx.aliases.get(ctx.root_path));
		while (!queue.isEmpty()) {
			AliasNode node = queue.poll();
			if (node.parent != null) {
				JoinType joinType = node.getJoinType();
				switch (joinType) {
				case left:
					sb.append(" left join ");
					break;
				case right:
					sb.append(" right join ");
					break;
				case inner:
					sb.append(" inner join ");
					break;
				}
				if (node.isFetch) {
					sb.append(" fetch ");
				}
				sb.append(node.parent.alias);
				sb.append(".");
				sb.append(node.property);
				sb.append(" as ");
				sb.append(node.alias);
				sb.append(" ");
			}
			for (AliasNode child : node.children) {
				queue.offer(child);
			}
		}

		return sb.toString();
	}
	
	
	protected String generateOrderByClause(FinderContext ctx, List<Sort> sorts) {
		if (sorts == null)
			return "";
		StringBuilder sb = null;
		boolean first = true;
		for (Sort sort : sorts) {
			if (first) {
				sb = new StringBuilder(" order by ");
				first = false;
			} else {
				sb.append(", ");
			}
			sb.append(ctx.getPathAlias(sort.getProperty()));
			sb.append(sort.isDesc() ? " desc" : " asc");
		}

		if (first) {
			return "";
		}
		return sb.toString();
	}
	
	
	/**
	 * 生产where语句
	 * @param ctx
	 * @param filter
	 * @param isDisjunction
	 * @return
	 */
	protected  String generateWhereClause(FinderContext ctx, IFilter filter, boolean isDisjunction) {
		String content = null;
		content = filterToQL(ctx, filter,false);
		
		return (content == null) ? "" : " where " + content;
	}

	/**
	 */
	@SuppressWarnings("rawtypes")
	protected String filterToQL(FinderContext ctx, IFilter filter,boolean isFieldOperator) {
		if (filter == null) {
			return null;
		}
		String property = filter.getProperty();
		Object value = filter.getValue();
		if (value == null
				|| (value instanceof String && ("".equals(value)
						|| "%null".equals(value) || "null%".equals(value) || "%null%"
							.equals(value)))) {
			return null;
		}
		int operator = filter.getOperator();

		switch (operator) {
			case IFilter.OP_NULL:
				if(isFieldOperator){
					return generateFieldOperateClause(property,filter.getFieldOperator(),ctx) + " is null";
				}
				return ctx.getPathAlias(property) + " is null";
			case IFilter.OP_NOT_NULL:
				if(isFieldOperator){
					return generateFieldOperateClause(property,filter.getFieldOperator(),ctx) + " is not null";
				}
				return ctx.getPathAlias(property) + " is not null";
			case IFilter.OP_IN:
				if (value == null || (value instanceof Collection && ((Collection)value).size() == 0)) {
					return null;
				}
				if(isFieldOperator){
					return generateFieldOperateClause(property,filter.getFieldOperator(),ctx) + " in (" + param(ctx, value) + ")";
				}
				return ctx.getPathAlias(property) + " in (" + param(ctx, value) + ")";
			case IFilter.OP_NOT_IN:
				if(isFieldOperator){
					return generateFieldOperateClause(property,filter.getFieldOperator(),ctx)+ " not in (" + param(ctx, value) + ")";
				}
				return ctx.getPathAlias(property) + " not in (" + param(ctx, value) + ")";
			case IFilter.OP_EQUAL:
				if(isFieldOperator){
					return generateFieldOperateClause(property,filter.getFieldOperator(),ctx)+ " = " + param(ctx, value);
				}
				return ctx.getPathAlias(property) + " = " + param(ctx, value);
			case IFilter.OP_NOT_EQUAL:
				if(isFieldOperator){
					return generateFieldOperateClause(property,filter.getFieldOperator(),ctx)+ " != " + param(ctx, value);
				}
				return ctx.getPathAlias(property) + " != " + param(ctx, value);
			case IFilter.OP_GREATER_THAN:
				if(isFieldOperator){
					return generateFieldOperateClause(property,filter.getFieldOperator(),ctx)+ " > " + param(ctx, value);
				}
				return ctx.getPathAlias(property) + " > " + param(ctx, value);
			case IFilter.OP_LESS_THAN:
				if(isFieldOperator){
					return generateFieldOperateClause(property,filter.getFieldOperator(),ctx)+ " < " + param(ctx, value);
				}
				return ctx.getPathAlias(property) + " < " + param(ctx, value);
			case IFilter.OP_GREATER_OR_EQUAL:
				if(isFieldOperator){
					return generateFieldOperateClause(property,filter.getFieldOperator(),ctx)+ " >= " + param(ctx, value);
				}
				return ctx.getPathAlias(property) + " >= " + param(ctx, value);
			case IFilter.OP_LESS_OR_EQUAL:
				if(isFieldOperator){
					return generateFieldOperateClause(property,filter.getFieldOperator(),ctx)+ " <= " + param(ctx, value);
				}
				return ctx.getPathAlias(property) + " <= " + param(ctx, value);
			case IFilter.OP_LIKE:
				if(isFieldOperator){
					return generateFieldOperateClause(property,filter.getFieldOperator(),ctx) + " like " + param(ctx, value.toString());
				}
				return ctx.getPathAlias(property) + " like " + param(ctx, value.toString());
			case IFilter.OP_ILIKE:
				if(isFieldOperator){
					return "lower(" + generateFieldOperateClause(property,filter.getFieldOperator(),ctx) + ") like lower(" + param(ctx, value.toString()) + ")";
				}
				return "lower(" + ctx.getPathAlias(property) + ") like lower(" + param(ctx, value.toString()) + ")";
				
			/* AND OR 处理,递归调用*/	
			case IFilter.OP_AND:
			case IFilter.OP_OR:
				if (!(value instanceof List)) {
					return null;
				}
	
				String op = filter.getOperator() == Filter.OP_AND ? " and " : " or ";
	
				StringBuilder sb = new StringBuilder("(");
				boolean first = true;
				for (Object o : ((List) value)) {
					if (o instanceof IFilter) {
						String filterStr = filterToQL(ctx, (IFilter) o,isFieldOperator);
						if (filterStr != null) {
							if (first) {
								first = false;
							} else {
								sb.append(op);
							}
							sb.append(filterStr);
						}
					}
				}
				if (first){
					return null;
				}
				sb.append(")");
				return sb.toString();
			case IFilter.OP_NOT:
				if (!(value instanceof IFilter)) {
					return null;
				}
				String filterStr = filterToQL(ctx, (IFilter) value,isFieldOperator);
				if (filterStr == null)
					return null;
	
				return "not " + filterStr;
			default:
				return noSupportFilterProcess(ctx, filter);
		}
	}
	
	protected String noSupportFilterProcess(FinderContext ctx, IFilter filter){
		throw new IllegalArgumentException("Filter 操作 ( " + filter.getOperator() + " ) is 无效的.");
	}

	protected String param(FinderContext ctx, Object value) {
		if (value instanceof Class<?>) {
			return ((Class<?>) value).getName();
		}

		if (value instanceof Collection<?>) {
			StringBuilder sb = new StringBuilder();
			boolean first = true;
			for (Object o : (Collection<?>) value) {
				if (first) {
					first = false;
				} else {
					sb.append(",");
				}
				ctx.paramList.add(o);
				sb.append(PLACEHOLDER_PARAM);
				sb.append(Integer.toString(ctx.paramList.size()));
			}
			return sb.toString();
		} else if (value instanceof Object[]) {
			StringBuilder sb = new StringBuilder();
			boolean first = true;
			for (Object o : (Object[]) value) {
				if (first) {
					first = false;
				} else {
					sb.append(",");
				}
				ctx.paramList.add(o);
				sb.append(PLACEHOLDER_PARAM);
				sb.append(Integer.toString(ctx.paramList.size()));
			}
			return sb.toString();
		} else {
			ctx.paramList.add(value);
			return PLACEHOLDER_PARAM + Integer.toString(ctx.paramList.size());
		}
	}
	
}
