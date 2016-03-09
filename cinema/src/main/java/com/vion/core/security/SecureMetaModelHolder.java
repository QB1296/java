/**
 * 文件名：SecureMetaModelHolder.java  
 *  
 * 版本信息：  
 * 日期：2014年10月23日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import com.vion.core.security.meta.model.SecureMetaModel;

/**
 * <b>功能描述</b> <br>
 * 数据权限元数据配置信息holder类
 * @author YUJB
 * @date 2014年10月23日 下午1:20:22
 */
public class SecureMetaModelHolder {
	
	private static SecureMetaModel secureMetaModel;

	
	/**
	 * 得到权限元数据模型
	 * @return
	 */
	public static SecureMetaModel getSecureMetaModel(){
		return secureMetaModel;
	}
	
	
	/**
	 * 设置元数据模型
	 * @param secureMetaModel
	 */
	public static void setSecureMetaModel(SecureMetaModel secureMetaModel){
		SecureMetaModelHolder.secureMetaModel = secureMetaModel;
	}
}
