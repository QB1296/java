/**
 * 文件名：ChartExcelExportHelper.java  
 *  
 * 版本信息：  
 * 日期：2014年3月24日  
 * Copyright(c) 2013 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;

import com.vion.core.SystemContext;
import com.vion.core.util.Classes;
import com.vion.core.util.Strings;


/**
 * <b>功能描述</b> <br>
 * Excel导入服务
 * @author YUJB
 * @date 2014年3月24日 下午4:22:03
 */
public class ExcelImportsService{
	
	
	private final static Log logger = LogFactory.getLog(ExcelImportsService.class);
	
	/** 批量处理数  */
	private int batchSize = Integer.MAX_VALUE;
	
	/** 验证器  */
	private Validator validator;
	
	/** 文件仓库  */
	private FileRepository fileRepository;
	
	/** 到处数据处理器  */
	private ExcelRecordProcessor recordProcessor;
	
	/** 临时文件路径  */
	private String tempFilePath = "/excel/files/"; 
	
	
	public ExcelImportsService() {
		super();
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		validator = vf.getValidator();
	}


	/**
	 * 得到workBook
	 * @param excelType excel类型
	 * @param inputStream 输入流
	 * @return workbook
	 */
	public Workbook getTempleteWorkBook(ExcelType excelType,InputStream inputStream){
		Workbook workbook = null;
		try {
			if (excelType.equals(ExcelType.xls)) {
				workbook = new HSSFWorkbook(inputStream);
			}else {
				workbook = new XSSFWorkbook(inputStream);
			}
		} catch (IOException e) {
			logger.info("文件流:" + inputStream + "不存在,或被其他程序占用....");
		} 
		
		return workbook;
	}
	
	
	/**
	 * 得到批量处理记录
	 * @param columnStart 起始列
	 * @param columnEnd 截止列
	 * @param rowStart 起始行
	 * @param rowEnd 截止行
	 * @param sheet sheet页
	 * @param clazz bo类型 class
	 * @return 导出的批量数据 
	 */
	protected <T extends PoiBaseBo> List<T> batchRecord(int columnStart,Integer columnEnd,int rowStart,int rowEnd,Sheet sheet,Class<T> clazz){
		List<T> rets = new ArrayList<T>();
		for (int i = rowStart; i <= rowEnd; i++) {
			Row row = sheet.getRow(i);
			if(row == null || row.getZeroHeight()){
				continue;
			}
			T object = Classes.constructorNewInstance(clazz,row);
			for (int j = columnStart; j <= columnEnd; j++) {
				Cell cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				Field field = PoiAnnotationResolver.getFieldByColumn(clazz, j);
				String cellValue = PoiUtils.getCellValue(cell);
				fieldSetValue(field, object, cellValue);
			}
			rets.add(object);
		}
		return rets;
	}
	
	
	/**
	 * 验证单元格
	 * @param sheet sheet页
	 * @param rowNum 行
	 * @param columnNum 列
	 * @param clazz 
	 * @param records 当前批处理的数据集合
	 * @return 验证结果
	 */
	private  <T extends PoiBaseBo> ValidateResult<T> validate(ValidateMeta meta,Sheet sheet,int rowNum,int columnNum,Class<T> clazz,List<T> records){
		
		ValidateResult<T>  validateResult = new ValidateResult<T>();
		
		Field field = PoiAnnotationResolver.getFieldByColumn(clazz, columnNum);
		String fieldValidate = meta.getColumnNumFieldValidateMapper().get(columnNum);
		PoiFieldRemoteValidate poiFieldRemoteValidate = meta.getColumnNumRemoteValidateMapper().get(columnNum);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(columnNum);
		T object = Classes.constructorNewInstance(clazz,row);
		T realObject = records.get(records.indexOf(object));
		validateResult.setObject(realObject);
		String cellValue = PoiUtils.getCellValue(cell);
		
		/*自定义注解 验证*/
		if (!Strings.isEmpty(cellValue) && !Strings.isEmpty(fieldValidate)) {
			boolean matches = cellValue.matches(fieldValidate);
			if (!matches) {
				validateResult.setSuccess(false);
				validateResult.addErrorMsg(PoiAnnotationResolver.getFieldValidateMsg(field));
				validateResult.setColumnNum(columnNum);
			}
		}
		if (poiFieldRemoteValidate != null) {
			ApplicationContext applicationContext = SystemContext.getApplicationContext();
			Object bean = applicationContext.getBean(poiFieldRemoteValidate.clazz());
			if(bean != null){
				try {
					Object invoke = null;
					Method method = MethodUtils.getAccessibleMethod(bean.getClass(), poiFieldRemoteValidate.method(), new Class[]{realObject.getClass(),List.class});
					if (method == null) {
						method = MethodUtils.getAccessibleMethod(bean.getClass(), poiFieldRemoteValidate.method(), new Class[]{realObject.getClass()});
						invoke = method.invoke(bean, realObject);
					}else {
						invoke = method.invoke(bean, realObject,records);
					}
					if (!(boolean) invoke) {
						validateResult.setSuccess(false);
						validateResult.setColumnNum(columnNum);
						validateResult.addErrorMsg(PoiAnnotationResolver.getFieldRemoteValidateMsg(field));
					}
					
				} catch (IllegalAccessException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		Field currentField = meta.getColumnNumFieldMapper().get(columnNum);
		
		/*javaEE 验证*/
		Set<ConstraintViolation<T>> validateRets = validator.validateProperty(realObject, currentField.getName());
		StringBuffer stringBuffer = new StringBuffer();
		for (ConstraintViolation<T> constraintViolation : validateRets) {
			String message = constraintViolation.getMessage();
			stringBuffer.append(message);
		}
		if (!Strings.isEmpty(stringBuffer)) {
			validateResult.setSuccess(false);
			validateResult.setColumnNum(columnNum);
			validateResult.addErrorMsg(stringBuffer.toString());
		}
		return validateResult;
	}
	
	
	
	/**
	 * 处理验证结果
	 * @param excelType excel类型 xls、xlsx
	 * @param sheet
	 * @param validateResults
	 * @param processor
	 * @return
	 */
	protected <T extends PoiBaseBo> ExcelImportResult processValidateResults(ExcelType excelType,Sheet sheet,List<ValidateResult<T>> validateResults,BatchProcessor<T> processor){
		
		ExcelImportResult result = new ExcelImportResult(fileRepository);
		
		boolean isAllSuccess = true;
		int successCount = 0;
		Drawing drawing = sheet.createDrawingPatriarch();
		
		Map<T, Boolean> map = new HashMap<T, Boolean>(); 
		for (ValidateResult<T> validateResult : validateResults) {
			if (validateResult.isSuccess()) {
				if (map.get(validateResult.getObject()) == null) {
					map.put(validateResult.getObject(), true);
				}
			}else {
				map.put(validateResult.getObject(), false);
				isAllSuccess = false;
				validateResult.getObject().validateErrorStyle(validateResult.getColumnNum(),validateResult.getErrorMsgs(),drawing);
			}
		}
		for (Boolean bool : map.values()) {
			if (bool) {
				successCount ++;
			}
		}
		result.setAllSuccess(isAllSuccess);
		result.setErrorCount(map.values().size() - successCount);
		result.setSuccessCount(successCount);
		
		return result;
	}
	
	
	protected void tempErrorExcel(Workbook workbook,OutputStream os){
		try {
			workbook.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	protected <T extends PoiBaseBo> List<ValidateResult<T>> batchValidate(ValidateMeta validateMeta,int columnStart,Integer columnEnd,int rowStart,int rowEnd,Sheet sheet,Class<T> clazz,List<T> records,int removeCount){
		
		List<ValidateResult<T>> validateResults = new ArrayList<ValidateResult<T>>();
		
		for (int i = rowStart; i <= rowEnd; i++) {
			boolean isSuccess = true;
			List<ValidateResult<T>> rowValidateResults = new ArrayList<ValidateResult<T>>();
			for (int j = columnStart; j <= columnEnd; j++) {
				ValidateResult<T> validate = validate(validateMeta,sheet, i, j, clazz,records);
				if (!validate.isSuccess()) {
					isSuccess = false;
				}
				rowValidateResults.add(validate);
			}
			if (isSuccess) {
				boolean currentRowDel = false;
				for (T record : records) {
					if(record.getRow() != null){
						int rowNum = record.getRow().getRowNum();
						if(rowNum > i){
							record.getRow().setRowNum(rowNum - 1);
						}else if(rowNum == i && !currentRowDel){
							record.setRow(null);
							currentRowDel = true;
							record.setDiscriminator(removeCount);
						}
					}
				}
				PoiUtils.removeRow(sheet, i);
				removeCount ++;
				if(rowStart == rowEnd){
					validateResults.addAll(rowValidateResults);
					break;
				}
				i--;
				rowEnd--;
			}
			validateResults.addAll(rowValidateResults);
		}
		return validateResults;
	}
	
	
	/**
	 * 导入excel
	 * @param excelType excel类型
	 * @param inputStream 输入流
	 * @param clazz
	 * @param processor
	 * @return
	 */
	public <T extends PoiBaseBo> ExcelImportResult importExcel(ExcelType excelType,InputStream inputStream,Class<T> clazz,BatchProcessor<T> processor,List<ExcelImportListener> listeners) {
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
		
		ExcelImportResult processValidateResults = new ExcelImportResult(fileRepository);
		
		int recordCount = rowEnd-rowStart + 1;
		int realCount = recordCount;
		int batchCount = (int)Math.ceil((double)realCount/batchSize);
		if (batchCount == 1) {
			batchSize = realCount;
		}
		int removeCount = 0;
		int subRemoveCount = 0;
		List<ValidateResult<T>> batchValidates = new ArrayList<ValidateResult<T>>();
		for (int i = 0; i < batchCount; i++) {
			int batchStart = (i) * batchSize + rowStart - subRemoveCount;
			int batchEnd = (i + 1) * batchSize + rowStart - 1 - subRemoveCount;
			if (batchEnd - rowStart + 1 > realCount) {
				batchEnd = realCount + rowStart-1;
			}
			List<T> batchRecord = batchRecord(columnStart, columnEnd, batchStart, batchEnd, sheet, clazz);
			List<ValidateResult<T>> batchValidate = batchValidate(validateMeta,columnStart, columnEnd,batchStart, batchEnd, sheet, clazz, batchRecord,subRemoveCount);
			batchValidates.addAll(batchValidate);
			removeCount = getSuccessRowCount(batchValidate);
			subRemoveCount +=removeCount;
			realCount -= removeCount;
			int batchEndFlag = (i + 1) * batchSize;
			if(batchEndFlag > recordCount){batchEndFlag = recordCount;};
			visitorImporting(listeners,(i) * batchSize + 1,batchEndFlag,recordCount,clazz);
			recordProcessor.processRecords(batchValidate,processor);
		}
		processValidateResults.merge(processValidateResults(excelType, sheet, batchValidates, processor));
		if (processValidateResults.getErrorCount() > 0) {
			String filePath = tempFilePath + "/" + new Date().getTime() +"." +  excelType.name();
			try {
				fileRepository.saveFile(filePath);
				OutputStream outputStream = fileRepository.getOutputStream(filePath);
				tempErrorExcel(sheet.getWorkbook(),outputStream);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			processValidateResults.setErrorFilePath(filePath);
		}
		
		visitorEnd(listeners,clazz);
		return processValidateResults;
	}
	
	
	
	private void fieldSetValue(Field field,Object object,String cellValue){
		field.setAccessible(true);
		try {
			if (field.getType() == Long.class) {
				field.set(object, Long.valueOf(cellValue));
			}else if(field.getType() == Double.class){
				field.set(object,  Double.valueOf(cellValue));
			}else if(field.getType() == int.class){
				field.set(object,  Integer.valueOf(cellValue));
			}else if(field.getType() == float.class){
				field.set(object,  Float.valueOf(cellValue));
			}else if(field.getType() == Date.class){
				field.set(object,  DateUtils.parseDate(cellValue, new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd","HH:mm:ss"}));
			}else if(field.getType() == BigDecimal.class){
				field.set(object, StringUtils.isBlank(cellValue)?null:new BigDecimal(Double.valueOf(cellValue)));
			}else {
				field.set(object, cellValue);
			}
		} catch (IllegalArgumentException | IllegalAccessException | ParseException e) {
			e.printStackTrace();
		}
	
	}
	
	
	protected <T extends PoiBaseBo> void  visitorImporting(List<ExcelImportListener> listeners,int start,int end,int count,Class<T> clazz){
		if(listeners != null){
			for (ExcelImportListener listener : listeners) {
				listener.importing(start, end, count,clazz);
			}
		}
	}
	
	
	protected <T extends PoiBaseBo> void  visitorStart(List<ExcelImportListener> listeners,Class<T> clazz){
		if(listeners != null){
			for (ExcelImportListener listener : listeners) {
				listener.importStart(clazz);
			}
		}
	}
	
	protected <T extends PoiBaseBo> void  visitorEnd(List<ExcelImportListener> listeners,Class<T> clazz){
		if(listeners != null){
			for (ExcelImportListener listener : listeners) {
				listener.importEnd(clazz);
			}
		}
	}
	
	protected ValidateMeta getClassValiateMeta(Class<?> clazz){
		ValidateMeta validateMeta = new ValidateMeta();
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			Integer columnNum = PoiAnnotationResolver.getColumnByField(clazz, field);
			PoiFieldRemoteValidate poiFieldRemoteValidate = PoiAnnotationResolver.getFieldRemoteValidate(field);
			String fieldValidate = PoiAnnotationResolver.getFieldValidate(field);
			validateMeta.putColumnNumFieldMapper(columnNum, field);
			validateMeta.putColumnNumFiledValidateMapper(columnNum, fieldValidate);
			validateMeta.putColumnNumRemoteValidateMapper(columnNum, poiFieldRemoteValidate);
		}
		return validateMeta;
	}

	/**
	 * 获得batchSize 
	 * @return  batchSize batchSize
	 */
	public int getBatchSize() {
		return batchSize;
	}
	
	/** 
	 * 设置batchSize 
	 * @param batchSize batchSize 
	 */
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	
	
	/**
	 * 获得fileRepository 
	 * @return  fileRepository fileRepository
	 */
	public FileRepository getFileRepository() {
		return fileRepository;
	}


	/** 
	 * 设置fileRepository 
	 * @param fileRepository fileRepository 
	 */
	public void setFileRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}


	/**
	 * 获得tempFilePath 
	 * @return  tempFilePath tempFilePath
	 */
	public String getTempFilePath() {
		return tempFilePath;
	}


	/** 
	 * 设置tempFilePath 
	 * @param tempFilePath tempFilePath 
	 */
	public void setTempFilePath(String tempFilePath) {
		this.tempFilePath = tempFilePath;
	}

	

	/**
	 * 获得recordProcessor 
	 * @return  recordProcessor recordProcessor
	 */
	public ExcelRecordProcessor getRecordProcessor() {
		return recordProcessor;
	}


	/** 
	 * 设置recordProcessor 
	 * @param recordProcessor recordProcessor 
	 */
	public void setRecordProcessor(ExcelRecordProcessor recordProcessor) {
		this.recordProcessor = recordProcessor;
	}


	protected <T extends PoiBaseBo> Integer getSuccessRowCount(List<ValidateResult<T>> batchValidates){
		Integer count = 0;
		
		Map<T, Boolean> map = new HashMap<T, Boolean>(); 
		for (ValidateResult<T> validateResult : batchValidates) {
			if (!validateResult.isSuccess()) {
				map.put(validateResult.getObject(), false);
			}else {
				if (map.get(validateResult.getObject()) == null) {
					map.put(validateResult.getObject(), true);
				}
			}
		}
		for (Boolean bool : map.values()) {
			if (bool) {
				count ++;
			}
		}
		return count;
	}
	
	
	public  class ValidateResult<T extends PoiBaseBo>{
		
		private boolean isSuccess = true;
		
		private T object;
		
		private int columnNum;
		
		private List<String> errorMsgs;
		
		/**
		 * 获得errorMsgs 
		 * @return  errorMsgs errorMsgs
		 */
		public List<String> getErrorMsgs() {
			return errorMsgs;
		}
		
		/**
		 * 获得columnNum 
		 * @return  columnNum columnNum
		 */
		public int getColumnNum() {
			return columnNum;
		}
		
		/** 
		 * 设置columnNum 
		 * @param columnNum columnNum 
		 */
		public void setColumnNum(int columnNum) {
			this.columnNum = columnNum;
		}

		/**
		 * 获得isSuccess 
		 * @return  isSuccess isSuccess
		 */
		public boolean isSuccess() {
			return isSuccess;
		}

		/** 
		 * 设置isSuccess 
		 * @param isSuccess isSuccess 
		 */
		public void setSuccess(boolean isSuccess) {
			this.isSuccess = isSuccess;
		}

		/**
		 * 获得object 
		 * @return  object object
		 */
		public T getObject() {
			return object;
		}

		/** 
		 * 设置object 
		 * @param object object 
		 */
		public void setObject(T object) {
			this.object = object;
		}

		/**
		 * 增加错误信息
		 * @param errorMsg
		 */
		public void addErrorMsg(String errorMsg){
			if(this.errorMsgs == null){
				this.errorMsgs = new ArrayList<String>();
			}
			this.errorMsgs.add(errorMsg);
		}
		
		
		
	}


	
}
