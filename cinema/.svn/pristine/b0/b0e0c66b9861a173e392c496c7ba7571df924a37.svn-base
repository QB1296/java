/**
 * 文件名：AbstractMapSecureSession.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */

package com.vion.core.security.session;

import java.util.Map;

/**
 * <b>功能描述</b> <br>
 * 抽象的Map Session 存放Attribute
 * @author YUJB
 * @date 2014年10月30日 下午12:52:17
 */
public abstract class AbstractMapSecureSession implements ValidatingSecureSession {

	private  Map<Object, Object> attributes;

	public Map<Object, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<Object, Object> attributes) {
		this.attributes = attributes;
	}

	public Object getAttribute(Object key) {
		Map<Object, Object> attributes = getAttributes();
		if (attributes == null) {
			return null;
		}
		return attributes.get(key);
	}

	public void setAttribute(Object key, Object value) {
		if (value == null) {
			removeAttribute(key);
		} else {
			getAttributes().put(key, value);
		}
	}

	public Object removeAttribute(Object key) {
		Map<Object, Object> attributes = getAttributes();
		if (attributes == null) {
			return null;
		} else {
			return attributes.remove(key);
		}
	}

}
