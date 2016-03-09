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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.Assert;

import com.vion.core.SystemContext;

/**
 * <b>功能描述</b> <br>
 * Excel导出对外暴漏接口
 * @author YUJB
 * @date 2014年3月24日 下午4:22:03
 */
public class ExcelExports {
	
	protected final static Log logger = LogFactory.getLog(ExcelExports.class);
	
	/** 标识是xls还是 xlsx  */
	private ExcelType excelType;
	
	private ExcelExportsService excelExportsService;
	
	private SpiElTagContext spiElTagContext;
	
	private Workbook workbook;
	
	private String templete;
	
	
	public ExcelExports() {
		super();
		excelExportsService = SystemContext.getApplicationContext().getBean(ExcelExportsService.class);
		spiElTagContext = new SpiElTagContext();
	}
	
	
	public void clear(){
		workbook = null;
		excelType = null;
		spiElTagContext.clear();
		templete = null;
	}

	public static ExcelExports XLS(){
		ExcelExports excelExports = new ExcelExports();
		excelExports.setExcelType(ExcelType.xls);
		return excelExports;
	}
	
	
	public static ExcelExports XLSX(){
		ExcelExports excelExports = new ExcelExports();
		excelExports.setExcelType(ExcelType.xlsx);
		return excelExports;
	}
	
	
	public ByteArrayOutputStream stream(){
		ByteArrayOutputStream workbookToStream = excelExportsService.workbookToStream(workbook);
		clear();
		return workbookToStream;
	}
	
	/** 
	 * 设置标识是xls还是xlsx 
	 * @param excelType 标识是xls还是xlsx 
	 */
	public void setExcelType(ExcelType excelType) {
		this.excelType = excelType;
	}
	
	
	public ExcelExports addValue(String key,Object value){
		spiElTagContext.addValue(key, value);
		return this;
	}
	
	public void addFixedRange(int firstRow,int lastRow,int firstCol,int lastCol){
		spiElTagContext.addFixedRange(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}
	
	
	public ExcelExports addTemplete(String templete){
		this.templete = templete;
		return this;
	}
	
	public void addListener(ExcelExportListener listener){
		spiElTagContext.addListener(listener);
	}
	


	/** 
	 * 设置导出监听器 
	 * @param listeners 导出监听器 
	 */
	public void setListeners(List<ExcelExportListener> listeners) {
		spiElTagContext.setListeners(listeners);
	}

	
	
	/**
	 * 更具数据、模板导出excel
	 * @param templete 模板文件
	 * @return excel stream
	 */
	public ByteArrayOutputStream exportExcel(){
		return exportExcel(0);
	}
	
	
	/**
	 * 更具数据、模板导出excel
	 * @param templete 模板文件
	 * @return excel stream
	 */
	public ByteArrayOutputStream exportExcel(Integer sheetAt){
		ByteArrayOutputStream stream = excelExportsService.exportExcel(spiElTagContext, excelType, templete, sheetAt);
		clear();
		return stream;
	}
	
	
	
	public ExcelExports segmentExport(Integer sheetAt){
		if (workbook == null) {
			workbook = excelExportsService.exportExcelWorkbook(spiElTagContext, excelType, templete, sheetAt);
		}else {
			workbook = excelExportsService.exportExcelWorkbook(spiElTagContext, workbook, sheetAt);
		}
		return this;
	}
	
	
	/**
	 * 更具数据、模板导出excel
	 * @param dataMap 填充模板的业务数据
	 * @param templete 模板文件
	 * @param reWorkbook 
	 * @return excel stream
	 */
	public <T extends PoiBaseBo> ByteArrayOutputStream exportExcel(Class<T> clazz,List<T> datas){
		if (datas.size()> 50000 && this.excelType == ExcelType.xlsx) {
			this.excelType = ExcelType.sxssf;
		}
		Workbook workbook = excelExportsService.getTempleteWorkBook(this.excelType, templete);
		List<List<T>> splitDatas = splitDatas(datas);
		for (int i = 0; i < splitDatas.size(); i++) {
			fillData(clazz,splitDatas.get(i), workbook,i);
		}
		return excelExportsService.workbookToStream(workbook);
	}
	
	
	public <T> List<List<T>>  splitDatas(List<T> datas) {
		int sum = datas.size();
		int threshold = 60000;
		int a = sum/threshold;
        int b = sum%threshold;
         
        List<List<T>> ret = new ArrayList<List<T>>();
        
        for (int i = 0; i < a; i++) {
        	ret.add(datas.subList(i*threshold, (i+1)*threshold));
        }
        if(b!=0){
        	ret.add(datas.subList(a*threshold, sum));
        }
         
        return ret;
	}
	
	
	private <T extends PoiBaseBo>  void fillData(Class<T> clazz,List<T> datas,Workbook workbook,int plusNum){
		Assert.notNull(datas);
		for (int z = 0; z < datas.size(); z++) {
			PoiBaseBo poiBaseBo = datas.get(z);
			int sheetAt = PoiAnnotationResolver.getSheetAt(clazz) + plusNum;
			int rowStart = PoiAnnotationResolver.getRowStart(clazz);
			int rowEnd = PoiAnnotationResolver.getRowEnd(clazz);
			int columnStart = PoiAnnotationResolver.getColumnStart(clazz);
			int columnEnd = PoiAnnotationResolver.getColumnEnd(clazz);
			if (rowEnd == -1 || datas.size() < rowEnd) {
				rowEnd = rowStart + datas.size();
			}
			Sheet sheet = workbook.getSheetAt(sheetAt);
			int i = rowStart + z;
			Row eachRow = sheet.getRow(i);
			if (eachRow == null) {
				eachRow = sheet.createRow(i);
			}
			for (int j = columnStart; j <= columnEnd; j++) {
				Cell eachCell = eachRow.getCell(j);
				Object invoke = poiBaseBo.getColumnValue(j);
				if (eachCell == null) {
					eachCell = eachRow.createCell(j);
				}
				if (invoke != null) {
					if (NumberUtils.isNumber(String.valueOf(invoke))) {
						eachCell.setCellValue(Double.valueOf(String.valueOf(invoke)));
						CellStyle thisCellStyle = eachCell.getCellStyle();
						thisCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
						eachCell.setCellStyle(thisCellStyle);
					}else {
						eachCell.setCellValue(String.valueOf(invoke));
					}
				}
			}
		}
	}



	
	
	
}
