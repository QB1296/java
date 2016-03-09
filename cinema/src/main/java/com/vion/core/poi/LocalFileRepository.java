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

import com.vion.core.util.Files;


/**
 * <b>功能描述</b> <br>
 * 本地文件仓库
 * @author YUJB
 * @date 2015年1月27日 上午9:55:01
 */
public class LocalFileRepository implements FileRepository {

	/* (non-Javadoc)
	 * @see com.vion.core.poi.FileRepository#saveFile(java.lang.String)*/
	@Override
	public void saveFile(String path) throws IOException {
		Files.createFileIfNoExists(path);
	}

	/* (non-Javadoc)
	 * @see com.vion.core.poi.FileRepository#getFile(java.lang.String)*/
	@Override
	public File getFile(String path) throws IOException {
		return new File(path);
	}
	
	/* (non-Javadoc)
	 * @see com.vion.core.poi.FileRepository#getOutputStream(java.lang.String)*/
	@Override
	public OutputStream getOutputStream(String path) throws IOException {
		return new FileOutputStream(getFile(path));
	}
	
}
