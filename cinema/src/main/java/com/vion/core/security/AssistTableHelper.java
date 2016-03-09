/**
 * 文件名：AssistTableHelper.java  
 *  
 * 版本信息：  
 * 日期：2015年3月4日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import java.io.Serializable;
import java.util.List;

import com.vion.core.ResourcesHolder;
import com.vion.core.SystemContext;
import com.vion.core.dao.FinderResult;
import com.vion.core.dao.IGeneralDAO;
import com.vion.core.security.session.SecureSession;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年3月4日 下午3:46:55
 */
public class AssistTableHelper {
	
	private static IGeneralDAO getGeneralDao(){
		return SystemContext.getApplicationContext().getBean(IGeneralDAO.class);
	}
	
	@SuppressWarnings("unchecked")
	public static  List<String> getAssistResourceIds(String resourceCode){
		SecureSession secureSession = SecureContextUtil.getSecureContext().getSecureSession();
		Serializable id = secureSession.getId();
		List<String> ret = ResourcesHolder.get("assistResource_" + resourceCode + id,List.class);
		if (ret == null) {
			IGeneralDAO generalDao = getGeneralDao();
			List<String> roleIdArray = (List<String>)secureSession.getAttribute("roleIds");
			FinderResult fr = generalDao.findBySQL("select data_resource_id from vtp_role_assist where role_id in (?) and resource_code = ?",new Object[]{roleIdArray,resourceCode});
			ret =  fr.result(String.class);
			ResourcesHolder.bind("assistResource_" + resourceCode + id, ret);
		}
		return ret;
	}
	
}
