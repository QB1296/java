/**
 * 文件名：TrafficEntityInterceptor.java  
 *  
 * 版本信息：  
 * 日期：2014年8月7日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
package com.vion;
import org.hibernate.EmptyInterceptor;

import com.vion.core.SystemContext;
import com.vion.core.security.SQLRuleFilterProcessor;
import com.vion.core.security.session.SecureCurrentSessionIdHolder;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月7日 下午1:10:24
 */
public class TrafficEntityInterceptor extends EmptyInterceptor{


	/**   */
	private static final long serialVersionUID = 1L;

	@Override
	public String onPrepareStatement(String sql) {
		if(SystemContext.getApplicationContext() == null){
			return sql;
		}
		
		SQLRuleFilterProcessor processor = SystemContext.getApplicationContext().getBean(SQLRuleFilterProcessor.class);
		String id = SecureCurrentSessionIdHolder.getSessionId();
		if (id != null) {
			return processor.process(sql,id);
		}else {
			return sql;
		}
	}

}
