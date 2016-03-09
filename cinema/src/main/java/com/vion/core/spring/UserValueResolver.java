/**
 * 文件名：PagedResolver.java  
 *  
 * 版本信息：  
 * 日期：2014年6月16日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.spring;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

import com.vion.core.annotation.UserValue;
import com.vion.core.security.SecureContextUtil;
import com.vion.core.security.session.SecureCurrentSessionIdHolder;
import com.vion.core.security.session.SecureSession;

/**
 * <b>功能描述</b> <br>
 * pagedQuery解析器,在controller中可以直接使用<code>@PagedValue PagedQuery pagedQuery</code>得到pagedQuery对象
 * @author YUJB
 * @date 2014年6月16日 上午9:25:30
 */
public class UserValueResolver implements WebArgumentResolver{
	
	//private transient Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Object resolveArgument(MethodParameter parameter,
			NativeWebRequest webRequest) throws Exception {
		UserValue userValue = parameter.getParameterAnnotation(UserValue.class);
		if (userValue == null) {
			return WebArgumentResolver.UNRESOLVED;
		}
		String id = SecureCurrentSessionIdHolder.getSessionId();
		SecureSession secureSession = SecureContextUtil.getSecureContext().getSecureSession(id);
		if (secureSession != null) {
			return secureSession.getAttribute("currentUser");
		}
		return null;
	}
	
	
}
