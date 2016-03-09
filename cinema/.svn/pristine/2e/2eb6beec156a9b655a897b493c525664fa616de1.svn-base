/**
 * 文件名：BasicSecureFinder.java  
 *  
 * 版本信息：  
 * 日期：2015年3月6日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vion.core.basic.IBasicFinder;
import com.vion.core.basic.meta.EntityMetaConfig;
import com.vion.core.dao.finder.Field;
import com.vion.core.dao.finder.IFilter;
import com.vion.core.dao.finder.IGroup;
import com.vion.core.dao.finder.IJoin;
import com.vion.core.dao.finder.Sort;
import com.vion.core.domain.entity.IEntity;
import com.vion.core.exception.NoCriteriaWritingException;
import com.vion.core.security.dao.meta.AccountMetaConfig;
import com.vion.core.security.dao.meta.FuncMetaConfig;
import com.vion.core.security.dao.meta.RoleAssistMetaConfig;
import com.vion.core.security.dao.meta.RoleMetaConfig;
import com.vion.core.security.entity.VtpAccounts;
import com.vion.core.security.entity.VtpFuncSource;
import com.vion.core.security.entity.VtpRoleAssist;
import com.vion.core.security.entity.VtpRoles;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年3月6日 下午1:46:17
 */
public class BasicSecureFinder implements IBasicFinder{

	
	private List<Field> fields = new ArrayList<Field>();
	
	private IFilter filter;
	
	private boolean distinct = false; 
	
	private List<Sort> sorts;
	
	private Class<? extends IEntity> rootClass;
	
	protected EntityMetaConfig entityMetaConfig;
	
	private BasicSecureGroup groupBy;

	private List<IJoin> joins;
	
	/** 是否自动抓取、分页查询中必须设置为false  */
	private boolean isAutoFetch = false;
	
	/**
	 * 自动抓取关联表、查询时自动join table on....。在分页查询时不要用autoFetch
	 * @return
	 */
	public BasicSecureFinder autoFetch(){
		isAutoFetch = true;
		return this;
	}
	
	/**
	 * 不会自动关联表查询join table on....。在分页查询时要用notaAutoFetch默认为不抓取
	 * @return
	 */
	public BasicSecureFinder notAutoFetch(){
		isAutoFetch = false;
		return this;
	}
	
	/**
	 * 获得isAutoFetch 
	 * @return  isAutoFetch isAutoFetch
	 */
	public boolean isAutoFetch() {
		return isAutoFetch;
	}
	
	protected static BasicSecureFinder me(){
		return new BasicSecureFinder();
	}
	
	/**
	 * 相机查找器
	 * @return
	 */
	public static BasicSecureFinder accountFinder(){
		BasicSecureFinder me = me();
		me.from(VtpAccounts.class);
		me.entityMetaConfig = AccountMetaConfig.me();
		return me;
	}
	
	
	/**
	 * 相机查找器
	 * @return
	 */
	public static BasicSecureFinder funcFinder(){
		BasicSecureFinder me = me();
		me.from(VtpFuncSource.class);
		me.entityMetaConfig = FuncMetaConfig.me();
		return me;
	}
	
	/**
	 * 相机查找器
	 * @return
	 */
	public static BasicSecureFinder roleFinder(){
		BasicSecureFinder me = me();
		me.from(VtpRoles.class);
		me.entityMetaConfig = RoleMetaConfig.me();
		return me;
	}
	
	
	public static BasicSecureFinder roleAssistFinder(){
		BasicSecureFinder me = me();
		me.from(VtpRoleAssist.class);
		me.entityMetaConfig = RoleAssistMetaConfig.me();
		return me;
	}
	
	
	
	public BasicSecureFinder groupBy(BasicSecureGroup groupBy){
		this.groupBy = groupBy;
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IMutableFinder#distinct(boolean)*/
	public BasicSecureFinder distinct(boolean distinct) {
		this.distinct = distinct;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IMutableFinder#from(java.lang.Class)*/
	protected BasicSecureFinder from(Class<? extends IEntity> rootClass) {
		this.rootClass = rootClass;
		return this;
	}


	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IMutableFinder#select(com.cybergreatwall.core.dao.finder.Field[])*/
	public BasicSecureFinder select(BasicSecureField... fields) {
		for (BasicSecureField field : fields) {
			String property = getProperty(field);
			Field realField = new Field(property, field.getOperator());
			realField.alias(field.getAlias());
			this.fields.add(realField);
		}
		return this;
	}
	
	
	public String getProperty(BasicSecureField field){
		String property = field.getProperty();
		if (property == null) {
			return null;
		}
		String entity = field.getEntity();
		if (!entity.equals(entityMetaConfig.getEntiyClass().getSimpleName())) {
			Map<String, String> joinMappers = entityMetaConfig.getJoinMappers();
			if (joinMappers  != null) {
				String fieldName = joinMappers.get(entity);
				if (fieldName == null) {
					throw new NoCriteriaWritingException("属性【%s】不能直接关联实体【%s】",property,entityMetaConfig.getEntiyClass().getSimpleName());
				}
				property = fieldName + "." + property;
			}
		}
		return property;
	}

	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IMutableFinder#sort(com.googlecode.genericdao.search.Sort[])*/
	public BasicSecureFinder sort(BasicSecureSort... sorts) {
		if (this.sorts == null) {
			this.sorts = new ArrayList<Sort>();
		}
		for (BasicSecureSort sort : sorts) {
			String property = getProperty(sort.getBasicField());
			sort.setProperty(property);
			this.sorts.add(sort);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see com.cybergreatwall.core.dao.finder.IMutableFinder#where(com.cybergreatwall.core.dao.finder.IFilter)*/
	public BasicSecureFinder where(BasicSecureFilter filter) {
		retypeFilter(filter);
		this.filter = filter;
		return this;
	}
	
	
	public void retypeFilter(BasicSecureFilter filter){
		BasicSecureField basicField = filter.getBasicField();
		if (basicField != null) {
			String property = getProperty(basicField);
			filter.setProperty(property);
		}
		if (filter.getValue() instanceof List) {
			List<?> filters = ((List<?>) (filter.getValue()));
			for (Object basicFilter : filters) {
				if (basicFilter instanceof BasicSecureFilter) {
					retypeFilter((BasicSecureFilter)basicFilter);
				}
			}
		}
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
		if (groupBy != null) {
			List<String> properties = new ArrayList<String>();
			if (groupBy.getFields() != null) {
				for (BasicSecureField field : groupBy.getFields()) {
					properties.add(getProperty(field));
				}
			}
			retypeFilter((BasicSecureFilter)groupBy.getHaving());
			groupBy.setProperties(properties.toArray(new String[]{}));
		}
		return groupBy;
	}
	
	
	/* (non-Javadoc)
	 * @see com.vion.core.dao.finder.IMutableFinder#join(com.vion.core.dao.finder.IJoin[])*/
	public BasicSecureFinder join(IJoin... joins) {
		if (this.joins == null) {
			this.joins = new ArrayList<IJoin>();
		}
		for (IJoin iJoin : joins) {
			this.joins.add(iJoin);
		}
		
		return this;
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.finder.IFinder#getJoins()*/
	@Override
	public List<IJoin> getJoins() {
		return this.joins;
	}
}
