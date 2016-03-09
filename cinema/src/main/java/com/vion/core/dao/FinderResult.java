package com.vion.core.dao;

import java.util.List;

import com.vion.core.dao.page.IpagedQuery;
import com.vion.core.dao.page.OriginPagedResult;
import com.vion.core.dao.page.PagedResult;



/**
 * <b>功能描述</b> <br>
 * 查询结果集,FinderResult将携带UnitOfWork。生命周期将与UnitOfWork相同。<br>
 * 所以FinderResult不适合跨layer传输。比如,从dao层传输到service层,再到controller层。出非uninOfWork生命周期很长,例如与Thread绑定<br>
 * 
 * 例子：
 * <pre>
 *   FinderResult finderResult = dao.finder(finder);
 *   List&lt;Entity&gt; entitys = finderResult.result(Entity.class);
 * <pre>
 * 传输entitys对象,而不要传输finderResult对象
 * @author YUJB
 * @date 2014-6-7 下午02:21:04
 */
public interface FinderResult {
		
	/**
	 * 得到单一的结果记录
	 * <li>pojo<br>
	 * 	       如果clazz是pojo类型,基于查询出来的结果,将根据setter方法注入,支持原生形式的匹配和驼峰规则<br>
	 * 	       例如：使用SQL查询出的FinderResult 字段COLUMN_NAME 将匹配setCOLUMN_NAME()和setColumnName();
	 * <li>map<br>
	 * 	       如果clazz是map类型 HQL 采用ORM映射过的字段作为Map的key,SQL都将采用原生字段名称作为字段map的key<br>
	 *     例如：使用SQL查询出的FinderResult 字段COLUMN_NAME --> map('COLUMN_NAME','${value}')
	 * <li>long,String,int<br>
	 *     如果clazz是基础类型,应用场景使用与查询单一字段的语句<br>
	 *     例如 查询所有学生的名称 List&lt;String&gt;  
	 * <li>entity<br>
	 * 	       如果clazz是实体类型,交付于ORM框架生成,oneToMany,oneToOne等关系依赖于JPA的相关注解和xml如何修饰    
	 * @param <T> 支持 <code>Map<String,Object></code>,<code>pojo</code>,<code>entity</code>,
	 * <code>long</code>,<code>String</code>,<code>int</code>
	 * @param clazz 结果类型
	 * @return 指定的记录
	 */
	public <T> T  uniqueResult (Class<T> clazz);
	
	
	
	/**
	 * 得到多条的结果记录<br>
	 * <li>pojo<br>
	 * 	       如果clazz是pojo类型,基于查询出来的结果,将根据setter方法注入,支持原生形式的匹配和驼峰规则<br>
	 * 	       例如：使用SQL查询出的FinderResult 字段COLUMN_NAME 将匹配setCOLUMN_NAME()和setColumnName();
	 * <li>map<br>
	 * 	       如果clazz是map类型 HQL 采用ORM映射过的字段作为Map的key,SQL都将采用原生字段名称作为字段map的key<br>
	 *     例如：使用SQL查询出的FinderResult 字段COLUMN_NAME --> map('COLUMN_NAME','${value}')
	 * <li>long,String,int<br>
	 *     如果clazz是基础类型,应用场景使用与查询单一字段的语句<br>
	 *     例如 查询所有学生的名称 List&ltString&gt  
	 * <li>entity<br>
	 * 	       如果clazz是实体类型,交付于ORM框架生成,oneToMany,oneToOne等关系依赖于JPA的相关注解和xml如何修饰    
	 * @param <T> 支持 <code>Map<String,Object></code>,<code>pojo</code>,<code>entity</code>,
	 * <code>int</code>,<code>number</code>
	 * @param clazz 结果类型
	 * @return 指定的记录集
	 */
	public <T> List<T> result(Class<T> clazz);
	
	
	/**
	 * 得到多条的结果记录<br>
	 * <li>pojo<br>
	 * 	       如果clazz是pojo类型,基于查询出来的结果,将根据setter方法注入,支持原生形式的匹配和驼峰规则<br>
	 * 	       例如：使用SQL查询出的FinderResult 字段COLUMN_NAME 将匹配setCOLUMN_NAME()和setColumnName();
	 * <li>map<br>
	 * 	       如果clazz是map类型 HQL 采用ORM映射过的字段作为Map的key,SQL都将采用原生字段名称作为字段map的key<br>
	 *     例如：使用SQL查询出的FinderResult 字段COLUMN_NAME --> map('COLUMN_NAME','${value}')
	 * <li>long,String,int<br>
	 *     如果clazz是基础类型,应用场景使用与查询单一字段的语句<br>
	 *     例如 查询所有学生的名称 List&ltString&gt  
	 * <li>entity<br>
	 * 	       如果clazz是实体类型,交付于ORM框架生成,oneToMany,oneToOne等关系依赖于JPA的相关注解和xml如何修饰    
	 * @param <T> 支持 <code>Map<String,Object></code>,<code>pojo</code>,<code>entity</code>,
	 * <code>int</code>,<code>number</code>
	 * @param clazz 结果类型
	 * @param pageQuery 分页查询条件
	 * @return 指定的记录集包含分页信息
	 */
	public <T> PagedResult<T> result(Class<T> clazz,IpagedQuery pageQuery);

	/**
	 * 得到原始的结果集合,spider不做任何处理。适用于较为复杂的返回结<br>
	 * <li>例如使用hibernate select t,t1 from table1,table2 where t.id = t1.id<br>
	 *     将返回array [table1,table2]。对于这种返回结果结构不太明确的的返回结果，使用此方法得到原生的结果
	 * 
	 * @return
	 */
	public Object originResult();
	
	
	/**
	 * 得到原始的结果集合,spider不做任何处理。适用于较为复杂的返回结<br>
	 * <li>例如使用hibernate select t,t1 from table1,table2 where t.id = t1.id<br>
	 *     将返回array [table1,table2]。对于这种返回结果结构不太明确的的返回结果，使用此方法得到原生的结果
	 * @param pageQuery 分页信息
	 * @return 包含分页信息的查询结构	
	 */
	public OriginPagedResult originResult(IpagedQuery pageQuery);
	
	
	/**
	 * 使用查询缓存
	 */
	public void useQueryCache();
	
	
	/**
	 * 设置查询缓存范围
	 * @param cacheRegion
	 */
	public void setCacheRegion(String cacheRegion);
}
