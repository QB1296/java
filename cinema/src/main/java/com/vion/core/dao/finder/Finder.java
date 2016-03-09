/**
 * 文件名：Finder.java  
 *  
 * 版本信息：  
 * 日期：2013-6-5  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao.finder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014-6-5 下午01:15:17
 */
public class Finder implements IMutableFinder{
	
	private List<Field> fields = new ArrayList<Field>();
	
	private IFilter filter;
	
	private boolean distinct = false; 
	
	private List<Sort> sorts;
	
	private Class<? extends IEntity> rootClass;
	
	private Group groupBy;
	
	private List<IJoin> joins;
	
	/**
	 * 初始化函数
	 * @return
	 */
	public static Finder me(){
		return new Finder();
	}
	
	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IMutableFinder#distinct(boolean)*/
	public Finder distinct(boolean distinct) {
		this.distinct = distinct;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IMutableFinder#from(java.lang.Class)*/
	public Finder from(Class<? extends IEntity> rootClass) {
		this.rootClass = rootClass;
		return this;
	}


	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IMutableFinder#select(com.cybergreatwall.core.dao.finder.Field[])*/
	public Finder select(Field... field) {
		fields.addAll(Arrays.asList(field));
		return this;
	}

	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IMutableFinder#sort(com.googlecode.genericdao.search.Sort[])*/
	public Finder sort(Sort... sorts) {
		if (this.sorts == null) {
			this.sorts = new ArrayList<Sort>();
		}
		for (Sort sort : sorts) {
			this.sorts.add(sort);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IMutableFinder#where(com.cybergreatwall.core.dao.finder.IFilter)*/
	public Finder where(IFilter filter) {
		this.filter = filter;
		return this;
	}
	
	
	public Finder groupBy(Group groupBy){
		this.groupBy = groupBy;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IFinder#getFilters()*/
	public IFilter getFilter() {
		return filter;
	}


	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IFinder#getRootClass()*/
	public Class<? extends IEntity> getRootClass() {
		return rootClass;
	}

	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IFinder#getSorts()*/
	public List<Sort> getSorts() {
		return sorts;
	}

	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IFinder#isDistinct()*/
	public boolean isDistinct() {
		return distinct;
	}

	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IFinder#getField()*/
	public List<Field> getFields() {
		return fields;
	}


	/* (non-Javadoc)
	 * @see com.vion.core.dao.finder.IFinder#getGroupBy()*/
	@Override
	public IGroup getGroupBy() {
		return groupBy;
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.finder.IFinder#getJoins()*/
	@Override
	public List<IJoin> getJoins() {
		return this.joins;
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.finder.IMutableFinder#join(com.vion.core.dao.finder.IJoin[])*/
	@Override
	public Finder join(IJoin... joins) {
		if (this.joins == null) {
			this.joins = new ArrayList<IJoin>();
		}
		for (IJoin iJoin : joins) {
			this.joins.add(iJoin);
		}
		
		return this;
	}
	

}
