/**
 * 文件名：AliasConfig.java  
 *  
 * 版本信息：  
 * 日期：2014年8月11日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.meta;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>功能描述</b> <br>
 * 别名配置信息
 * @author YUJB
 * @date 2014年8月11日 上午9:28:14
 */
public class AliasConfig {
	
	/** 所有实体的别名列表  */
	List<AliasConfigEntry> aliasConfigs;
	
	/** root 实体  */
	private String rootEntity;

	public List<AliasConfigEntry> getAliasConfigs() {
		return aliasConfigs;
	}

	public void setAliasConfigs(List<AliasConfigEntry> aliasConfigs) {
		this.aliasConfigs = aliasConfigs;
	}
	
	
	public void addConfigEntry(AliasConfigEntry entry){
		if (aliasConfigs == null) {
			aliasConfigs = new ArrayList<AliasConfigEntry>();
		}
		aliasConfigs.add(entry);
	}
	
	public String getRootEntity() {
		return rootEntity;
	}

	public void setRootEntity(String rootEntity) {
		this.rootEntity = rootEntity;
	}

	public AliasConfigEntry getAliasConfigEntryByEntity(String entity){
		if (aliasConfigs != null) {
			for (AliasConfigEntry aliasConfig : aliasConfigs) {
				if (aliasConfig.getEntity().equals(entity)) {
					return aliasConfig;
				}
			}
		}
		return null;
	}
	
	public AliasConfigEntry getAliasConfigEntryByAlias(String alias){
		if (aliasConfigs != null) {
			for (AliasConfigEntry aliasConfig : aliasConfigs) {
				if (aliasConfig.getAlias().equals(alias)) {
					return aliasConfig;
				}
			}
		}
		return null;
	}
	
	
	public AliasConfigEntry getRootEntityAliasConfig(){
		if (aliasConfigs != null) {
			for (AliasConfigEntry aliasConfig : aliasConfigs) {
				if (aliasConfig.getEntity().indexOf(".") < 0) {
					return aliasConfig;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * <b>功能描述</b> <br>
	 * 别名Entry,包含实体和别名
	 * @author YUJB
	 * @date 2015年3月11日 上午11:00:38
	 */
	public class AliasConfigEntry{
		
		/** 实体名称 */
		private String entity;
		
		/** 别名  */
		private String alias;

		public String getEntity() {
			return entity;
		}

		public void setEntity(String entity) {
			this.entity = entity;
		}

		public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}
		
		
	}
	
	
}
