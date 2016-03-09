/**
 * 文件名：SecureAbstractTouchSessionFilter.java  
 *  
 * 版本信息：  
 * 日期：2014年11月3日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */

package com.vion.core.security.session;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.vion.core.FilterAdapter;
import com.vion.core.util.RequestUtils;

/**
 * <b>功能描述</b> <br>
 * session抽象的touch过滤器,子类中必须实现{@link #doGetSecureSessionId(HttpServletRequest)}
 * 方法。<br>
 * 同事过滤器将当前的sessionId存储到了 {@link SecureCurrentSessionIdHolder}中
 * 
 * @author YUJB
 * @date 2014年11月3日 下午1:04:43
 */
public abstract class SecureAbstractTouchSessionFilter extends FilterAdapter
		implements InitializingBean {

	private transient Logger logger = LoggerFactory.getLogger(this.getClass());

	/** 不过滤的URL列表，比如登陆请求 */
	private String[] excludeURLs;

	private SecureSessionManager secureSessionManager;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String uri = RequestUtils.getUri(httpServletRequest);
		if (!isIgnoreUrl(uri)) {
			String sessionId = doGetSecureSessionId(httpServletRequest);
			if (sessionId != null) {
				SecureCurrentSessionIdHolder.setSessoinId(sessionId);
				SecureSession secureSession = secureSessionManager
						.getSession(sessionId);
				if (secureSession != null) {
					secureSession.touch();
				} else {
					logger.info(
							"请求[{}],sessionId[{}]服务器端没有session,分析原因为[非法操作,session失效]",
							new String[] { uri, sessionId });
				}
			}else {
				SecureCurrentSessionIdHolder.setSessoinId(null);
			}
		}
		filterChain.doFilter(request, response);
	}

	private boolean isIgnoreUrl(String url) {
		for (String excudeUrl : excludeURLs) {
			if (url.contains(excudeUrl.trim())) {
				return true;
			}
		}
		return false;
	}

	protected abstract String doGetSecureSessionId(
			HttpServletRequest httpServletRequest);

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(secureSessionManager);
	}

	public String[] getExcludeURLs() {
		return excludeURLs;
	}

	public void setExcludeURLs(String[] excludeURLs) {
		this.excludeURLs = excludeURLs;
	}

	public SecureSessionManager getSecureSessionManager() {
		return secureSessionManager;
	}

	public void setSecureSessionManager(
			SecureSessionManager secureSessionManager) {
		this.secureSessionManager = secureSessionManager;
	}

}
