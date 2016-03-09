/**
 * 文件名：FinderContext.java  
 *  
 * 版本信息：  
 * 日期：2013-6-6  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vion.core.dao.finder.IJoin;
import com.vion.core.dao.finder.IJoin.JoinType;

/**
 * <b>功能描述</b> <br>
 * 查询上下文信息,维护了别名,查询参数值等基础信息
 * @author YUJB
 * @date 2014-6-6 下午01:46:25
 */
public class FinderContext {
	
	/** rootClass 主表实体类  */
	Class<?> rootClass;
	
	/** path 即为 实体类名称 或 实体类中关联表的类名称*/
	Map<String, AliasNode> aliases = new HashMap<String, AliasNode>();
	
	/** 查询参数值列表  */
	List<Object> paramList = new ArrayList<Object>();
	
	/** 主表的别名  */
	public final String rootAlias = "_vion_t";
	
	/** 主表path 为null 主表字段不用增加类名称限制 <br> 例如： TestEntity.a 将写成a */
	public final String root_path = "";

	/** 用于子表别名自动生成使用  */
	int nextAliasNum = 1;

	public FinderContext() {
		
	}
	
	/**
	 * @param rootClass
	 */
	public FinderContext(Class<?> rootClass) {
		this(rootClass,null,null);
	}

	/**
	 * @param rootClass
	 * @param rootAlias
	 * @param paramList
	 */
	public FinderContext(Class<?> rootClass, String rootAlias, List<Object> paramList) {
		this.rootClass = rootClass;
		setRootAlias(rootAlias);
		if (paramList != null) {
			this.paramList = paramList;
		}
	}

	/**
	 * @param rootAlias
	 */
	public void setRootAlias(String rootAlias) {
		if (rootAlias == null) {
			rootAlias = this.rootAlias;
		}
		this.aliases.put(root_path, new AliasNode(root_path, rootAlias));
	}

	/**
	 * 得到主表别名
	 * @return
	 */
	public String getRootAlias() {
		return this.aliases.get(root_path).alias;
	}
	
	/**
	 * 得到表别名
	 * @param path 表实体类名称
	 * @return 别名,如果没有返回<code>null</code>
	 */
	public String getAlias(String path) {
		if (this.aliases.get(path)!= null) {
			return this.aliases.get(path).alias;
		}
		return null;
	}
	
	
	
	/**
	 * 得到HQL语句显示的名称<br>
	 * 例如：
	 * {@link com.cybergreatwall.core.dao.finder.Filter} 构造如下   field:a,b,sub_table.sa rootClass:table<br>   
	 * <li>table --> _spider_t
	 * <li>a --> _spider_t.a
	 * <li>sub_table --> t1 (自动生成)
	 * <li>sub_table.sa --> t1.sa
	 * @param path "表限制" + "." + "属性名称",主表可直接写 "属性名称"
	 * @return 转换后格式，用于HQL拼装
	 */
	protected String getPathAlias(String path) { 
		return getPathAlias(path, null);
	}
	
	protected void addAliasNode(List<IJoin> joins){
		if (joins == null) {
			return;
		}
		for (IJoin iJoin : joins) {
			String joinTable = iJoin.getJoinTable();
			JoinType joinType = iJoin.getJoinType();
			boolean fetch = iJoin.isFetch();
			addAliasNode(joinTable,null,joinType,fetch);
		}
	}
	
	protected void addAliasNode(String path,String parentPath,JoinType joinType,boolean isFetch) { 
		if (parentPath == null) {
			parentPath = root_path;
		}
		if (path == null || "".equals(path)) {
			return;
		}
		String realPath = path;
		String property = path;
		int index = path.indexOf(".");
		if (index > 0) {
			property = path.substring(index + 1, path.length());
			realPath = path.substring(0, index);
		}
		String realAlias = "t_" + nextAliasNum++;
		AliasNode aliasNode = new AliasNode(realPath, realAlias,joinType,isFetch);
		this.aliases.get(parentPath).addChild(aliasNode);
		this.aliases.put(realPath, aliasNode);
		if (property != realPath) {
			addAliasNode(property,realPath, joinType, isFetch);
		}
	}
	
	
	protected String getPathAlias(String path,String parentPath) { 
		if (parentPath == null) {
			parentPath = root_path;
		}
		if (path == null || "".equals(path)) {
			return getRootAlias();
		}
		int index = path.indexOf(".");
		if (index > 0) {
			String property = path.substring(index + 1, path.length());
			String realPath = path.substring(0, index);
			
			String alias = getAlias(realPath);
			if (alias == null) {
				String realAlias = "t_" + nextAliasNum++;
				AliasNode aliasNode = new AliasNode(realPath, realAlias );
				this.aliases.get(parentPath).addChild(aliasNode);
				this.aliases.put(realPath, aliasNode);
				if (property.indexOf(".") > 0) {
					return getPathAlias(property,realPath);
				}
				return aliasNode.alias + "." + property;
			}else {
				if (property.indexOf(".") > 0) {
					return getPathAlias(property,realPath);
				}
				return alias + "." + property;
			}
		}

		return rootAlias + "." + path;
	}
	
	
	
	/**
	 * <b>功能描述</b> <br>
	 * 别名Node,用于维护表别名,同时维护了别名的主、子关系。便于拼装多表关联查询。
	 * @author YUJB
	 * @date 2013-6-7 下午02:25:23
	 */
	public static final class AliasNode {
		
		/** 对应ORM中实体类在主表中的Field名称  */
		String property;
		
		/** 别名  */
		String alias;
		
		/** 父节点  */
		AliasNode parent;
		
		JoinType joinType;
		
		boolean isFetch = false;
		
		/** 子节点列表  */
		List<AliasNode> children = new ArrayList<AliasNode>();

		AliasNode(String property, String alias,JoinType joinType,boolean isFetch) {
			this.property = property;
			this.alias = alias;
			this.joinType = joinType;
			this.isFetch = isFetch;
		}
		
		AliasNode(String property, String alias) {
			this.property = property;
			this.alias = alias;
			this.joinType = JoinType.left;
		}

		/**
		 * 增加子节点
		 * @param node
		 */
		void addChild(AliasNode node) {
			children.add(node);
			node.parent = this;
		}
		
		/**
		 * {@link #joinType}
		 * @return the joinType
		 */
		public JoinType getJoinType() {
			return joinType;
		}

		public String getFullPath() {
			if (parent == null)
				return "";
			else if (parent.parent == null)
				return property;
			else
				return parent.getFullPath() + "." + property;
		}
	}
}
