/**
 * 文件名：Oracle10gDICA.java  
 *  
 * 版本信息：  
 * 日期：2014年8月27日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.hibernate;

import java.sql.Types;

import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;



/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月27日 上午10:54:45
 */
public class Oracle10gDialect extends org.hibernate.dialect.Oracle10gDialect{

	public Oracle10gDialect() {
		super();
		this.registerFunction("fn_ispointinpolygon", new SQLFunctionTemplate(IntegerType.INSTANCE,"fn_ispointinpolygon(?1,?2,?3,?4)"));
		registerHibernateType(Types.NVARCHAR,StringType.INSTANCE.getName() );
	}
	
	
}
