/**
 * 文件名：ExcelSpiElParser.java  
 *  
 * 版本信息：  
 * 日期：2015年1月22日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import com.vion.core.SortedAble;


/**
 * <b>功能描述</b> <br>
 * excel导出模板解析器,更具{@link SpiElTagContext}提供的上下文信息,将模板excel中的Tag填充成业务数据
 * @author YUJB
 * @date 2015年1月22日 下午3:24:09
 */
public class ExcelSpiElParser implements ApplicationContextAware{
	
	private transient static Logger logger = LoggerFactory.getLogger(ExcelSpiElParser.class);
	
	/** 所有的候选前边  */
	private List<ISpiElTag> candidateTags;
	
	/** 模板中标签excel的type,只能是String  */
	private final int MUST_CELL_TYPE = Cell.CELL_TYPE_STRING;
	
	
	private Map<String, ISpiElTag> tagCache = new HashMap<String, ISpiElTag>();
	
	/**
	 * 解析Sheet页
	 * @param spiElTagContext 上下文信息
	 * @param sheet sheet页
	 * @param listeners 监听器
	 */
	public  void parserSheet(SpiElTagContext spiElTagContext,Sheet sheet){
		Assert.notNull(sheet, "【excel sheet】页不能传入空！");
		spiElTagContext.visitorStart(sheet.getSheetName());
		Integer recordsCount = getRecordsCount(spiElTagContext, sheet);
		spiElTagContext.setCount(recordsCount);
		
		int firstRowNum = sheet.getFirstRowNum();
		for (int i = firstRowNum; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				int skipNum = 0;
				int firstCellNum = row.getFirstCellNum();
				int lastCellNum = row.getLastCellNum();
				/*循环当前行每一个单元格*/
				for (int j = firstCellNum; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						InflConfig inflConfig = parserCell(spiElTagContext,cell);
						skipNum = inflConfig.getSkipNum() != 0 ? inflConfig
								.getSkipNum() : skipNum;
						if(InflConfig.BREAK_TRUE == inflConfig.getBreakFlag()){
							break;
						}
					}
				}
				i += skipNum;
			}
		}
		
		spiElTagContext.visitorEnd(sheet.getSheetName());
	}
	
	
	public Integer getRecordsCount(SpiElTagContext spiElTagContext,Sheet sheet){
		int records = 0;
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				int firstCellNum = row.getFirstCellNum();
				int lastCellNum = row.getLastCellNum();
				/*循环当前行每一个单元格*/
				for (int j = firstCellNum; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						if(MUST_CELL_TYPE == cell.getCellType()){
							ISpiElTag spiElTag = getExplicitTag(PoiUtils.getCellValue(cell));
							if (spiElTag != null) {
								records += spiElTag.recordCount(spiElTagContext, cell);
							}
						}
					}
				}
			}
		}
		return records;
	}
	
	
	/**
	 * 解析单元格,从候选标签中找到合适的标签解析,如果没有找到合适的解析器,忽略此单元格的解析。
	 * @param spiElTagContext 上下文信息
	 * @param cell 单元格
	 * @return 影响配置心想你
	 */
	public InflConfig parserCell(SpiElTagContext spiElTagContext,Cell cell){
		InflConfig noInflConfig = InflConfig.noInflConfig();
		if(MUST_CELL_TYPE == cell.getCellType()){
			ISpiElTag explicitTag = getExplicitTag(cell.getStringCellValue());
			if (explicitTag == null) {
				logger.debug( "excel单元格【{}】没有找到ISpiElTag解析【{}】",new String[]{"行：" + cell.getRowIndex() + "列："  + cell.getColumnIndex(),cell.getStringCellValue()});
				return noInflConfig;
			}
			InflConfig parseTag = explicitTag.parseTag(spiElTagContext, cell);
			return parseTag;
		}
		
		return noInflConfig;
	}
	
	
	/**
	 * 根据SpiEl表达式从候选的标签中找到合适的标签解析
	 * @param spiEl spi表达式
	 * @return 合适的标签，如果没有找到返回null
	 */
	public ISpiElTag getExplicitTag(String spiEl){
		if (tagCache.containsKey(spiEl)) {
			return tagCache.get(spiEl);
		}
		for (ISpiElTag spiElTag : candidateTags) {
			if(spiElTag.isSupport(spiEl)){
				tagCache.put(spiEl, spiElTag);
				return spiElTag;
			}
		}
		tagCache.put(spiEl, null);
		return null;
	}


	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)*/
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		Map<String, ISpiElTag> beanTags = applicationContext.getBeansOfType(ISpiElTag.class);
		candidateTags = new ArrayList<ISpiElTag>();
		candidateTags.addAll(beanTags.values());
		java.util.Collections.sort(candidateTags, new Comparator<ISpiElTag>(){

			@Override
			public int compare(ISpiElTag o1, ISpiElTag o2) {
				return ((SortedAble)o1).sortNumber() - ((SortedAble)o2).sortNumber();
			}
			
		});
	}
	
	
}
