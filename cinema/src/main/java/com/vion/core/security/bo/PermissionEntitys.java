/**
 * 文件名：PermissionEntitys.java  
 *  
 * 版本信息：  
 * 日期：2014年10月28日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.bo;

import java.util.List;

import com.vion.core.security.entity.VtpDataResource;
import com.vion.core.security.entity.VtpFuncSource;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月28日 上午9:21:44
 */
public class PermissionEntitys {
	
	List<VtpDataResource> datas;
	
	List<VtpFuncSource> funcs;

	public List<VtpDataResource> getDatas() {
		return datas;
	}

	public void setDatas(List<VtpDataResource> datas) {
		this.datas = datas;
	}

	public List<VtpFuncSource> getFuncs() {
		return funcs;
	}

	public void setFuncs(List<VtpFuncSource> funcs) {
		this.funcs = funcs;
	}
	
	
	
}
