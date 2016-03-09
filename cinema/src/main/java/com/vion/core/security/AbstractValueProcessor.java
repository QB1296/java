/**
 * 
 */
package com.vion.core.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月29日 上午9:18:19
 */
public abstract class  AbstractValueProcessor implements SQLProcessor{
	
	private static final Pattern PATTERN_CURRENT = Pattern.compile("\\#\\{(.*?)\\}");
	

	@Override
	public String processorSQL(Object value,String sql) {
		StringBuffer sb = new StringBuffer();
		Matcher matcher = PATTERN_CURRENT.matcher(sql);
		boolean isMatcher = false;
		while (matcher.find()) {
			isMatcher = true;
			String match = matcher.group();
			String replaceValue = match;
			if (canProcess(match)) {
				replaceValue = getRealField(value);
			}
			replaceValue = replaceValue.replaceAll("\\$", "\\\\\\$"); 
			matcher.appendReplacement(sb,replaceValue);
		}
		if (isMatcher) {
			matcher.appendTail(sb);
			return sb.toString();
		}else {
			return sql;
		}
	}
	
	
	public abstract String getRealField(Object value);
	
	
	public abstract boolean canProcess(String placeholder);

}
