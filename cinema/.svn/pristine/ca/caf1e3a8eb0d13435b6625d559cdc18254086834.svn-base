/**
 * 文件名：ChartExcelExportHelper.java  
 *  
 * 版本信息：  
 * 日期：2014年3月24日  
 * Copyright(c) 2013 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年3月24日 下午4:22:03
 */
public class ExcelExportsService{
	
	
	private final static Log logger = LogFactory.getLog(ExcelExportsService.class);
	
	private ExcelLoader excelLoader;
	
	private ExcelSpiElParser excelSpiElParser;
	
	
	/**
	 * 得到workBook
	 * @param excelType
	 * @param templete
	 * @return
	 */
	public Workbook getTempleteWorkBook(ExcelType excelType,String templete){
		Workbook workbook = null;
		try {
			if (excelType.equals(ExcelType.xls)) {
				workbook = new HSSFWorkbook(excelLoader.loadExcelResource(templete));
			}else if (excelType.equals(ExcelType.sxssf)) {
				workbook = new SXSSFWorkbook(new XSSFWorkbook(excelLoader.loadExcelResource(templete)),5000);
			} else{
				workbook = new XSSFWorkbook(excelLoader.loadExcelResource(templete));
			}
		} catch (IOException e) {
			logger.info("文件:" + templete + "不存在,或被其他程序占用....");
		} 
		
		return workbook;
	}
	
	
	/**
	 * 更具数据、模板导出excel
	 * @param dataMap 填充模板的业务数据
	 * @param templete 模板文件
	 * @param reWorkbook 
	 * @return excel stream
	 */
	public  ByteArrayOutputStream exportExcel(SpiElTagContext spiElTagContext,ExcelType excelType,String templete,int sheetIndex){
		return workbookToStream(exportExcelWorkbook(spiElTagContext, excelType, templete, sheetIndex));
	}
	
	
	public Workbook exportExcelWorkbook(SpiElTagContext spiElTagContext,ExcelType excelType,String templete,int sheetIndex){
		Workbook workbook = getTempleteWorkBook(excelType, templete);
		excelSpiElParser.parserSheet(spiElTagContext, workbook.getSheetAt(sheetIndex));
		return workbook;
	}
	
	
	public Workbook exportExcelWorkbook(SpiElTagContext spiElTagContext,Workbook workbook,int sheetIndex){
		excelSpiElParser.parserSheet(spiElTagContext, workbook.getSheetAt(sheetIndex));
		return workbook;
	}
	
	
	public  ByteArrayOutputStream workbookToStream(Workbook workbook){
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			workbook.write(os);
		} catch (IOException e) {
			logger.info("excel文件:" + workbook + "填写数据后生成流,出现IO异常",e);
		}finally{
			try {
				workbook.close();
			} catch (IOException e) {
				logger.info("excel文件:" + workbook + "关闭时出现IO异常",e);
			}
		}
		return os;
	}
	
	
	
	/**
	 * 获得excelLoader 
	 * @return  excelLoader excelLoader
	 */
	public ExcelLoader getExcelLoader() {
		return excelLoader;
	}
	
	/** 
	 * 设置excelLoader 
	 * @param excelLoader excelLoader 
	 */
	public void setExcelLoader(ExcelLoader excelLoader) {
		this.excelLoader = excelLoader;
	}
	

	/**
	 * 获得excelSpiElParser 
	 * @return  excelSpiElParser excelSpiElParser
	 */
	public ExcelSpiElParser getExcelSpiElParser() {
		return excelSpiElParser;
	}


	/** 
	 * 设置excelSpiElParser 
	 * @param excelSpiElParser excelSpiElParser 
	 */
	public void setExcelSpiElParser(ExcelSpiElParser excelSpiElParser) {
		this.excelSpiElParser = excelSpiElParser;
	}

	
}
