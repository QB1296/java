/**
 * 文件名：FileRepository.java  
 *  
 * 版本信息：  
 * 日期：2015年1月27日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.vion.core.SystemContext;
import com.vion.core.spring.CompositeResourceLoader;
import com.vion.core.util.Files;


/**
 * <b>功能描述</b> <br>
 * 基于Spring的文件仓库
 * @author YUJB
 * @date 2015年1月27日 上午9:55:01
 */
public class SpringResourceFileRepository implements FileRepository {

	/* (non-Javadoc)
	 * @see com.vion.core.poi.FileRepository#saveFile(java.lang.String)*/
	@Override
	public void saveFile(String path) throws IOException {
		ServletContext servletContext = SystemContext.getHttpSession().getServletContext();
		String realPath = servletContext.getRealPath("/");
		realPath += "/" + path;
		Files.createFileIfNoExists(realPath);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.poi.FileRepository#getFile(java.lang.String)*/
	@Override
	public File getFile(String path) throws IOException {
		ResourceLoader resourceLoader = new CompositeResourceLoader();
		Resource resource = resourceLoader.getResource("context:/" + path);
		return resource.getFile();
	}
	
	
	/* (non-Javadoc)
	 * @see com.vion.core.poi.FileRepository#getOutputStream(java.lang.String)*/
	@Override
	public OutputStream getOutputStream(String path) throws IOException {
		return new FileOutputStream(getFile(path));
	}
	
	
}
