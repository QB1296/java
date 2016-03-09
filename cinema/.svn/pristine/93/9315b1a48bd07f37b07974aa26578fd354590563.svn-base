/**
 * 文件名：AbstractSecureSessionRepository.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;


/**
 * <b>功能描述</b> <br>
 * 抽象的session仓库,包含{@link #getSecureSessionFactory()}方法,子类可直接使用session工厂
 * @author YUJB
 * @date 2014年10月30日 下午1:47:17
 */
public  abstract class AbstractSecureSessionRepository implements SecureSessionRepository {
	
	private SecureSessionFactory secureSessionFactory;
	
	protected SecureSessionFactory getSecureSessionFactory() {
		return secureSessionFactory;
	}

	public void setSecureSessionFactory(SecureSessionFactory secureSessionFactory) {
		this.secureSessionFactory = secureSessionFactory;
	}

	 
	 
}
