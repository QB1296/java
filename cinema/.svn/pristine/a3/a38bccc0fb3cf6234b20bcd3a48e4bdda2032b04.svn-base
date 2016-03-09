/**
 * 文件名：SecureWebService.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.client;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.vion.core.model.ReplyModel;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月30日 上午9:56:47
 */
@WebService 
public interface SecureWebService {
	
	public String processSql(@WebParam(name = "sql")String sql, Object ticket);

	
	public String addAccount(@WebParam(name = "Identifier")String Identifier, @WebParam(name = "accountName")String accountName, @WebParam(name = "system") String system);

	
	public ReplyModel authentication(@WebParam(name = "accountName")String accountName,@WebParam(name = "pwd") String pwd, @WebParam(name = "system") String system);



}
