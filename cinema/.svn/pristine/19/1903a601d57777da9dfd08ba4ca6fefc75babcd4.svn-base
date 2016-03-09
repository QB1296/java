/**
 * 文件名：SpringExcelLoader.java  
 *  
 * 版本信息：  
 * 日期：2015年1月21日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.vion.core.spring.CompositeResourceLoader;

/**
 * <b>功能描述</b> <br>
 * 基于Spring的excel文件loader加载器
 * @author YUJB
 * @date 2015年1月21日 下午4:36:02
 */
@Component
public class SpringExcelLoader implements ExcelLoader{

	/* (non-Javadoc)
	 * @see com.vion.core.poi.ExcelLoader#LoadExcelResource(java.lang.String)*/
	@Override
	public InputStream loadExcelResource(String path) throws IOException {
		ResourceLoader loader = new CompositeResourceLoader();
		Resource resource = loader.getResource(path);
		return resource.getInputStream();
	}

}
