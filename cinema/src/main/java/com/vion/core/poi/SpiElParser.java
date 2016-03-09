/**
 * 文件名：SpiElParser.java  
 *  
 * 版本信息：  
 * 日期：2015年1月22日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.vion.core.SystemContext;
import com.vion.core.util.Strings;


/**
 * <b>功能描述</b> <br>
 * Spi表达式解析器,基于sp（spring 表达式语言）的扩展、增加了${}包裹,增加了 #each #if等消息路由
 * @author YUJB
 * @date 2015年1月22日 下午4:17:46
 */
public class SpiElParser{

	
	/**  属性访问器 */
	private List<PropertyAccessor> propertyAccessors = new ArrayList<PropertyAccessor>();
	
	/**  占位符前缀 */
	private static final String PLACEHOLDER_PREFIX = "${";
	
	/**  占位符后缀 */
	private static final String PLACEHOLDER_SUFFIX = "}";
	
	/**  占位符pattern */
	private static final Pattern PATTERN = Pattern.compile("\\$\\{(.*?)\\}");
	
	private final PropertyAccessor[] defaultPropertyAccesss = new PropertyAccessor[]{
			new SpiElTagContextPropertyAccessor(),
	};
	
	private static SpiElParser spiElParser;
	
	private SpiElParser() {
		Map<String, PropertyAccessor> beansOfType = SystemContext.getApplicationContext().getBeansOfType(PropertyAccessor.class);
		propertyAccessors.addAll(beansOfType.values());
	}
	
	public synchronized static SpiElParser me(){
		if (spiElParser == null) {
			spiElParser = new SpiElParser();
		}
		return spiElParser;
	}


	public String parserElString(SpiElTagContext context,String spiEl){
		if (Strings.isEmpty(spiEl)) {
			return spiEl;
		}
		StringBuffer sb = new StringBuffer();
		Matcher matcher = PATTERN.matcher(spiEl);
		boolean isMatcher = false;
		
		while (matcher.find()) {
			isMatcher = true;
			String match = matcher.group();
			String replaceName = match.substring(PLACEHOLDER_PREFIX.length(), match.length()-PLACEHOLDER_SUFFIX.length());
			String realReplaceName = Strings.trim(replaceName);
			Object spelParser = spelParser(realReplaceName,context,Object.class);
			try {
				if (spelParser == null) {
					spelParser = "";
				}
				matcher.appendReplacement(sb,spelParser.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		matcher.appendTail(sb);
		if (!isMatcher) {
			sb.append(spiEl);
		}
		return sb.toString();
	}
	
	
	public String replaceStartWithSpiEl(String spiEl,String startWith,String replaceValue){
		return spiEl.replace(PLACEHOLDER_PREFIX + startWith, PLACEHOLDER_PREFIX + replaceValue);
	}
	
	
	
	public boolean spiElStartWith(String spiEl,String containStr){
		if (Strings.isEmpty(spiEl)) {
			return false;
		}
		Matcher matcher = PATTERN.matcher(spiEl);
		while (matcher.find()) {
			String match = matcher.group();
			String replaceName = match.substring(PLACEHOLDER_PREFIX.length(), match.length()-PLACEHOLDER_SUFFIX.length());
			String realReplaceName = Strings.trim(replaceName);
			if (realReplaceName.startsWith(containStr)) {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean isSpiEl(String spiEl){
		if (Strings.isEmpty(spiEl)) {
			return false;
		}
		Matcher matcher = PATTERN.matcher(spiEl);
		while (matcher.find()) {
			return true;
		}
		return false;
	}
	
	
	public <T> T parserEl(SpiElTagContext context,String spiEl,Class<T> clazz){
		spiEl = Strings.trim(spiEl);
		if (!(spiEl.startsWith(PLACEHOLDER_PREFIX) && spiEl.endsWith(PLACEHOLDER_SUFFIX))) {
			throw new IllegalArgumentException("spiEl必须是${value}格式");
		}
		String replaceName = unWrapSpel(spiEl);
		return spelParser(replaceName,context,clazz);
	}
	
	
	public String unWrapSpel(String wrapSpel){
		return wrapSpel.substring(PLACEHOLDER_PREFIX.length(), wrapSpel.length()-PLACEHOLDER_SUFFIX.length());
	}
	
	public String wrapSpel(String wrapSpel){
		return PLACEHOLDER_PREFIX + wrapSpel + PLACEHOLDER_SUFFIX;
	}
	
	
	private <T> T spelParser(String spiEl,SpiElTagContext context,Class<T> clazz){
		return this.getValue(context, spiEl, clazz);
	}
	
	
	
	public <T> T getValue(SpiElTagContext spiElTagContext,String spiEl,Class<T> clazz){
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		propertyAccessors.addAll(Arrays.asList(defaultPropertyAccesss));
		for (PropertyAccessor propertyAccessor : propertyAccessors) {
			context.addPropertyAccessor(propertyAccessor);
		}
		context.setRootObject(spiElTagContext);
		T value = null;
		try {
			value = parser.parseExpression(spiEl).getValue(context,clazz);
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 获得属性访问器 
	 * @return  propertyAccessors 属性访问器
	 */
	public List<PropertyAccessor> getPropertyAccessors() {
		return propertyAccessors;
	}

	/** 
	 * 设置属性访问器 
	 * @param propertyAccessors 属性访问器 
	 */
	public void setPropertyAccessors(List<PropertyAccessor> propertyAccessors) {
		this.propertyAccessors = propertyAccessors;
	}


	

	
	
}
