/**
 * 文件名：BasicDao.java  
 *  
 * 版本信息：  
 * 日期：2015年1月15日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.basic;

import java.util.List;

import com.vion.core.dao.page.IpagedQuery;
import com.vion.core.dao.page.PagedResult;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年1月15日 上午10:00:55
 */
public interface BasicDao<F extends IBasicFinder> {

	/**
	 * 基础库的查找方法
	 * @param finder 构造查找的主表机器过滤条件 <br>例子：
	 * <code>
	 * BaiscFinder.monitorSiteFinder()
	 *					.where(BasicFilter.and(
	 *							BasicFilter.equal(BasicField.MONITORSITE_NAME, "杜甫路新华路"))
	 *				    ).sort(BasicSort.asc(BasicField.MONITORGROUP_NAME),BasicSort.asc(BasicField.DEVICE_NAME))
	 *  </code>
	 * @param clazz
	 * <li>pojo<br>
	 * 	       如果clazz是pojo类型,基于查询出来的结果,将根据setter方法注入,支持原生形式的匹配和驼峰规则<br>
	 * 	       例如：使用SQL查询出的FinderResult 字段COLUMN_NAME 将匹配setCOLUMN_NAME()和setColumnName()、或者成员变量使用注解
	 *    BasicColumn("COLUMN_NAME");
	 *    <code>BasicField.MONITORSITE_NAME.count().alias("countAlias")</code>字段将取别名 countAlias
	 * <li>map<br>
	 * 	       如果clazz是map类型 HQL 采用ORM映射过的字段作为Map的key,SQL都将采用原生字段名称作为字段map的key<br>
	 *     例如：使用SQL查询出的FinderResult 字段COLUMN_NAME --> map('COLUMN_NAME','${value}')
	 * <li>long,String,int<br>
	 *     如果clazz是基础类型,应用场景使用与查询单一字段的语句<br>
	 *     例如 查询所有学生的名称 List&lt;String&gt;  
	 * <li>BasicAble<br>
	 * 	       实现BasicAble接口,默认实现都在com.vion.core.basic.result包下。<br>
	 *    比如：MonitorSiteAble里面包含了监控点的基础信息。只要自定义数据传输对象中包含了那些实现，就将返回具体的数据。但这些必须是和主表直接关联
	 * @return 结果列表
	 */
	public <T> List<T> find(F finder,Class<T> clazz);
	
	/**
	 * @see #find(BasicFinder, Class)
	 * @param IpagedQuery 分页组件
	 * @return 分页结果列表
	 */
	public <T> PagedResult<T> find(F finder,Class<T> clazz,IpagedQuery pagedQuery);
	
	/**
	 * @see #find(BasicFinder, Class)
	 * @return 返回唯一的结果集,如果结果大于1条将抛出异常.所以慎用此方法。除非比如DB中有唯一约束的字段查找
	 */
	public <T> T findUniqued(F finder,Class<T> clazz);
}
