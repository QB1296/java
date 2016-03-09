package com.vion.core.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.vion.core.dao.finder.IFinder;
import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 * 常规数据访问类,包含基础的CRUD操作
 * @author YUJB
 * @date 2014-6-14 上午10:43:43
 */
public interface IGeneralDAO {

	/**
	 * 通过持久化标识符的值查找实体对象
	 * 
	 * @param <T>
	 * @param classType
	 *            返回是实体类型
	 * @param id
	 *            持久化标识符
	 * @return 实体对象, 如果没有相应记录则返回<code>null</code>
	 */
	public <T extends IEntity> T findById(Class<T> classType, Serializable id);

	/**
	 * 保存实体,如果内存中存在相同数据,执行update方法
	 * @param <T>
	 * @param entity 待保存的实体
	 */
	public <T extends IEntity> Serializable save(T entity);
	
	
	/**
	 * 批量执行保存实体,如果内存中存在相同数据,执行update方法
	 * entity 20条批量提交,同时释放实体缓存
	 * @param <T>
	 * @param entitys 待保存的实体列表
	 */
	public <T extends IEntity> void batchSave(T[] entitys);
	
	/**
	 * 批量执行更改实体
	 * entity 20条批量提交,同时释放实体缓存
	 * @param <T>
	 * @param entitys 待保存的实体列表
	 */
	public <T extends IEntity> void batchUpdate(T[] entitys);
	
	/**
	 * 批量执行更改实体
	 * entity 20条批量提交,同时释放实体缓存
	 * @param <T>
	 * @param entitys 待保存的实体列表
	 */
	public <T extends IEntity> void batchMerge(T[] entitys);
	
	/**
	 * 修改实体,如果在ORM框架中有缓存存在,merge内存中数据执行修改
	 * @param <T>
	 * @param entity 待修改的实体
	 */
	public <T extends IEntity> void update(T entity);
	
	/**
	 * 修改实体,如果在ORM框架中有缓存存在,merge内存中数据执行修改
	 * @param <T>
	 * @param entity 待修改的实体
	 */
	public <T extends IEntity> void merge(T entity);
	
	/**
	 * 保存或更新实体记录 
	 * <li>添加：<br>
	 * 实体中有持久化标识符，并且值在DB中有相应记录</li> 
	 * <li>保存：<br>
	 * 实体中没有持久化标识符，或标识符的值在DB中没有相应记录</li>
	 * @param <T>
	 * @param entity 待操作的实体对象
	 * @return
	 */
	public <T extends IEntity> void saveOrUpdate(T entity);

	/**
	 * 删除实体对象
	 * @param <T>
	 * @param entity 待删除的实体对象
	 * @return
	 */
	public <T extends IEntity> void remove(T entity);

	/**
	 * 批量删除
	 * 
	 * @param <T>
	 * @param entitys 实体数组
	 * @return
	 */
	public <T extends IEntity> void batchRemove(T[] entitys);

	/**
	 * 通过ID删除记录 
	 * @param id 待删除记录的持久化标识的值
	 * @param classType
	 * @return
	 */
	public <T extends IEntity> void removeById(Serializable id, Class<T> classType);

	/**
	 * 查找所有记录
	 * 
	 * @param <T>
	 * @param classType
	 * @return
	 */
	public <T extends IEntity> List<T> findAll(Class<T> classType);
	
	
	
	
	/**
	 * 执行HQL语句,例如update、delete语句
	 * @param hql
	 */
	public void executeHQL(String hql);
	
	
	/**
	 * 执行HQL语句,准许使用参数占位符
	 * @param hql
	 * @param parameterVlaues
	 */
	public void executeHQL(String hql, Object[] parameterVlaues);
	
	/**
	 * 
	 * @param hql
	 */
	public void executeSQL(String sql);
	
	/**
	 * 执行SQL语句,例如update、delete语句
	 * @param sql
	 * @param parameterVlaues
	 */
	public void executeSQL(String sql, Object[] parameterVlaues);

	/**
	 * 
	 * @param sql
	 * @return
	 */
	public FinderResult findBySQL(String sql);
	
	
	
	/**
	 * 
	 * @param qlType 查询语言类型
	 * @param vql 查询表达式
	 * @return
	 */
	public FinderResult findByVQL(String qlType, String vql);
	
	
	/**
	 *  通过HQLkey执行HQL语句,例如update、delete语句
	 * @param HQL
	 */
	public void excuteNamedHQL(String HQL);
	
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public FinderResult findBySQL(String sql,String[] parameterNames,Object[] parameterVlaues);
	
	
	
	/**
	 * 通过SQL查找
	 * @param sql sql语句
	 * @param parameterVlaues 参数值
	 * @return
	 */
	public FinderResult findBySQL(String sql,Object[] parameterVlaues);
	
	
	/**
	 * 使用ORM查询语句查询数据
	 * @param  java 持久化查询语言
	 * @return 查询结果集对象
	 * @see FinderResult
	 */
	public FinderResult findByJPQL(String jpql,String[] parameterNames,Object[] parameterVlaues);
	
	
	/**
	 * 使用ORM查询语句查询数据
	 * @param  java 持久化查询语言
	 * @return 查询结果集对象
	 * @see FinderResult
	 */
	public FinderResult findByJPQL(String jpql,Object[] parameterVlaues);
	
	
	
	/**
	 * 使用ORM查询语句查询数据
	 * @param  java 持久化查询语言
	 * @return 查询结果集对象
	 * @see FinderResult
	 */
	public FinderResult findByJPQL(String jpql);
	

	/**
	 * 通过给定的finder,得到FinderResult
	 * @param finder 查询finder
	 * @return 查询结果集对象
	 * @see FinderResult
	 * @see IFinder
	 */
	public FinderResult find(IFinder finder);
	
	
	
	/**
	 * 
	 * @param namedjpql
	 * @return
	 */
	public FinderResult findByNamedJPQL(String namedjpql);
	
	
	/**
	 * 
	 * @param namedjpql
	 * @return
	 */
	public FinderResult findByNamedJPQL(String namedjpql,String[] parameterNames,Object[] values);
	
	
	/**
	 * 命名查找   在config/query下xml配置或在实体中指定namedQuery
	 * @param namedjpql
	 * @return
	 */
	public FinderResult findByNamedJPQL(String namedjpql,Object[] values);
	
	
	/**
	 * 使entity转换成游离态
	 * @param <T>
	 * @param entity
	 */
	public <T extends IEntity> void evict(T entity);

	/**
	 * 返回数据库连接，connection为代理对象无需使用者关闭连接
	 * 
	 * @return
	 * @throws SQLException 
	 */
	public Connection getConnection() throws SQLException;

	

}
