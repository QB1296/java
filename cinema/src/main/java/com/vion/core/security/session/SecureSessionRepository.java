/**
 * 文件名：SecureSessionRepository.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;

import java.io.Serializable;
import java.util.List;

/**
 * <b>功能描述</b> <br>
 * Session 仓库,维护session的存储、更新、伤处的具体操作
 * @author YUJB
 * @date 2014年10月30日 上午11:49:22
 */
public interface SecureSessionRepository {
	
	/**
	 * 得到所有的session
	 * @return
	 */
	List<SecureSession> getSessions();
	
	/**
	 * 创建session
	 * @return
	 */
	SecureSession createSession();
	
	/**
	 * 得到session
	 * @param id
	 * @return
	 */
	SecureSession getSession(Serializable id);

	/**
	 * 移除sesssion
	 * @param id
	 */
	void remove(Serializable id);

	/**
	 * 更新session
	 * @param secureSession
	 */
	void updateSession(SecureSession secureSession);
	
}
