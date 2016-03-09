/**
 * 文件名：PoiBaseBo.java  
 *  
 * 版本信息：  
 * 日期：2014年11月25日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.ReflectionUtils;


/**
 * <b>功能描述</b> <br>
 * 
 * @author YUJB
 * @date 2014年11月25日 上午9:22:41
 */
public class PoiBaseBo {
	
	private Row row;
	
	private Integer discriminator;
	
	
	public PoiBaseBo() {
		super();
	}

	public PoiBaseBo(Row row) {
		super();
		this.row = row;
	}
	
	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}
	
	/** 
	 * 设置discriminator 
	 * @param discriminator discriminator 
	 */
	void setDiscriminator(Integer discriminator) {
		this.discriminator = discriminator;
	}

	public Object getColumnValue(int column){
		Field field = PoiAnnotationResolver.getFieldByColumn(getClass(), column);
		field.setAccessible(true);
		try {
			return field.get(this);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void validateErrorStyle(int columnIndex,List<String> errorMsgs,Drawing drawing){
		Cell cell = PoiUtils.getCell(row, columnIndex);
		setComment(cell,toString(errorMsgs),drawing);
		Row row = cell.getRow();
		Sheet sheet = row.getSheet();
		Workbook workbook = sheet.getWorkbook();
		CellStyle style = workbook.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(CellStyle.ALIGN_FILL);
		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);
		cell.setCellStyle(style);
	}
	
	private String toString(List<String> errorMsgs){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < errorMsgs.size(); i++) {
			sb.append((i + 1) + "." + errorMsgs.get(i) + "\n");
		}
		return sb.toString();
	}
	
	
	public void validateErrorStyle(String fieldName,Drawing drawing){
		Field field = ReflectionUtils.findField(getClass(), fieldName);
		Integer column = PoiAnnotationResolver.getColumnByField(getClass(),field);
		Cell cell = row.getCell(column);
		setComment(cell,PoiAnnotationResolver.getFieldValidateMsg(field),drawing);
		Row row = cell.getRow();
		Sheet sheet = row.getSheet();
		Workbook workbook = sheet.getWorkbook();
		CellStyle style = workbook.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(CellStyle.ALIGN_FILL);
		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);
		cell.setCellStyle(style);
	}
	
	
	protected void setComment(Cell cell, String message,Drawing drawing) {
		
	    CreationHelper factory = cell.getSheet().getWorkbook()
	            .getCreationHelper();
	    ClientAnchor anchor = factory.createClientAnchor();
	    anchor.setRow1(cell.getRowIndex());
	    anchor.setRow2(cell.getRowIndex() + 3);
	    anchor.setCol1(cell.getColumnIndex());
	    anchor.setCol2(cell.getColumnIndex() + 2);
	    anchor.setAnchorType(ClientAnchor.MOVE_AND_RESIZE);
	    Comment comment = drawing.createCellComment(anchor);
	    RichTextString str = factory.createRichTextString(message);
	    comment.setString(str);
	    cell.setCellComment(comment);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((discriminator == null) ? 0 : discriminator.hashCode());
		result = prime * result + ((row == null) ? 0 : row.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PoiBaseBo other = (PoiBaseBo) obj;
		if (discriminator == null) {
			if (other.discriminator != null)
				return false;
		} else if (!discriminator.equals(other.discriminator))
			return false;
		if (row == null) {
			if (other.row != null)
				return false;
		} else if (!row.equals(other.row))
			return false;
		return true;
	}

	
	
	
	
	
	
}
