/**
 * 文件名：SecureCompositeTouchSessionFilter.java  
 *  
 * 版本信息：  
 * 日期：2014年11月3日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.vion.core.util.RequestUtils;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年11月3日 下午1:21:43
 */
public class SecureCompositeTouchSessionFilter extends SecureAbstractTouchSessionFilter {
	
	private static Logger logger = LoggerFactory.getLogger(SecureCompositeTouchSessionFilter.class);
	
	private String sessionTokenKey;
	
	private String sessionTokenType;
	
	@Override
	protected String doGetSecureSessionId(
			HttpServletRequest httpServletRequest) {
		SessionTokenType sessionTokenType = SessionTokenType.getSessionTokenTypeByName(this.sessionTokenType);
		if (sessionTokenType == null) {
			logger.info("[sessionTokenKey]{}必须param或cookie,如果不能满足实际需求,自定义filter继承SecureAbstractTouchSessionFilter",new String[]{sessionTokenKey});
			return null;
		}
		String sessionId = sessionTokenType.doGetSessionId(httpServletRequest,sessionTokenKey);
		if (sessionId == null) {
			logger.info(
					"请求[{}],sessionTokenKey:[{}],sessionTokenType:[{}]没有找到sessionId标识,分析原因为[非法操作,或没有在excludeURLs过滤掉此url]",
					new String[] { RequestUtils.getUri(httpServletRequest),
							sessionTokenKey, this.sessionTokenType });
			return null;
		}
		return sessionId;
		
	}
	
	
	public String getSessionTokenKey() {
		return sessionTokenKey;
	}


	public void setSessionTokenKey(String sessionTokenKey) {
		this.sessionTokenKey = sessionTokenKey;
	}


	public String getSessionTokenType() {
		return sessionTokenType;
	}


	public void setSessionTokenType(String sessionTokenType) {
		this.sessionTokenType = sessionTokenType;
	}



	enum SessionTokenType{
		PARAM("param")
		{
			@Override
			String doGetSessionId(HttpServletRequest request,String tokenId) {
				String idObj = request.getParameter(tokenId);
				if (idObj != null) {
					return idObj.toString();
				}
				return null;
			}
		},		
		COOKIE("cookie")
		{
			@Override
			String doGetSessionId(HttpServletRequest request,String tokenId) {
				Cookie cookie = RequestUtils.getCookieByName(request, tokenId);
				if (cookie != null) {
					return cookie.getValue();
				}
				return null;
			}
		};
		
		private String name;
		
		
		private SessionTokenType(String name) {
			this.name = name;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		abstract String doGetSessionId(HttpServletRequest request,String tokenId);
		
		
		public static SessionTokenType getSessionTokenTypeByName(String name){
			Assert.notNull(name);
			SessionTokenType[] values = SessionTokenType.values();
			for (SessionTokenType sessionTokenType : values) {
				if (name.equals(sessionTokenType.getName())) {
					return sessionTokenType;
				}
			}
			return null;
		}
	}


}
