package com.vion.core.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import com.vion.core.dao.finder.IFinder;



/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014-6-14 下午1:10:06
 * @param <T>
 * @param <ID>
 */
public interface IGenericDAO <T, ID extends Serializable>{
	
	 public T findById(ID id); 
	 
	 public T[] findById(@SuppressWarnings("unchecked") ID... ids);
	 
	 public void save(T entity);
	 
	 public T[] saveOrUpdate(T entity);
	 
	 public boolean remove(T entity);
	 
	 public boolean batchRemove(T[] entity);
	 
	 public boolean removeById(ID id);
	 
	 public List<T> findAll();
	 
	 public FinderResult search(IFinder search);
	 
	 /**
	 * 返回数据库连接，connection为代理对象无需使用者关闭连接 
	 * @return
	 */
	 public Connection getConnection();
	 
}
