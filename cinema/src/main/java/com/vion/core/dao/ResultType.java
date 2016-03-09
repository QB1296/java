/**
 * 文件名：ResultType.java  
 *  
 * 版本信息：  
 * 日期：2013-6-7  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.dao;

import java.util.Map;

import com.vion.core.domain.entity.IEntity;
import com.vion.core.domain.vo.ValueObject;
import java.math.BigDecimal;

/**
 * <b>功能描述</b> <br>
 * 返回结果类型,支持Map,POJO,Entity,Primitive
 * @author YUJB
 * @date 2014-6-13 上午11:28:35
 */
public enum ResultType{
	Map {
		@Override
		public boolean isSupport(Class<?> clazz) {
			if (clazz.isAssignableFrom(Map.class)) {
				return true;
			}
			return false;
			
		}

		@Override
		public String supportDesp() {
			return Map.class + "";
		}
	},POJO {
		@Override
		public boolean isSupport(Class<?> clazz) {
			if (ValueObject.class.isAssignableFrom(clazz)) {
				return true;
			}
			return false;
		}

		@Override
		public String supportDesp() {
			return " 继承自" + ValueObject.class + "的VO类";
		}
	},Entity {
		@Override
		public boolean isSupport(Class<?> clazz) {
			if (IEntity.class.isAssignableFrom(clazz)) {
				return true;
			}
			return false;
		}

		@Override
		public String supportDesp() {
			return " 继承自" + IEntity.class + "的实体类";
		}
	},Primitive {
		
		private Class<?>[] supportedClasses = new Class[]{
				String.class,Integer.class,Long.class,BigDecimal.class,Double.class
		};
		
		@Override
		public boolean isSupport(Class<?> clazz) {
			for(Class<?> supportedClass : supportedClasses){
				if(supportedClass.equals(clazz)){
					return true;
				}
			}
			return false;
		}

		@Override
		public String supportDesp() {
			StringBuffer sb = new StringBuffer();
			for(Class<?> supportedClass : supportedClasses){
				sb.append(supportedClass);
				sb.append(", ");
			}
			return sb.substring(0, sb.length() -2);
		}
	};

	/**
	 * 是否支持指定类型
	 * @param clazz 
	 */
	public abstract boolean isSupport(Class<?> clazz);
	
	/**
	 * 支持class的描述,用于日志生成使用
	 * @return
	 */
	protected abstract String supportDesp();
	
	/**
	 * 根据clazz得到指定ResultType
	 * @param clazz
	 * @return 如果没又找到将抛出 {@link DaoResultTypeMismatchException}
	 */
	public static ResultType getSupportResultType(Class<?> clazz){
		ResultType[] values = ResultType.values();
		for (ResultType resultType : values) {
			if(resultType.isSupport(clazz)){
				return resultType;
			}
		}
		throw new DaoResultTypeMismatchException(clazz,getSupportDesp());
	}
	
	public static String getSupportDesp(){
		StringBuffer sb = new StringBuffer("[");
		ResultType[] values = ResultType.values();
		for (ResultType resultType : values) {
			sb.append(resultType.name());
			sb.append(":");
			sb.append(resultType.supportDesp());
			sb.append(", ");
		}
		String str = sb.substring(0, sb.length() -2);
		return str + "]";
	}
		
}