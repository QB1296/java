/**
 * 
 */
package com.vion.core.security;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;


/**
 * @author YUJB
 *
 */
public class InValuesProcessor implements ValueProcessor{

	@SuppressWarnings("rawtypes")
	@Override
	public String processorValue(Object value) {
		if (value instanceof Collection) {
			String join = StringUtils.join((Collection)value, "','");
			return join.substring(2, join.length());
		}
		return value.toString();
	}

}
