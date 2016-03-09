/**
\ * 文件名：BasicSecureDao.java  
 *  
 * 版本信息：  
 * 日期：2015年3月5日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.dao;

import java.util.List;

import com.vion.core.basic.BasicDao;
import com.vion.core.security.bo.SecureAccountBo;
import com.vion.core.security.bo.SecureDataBo;
import com.vion.core.security.bo.SecureFuncBo;
import com.vion.core.security.bo.SecureLogonModel;
import com.vion.core.security.bo.SecureRoleBo;
import com.vion.core.security.bo.SecureSystemBo;
import com.vion.core.security.entity.VtpDataResource;
import com.vion.core.security.meta.DataResourceFormBo;

/**
 * <b>功能描述</b> <br>
 * 安全权限系统对外接口
 * @author YUJB
 * @date 2015年3月5日 上午9:53:54
 */
public interface BasicSecureDao extends BasicDao<BasicSecureFinder>{
	
	/**
	 * 指定账户的账户组
	 * @param accountId 账户ID
	 * @param groupIds 账户组列表
	 */
	public void relAccountGroups(String accountId,String... groupIds);
	
	
	/**
	 * 解除与账户组的关联
	 * @param accountId 账户ID
	 * @param groupIds 账户组列表
	 */
	public void unRelAccountGroups(String accountId,String... groupIds);
	
	
	/**
	 * 重新关联账户组,将删除账户关联的账户组,重新指定账户组
	 * @param accountId 账户ID
	 * @param groupIds 账户组列表
	 */
	public void reRelAccountGroups(String accountId,String... groupIds);
	
	
	/**
	 * 指定账户的角色
	 * @param accountId 账户ID
	 * @param roleIds 角色ID列表
	 */
	public void relAccountRoles(String accountId,String... roleIds);
	
	
	/**
	 * 解除与角色的关联
	 * @param accountId 账户ID
	 * @param roleIds 角色ID列表
	 */
	public void unRelAccountRoles(String accountId,String... roleIds);
	
	
	/**
	 * 重新关联角色,将删除账户关联的角色,重新指定角色
	 * @param accountId 账户ID
	 * @param roleIds 角色ID列表
	 */
	public void reRelAccountRoles(String accountId,String... roleIds);
	
	
	/**
	 * 指定角色的功能资源
	 * @param roleId 角色ID
	 * @param funcIds 功能资源ID列表
	 */
	public void relRoleFuncResources(String roleId,String... funcIds);
	
	
	/**
	 * 指定角色的数据资源
	 * @param roleId 角色ID
	 * @param dataIds 数据资源ID列表
	 */
	public void relRoleDataResources(String roleId,String... dataIds);
	
	
	/**
	 * 解除与功能资源的关联
	 * @param roleId 角色ID
	 * @param funcIds 功能资源ID列表
	 */
	public void unRelRoleFuncResources(String roleId,String... funcIds);
	
	
	/**
	 * 解除与数据资源的关联
	 * @param roleId 角色ID
	 * @param dataIds 数据资源ID列表
	 */
	public void unRelRoleDataResources(String roleId,String... dataIds);
	
	
	/**
	 * 重新关联功能资源,将删除角色关联的功能资源,重新指定功能资源
	 * @param roleId 角色ID
	 * @param funcIds 功能资源ID列表
	 */
	public void reRelRoleFuncResources(String roleId,String... funcIds);
	
	
	/**
	 * 重新关联数据资源,将删除角色关联的数据资源,重新指定数据资源
	 * @param roleId 角色ID
	 * @param dataIds 数据资源ID列表
	 */
	public void reRelRoleDataResources(String roleId,String... dataIds);
	
	
	/**
	 * 重新关联账户,将删除角色关联的账户,重新指定账户
	 * @param roleId 角色ID
	 * @param dataIds 账户ID列表
	 */
	public void reRelRoleAccounts(String roleId,String... accountIds);
	
	
	/**
	 * 增加帐户
	 * @param accountBo
	 * @return
	 */
	public String addAccount(SecureAccountBo accountBo);
	
	
	/**
	 * 更新帐户
	 * @param accountBo
	 */
	public void updateAccount(SecureAccountBo accountBo);
	
	
	/**
	 * 删除帐户
	 * @param accountIds
	 */
	public void deleteAccounts(String... accountIds);
	
	
	/**
	 * 增加功能资源
	 * @param accountBo
	 * @return
	 */
	public String addFunc(SecureFuncBo funcBo);
	
	
	/**
	 * 更新功能资源
	 * @param accountBo
	 */
	public void updateFunc(SecureFuncBo accountBo);
	
	
	/**
	 * 删除功能资源
	 * @param funcIds
	 */
	public void deleteFuncs(String... funcIds);
	
	
	/**
	 * 增加功角色
	 * @param roleBo 角色Bo
	 * @return
	 */
	public String addRole(SecureRoleBo roleBo);
	
	
	/**
	 * 更新角色
	 * @param roleBo 角色Bo
	 */
	public void updateRole(SecureRoleBo roleBo);
	
	
	/**
	 * 删除角色
	 * @param roleIds 角色Ids
	 */
	public void deleteRoles(String... roleIds);
	
	
	/**
	 * 增加系统
	 * @param systemBo 系统Bo 
	 * @return
	 */
	public String addSystem(SecureSystemBo systemBo);
	
	
	/**
	 * 更新系统
	 * @param systemBo 系统Bo 
	 */
	public void updateSystem(SecureSystemBo systemBo);
	
	
	/**
	 * 删除系统
	 * @param roleIds 系统Ids 
	 */
	public void deleteSystems(String... systemIds);
	
	
	
	/**
	 * 增加数据资源
	 * @param dataFromBo 数据资源Bo 
	 * @return
	 */
	public String addDataResource(DataResourceFormBo dataFromBo);
	
	
	/**
	 * 在辅助表中关联资源
	 * @param roleId 角色ID
	 * @param resourceCode 资源代码
	 * @param resourceIds 资源Id列表
	 */
	public void relAssistTable(String roleId,String resourceCode,List<String> resourceIds);
	
	
	/**
	 * 在辅助表中重新关联资源
	 * @param roleId 角色ID
	 * @param resourceCode 资源代码
	 * @param resourceIds 资源Id列表
	 */
	public void reRelAssistTable(String roleId,String resourceCode,List<String> resourceIds);
	
	
	
	/**
	 * 认证
	 * @param loginName
	 * @param password
	 * @return
	 */
	public SecureLogonModel authentication(String loginName,String password);


	/**
	 * @param accountGroupId
	 * @param roleIds
	 */
	void relAccountGroupRoles(String accountGroupId, String... roleIds);


	/**
	 * @param accountId
	 * @param roleIds
	 */
	void unRelAccountGroupRoles(String accountId, String... roleIds);


	/**
	 * @param accountId
	 * @param roleIds
	 */
	void reRelAccountGroupRoles(String accountId, String... roleIds);


	/**
	 * 得到所有的数据权限
	 * @return
	 */
	List<SecureDataBo> getAllDataResources();


	/**
	 * @param roleIds
	 * @return
	 */
	List<VtpDataResource> getDatasByRoleIds(List<String> roleIds);


	/**
	 * @param loginName
	 * @param ticket
	 */
	void doSubject(String loginName, String ticket);
	
}
