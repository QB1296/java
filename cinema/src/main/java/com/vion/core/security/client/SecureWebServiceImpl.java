/**
 * 文件名：SecureWebServiceImpl.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.client;

import javax.jws.WebService;

import com.vion.core.model.ReplyModel;
import com.vion.core.security.SecureContextUtil;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月30日 上午9:57:02
 */
@WebService(endpointInterface="com.vion.core.security.client.SecureWebService", serviceName="SecureWebService" )
public class SecureWebServiceImpl implements SecureWebService{

	@Override
	public String processSql(String sql, Object ticket) {
		return SecureContextUtil.getSecureContext().processSql(sql, ticket);
	}

	@Override
	public String addAccount(String Identifier, String accountName, String system) {
		return SecureContextUtil.getSecureContext().addAccount(Identifier, accountName, system);
	}

	@Override
	public ReplyModel authentication(String accountName, String pwd,
			String system) {
		return null;
	}

}
