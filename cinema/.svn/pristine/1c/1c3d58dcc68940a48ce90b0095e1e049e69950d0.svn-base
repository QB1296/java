/**
 * 文件名：EntityMetaConfig.java  
 *  
 * 版本信息：  
 * 日期：2014年12月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.basic.meta;

import java.util.Map;


/**
 * <b>功能描述</b> <br>
 * 实体元数据信息,维护实体的名称、类型、可关联关系
 * @author YUJB
 * @date 2014年12月30日 下午1:46:58
 */
public interface EntityMetaConfig {
	
	/**
	 * Tramp : 把entity写成entiy了，大家知道就好，牵扯太多不改动了 ；2015年5月15日
	 * 得到实体类
	 * @return
	 */
	public Class<?> getEntiyClass();
	
	/**
	 * 得到实体名称
	 * @return
	 */
	public String getEntityName();
	
	/**
	 * 得到可关联的表映射
	 * @return
	 */
	public Map<String, String> getJoinMappers();
	
	
}
