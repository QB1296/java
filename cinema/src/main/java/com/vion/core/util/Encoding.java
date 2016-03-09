/**
 * 文件名：asdf.java  
 *  
 * 版本信息：  
 * 日期：2013-6-3  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.util;

import java.nio.charset.Charset;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2013-6-3 下午12:36:01
 */
public final class Encoding{

    public static final String UTF8 = "UTF-8";
    
    public static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
    
    public static String defaultJVMEncoding(){
        return Charset.defaultCharset().name();
    }
    
    public static String defaultSysEncoding(){
		return defaultJVMEncoding();
		
    }


}
