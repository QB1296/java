/**
 * 文件名： CustomExcelImportsService.java
 *  
 * 版本信息：  
 * 日期：2015年3月28日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.excel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.vion.core.poi.BatchProcessor;
import com.vion.core.poi.ExcelImportListener;
import com.vion.core.poi.ExcelImportResult;
import com.vion.core.poi.ExcelImportsService;
import com.vion.core.poi.ExcelType;
import com.vion.core.poi.PoiAnnotationResolver;
import com.vion.core.poi.PoiBaseBo;
import com.vion.core.poi.ValidateMeta;

/**
 * <b>Excel导入服务</b> <br>
 * 
 * @author GANJX
 * @date 2015年3月28日 上午9:20:55
 */
public class CustomExcelImportsService extends ExcelImportsService{

	/**
	 * 是否进行验证
	 */
	private boolean isValid = false;

	/**
	 * 
	 */
	private CountDownLatch countDownLatch; 
	
	/**
	 * @return
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * @param isValid
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	/* (non-Javadoc)
	 * @see com.vion.core.poi.ExcelImportsService#importExcel(com.vion.core.poi.ExcelType, java.io.InputStream, java.lang.Class, com.vion.core.poi.BatchProcessor, java.util.List)
	 */
	@Override
	public <T extends PoiBaseBo> ExcelImportResult importExcel(
			ExcelType excelType, InputStream inputStream, Class<T> clazz,
			BatchProcessor<T> processor, List<ExcelImportListener> listeners) {
		// TODO Auto-generated method stub
		if(isValid()){
			return super.importExcel(excelType, inputStream, clazz, processor, listeners);
		}
		return this.importExcelNoValid(excelType, inputStream, clazz, processor, listeners);
	}
	
	/**
	 * @param excelType
	 * @param inputStream
	 * @param clazz
	 * @param processor
	 * @param listeners
	 * @return
	 */
	public <T extends PoiBaseBo> ExcelImportResult importExcelNoValid(
			ExcelType excelType, InputStream inputStream, Class<T> clazz,
			BatchProcessor<T> processor, List<ExcelImportListener> listeners) {
		visitorStart(listeners,clazz);
		ValidateMeta validateMeta = getClassValiateMeta(clazz);
		Workbook workbook = getTempleteWorkBook(excelType, inputStream);
		int sheetAt = PoiAnnotationResolver.getSheetAt(clazz);
		Sheet sheet = workbook.getSheetAt(sheetAt);
		int rowStart = PoiAnnotationResolver.getRowStart(clazz);
		int columnStart = PoiAnnotationResolver.getColumnStart(clazz);
		int rowEnd = PoiAnnotationResolver.getRowEnd(clazz);
		int columnEnd = PoiAnnotationResolver.getColumnEnd(clazz);
		rowEnd =rowEnd == -1 ? sheet.getLastRowNum() : rowEnd;
		columnEnd = columnEnd == -1 ? Integer.MAX_VALUE : columnEnd;
		
		ExcelImportResult processValidateResults = new ExcelImportResult(super.getFileRepository());
		
		int recordCount = rowEnd-rowStart + 1;
		int realCount = recordCount;
		int batchCount = (int)Math.ceil((double)realCount/super.getBatchSize());
		if (batchCount == 1) {
			super.setBatchSize(realCount);
		}
		this.setCountDownLatch(new CountDownLatch(batchCount));
		List<ValidateResult<T>> batchValidates = new ArrayList<ValidateResult<T>>();
		for (int i = 0; i < batchCount; i++) {
			int batchStart = (i) * super.getBatchSize() + rowStart;
			int batchEnd = (i + 1) * super.getBatchSize() + rowStart - 1;
			if (batchEnd - rowStart + 1 > realCount) {
				batchEnd = realCount + rowStart-1;
			}
			List<T> batchRecord = batchRecord(columnStart, columnEnd, batchStart, batchEnd, sheet, clazz);
			List<ValidateResult<T>> batchValidate = batchValidate(validateMeta,columnStart, columnEnd,batchStart, batchEnd, sheet, clazz, batchRecord,0);
			batchValidates.addAll(batchValidate);
			int batchEndFlag = (i + 1) * super.getBatchSize();
			if(batchEndFlag > recordCount){batchEndFlag = recordCount;};
			visitorImporting(listeners,(i) * super.getBatchSize() + 1,batchEndFlag,recordCount,clazz);
			super.getRecordProcessor().processRecords(batchValidate,processor);
		}
		processValidateResults.merge(processValidateResults(excelType, sheet, batchValidates, processor));
		visitorEnd(listeners,clazz);
		return processValidateResults;
	}
	
	
	/* (non-Javadoc)
	 * @see com.vion.core.poi.ExcelImportsService#batchValidate(com.vion.core.poi.ValidateMeta, int, java.lang.Integer, int, int, org.apache.poi.ss.usermodel.Sheet, java.lang.Class, java.util.List, int)
	 */
	@Override
	protected <T extends PoiBaseBo> List<ValidateResult<T>> batchValidate(
			ValidateMeta validateMeta, int columnStart, Integer columnEnd,
			int rowStart, int rowEnd, Sheet sheet, Class<T> clazz,
			List<T> records, int removeCount) {
		// TODO Auto-generated method stub
		if(isValid()){
			return super.batchValidate(validateMeta, columnStart, columnEnd, rowStart,
					rowEnd, sheet, clazz, records, removeCount);
		}
		else{
			List<ValidateResult<T>> validateResults = new ArrayList<ValidateResult<T>>();
			for (T record : records) {
				ValidateResult<T>  validateResult = new ValidateResult<T>();
				validateResult.setObject(record);
				validateResults.add(validateResult);
			}
			return validateResults;
		}
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	public void setCountDownLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}
}
