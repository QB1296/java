/**
 * 文件名：ExcelImportResult.java  
 *  
 * 版本信息：  
 * 日期：2014年11月24日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.io.IOException;
import java.io.OutputStream;


/**
 * <b>功能描述</b> <br>
 * Excel导入文件结果
 * @author YUJB
 * @date 2014年11月24日 下午3:25:06
 */
public class ExcelImportResult {
	
	/** 是否都成功  */
	private boolean isAllSuccess;
	
	/** 成功数量  */
	private int successCount;
	
	/** 失败数量  */
	private int errorCount;
	
	/** 失败文件路径  */
	private String errorFilePath;
	
	private FileRepository fileRepository;
	
	
	public ExcelImportResult(FileRepository fileRepository) {
		super();
		this.fileRepository = fileRepository;
	}

	public void merge(ExcelImportResult result){
		this.successCount += result.successCount;
		this.errorCount += result.errorCount;
		this.isAllSuccess = isAllSuccess && result.isAllSuccess;
	}
	
	
	public OutputStream getErrorOutputStream() throws IOException{
		return fileRepository.getOutputStream(getErrorFilePath());
	}

	public boolean isAllSuccess() {
		return isAllSuccess;
	}

	void setAllSuccess(boolean isAllSuccess) {
		this.isAllSuccess = isAllSuccess;
	}

	public int getSuccessCount() {
		return successCount;
	}

	void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	public int getErrorCount() {
		return errorCount;
	}

	void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	/**
	 * 获得errorFilePath 
	 * @return  errorFilePath errorFilePath
	 */
	public String getErrorFilePath() {
		return errorFilePath;
	}

	/** 
	 * 设置errorFilePath 
	 * @param errorFilePath errorFilePath 
	 */
	public void setErrorFilePath(String errorFilePath) {
		this.errorFilePath = errorFilePath;
	}
	
	
	
}
