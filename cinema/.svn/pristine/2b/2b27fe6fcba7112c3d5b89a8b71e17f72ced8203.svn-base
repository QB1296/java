/**
 * 
 */
package com.vion.core.security;


/**
 * <b>功能描述</b> <br>
 * 日期处理器处理后的格式为  yyyy-mm-dd
 * @author YUJB
 * @date 2015年1月20日 上午9:58:38
 */
public class DateProcessor implements ValueProcessor{

	@Override
	public String processorValue(Object value) {
		return "to_date('" + value.toString()+ "','yyyy-mm-dd')";
	}

}
