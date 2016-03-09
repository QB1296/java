/**
 * 文件名： BasicDao.java
 *  
 * 版本信息：  
 * 日期：2015年3月5日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.vion.core.dao.IGeneralDAO;
import com.vion.core.domain.entity.IEntity;
import com.vion.core.util.Collections;
import com.vion.core.util.Collections.Convert;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年3月5日 上午10:54:01
 */
public class GenericBoDao{

	@Autowired
	private IGeneralDAO generalDao;
	
	/**
	 * 实体拷贝到值对象
	 * @param entity
	 * @param clazz
	 * @return
	 */
	public <T> T copy(Object entity,Class<T> clazz){		
		if(entity != null){
			T rtn = null;
			try {
				rtn = clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BeanUtils.copyProperties(entity, rtn);
			return rtn;
		}
		return null;
	}
	
	/**
	 * List集合拷贝
	 * @param orginList
	 * @param clazz
	 * @return
	 */
	public <T,K> List<T> copy(List<K> orginList,final Class<T> clazz){
		Convert<T, K> convert = new Convert<T, K>() {
			
			@Override
			public T convert(K orgin) {
				// TODO Auto-generated method stub
				T t = null;
				try {
					t = clazz.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BeanUtils.copyProperties(orgin, t);
				return t;
			}
		};
		return Collections.copyConvertList(orginList, convert);
	}
	
	/**
	 * 值对象拷贝到实体
	 * @param vo
	 * @param enClazz
	 * @return
	 */
	public IEntity copyToEntity(Object vo,Class<? extends IEntity> clazz){		
		if(vo != null){
			IEntity entity = null;
			try {
				entity = clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BeanUtils.copyProperties(vo, entity);
			return entity;
		}
		return null;
	}
	
	/**
	 * 保存
	 * @param vo
	 * @param clazz
	 * @return
	 */
	public Serializable save(Object vo,Class<? extends IEntity> clazz){
		Assert.notNull(vo);
		Assert.notNull(clazz);
		return generalDao.save(copyToEntity(vo, clazz));
	}
	
	/**
	 * 删除信息
	 * @param id
	 * @param classType
	 */
	public <T extends IEntity> void removeById(Serializable id, Class<T> classType){
		generalDao.removeById(id, classType);
	}

	/**
	 * 更新信息
	 * @param vo
	 * @param clazz
	 */
	public void update(Object vo,Class<? extends IEntity> clazz){
		Assert.notNull(vo);
		Assert.notNull(clazz);
		generalDao.update(copyToEntity(vo, clazz));
	}
	
	/**
	 * 查询单个对象
	 * @param entity
	 * @param bo
	 * @param id
	 * @return
	 */
	public <T> T findById(Class<? extends IEntity> entity,Class<T> bo,Serializable id){
		return copy(generalDao.findById(entity, id),bo);
	}
	
	/**
	 * 查询所有
	 * @param entity
	 * @param bo
	 * @return
	 */
	public <T> List<T> findAll(Class<? extends IEntity> entity,Class<T> bo){
		List<? extends IEntity> findAll = generalDao.findAll(entity);
		List<T> objs = null;
		if(findAll != null){
			objs = new ArrayList<T>();
			BeanUtils.copyProperties(findAll, objs);	
		}		
		return objs;
	}
}
