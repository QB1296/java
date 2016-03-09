/**
 * 文件名：CompositeGenrealDao.java  
 *  
 * 版本信息：  
 * 日期：2015年4月16日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.vion.core.dao.finder.IFinder;
import com.vion.core.domain.entity.IEntity;
import com.vion.core.exception.NoCriteriaWritingException;
import com.vion.core.exception.NoSupportedException;

/**
 * <b>功能描述</b> <br>
 * 复合通用Dao 集成rdb和其他数据源操作 {@link #rdbGeneralDao}指定关系数据库 、
 * {@link #customQLGeneralDaos}指定其他查询语言,目前只实现了ES搜索引擎的支持
 * @author YUJB
 * @date 2015年4月16日 下午1:52:42
 */
public class CompositeGenrealDao implements IGeneralDAO{
	
	private IGeneralDAO rdbGeneralDao;
	
	private CustomVQLGeneralDao[] customQLGeneralDaos; 

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findById(java.lang.Class, java.io.Serializable)*/
	@Override
	public <T extends IEntity> T findById(Class<T> classType, Serializable id) {
		return rdbGeneralDao.findById(classType, id);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#save(com.vion.core.domain.entity.IEntity)*/
	@Override
	public <T extends IEntity> Serializable save(T entity) {
		return rdbGeneralDao.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#batchSave(com.vion.core.domain.entity.IEntity[])*/
	@Override
	public <T extends IEntity> void batchSave(T[] entitys) {
		rdbGeneralDao.batchSave(entitys);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#update(com.vion.core.domain.entity.IEntity)*/
	@Override
	public <T extends IEntity> void update(T entity) {
		rdbGeneralDao.update(entity);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#saveOrUpdate(com.vion.core.domain.entity.IEntity)*/
	@Override
	public <T extends IEntity> void saveOrUpdate(T entity) {
		rdbGeneralDao.saveOrUpdate(entity);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#remove(com.vion.core.domain.entity.IEntity)*/
	@Override
	public <T extends IEntity> void remove(T entity) {
		rdbGeneralDao.remove(entity);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#batchRemove(com.vion.core.domain.entity.IEntity[])*/
	@Override
	public <T extends IEntity> void batchRemove(T[] entitys) {
		rdbGeneralDao.batchRemove(entitys);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#removeById(java.io.Serializable, java.lang.Class)*/
	@Override
	public <T extends IEntity> void removeById(Serializable id,
			Class<T> classType) {
		rdbGeneralDao.removeById(id, classType);
		
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findAll(java.lang.Class)*/
	@Override
	public <T extends IEntity> List<T> findAll(Class<T> classType) {
		return rdbGeneralDao.findAll(classType);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#executeHQL(java.lang.String)*/
	@Override
	public void executeHQL(String hql) {
		rdbGeneralDao.executeHQL(hql);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#executeHQL(java.lang.String, java.lang.Object[])*/
	@Override
	public void executeHQL(String hql, Object[] parameterVlaues) {
		rdbGeneralDao.executeHQL(hql, parameterVlaues);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#executeSQL(java.lang.String)*/
	@Override
	public void executeSQL(String sql) {
		rdbGeneralDao.executeSQL(sql);
		
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findBySQL(java.lang.String)*/
	@Override
	public FinderResult findBySQL(String sql) {
		return rdbGeneralDao.findBySQL(sql);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findByVQL(java.lang.String, java.lang.String)*/
	@Override
	public FinderResult findByVQL(String qlType, String vql) {
		if (customQLGeneralDaos != null) {
			for (CustomVQLGeneralDao vqlDao : customQLGeneralDaos) {
				if (vqlDao.isSupportQLType(qlType)) {
					return vqlDao.findByVQL(vql);
				}
			}
			throw new NoCriteriaWritingException("没有任何自定义Dao支持【%s】",qlType);
		}else {
			throw new NoSupportedException("没有任何自定义QL dao！");
		}
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#excuteNamedHQL(java.lang.String)*/
	@Override
	public void excuteNamedHQL(String HQL) {
		rdbGeneralDao.excuteNamedHQL(HQL);
		
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findBySQL(java.lang.String, java.lang.String[], java.lang.Object[])*/
	@Override
	public FinderResult findBySQL(String sql, String[] parameterNames,
			Object[] parameterVlaues) {
		return rdbGeneralDao.findBySQL(sql, parameterNames, parameterVlaues);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findBySQL(java.lang.String, java.lang.Object[])*/
	@Override
	public FinderResult findBySQL(String sql, Object[] parameterVlaues) {
		return rdbGeneralDao.findBySQL(sql, parameterVlaues);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findByJPQL(java.lang.String, java.lang.String[], java.lang.Object[])*/
	@Override
	public FinderResult findByJPQL(String jpql, String[] parameterNames,
			Object[] parameterVlaues) {
		return rdbGeneralDao.findByJPQL(jpql, parameterNames, parameterVlaues);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findByJPQL(java.lang.String, java.lang.Object[])*/
	@Override
	public FinderResult findByJPQL(String jpql, Object[] parameterVlaues) {
		return rdbGeneralDao.findByJPQL(jpql, parameterVlaues);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findByJPQL(java.lang.String)*/
	@Override
	public FinderResult findByJPQL(String jpql) {
		return rdbGeneralDao.findByJPQL(jpql);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#find(com.vion.core.dao.finder.IFinder)*/
	@Override
	public FinderResult find(IFinder finder) {
		return rdbGeneralDao.find(finder);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findByNamedJPQL(java.lang.String)*/
	@Override
	public FinderResult findByNamedJPQL(String namedjpql) {
		return rdbGeneralDao.findByNamedJPQL(namedjpql);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findByNamedJPQL(java.lang.String, java.lang.String[], java.lang.Object[])*/
	@Override
	public FinderResult findByNamedJPQL(String namedjpql,
			String[] parameterNames, Object[] values) {
		return rdbGeneralDao.findByNamedJPQL(namedjpql, parameterNames, values);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#findByNamedJPQL(java.lang.String, java.lang.Object[])*/
	@Override
	public FinderResult findByNamedJPQL(String namedjpql, Object[] values) {
		return rdbGeneralDao.findByNamedJPQL(namedjpql, values);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#evict(com.vion.core.domain.entity.IEntity)*/
	@Override
	public <T extends IEntity> void evict(T entity) {
		rdbGeneralDao.evict(entity);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#getConnection()*/
	@Override
	public Connection getConnection() throws SQLException {
		return rdbGeneralDao.getConnection();
	}
	
	
	/**
	 * 获得rdbGeneralDao 
	 * @return  rdbGeneralDao rdbGeneralDao
	 */
	public IGeneralDAO getRdbGeneralDao() {
		return rdbGeneralDao;
	}
	
	
	/** 
	 * 设置rdbGeneralDao 
	 * @param rdbGeneralDao rdbGeneralDao 
	 */
	public void setRdbGeneralDao(IGeneralDAO rdbGeneralDao) {
		this.rdbGeneralDao = rdbGeneralDao;
	}
	
	/**
	 * 获得customQLGeneralDaos 
	 * @return  customQLGeneralDaos customQLGeneralDaos
	 */
	public CustomVQLGeneralDao[] getCustomQLGeneralDaos() {
		return customQLGeneralDaos;
	}
	
	/** 
	 * 设置customQLGeneralDaos 
	 * @param customQLGeneralDaos customQLGeneralDaos 
	 */
	public void setCustomQLGeneralDaos(CustomVQLGeneralDao[] customQLGeneralDaos) {
		this.customQLGeneralDaos = customQLGeneralDaos;
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#batchMerge(com.vion.core.domain.entity.IEntity[])*/
	@Override
	public <T extends IEntity> void batchMerge(T[] entitys) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.vion.core.dao.IGeneralDAO#executeSQL(java.lang.String, java.lang.Object[])*/
	@Override
	public void executeSQL(String sql, Object[] parameterVlaues) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends IEntity> void batchUpdate(T[] entitys) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends IEntity> void merge(T entity) {
		// TODO Auto-generated method stub
		
	}
	
}
