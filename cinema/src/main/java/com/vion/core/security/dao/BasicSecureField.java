/**
 * 文件名：BasicSecureField.java  
 *  
 * 版本信息：  
 * 日期：2015年3月6日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.dao;

import com.vion.core.dao.finder.Field;
import com.vion.core.security.dao.meta.AccountMetaConfig;
import com.vion.core.security.dao.meta.FuncMetaConfig;
import com.vion.core.security.dao.meta.RoleAssistMetaConfig;
import com.vion.core.security.dao.meta.RoleMetaConfig;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年3月6日 下午2:26:08
 */
public class BasicSecureField extends Field{
	
	/**   */
	private static final long serialVersionUID = 1L;
	
	private String entity;
	
	/** 账户名称  */
	public final static BasicSecureField ACCOUNT_ID = new BasicSecureField("identify",AccountMetaConfig.me().getEntityName());
	
	/** 账户名称  */
	public final static BasicSecureField ACCOUNT_NAME = new BasicSecureField("name",AccountMetaConfig.me().getEntityName());
	
	/** 账户登陆名 */
	public final static BasicSecureField ACCOUNT_LOGINNAME = new BasicSecureField("loginName",AccountMetaConfig.me().getEntityName());
	
	/** 账户系统 */
	public final static BasicSecureField ACCOUNT_SYSTEM = new BasicSecureField("system",AccountMetaConfig.me().getEntityName());
	
	/** 账户是否有效 */
	public final static BasicSecureField ACCOUNT_ISVALID = new BasicSecureField("isValid",AccountMetaConfig.me().getEntityName());
	
	/** 角色ID */
	public final static BasicSecureField ROLE_ID = new BasicSecureField("id",RoleMetaConfig.me().getEntityName());
	
	/** 角色名称 */
	public final static BasicSecureField ROLE_NAME = new BasicSecureField("name",RoleMetaConfig.me().getEntityName());
	
	/** 角色所属系统 */
	public final static BasicSecureField ROLE_SYSTEM = new BasicSecureField("system",RoleMetaConfig.me().getEntityName());
	
	/** 角色是否缺省 */
	public final static BasicSecureField ROLE_ISDEFAULT = new BasicSecureField("isDefault",RoleMetaConfig.me().getEntityName());
	
	/** 角色描述 */
	public final static BasicSecureField ROLE_DESCRIPTION = new BasicSecureField("description",RoleMetaConfig.me().getEntityName());
	
	/** 功能资源ID */
	public final static BasicSecureField FUNC_ID = new BasicSecureField("id",FuncMetaConfig.me().getEntityName());
	
	/** 功能资源CODE */
	public final static BasicSecureField FUNC_CODE = new BasicSecureField("code",FuncMetaConfig.me().getEntityName());
	
	/** 功能资源名称 */
	public final static BasicSecureField FUNC_NAME = new BasicSecureField("name",FuncMetaConfig.me().getEntityName());
	
	/** 功能资源PID */
	public final static BasicSecureField FUNC_PID = new BasicSecureField("pid",FuncMetaConfig.me().getEntityName());
	
	/** 功能资源类型 */
	public final static BasicSecureField FUNC_TYPE = new BasicSecureField("type",FuncMetaConfig.me().getEntityName());
	
	/** 功能资源URL */
	public final static BasicSecureField FUNC_URL = new BasicSecureField("url",FuncMetaConfig.me().getEntityName());
	
	/** 功能资源所属系统 */
	public final static BasicSecureField FUNC_SYSTEM = new BasicSecureField("system",FuncMetaConfig.me().getEntityName());
	
	/** 账户组ID */
	public final static BasicSecureField GROUP_ID = new BasicSecureField("id",RoleMetaConfig.me().getEntityName());
	
	/** 账户组名称 */
	public final static BasicSecureField GROUP_NAME = new BasicSecureField("name",RoleMetaConfig.me().getEntityName());
	
	/** 账户组所属系统 */
	public final static BasicSecureField GROUP_SYSTEM = new BasicSecureField("system",RoleMetaConfig.me().getEntityName());
	
	/** 系统ID */
	public final static BasicSecureField SYSTEM_ID = new BasicSecureField("id",RoleMetaConfig.me().getEntityName());
	
	/** 系统名称 */
	public final static BasicSecureField SYSTEM_NAME = new BasicSecureField("name",RoleMetaConfig.me().getEntityName());
	
	/** 所属系统编号 */
	public final static BasicSecureField SYSTEM_CODE = new BasicSecureField("code",RoleMetaConfig.me().getEntityName());
	
	/** 所属系统访问路径 */
	public final static BasicSecureField SYSTEM_URL = new BasicSecureField("url",RoleMetaConfig.me().getEntityName());
	
	
	/** 所属系统访问路径 */
	public final static BasicSecureField ROLE_ASSIST_ROLE_ID = new BasicSecureField("roleId",RoleAssistMetaConfig.me().getEntityName());
	public final static BasicSecureField ROLE_ASSIST_RESOURCE_ID = new BasicSecureField("dataResourceId",RoleAssistMetaConfig.me().getEntityName());
	public final static BasicSecureField ROLE_ASSIST_RESOURCE_CODE = new BasicSecureField("resourceCode",RoleAssistMetaConfig.me().getEntityName());
	
	
	public BasicSecureField(String property, Integer operator,String entity) {
		super(property,operator);
		this.entity = entity;
	}
	
	public BasicSecureField(String property,String entity) {
		super(property);
		this.entity = entity;
	}
	
	
	/**
	 * {@link #entity}
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * {@link #entity}	
	 * @param entity the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	public BasicSecureField count(){
		BasicSecureField BasicSecureField = new BasicSecureField(this.getProperty(), this.getEntity());
		BasicSecureField.setOperator(OP_COUNT);
		return BasicSecureField;
	}
	
	public BasicSecureField max(){
		BasicSecureField BasicSecureField = new BasicSecureField(this.getProperty(), this.getEntity());
		BasicSecureField.setOperator(OP_MAX);
		return BasicSecureField;
	}
	
	public BasicSecureField min(){
		BasicSecureField BasicSecureField = new BasicSecureField(this.getProperty(), this.getEntity());
		BasicSecureField.setOperator(OP_MIN);
		return BasicSecureField;
	}
	
	public BasicSecureField sum(){
		BasicSecureField BasicSecureField = new BasicSecureField(this.getProperty(), this.getEntity());
		BasicSecureField.setOperator(OP_SUM);
		return BasicSecureField;
	}
	
	public BasicSecureField avg(){
		BasicSecureField BasicSecureField = new BasicSecureField(this.getProperty(), this.getEntity());
		BasicSecureField.setOperator(OP_AVG);
		return BasicSecureField;
	}
	
	public BasicSecureField countDistinct(){
		BasicSecureField BasicSecureField = new BasicSecureField(this.getProperty(), this.getEntity());
		BasicSecureField.setOperator(OP_COUNT_DISTINCT);
		return BasicSecureField;
	}
	
	public BasicSecureField alias(String alias){
		super.alias(alias);
		return this;
	}
}
