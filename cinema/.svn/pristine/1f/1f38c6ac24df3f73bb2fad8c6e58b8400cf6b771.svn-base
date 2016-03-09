package com.vion.core.security.meta;

import java.io.Serializable;



/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年5月13日 下午7:34:22
 */
public interface Subject<T,K> extends Serializable{
	
	/**
	 * 是否有查看此功能资源的权限
	 * @param permissionId
	 * @return
	 */
	boolean isPermitted(String permissionId);
	
	
	/**
	 * 得到所有的功能资源权限
	 * @param clazz
	 * @return
	 */
	 T getFuncPermissions();
	
	/**
	 * 得到数据资源权限
	 * @param clazz
	 * @return
	 */
	 K getDataPermissions();
	
}
