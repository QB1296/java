package com.vion.core.dao.finder;

import java.util.List;

import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 * 查询封装对象
 * @author YUJB
 * @date 2014-6-7 下午02:22:06
 */
public interface IFinder {
	
	/**
	 * 得到过滤器
	 * @return
	 */
	IFilter getFilter();
	
	/**
	 * 主表实体类
	 * @return
	 */
	Class<? extends IEntity> getRootClass();
	
	/**
	 * 是否过滤重复数据
	 * @return
	 */
	public boolean isDistinct();
	
	/**
	 * 得到排序信息
	 * @return
	 */
	public List<Sort> getSorts();
	
	/**
	 * 得到查询的字段
	 * @return
	 */
	public List<Field> getFields();
	
	
	public IGroup getGroupBy();
	
	
	public List<IJoin> getJoins();
	
}
