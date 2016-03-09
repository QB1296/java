/**
 * 文件名：AliasProcessor.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vion.core.SystemContext;
import com.vion.core.exception.NoCriteriaWritingException;
import com.vion.core.security.meta.AliasConfig;
import com.vion.core.security.meta.AliasConfig.AliasConfigEntry;
import com.vion.core.util.Strings;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 下午2:59:49
 */
public class AliasProcessor {
	
	/** 日志  */
	private transient static Logger logger = LoggerFactory.getLogger(AliasProcessor.class);
	
	private AliasConfig aliasConfig;

	private static final Pattern PATTERN = Pattern.compile("\\$\\{(.*?)\\}");
	
	private static final String PLACEHOLDER_PREFIX = "${";
	
	private static final String PLACEHOLDER_SUFFIX = "}";
	
	private static final String CONNHOLDER_DOT = ".";
	
	private List<SQLProcessor> sqlProcessors = new ArrayList<SQLProcessor>();
	
	public AliasProcessor(AliasConfig aliasConfig) {
		super();
		this.aliasConfig = aliasConfig;
		Map<String, SQLProcessor> beansOfType = SystemContext.getApplicationContext().getBeansOfType(SQLProcessor.class);
		if (beansOfType != null && beansOfType.size() > 0) {
			sqlProcessors.addAll(beansOfType.values());
		}
	}
	
	/**
	 * 替换SQL语句中的占位符
	 * @param sql
	 * @return
	 */
	public String replacePlaceholderField(Object value,String sql,Map<String, String> keyMapper){
		String realSql = replace$Field(value, sql, keyMapper);
		realSql = replaceCustomField(value,realSql);
		realSql = replace$Field(value, realSql, keyMapper);
		return realSql;
	}
	
	
	public String replace$Field(Object value,String sql,Map<String, String> keyMapper){
		if (Strings.isEmpty(sql)) {
			return sql;
		}
		StringBuffer sb = new StringBuffer();
		Matcher matcher = PATTERN.matcher(sql);
		boolean isMatcher = false;
		
		while (matcher.find()) {
			isMatcher = true;
			String match = matcher.group();
			String replaceName = match.substring(PLACEHOLDER_PREFIX.length(), match.length()-PLACEHOLDER_SUFFIX.length());
			String realReplaceName = Strings.trim(replaceName);
			matcher.appendReplacement(sb,getRealField(realReplaceName,keyMapper));
		}
		if (!isMatcher) {
			sb.append(sql);
		}else {
			matcher.appendTail(sb);
		}
		return sb.toString();
	}
	
	
	public String replaceCustomField(Object value,String sql){
		return replaceCurrentField(value,sql.toString());
	}
	
	
	/**
	 * 替换SQL语句中的占位符
	 * @param sql
	 * @return
	 */
	public String replaceCurrentField(Object value,String sql){
		String retSQL = sql;
		for (SQLProcessor processor : sqlProcessors) {
			retSQL = processor.processorSQL(value, retSQL);
		}
		return retSQL;
	}
	
	/**
	 * 替换SQL语句中的占位符
	 * @param sql
	 * @return
	 */
	public String replacePlaceholderField(String sql){
		return replacePlaceholderField(null,sql,null);
	}
	
	
	/**
	 * 得到别名标识的field
	 * @param field
	 * @return
	 */
	public String getRealField(String field,Map<String, String> keyMapper){
		String cleanField = field;
		String alias = null;
		if (aliasConfig.getRootEntity() != null || aliasConfig.getAliasConfigs() != null) {
			alias = aliasConfig.getAliasConfigEntryByEntity(aliasConfig.getRootEntity()).getAlias();
		}
		if (field.indexOf(CONNHOLDER_DOT) != 0) {
			String[] split = field.split("\\" + CONNHOLDER_DOT);
			if (split.length == 2) {
				String realEntity = split[0];
				if (keyMapper != null && keyMapper.size() > 0) {
					String tempEntity = keyMapper.get(realEntity);
					if (tempEntity != null) {
						realEntity = tempEntity;
					}else {
						throw new NoCriteriaWritingException("SQL片段 【" + field  + "】没有找到对应的实体Entity code(别名),是否为【" + keyMapper.keySet() + "】");
					}
				}
				AliasConfigEntry aliasConfigEntry = aliasConfig.getAliasConfigEntryByEntity(realEntity);
				if (aliasConfigEntry == null) {
					alias = realEntity + "_alias";
				}else {
					alias = aliasConfig.getAliasConfigEntryByEntity(realEntity).getAlias();
				}
				cleanField = split[1];
			}else {
				logger.info( "字段{}中应该包含一个[.]逗号标识 tableAlias.field",new String[]{field});
			}
		}
		if (alias == null) {
			return field;
		}
		return alias + CONNHOLDER_DOT + cleanField;
	}

	
}
