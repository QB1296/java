/**
 * 文件名：BaiscSecureDaoImpl.java  
 *  
 * 版本信息：  
 * 日期：2015年3月6日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */

package com.vion.core.security.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.vion.core.ResourcesHolder;
import com.vion.core.SystemContext;
import com.vion.core.basic.BasicDaoImpl;
import com.vion.core.dao.FinderResult;
import com.vion.core.exception.NoCriteriaWritingException;
import com.vion.core.security.SecureContextUtil;
import com.vion.core.security.SecureMetaModelHolder;
import com.vion.core.security.SecureSubjectHolder;
import com.vion.core.security.bo.PermissionEntitys;
import com.vion.core.security.bo.SecureAccountBo;
import com.vion.core.security.bo.SecureDataBo;
import com.vion.core.security.bo.SecureFuncBo;
import com.vion.core.security.bo.SecureLogonModel;
import com.vion.core.security.bo.SecureRoleBo;
import com.vion.core.security.bo.SecureSystemBo;
import com.vion.core.security.entity.VtpAccountGroup;
import com.vion.core.security.entity.VtpAccountRole;
import com.vion.core.security.entity.VtpAccounts;
import com.vion.core.security.entity.VtpDataResource;
import com.vion.core.security.entity.VtpFuncSource;
import com.vion.core.security.entity.VtpRoleAssist;
import com.vion.core.security.entity.VtpRoleResource;
import com.vion.core.security.entity.VtpRoles;
import com.vion.core.security.entity.VtpSystem;
import com.vion.core.security.meta.DataResourceBo;
import com.vion.core.security.meta.DataResourceBo.Column;
import com.vion.core.security.meta.DataResourceBo.Moudle;
import com.vion.core.security.meta.DataResourceBo.SelectColumn;
import com.vion.core.security.meta.DataResourceFormBo;
import com.vion.core.security.meta.SecureFuncModels;
import com.vion.core.security.meta.SecureFuncModels.FuncModel;
import com.vion.core.security.meta.SecureSubject;
import com.vion.core.security.meta.model.ColumnModel;
import com.vion.core.security.meta.model.DefaultFuncModel;
import com.vion.core.security.meta.model.EntityModel;
import com.vion.core.security.meta.model.MoudleModel;
import com.vion.core.security.meta.model.RowRule;
import com.vion.core.security.meta.model.RowRuleValue;
import com.vion.core.security.meta.model.SecureMetaModel;
import com.vion.core.security.rule.FilterRule;
import com.vion.core.security.session.SecureSession;
import com.vion.core.security.session.SecureSessionManager;
import com.vion.core.util.Collections;

/**
 * <b>功能描述</b> <br>
 * 
 * @author YUJB
 * @date 2015年3月6日 上午11:35:53
 */
@Component("BasicSecureDao")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class BaiscSecureDaoImpl extends BasicDaoImpl<BasicSecureFinder> implements BasicSecureDao {

	public final static String ACCOUNT_TYPE_SIGNLE = "SIGNLE";

	public final static String ACCOUNT_TYPE_GROUP = "GROUP";

	public final static String RESOURCE_TYPE_FUNC = "FUNC";

	public final static String RESOURCE_TYPE_DATA = "DATA";

	@Autowired
	private SecureSessionManager secureSessionManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#relAccountGroups(java.lang.
	 * String, java.lang.String[])
	 */
	@Override
	public void relAccountGroups(String accountId, String... groupIds) {
		for (String groupId : groupIds) {
			VtpAccountGroup accountGroup = new VtpAccountGroup();
			accountGroup.setAccountId(accountId);
			accountGroup.setGroupId(groupId);
			generalDAO.save(accountGroup);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#unRelAccountGroups(java.lang
	 * .String, java.lang.String[])
	 */
	@Override
	public void unRelAccountGroups(String accountId, String... groupIds) {
		Assert.notNull(accountId);
		FinderResult fr = generalDAO.findByJPQL("from VtpAccountGroup t where t.groupId in (?) " + "and t.accountId='" + accountId + "'",
				new Object[] { groupIds });
		List<VtpAccountGroup> accountGroups = fr.result(VtpAccountGroup.class);
		if (accountGroups != null) {
			generalDAO.batchRemove(accountGroups.toArray(new VtpAccountGroup[] {}));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#reRelAccountGroups(java.lang
	 * .String, java.lang.String[])
	 */
	@Override
	public void reRelAccountGroups(String accountId, String... groupIds) {
		Assert.notNull(accountId);
		generalDAO.executeHQL("delete VtpAccountGroup t where  t.accountId = ?", new Object[] { accountId });
		if (groupIds.length > 0) {
			relAccountGroups(accountId, groupIds);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#relAccountRoles(java.lang.String
	 * , java.lang.String[])
	 */
	@Override
	public void relAccountRoles(String accountId, String... roleIds) {
		for (String roleId : roleIds) {
			VtpAccountRole accountRole = new VtpAccountRole();
			accountRole.setAccountId(accountId);
			accountRole.setRoleId(roleId);
			accountRole.setAccountType(ACCOUNT_TYPE_SIGNLE);
			generalDAO.saveOrUpdate(accountRole);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#relAccountRoles(java.lang.String
	 * , java.lang.String[])
	 */
	@Override
	public void relAccountGroupRoles(String accountGroupId, String... roleIds) {
		for (String roleId : roleIds) {
			VtpAccountRole accountRole = new VtpAccountRole();
			accountRole.setAccountId(accountGroupId);
			accountRole.setRoleId(roleId);
			accountRole.setAccountType(ACCOUNT_TYPE_GROUP);
			generalDAO.saveOrUpdate(accountRole);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#unRelAccountRoles(java.lang
	 * .String, java.lang.String[])
	 */
	@Override
	public void unRelAccountRoles(String accountId, String... roleIds) {
		unRelAccountAndGroupRoles(accountId, ACCOUNT_TYPE_SIGNLE, roleIds);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#unRelAccountRoles(java.lang
	 * .String, java.lang.String[])
	 */
	@Override
	public void unRelAccountGroupRoles(String accountId, String... roleIds) {
		unRelAccountAndGroupRoles(accountId, ACCOUNT_TYPE_GROUP, roleIds);

	}

	private void unRelAccountAndGroupRoles(String accountId, String accountType, String... roleIds) {
		Assert.notNull(accountId);
		FinderResult fr = generalDAO.findByJPQL("from VtpAccountRole t where t.roleId in (?) " + "and t.accountType = " + accountType
				+ " and t.accountId='" + accountId + "'", new Object[] { roleIds });
		List<VtpAccountRole> accountRoles = fr.result(VtpAccountRole.class);
		if (accountRoles != null) {
			generalDAO.batchRemove(accountRoles.toArray(new VtpAccountGroup[] {}));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#reRelAccountRoles(java.lang
	 * .String, java.lang.String[])
	 */
	@Override
	public void reRelAccountRoles(String accountId, String... roleIds) {
		deleteAllSpecifiedAccountRoles(accountId, ACCOUNT_TYPE_SIGNLE);
		if (roleIds.length > 0) {
			relAccountRoles(accountId, roleIds);
		}
	}

	public void reRelAccountAllRoles(String... accountId) {
		Assert.notNull(accountId);
		generalDAO.executeHQL("delete VtpAccountRole t where  t.accountId in (?)", new Object[] { StringUtils.join(accountId, ',') });
	}

	@Override
	public void reRelAccountGroupRoles(String accountId, String... roleIds) {
		deleteAllSpecifiedAccountRoles(accountId, ACCOUNT_TYPE_GROUP);
		if (roleIds.length > 0) {
			relAccountGroupRoles(accountId, roleIds);
		}
	}

	private void deleteAllSpecifiedAccountRoles(String accountId, String accountType) {
		Assert.notNull(accountId);
		generalDAO.executeHQL("delete VtpAccountRole t where  t.accountId = ? and t.accountType = ?", new Object[] { accountId, accountType });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#relRoleFuncResources(java.lang
	 * .String, java.lang.String[])
	 */
	@Override
	public void relRoleFuncResources(String roleId, String... funcIds) {
		for (String funcId : funcIds) {
			VtpRoleResource roleResource = new VtpRoleResource();
			roleResource.setRoleId(roleId);
			roleResource.setResourceId(funcId);
			roleResource.setResourceType(RESOURCE_TYPE_FUNC);
			generalDAO.saveOrUpdate(roleResource);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#relRoleDataResources(java.lang
	 * .String, java.lang.String[])
	 */
	@Override
	public void relRoleDataResources(String roleId, String... dataIds) {
		for (String dataId : dataIds) {
			VtpRoleResource roleResource = new VtpRoleResource();
			roleResource.setRoleId(roleId);
			roleResource.setResourceId(dataId);
			roleResource.setResourceType(RESOURCE_TYPE_DATA);
			generalDAO.saveOrUpdate(roleResource);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#unRelRoleFuncResources(java
	 * .lang.String, java.lang.String[])
	 */
	@Override
	public void unRelRoleFuncResources(String roleId, String... funcIds) {
		unRelRoleResources(roleId, RESOURCE_TYPE_FUNC, funcIds);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#unRelRoleDataResources(java
	 * .lang.String, java.lang.String[])
	 */
	@Override
	public void unRelRoleDataResources(String roleId, String... dataIds) {
		unRelRoleResources(roleId, RESOURCE_TYPE_DATA, dataIds);

	}

	private void unRelRoleResources(String roleId, String resourceType, String... resourceIds) {
		Assert.notNull(roleId);
		FinderResult fr = generalDAO.findByJPQL("from VtpRoleResource t where t.resourceId in (?) " + "and t.resourceType = " + resourceType
				+ " and t.roleId='" + roleId + "'", new Object[] { resourceIds });
		List<VtpRoleResource> roleResources = fr.result(VtpRoleResource.class);
		if (roleResources != null) {
			generalDAO.batchRemove(roleResources.toArray(new VtpRoleResource[] {}));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#reRelRoleFuncResources(java
	 * .lang.String, java.lang.String[])
	 */
	@Override
	public void reRelRoleFuncResources(String roleId, String... funcIds) {
		deleteAllSpecifiedRoleResources(roleId, RESOURCE_TYPE_FUNC);
		if (funcIds != null && funcIds.length > 0) {
			relRoleFuncResources(roleId, funcIds);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#reRelRoleDataResources(java
	 * .lang.String, java.lang.String[])
	 */
	@Override
	public void reRelRoleDataResources(String roleId, String... dataIds) {
		deleteAllSpecifiedRoleResources(roleId, RESOURCE_TYPE_DATA);
		if (dataIds.length > 0) {
			relRoleDataResources(roleId, dataIds);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#reRelRoleAccounts(java.lang
	 * .String, java.lang.String[])
	 */
	@Override
	public void reRelRoleAccounts(String roleId, String... accountIds) {
		deleteAllSpecifiedRoleAccounts(roleId);
		if (accountIds != null) {
			for (String accountId : accountIds) {
				VtpAccountRole accountRole = new VtpAccountRole();
				accountRole.setAccountId(accountId);
				accountRole.setRoleId(roleId);
				accountRole.setAccountType(ACCOUNT_TYPE_SIGNLE);
				generalDAO.saveOrUpdate(accountRole);
			}
		}
	}

	private void deleteAllSpecifiedRoleAccounts(String roleId) {
		Assert.notNull(roleId);
		generalDAO.executeHQL("delete VtpAccountRole t where  t.roleId = ?", new Object[] { roleId });
	}

	private void deleteAllSpecifiedRoleResources(String roleId, String resourceType) {
		Assert.notNull(roleId);
		generalDAO.executeHQL("delete VtpRoleResource t where  t.roleId = ? and t.resourceType = ?", new Object[] { roleId, resourceType });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#addAccount(com.vion.core.security
	 * .bo.SecureAccountBo)
	 */
	@Override
	public String addAccount(SecureAccountBo accountBo) {
		Assert.notNull(accountBo);
		VtpAccounts accounts = new VtpAccounts();
		BeanUtils.copyProperties(accountBo, accounts);
		accounts.setAccountsIdentify(accountBo.getSystem() + accountBo.getLoginName());
		return generalDAO.save(accounts).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#updateAccount(com.vion.core
	 * .security.bo.SecureAccountBo)
	 */
	@Override
	public void updateAccount(SecureAccountBo accountBo) {
		Assert.notNull(accountBo);
		Assert.notNull(accountBo.getIdentifier());
		VtpAccounts accounts = new VtpAccounts();
		BeanUtils.copyProperties(accountBo, accounts);
		generalDAO.update(accounts);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#deleteAccounts(java.lang.String
	 * [])
	 */
	@Override
	public void deleteAccounts(String... accountIds) {
		if (accountIds.length > 0) {
			generalDAO.executeHQL("delete VtpAccounts t where t.identify in (?) ", new Object[] { Arrays.asList(accountIds) });
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#addFunc(com.vion.core.security
	 * .bo.SecureFuncBo)
	 */
	@Override
	public String addFunc(SecureFuncBo funcBo) {
		Assert.notNull(funcBo);
		VtpRoles func = new VtpRoles();
		BeanUtils.copyProperties(funcBo, func);
		return generalDAO.save(func).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#updateFunc(com.vion.core.security
	 * .bo.SecureFuncBo)
	 */
	@Override
	public void updateFunc(SecureFuncBo funcBo) {
		Assert.notNull(funcBo);
		Assert.notNull(funcBo.getId());
		VtpRoles func = new VtpRoles();
		BeanUtils.copyProperties(funcBo, func);
		generalDAO.update(func);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#deleteFuncs(java.lang.String[])
	 */
	@Override
	public void deleteFuncs(String... funcIds) {
		if (funcIds.length > 0) {
			generalDAO.executeHQL("delete VtpFuncSource t where t.id in (?) ", new Object[] { Arrays.asList(funcIds) });
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#addRole(com.vion.core.security
	 * .bo.SecureRoleBo)
	 */
	@Override
	public String addRole(SecureRoleBo roleBo) {
		Assert.notNull(roleBo);
		VtpRoles role = new VtpRoles();
		BeanUtils.copyProperties(roleBo, role);
		return generalDAO.save(role).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#updateRole(com.vion.core.security
	 * .bo.SecureRoleBo)
	 */
	@Override
	public void updateRole(SecureRoleBo roleBo) {
		Assert.notNull(roleBo);
		Assert.notNull(roleBo.getId());
		VtpRoles role = new VtpRoles();
		BeanUtils.copyProperties(roleBo, role);
		generalDAO.update(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#deleteRoles(java.lang.String[])
	 */
	@Override
	public void deleteRoles(String... roleIds) {
		if (roleIds.length > 0) {
			generalDAO.executeHQL("delete VtpRoles t where t.id in (?) ", new Object[] { Arrays.asList(roleIds) });
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#addSystem(com.vion.core.security
	 * .bo.SecureSystemBo)
	 */
	@Override
	public String addSystem(SecureSystemBo systemBo) {
		Assert.notNull(systemBo);
		VtpSystem system = new VtpSystem();
		BeanUtils.copyProperties(systemBo, system);
		return generalDAO.save(system).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#updateSystem(com.vion.core.
	 * security.bo.SecureSystemBo)
	 */
	@Override
	public void updateSystem(SecureSystemBo systemBo) {
		Assert.notNull(systemBo);
		Assert.notNull(systemBo.getId());
		VtpSystem system = new VtpSystem();
		BeanUtils.copyProperties(systemBo, system);
		generalDAO.update(system);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#deleteSystems(java.lang.String
	 * [])
	 */
	@Override
	public void deleteSystems(String... systemIds) {
		if (systemIds.length > 0) {
			generalDAO.executeHQL("delete VtpSystem t where t.id in (?) ", new Object[] { Arrays.asList(systemIds) });
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#addDataResource(com.vion.core
	 * .security.meta.DataResourceFormBo)
	 */
	@Override
	public String addDataResource(DataResourceFormBo dataFromBo) {
		try {
			return generalDAO.save(convertToVtpDataResource(dataFromBo)).toString();
		}
		catch (IOException e) {
			throw new NoCriteriaWritingException("dataFormBo无法转化", e);
		}
	}

	private VtpDataResource convertToVtpDataResource(DataResourceFormBo bo) throws JsonGenerationException, JsonMappingException, IOException {
		List<FilterRule> filterRules = bo.convertToFilterRule();
		ObjectMapper objectMapper = new ObjectMapper();
		String content = objectMapper.writeValueAsString(filterRules.toArray(new FilterRule[] {}));
		MoudleModel moudleModel = SecureMetaModelHolder.getSecureMetaModel().getMoudleModelByCode(bo.getMoudle());

		VtpDataResource vtpData = new VtpDataResource();
		if (moudleModel != null) {
			vtpData.setMoudleName(moudleModel.getName());
			vtpData.setMoudleCode(moudleModel.getCode());
		}
		vtpData.setContent(content);
		vtpData.setName(bo.getName());
		vtpData.setSystem(bo.getSystem());
		return vtpData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#relAssistTable(java.lang.String
	 * , java.lang.String, java.util.List)
	 */
	@Override
	public void relAssistTable(String roleId, String resourceCode, List<String> resourceIds) {
		if (resourceIds != null) {
			for (String resourceId : resourceIds) {
				VtpRoleAssist vtpRoleAssist = new VtpRoleAssist();
				vtpRoleAssist.setDataResourceId(resourceId);
				vtpRoleAssist.setResourceCode(resourceCode);
				vtpRoleAssist.setRoleId(roleId);
				generalDAO.save(vtpRoleAssist);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#reRelAssistTable(java.lang.
	 * String, java.lang.String, java.util.List)
	 */
	@Override
	public void reRelAssistTable(String roleId, String resourceCode, List<String> resourceIds) {
		generalDAO.executeHQL("delete  VtpRoleAssist t where t.roleId = ? and t.resourceCode = ?", new Object[] { roleId, resourceCode });
		if (resourceIds != null && resourceIds.size() > 0) {
			relAssistTable(roleId, resourceCode, resourceIds);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vion.core.security.dao.BasicSecureDao#authentication(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public SecureLogonModel authentication(String loginName, String password) {
		boolean isLogon = false;
		VtpAccounts account = getAccountByLoginName(loginName);
		if (account != null && account.getPassword().equals(password)) {
			isLogon = true;
		}
		SecureLogonModel model = new SecureLogonModel();
		model.setSuccess(isLogon);
		if (!isLogon) {
			model.setDescription("用户名或密码不正确");
		}
		else {
			model.setDescription("登陆成功");
			SecureSession secureSession = secureSessionManager.createSession();
			model.putBizData("user", account);
			secureSession.setAttribute("userInfo", account);
			doSubject(loginName, secureSession.getId().toString());
			SecureSubject subject = SecureContextUtil.getSecureContext().getSecureSubject(secureSession.getId().toString());
			model.setFuncModels(subject.getFuncPermissions().getNoMenuFuncModles());
			model.setSessionId(secureSession.getId().toString());
		}
		return model;
	}

	/**
	 * 保存数据资源
	 * 
	 * @param bo
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@Transactional(readOnly = false)
	public PermissionEntitys accountPermissions(String loginName) {

		PermissionEntitys permissionEntitys = new PermissionEntitys();

		VtpAccounts account = getAccountByLoginName(loginName);
		if (account != null) {

			List<String> accountIds = new ArrayList<String>();

			String accountId = account.getAccountsIdentify();
			List<String> groupIds = getAccountGroupIds(accountId);
			accountIds.add(accountId);
			accountIds.addAll(groupIds);
			List<String> roleIds = getAccountRoleIds(accountIds);
			SecureContextUtil.getSecureContext().getSecureSession().setAttribute("secureAccountId", account.getAccountsIdentify());
			SecureContextUtil.getSecureContext().getSecureSession().setAttribute("roleIds", roleIds);

			List<VtpRoleResource> roleResources = getAllRoleResourceIds(roleIds);

			List<VtpDataResource> datas = new ArrayList<VtpDataResource>();
			List<VtpFuncSource> funcs = new ArrayList<VtpFuncSource>();
			for (VtpRoleResource roleResource : roleResources) {
				VtpDataResource data = roleResource.getData();
				if (data != null) {
					datas.add(data);
				}
				VtpFuncSource func = roleResource.getFunc();
				if (func != null) {
					funcs.add(func);
				}
			}
			Collections.removeDuplicate(datas);
			Collections.removeDuplicate(funcs);
			permissionEntitys.setDatas(datas);
			permissionEntitys.setFuncs(funcs);
		}

		return permissionEntitys;
	}
	
	@Override
	public void doSubject(String loginName, String ticket) {
		SecureSubject subject = getSubject(loginName);
		SecureSubjectHolder subjectHolder = SystemContext.getApplicationContext().getBean(SecureSubjectHolder.class);
		subjectHolder.setSecureSubject(subject, ticket);
	}

	/**
	 * 得到所有的数据资源
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SecureDataBo> getAllDataResources() {
		List<VtpDataResource> dbSources = new ArrayList<VtpDataResource>();
		Object object = ResourcesHolder.get("allDataResource");
		if (object == null) {
			FinderResult finderResult = generalDAO.findByJPQL("from VtpDataResource");
			dbSources = finderResult.result(VtpDataResource.class);
			ResourcesHolder.bind("allDataResource", dbSources);
		}
		else {
			dbSources = ((List<VtpDataResource>) object);
		}
		List<VtpDataResource> medSources = new ArrayList<VtpDataResource>();
		SecureMetaModel secureMetaModel = SecureMetaModelHolder.getSecureMetaModel();
		List<DefaultFuncModel> defaultFuncModels = secureMetaModel.getDefaultFuncModels();
		if (defaultFuncModels != null) {
			for (DefaultFuncModel defaultFuncModel : defaultFuncModels) {
				if (defaultFuncModel.getIsDefault().equals("false")) {
					DataResourceFormBo dataResourceFormBo = defaultFuncModel.convertFormBo();
					try {
						VtpDataResource vtpDataResource = convertToVtpDataResource(dataResourceFormBo);
						vtpDataResource.setId(defaultFuncModel.getId());
						medSources.add(vtpDataResource);
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (dbSources != null) {
			medSources.addAll(dbSources);
		}
		List<SecureDataBo> secureDataBos = new ArrayList<SecureDataBo>();
		for (VtpDataResource dataResource : medSources) {
			SecureDataBo secureDataBo = new SecureDataBo();
			BeanUtils.copyProperties(dataResource, secureDataBo);
			secureDataBos.add(secureDataBo);
		}
		return secureDataBos;
	}

	@SuppressWarnings("unchecked")
	private SecureSubject getSubject(String loginName) {

		PermissionEntitys accountPermissions = accountPermissions(loginName);
		List<VtpDataResource> datas = accountPermissions.getDatas();
		Object attribute = SecureContextUtil.getSecureContext().getSecureSession().getAttribute("roleIds");
		if (attribute != null) {
			List<VtpDataResource> defaultDatas = getDatasByRoleIds((List<String>) attribute);
			datas.addAll(defaultDatas);
		}
		List<FilterRule> dataSubject = convertDataSubject(datas);
		List<VtpFuncSource> funcs = accountPermissions.getFuncs();
		SecureFuncModels funcSubject = convertFuncSubject(funcs);

		return new SecureSubject(dataSubject, funcSubject);
	}

	public List<VtpDataResource> getDatasByRoleId(String roleIds) {
		return getDatasByRoleIds(Arrays.asList(roleIds));
	}

	public List<VtpDataResource> getDatasByRoleIds(List<String> roleIds) {
		List<String> assDataIds = getAssDataIds(roleIds.toArray(new String[] {}));
		List<VtpDataResource> dataResources = new ArrayList<VtpDataResource>();
		SecureMetaModel secureMetaModel = SecureMetaModelHolder.getSecureMetaModel();
		List<DefaultFuncModel> defaultFuncModels = secureMetaModel.getDefaultFuncModels();
		if (defaultFuncModels != null) {
			for (DefaultFuncModel defaultFuncModel : defaultFuncModels) {
				if (defaultFuncModel.getIsDefault().equals("false") && !assDataIds.contains(defaultFuncModel.getId())) {
					continue;
				}
				DataResourceFormBo dataResourceFormBo = defaultFuncModel.convertFormBo();
				try {
					VtpDataResource vtpDataResource = convertToVtpDataResource(dataResourceFormBo);
					vtpDataResource.setId(defaultFuncModel.getId());
					dataResources.add(vtpDataResource);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return dataResources;
	}

	private SecureFuncModels convertFuncSubject(List<VtpFuncSource> funcs) {

		SecureFuncModels funcModels = new SecureFuncModels();

		if (funcs != null) {
			for (VtpFuncSource funcSource : funcs) {
				FuncModel funcModel = funcModels.new FuncModel();
				funcModel.setCode(funcSource.getCode());
				funcModel.setId(funcSource.getId());
				funcModel.setType(funcSource.getType());
				funcModels.addFuncModel(funcModel);
			}
		}
		return funcModels;

	}

	private List<FilterRule> convertDataSubject(List<VtpDataResource> datas) {

		List<FilterRule> rules = new ArrayList<FilterRule>();

		if (datas != null) {
			for (VtpDataResource vtpDataResource : datas) {
				String content = vtpDataResource.getContent();
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, FilterRule.class);
				try {
					List<FilterRule> oneFilterRules = objectMapper.readValue(content, javaType);
					rules.addAll(oneFilterRules);
				}
				catch (JsonParseException e) {
					e.printStackTrace();
				}
				catch (JsonMappingException e) {
					e.printStackTrace();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return rules;
	}

	/**
	 * 数据权限模型转化为前端BO
	 * 
	 * @param moudleModels
	 * @return
	 */
	public DataResourceBo convertDateResourceRule(List<MoudleModel> moudleModels) {
		DataResourceBo dataResourceBo = new DataResourceBo();
		if (moudleModels != null) {
			for (MoudleModel moudleModel : moudleModels) {
				Moudle moudle = dataResourceBo.new Moudle();
				moudle.setCode(moudleModel.getCode());
				moudle.setName(moudleModel.getName());
				List<EntityModel> entityModels = moudleModel.getEntityModels();
				for (EntityModel entityModel : entityModels) {
					List<ColumnModel> columnModels = entityModel.getColumnModels();
					if (columnModels != null) {
						for (ColumnModel columnModel : columnModels) {
							Column column = dataResourceBo.new SingleColumn();
							BeanUtils.copyProperties(columnModel, column);
							if (columnModel.getValueConfigModel() != null) {
								BeanUtils.copyProperties(columnModel.getValueConfigModel(), column);
							}
							moudle.addColumn(column);
						}
					}
				}
				List<RowRule> rowRules = moudleModel.getRowRules();
				if (rowRules != null) {
					for (RowRule rowRule : rowRules) {
						if (rowRule.getSql() == null) {
							SelectColumn column = dataResourceBo.new SelectColumn();
							column.setCode(rowRule.getCode());
							column.setName(rowRule.getName());
							column.setOperate(rowRule.getOperate());

							List<RowRuleValue> rowRuleValues = rowRule.getRowRuleValues();
							List<Map<String, String>> selectItems = new ArrayList<Map<String, String>>();
							for (RowRuleValue ruleValue : rowRuleValues) {
								Map<String, String> selectItem = new HashMap<String, String>();
								selectItem.put("key", ruleValue.getKey());
								selectItem.put("value", ruleValue.getValue());
								selectItems.add(selectItem);
							}
							column.setSelectItems(selectItems);

							moudle.addColumn(column);
						}
						else {
							Column column = dataResourceBo.new SingleColumn();
							column.setCode(rowRule.getCode());
							column.setName(rowRule.getName());
							column.setOperate(rowRule.getOperate());
							if (rowRule.getValueConfigModel() != null) {
								BeanUtils.copyProperties(rowRule.getValueConfigModel(), column);
							}
							moudle.addColumn(column);
						}
					}
				}

				dataResourceBo.addMoudle(moudle);
			}
		}
		return dataResourceBo;
	}

	private List<String> getAssDataIds(String... roleIds) {
		List<String> ret = new ArrayList<String>();
		if (roleIds != null && roleIds.length > 0) {
			FinderResult fr = generalDAO.findByJPQL("select t.resourceId from VtpRoleResource t where t.resourceType='DATA' and t.roleId in (?)",
					new Object[] { Arrays.asList(roleIds) });
			ret = fr.result(String.class);
		}
		return ret;
	}

	private List<String> getAccountRoleIds(List<String> accountIds) {
		FinderResult fr = generalDAO.findByJPQL("select t.roleId from VtpAccountRole t where t.accountId in (?)", accountIds.toArray());
		return fr.result(String.class);
	}

	private List<VtpRoleResource> getAllRoleResourceIds(List<String> roleIds) {
		if (roleIds == null || roleIds.size() == 0) {
			return new ArrayList<VtpRoleResource>();
		}
		FinderResult fr = generalDAO.findByJPQL("from VtpRoleResource t where t.roleId in (?)",new Object[]{roleIds});
		return fr.result(VtpRoleResource.class);
	}

	private List<String> getAccountGroupIds(String accountId) {
		FinderResult fr = generalDAO.findByJPQL("select t.groupId from VtpAccountGroup t where  t.accountId='" + accountId + "'");
		return fr.result(String.class);
	}

	private VtpAccounts getAccountByLoginName(String loginName) {
		FinderResult fr = generalDAO.findByJPQL("from VtpAccounts t where t.loginName = '" + loginName + "'");
		VtpAccounts account = fr.uniqueResult(VtpAccounts.class);
		return account;
	}

}
