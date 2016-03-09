/**
 * 文件名：IMutableFinder.java  
 *  
 * 版本信息：  
 * 日期：2013-6-5  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao.finder;

import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014-6-5 下午12:54:15
 */
public interface IMutableFinder extends IFinder{
	
	 public Finder where(IFilter filter);
	
	 public Finder from(Class<? extends IEntity> rootClass);
	 
	 public Finder distinct(boolean distinct);
		
	 public Finder sort(Sort... sorts);
	 
	 public Finder select(Field... field);
	 
	 public Finder groupBy(Group groupBy);
	 
	 public Finder join(IJoin... joins);
	 
}
