/**
 * 文件名：ExcelLoader.java  
 *  
 * 版本信息：  
 * 日期：2015年1月21日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.io.IOException;
import java.io.InputStream;

/**
 * <b>功能描述</b> <br>
 * Excel资源加载器,实现类需实现{@link #LoadExcelResource(String)}方法
 * @author YUJB
 * @date 2015年1月21日 下午4:35:14
 */
public interface ExcelLoader {

	/**
	 * 根据path加载excel资源
	 * @param path 资源路径
	 * @return 输入流
	 * @throws IOException 加载过程中出现错误抛出IO异常
	 */
	public InputStream loadExcelResource(String path) throws IOException;
}
