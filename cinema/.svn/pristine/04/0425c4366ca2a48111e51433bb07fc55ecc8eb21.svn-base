/**
 * 文件名：AccountMetaConfig.java  
 *  
 * 版本信息：  
 * 日期：2015年3月6日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.dao.meta;

import java.util.HashMap;
import java.util.Map;
import com.vion.core.basic.meta.EntityMetaConfig;
import com.vion.core.security.entity.VtpFuncSource;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2015年3月6日 下午2:03:49
 */
public class FuncMetaConfig implements EntityMetaConfig{

	/* (non-Javadoc)
	 * @see com.vion.core.basic.EntityMetaConfig#getEntiyClass()*/
	@Override
	public Class<?> getEntiyClass() {
		return VtpFuncSource.class;
	}

	/* (non-Javadoc)
	 * @see com.vion.core.basic.EntityMetaConfig#getEntityName()*/
	@Override
	public String getEntityName() {
		return getEntiyClass().getSimpleName();
	}
	
	public static FuncMetaConfig me(){
		return new FuncMetaConfig();
	}

	/* (non-Javadoc)
	 * @see com.vion.core.basic.EntityMetaConfig#getJoinMappers()*/
	@Override
	public Map<String, String> getJoinMappers() {
		Map<String, String> maper = new HashMap<String, String>();
		return maper;
	}

}
