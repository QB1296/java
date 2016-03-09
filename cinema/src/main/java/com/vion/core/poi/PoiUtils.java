/**
 * 文件名：PoiUtils.java  
 *  
 * 版本信息：  
 * 日期：2015年1月21日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */

package com.vion.core.poi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vion.core.exception.NoSupportedException;
import com.vion.core.util.Strings;

/**
 * <b>功能描述</b> <br>
 * 
 * @author YUJB
 * @date 2015年1月21日 下午12:36:47
 */
public class PoiUtils {
	
	
	/**
	 * 得到数组数量
	 * @param obj 数组对象 Array Collection
	 * @return 数据数量,如果不是数组抛出{@link NoSupportedException}
	 */
	public static Integer getArrayCount(Object obj){
		if (obj == null) {
			return 0;
		}
		if (obj instanceof Collection<?>) {
			return ((Collection<?>)obj).size();
		}
		if (obj.getClass().isArray()) {
			return ((Object[])obj).length;
		}
		throw new NoSupportedException("不支持" + obj + "#foreach");
	}
	
	
	/**
	 * 得到矩阵水平数量
	 * @param obj Array 或 Collection 二维数组
	 * @return 数据数量,如果不是数组抛出{@link NoSupportedException}
	 */
	public static Integer getMatrixHCount(Object obj){
		if (obj == null) {
			return 0;
		}
		if (obj instanceof Collection<?>) {
			Iterator<?> iterator = ((Collection<?>)obj).iterator();
			if (iterator.hasNext()) {
				Object next = iterator.next();
				return getArrayCount(next);
			}else {
				return 0;
			}
			
		}
		if (obj.getClass().isArray()) {
			Object[] objArray = (Object[])obj;
			if (objArray.length > 0) {
				return getArrayCount(((Object[])obj)[0]);
			}else {
				return 0;
			}
		}
		throw new NoSupportedException("不支持" + obj + "#foreach");
	}
	
	
	/**
	 * 得到矩阵垂直数量
	 * @param obj Array 或 Collection 二维数组
	 * @return 数据数量,如果不是数组抛出{@link NoSupportedException}
	 */
	public static Integer getMatrixVCount(Object obj){
		return getArrayCount(obj);
	}
	
	
	/**
	 * 同一个sheet页内拷贝行 
	 * @param sheet sheet页
	 * @param firstrow 起始行
	 * @param lastrow 结束行
	 * @param newFirstrow 拷贝的起始行
	 */
	public static void copyRows(Sheet sheet,int firstrow, int lastrow,int newFirstrow) {
		copyRows(sheet,sheet,firstrow,lastrow,newFirstrow);
		
	}
	
	
	/**
	 * 同一个sheet页内拷贝行 
	 * @param sheet sheet页
	 * @param firstrow 起始行
	 * @param lastrow 结束行
	 * @param newFirstrow 拷贝的起始行
	 */
	public static void copyRows(Sheet sheet,int firstrow, int lastrow,int newFirstrow,List<CellRangeAddress> ranges) {
		copyRows(sheet,sheet,firstrow,lastrow,newFirstrow,ranges);
		
	}
	
	/**
	 * 不同sheet页拷贝行
	 * @param fromsheet 拷贝sheet页
 	 * @param newsheet  待拷贝sheet页
	 * @param firstrow 起始行
	 * @param lastrow 结束行
	 * @param newFirstrow 拷贝的起始行
	 */
	public static void copyRows(Sheet fromsheet,
			Sheet newsheet, int firstrow, int lastrow,int newFirstrow) {
		copyRows(fromsheet, newsheet,firstrow, lastrow, newFirstrow,null);
	}

	/**
	 * 不同sheet页拷贝行
	 * @param fromsheet 拷贝sheet页
 	 * @param newsheet  待拷贝sheet页
	 * @param firstrow 起始行
	 * @param lastrow 结束行
	 * @param newFirstrow 拷贝的起始行
	 */
	public static void copyRows(Sheet fromsheet,
			Sheet newsheet, int firstrow, int lastrow,int newFirstrow,List<CellRangeAddress> ranges) {
		if ((firstrow == -1) || (lastrow == -1) || lastrow < firstrow) {
			return;
		}
		
		
		Row fromRow = null;
		Row newRow = null;
		Cell fromCell = null;

		for (int i = firstrow; i <= lastrow; i++) {
			fromRow = fromsheet.getRow(i);
			if (fromRow != null) {
				short firstCellNum = fromRow.getFirstCellNum();
				short lastCellNum = fromRow.getLastCellNum();
				for (short j = lastCellNum; j >= firstCellNum; j--) {
					int colnum = fromsheet.getColumnWidth((short) j);
					if (colnum > 100) {
						newsheet.setColumnWidth((short) j, (short) colnum);
					}
					if (colnum == 0) {
						newsheet.setColumnHidden((short) j, true);
					} else {
						newsheet.setColumnHidden((short) j, false);
					}
				}
				break;
			}
		}
		
		for (int i = firstrow; i <= lastrow; i++) {
			fromRow = fromsheet.getRow(i);
			if (fromRow == null) {
				continue;
			}
			int newRowIndex = newFirstrow + i - firstrow;
			newRow = newsheet.getRow(newRowIndex);
			if (newRow != null) {
				shiftRows(newsheet,newRowIndex, newsheet.getLastRowNum(), 1,ranges);
				newRow = newsheet.createRow(newRowIndex);
			} else {
			    newRow = newsheet.createRow(newRowIndex);
			}
			newRow.setHeight(fromRow.getHeight());
			for (int j = fromRow.getFirstCellNum(); j < fromRow
					.getPhysicalNumberOfCells(); j++) {
				
				fromCell = fromRow.getCell(j);
				copyCell(fromCell,newsheet,newRow.getRowNum(),j);
			}
		}
		
		List<CellRangeAddress> shiftedRegions = new ArrayList<CellRangeAddress>();
		for (int i = 0; i < fromsheet.getNumMergedRegions(); i++) {
			CellRangeAddress region = fromsheet.getMergedRegion(i);
			if (ranges != null && ranges.contains(region)) {
				continue;
			}
			if ((region.getFirstRow() >= firstrow)
					&& (region.getLastRow() <= lastrow)) {
				CellRangeAddress n_r = new CellRangeAddress(newFirstrow - firstrow + region.getFirstRow(),
						newFirstrow - firstrow + region.getLastRow(),
						region.getFirstColumn(),
						region.getLastColumn());
				shiftedRegions.add(n_r);				
			}
		}
		
		Iterator<CellRangeAddress> iterator = shiftedRegions.iterator();
		while (iterator.hasNext()) {
			CellRangeAddress region = (CellRangeAddress) iterator.next();
			newsheet.addMergedRegion(region);
		}		
		
		
	}
	
	
    public static boolean containsCell(CellRangeAddress cr, int rowIx, int colIx) {
        if (cr.getFirstRow() <= rowIx && cr.getLastRow() >= rowIx
                && cr.getFirstColumn() <= colIx && cr.getLastColumn() >= colIx) {
            return true;
        }
        return false;
    }
    
    
    public static boolean inOneRange(Sheet sheet,Cell... cells) {
    	boolean flag = false;
    	for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
    		CellRangeAddress region = sheet.getMergedRegion(i);
    		if (flag == true) {
				break;
			}
    		flag = true;
			for (Cell cell : cells) {
				if(!containsCell(region,cell.getRowIndex(),cell.getColumnIndex())){
					flag = false;
					break;
				}
			}
    	}
    	return flag;
    }
	
	
	public static void shiftRows(Sheet sheet,int startRow, int endRow, int n,List<CellRangeAddress> ranges){
		
		List<CellRangeAddress> shiftFixedRegins = new ArrayList<CellRangeAddress>();
		
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress region = sheet.getMergedRegion(i);
			boolean isShiftMergeCell = false;
			if (n<0) {
				isShiftMergeCell = (region.getFirstRow() < startRow)
						&& (region.getLastRow() >= startRow -1);
			}else {
				isShiftMergeCell = (region.getFirstRow() < startRow)
						&& (region.getLastRow() >= startRow);
			}
			if (isShiftMergeCell) {
				int lastRow = region.getLastRow()  + n;
				if (lastRow >= region.getFirstRow()) {
					CellRangeAddress n_r = new CellRangeAddress(region.getFirstRow(),
							region.getLastRow()  + n,
							region.getFirstColumn(),
							region.getLastColumn());
					shiftFixedRegins.add(n_r);			
				}
				sheet.removeMergedRegion(i);
				i--;
			}
		}
		for (CellRangeAddress cellRangeAddress : shiftFixedRegins) {
			sheet.addMergedRegion(cellRangeAddress);
		}
		sheet.shiftRows(startRow, endRow, n);
	}
	
	
	public static void shiftRows(Sheet sheet,int startRow, int endRow, int n){
		shiftRows(sheet, startRow, endRow, n, null);
	}
	
	
	public static void setBackGroundColor(CellStyle style,Workbook workbook,int r,int g, int b){
		if (workbook instanceof XSSFWorkbook ) {
		    XSSFCellStyle style1 = (XSSFCellStyle)style;
		    style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(r, g, b)));
		    style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		}
		if (workbook instanceof HSSFWorkbook ) {
			HSSFWorkbook wb = (HSSFWorkbook)workbook;
			HSSFPalette palette = wb.getCustomPalette();
		    palette.setColorAtIndex(HSSFColor.AUTOMATIC.index, (byte) r, (byte) g, (byte) b);
		    style.setFillBackgroundColor(HSSFColor.AUTOMATIC.index);
		}
	}
	
	/**
	 * 拷贝单元格
	 * @param fromCell 拷贝单元格
	 * @param newSheet sheet页
	 * @param rowNum  行
	 * @param CellRangeAddress 范围
	 */
	public static void copyCell(Cell fromCell,Sheet newSheet,CellRangeAddress range) {
		for (int i = range.getFirstRow(); i <= range.getLastRow(); i++) {
			for (int j = range.getFirstColumn(); j <= range.getLastColumn(); j++) {
				copyCell(fromCell,newSheet,i,j);
			}
		}
	}
	
	
	public static void copyCellStyle(Cell cell,Sheet sheet,Cell fromCell){
		if (fromCell.getSheet().equals(sheet)) {
			boolean inOneRange = inOneRange(sheet,cell,fromCell);
			if (inOneRange) {
				//return;
			}
			cell.setCellStyle(fromCell.getCellStyle());
		}else {
			CellStyle newStyle = sheet.getWorkbook().createCellStyle();
			newStyle.cloneStyleFrom(fromCell.getCellStyle());
			cell.setCellStyle(newStyle);
		}
	}
	
	
	
	public static void copyRowStyle(Row row, Sheet sheet, Row fromRow) {
		copyRowStyle(row, sheet, fromRow,null,null);
	}
	
	
	public static void copyRowStyle(Row row, Sheet sheet, Row fromRow,
			Integer startCol, Integer endCol) {
		startCol = startCol == null ? fromRow.getFirstCellNum() : startCol;
		endCol = endCol == null ? fromRow.getPhysicalNumberOfCells() : endCol;
		for (int j = startCol; j < endCol; j++) {
			Cell fromCell = fromRow.getCell(j);
			if (fromCell != null) {
				copyCellStyle(PoiUtils.getCell(row, j), sheet, fromCell);
			}
		}
	}
	
	/**
	 * 拷贝单元格
	 * @param fromCell 拷贝单元格
	 * @param newSheet sheet页
	 * @param rowNum  行
	 * @param columnNum 列 和行共同确定待拷贝的单元格
	 * @return 新的单元格
	 */
	public static Cell copyCell(Cell fromCell,Sheet newSheet,int rowNum,int columnNum) {
		if (fromCell == null) {
			return null;
		}
		Row newRow = getRow(rowNum, newSheet);
		Cell newCell = newRow.createCell(columnNum);
		copyCellStyle(newCell,newSheet,fromCell);
		int cType = fromCell.getCellType();
		newCell.setCellType(cType);
		switch (cType) {
		case Cell.CELL_TYPE_STRING:
			newCell.setCellValue(fromCell.getRichStringCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			newCell.setCellValue(fromCell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			newCell.setCellFormula(fromCell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			newCell.setCellValue(fromCell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_ERROR:
			newCell.setCellValue(fromCell.getErrorCellValue());
			break;
		default:
			newCell.setCellValue(fromCell.getRichStringCellValue());
			break;
		}
		return newCell;
	}
	
	

	/**
	 * 拷贝sheet页
	 * @param fromsheet sheet
	 * @param wb 待拷贝的workbook
	 * @return 新的sheet页
	 */
	public static Sheet copySheet(Sheet fromsheet,Workbook wb) {
		Sheet newsheet = wb.createSheet(fromsheet.getSheetName());
		newsheet.setMargin(Sheet.TopMargin,fromsheet.getMargin(Sheet.TopMargin));
		newsheet.setMargin(Sheet.BottomMargin,fromsheet.getMargin(Sheet.BottomMargin));
		newsheet.setMargin(Sheet.LeftMargin,fromsheet.getMargin(Sheet.LeftMargin));
		newsheet.setMargin(Sheet.RightMargin,fromsheet.getMargin(Sheet.RightMargin));

		PrintSetup ps = newsheet.getPrintSetup();
		ps.setLandscape(false);
		ps.setVResolution((short) 600);
		ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
		copyRows(fromsheet, newsheet, fromsheet.getFirstRowNum(),
				fromsheet.getLastRowNum(),fromsheet.getFirstRowNum());
		return newsheet;
	}

	
	/**
	 * 合并单元格
	 * @param sheet sheet页
	 * @param firstRow 起始行
	 * @param endRow 截止行
	 * @param firstColumn 起始列
	 * @param endClumn 截止列
	 */
	public static void mergedCells(Sheet sheet,int firstRow,int endRow,int firstColumn,int endClumn){
		CellRangeAddress newRange = new CellRangeAddress(firstRow,
				endRow,
				firstColumn,
				endClumn);
		sheet.addMergedRegion(newRange);
	}
	
	/**
	 * 删除单元格
	 * @param sheet sheet页
	 * @param startRow 其实行
	 * @param endRow 截止行
	 * @param startColumn 其实列
	 * @param endColumn 截止列
	 */
	public static void removeCells(Sheet sheet, int startRow, int endRow,int startColumn, int endColumn) {
		for (int i = startRow; i <= endRow; i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				for (int j = startColumn; j < endColumn; j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						row.removeCell(cell);
					}
				}
			}
		}
	}
	
	
	/**
	 * 得到单元格的值
	 * @param cell 单元格
	 * @return 单元格的值
	 */
	public static String getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
        String ret;  
        switch (cell.getCellType()) {  
        case Cell.CELL_TYPE_BLANK:  
            ret = "";  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            ret = String.valueOf(cell.getBooleanCellValue());  
            break;  
        case Cell.CELL_TYPE_ERROR:  
            ret = null;  
            break;  
        case Cell.CELL_TYPE_FORMULA:  
            Workbook wb = cell.getSheet().getWorkbook();  
            CreationHelper crateHelper = wb.getCreationHelper();  
            FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();  
            ret = getCellValue(evaluator.evaluateInCell(cell));  
            break;  
        case Cell.CELL_TYPE_NUMERIC:  
            if (DateUtil.isCellDateFormatted(cell)) {   
                Date theDate = cell.getDateCellValue();  
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ret = simpleDateFormat.format(theDate);  
            } else {   
                ret = NumberToTextConverter.toText(cell.getNumericCellValue());  
            }  
            break;  
        case Cell.CELL_TYPE_STRING:  
            ret = cell.getRichStringCellValue().getString();  
            break;  
        default:  
            ret = null;  
        }  
        return ret; 
    }  
	
	
	public static boolean isRowInFixedRanges(Row row,List<CellRangeAddress> ranges){
		boolean flag = false;
		if (ranges != null) {
			for (CellRangeAddress range : ranges) {
				int firstRow = range.getFirstRow();
				int lastRow = range.getLastRow();
				int rowNum = row.getRowNum();
				flag = firstRow < rowNum && lastRow > rowNum;
			}
		}
		return flag;
	}
	
	
	/**
	 * 单元格所在的区域范围
	 * @param cell
	 * @return 如果没有返回空
	 */
	public static CellRangeAddress rangeByOneCell(Cell cell){
		Sheet sheet = cell.getSheet();
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
    		CellRangeAddress region = sheet.getMergedRegion(i);
			if(containsCell(region, cell.getRowIndex(), cell.getColumnIndex())){
				return region;
			}
    	}
		return null;
	}
	
	
	/**
	 * 删除行
	 * @param sheet sheet页
	 * @param rowIndex 待删除的行号
	 */
	public static void removeRow(Sheet sheet, int rowIndex) {
		int lastRowNum = sheet.getLastRowNum();
		boolean isShift = rowIndex >= 0 && rowIndex < lastRowNum;
		boolean isRemove = rowIndex == lastRowNum;
		List<CellRangeAddress> lastShiftRanges = new ArrayList<CellRangeAddress>();
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress region = sheet.getMergedRegion(i);
			if (rowIndex ==  region.getFirstRow() && region.getLastRow() > rowIndex + 1) {
				PoiUtils.copyCell(sheet.getRow(rowIndex).getCell(region.getFirstColumn()), sheet, rowIndex + 1, region.getFirstColumn());
			}
			if (isRemove && region.getLastRow() == rowIndex) {
				CellRangeAddress copy = region.copy();
				copy.setLastRow(copy.getLastRow() - 1);
				sheet.removeMergedRegion(i);
				i--;
				lastShiftRanges.add(copy);
			}
		}
		for (CellRangeAddress cellRangeAddress : lastShiftRanges) {
			sheet.addMergedRegion(cellRangeAddress);
		}
		if (isShift) {
			PoiUtils.shiftRows(sheet,rowIndex + 1, lastRowNum, -1);
		}
		if (isRemove) {
			Row removingRow = sheet.getRow(rowIndex);
			if (removingRow != null) {
				sheet.removeRow(removingRow);
			}
		}
	}
	
	/**
	 * 如果单元格是合并单元格，得到合并单元格CellRangeAddress
	 * @param cell
	 * @return 如果没有返回null
	 */
	public static CellRangeAddress getCellRange(Cell cell){
		Sheet sheet = cell.getSheet();
		for (int i = 0; i < cell.getSheet().getNumMergedRegions(); i++) {
			CellRangeAddress region = sheet.getMergedRegion(i);
			if ((region.getFirstRow() <= cell.getRowIndex())
					&& (region.getLastRow() >= cell.getRowIndex())
					&& (region.getFirstColumn() <= cell.getColumnIndex())
					&& (region.getLastColumn() >= cell.getColumnIndex())
					) {
				return region;
			}
		}
		return null;
	}
	
	
	/**
	 * 水平扩展单元格,如果单元格式合并单元格,扩展合并单元格
	 * @param cell 单元格
	 * @param i 扩展第几个 从1开始
	 * @param value 值
	 */
	public static void extendCellHorizontal(Cell cell,int i,String value){
		CellRangeAddress cellRange = PoiUtils.getCellRange(cell);
		Sheet sheet = cell.getSheet();
		int rowNum = cell.getRowIndex();
		int columnNum = cell.getColumnIndex();
		int columnIndex = i+ columnNum;
		if (cellRange == null) {
			PoiUtils.copyCell(cell, sheet, rowNum,columnIndex);
		}else {
			Integer rangeWidth = cellRange.getLastColumn() - cellRange.getFirstColumn() + 1;
			int mergedFR = cellRange.getFirstRow();
			int mergedLR = cellRange.getLastRow();
			int mergedFC = rangeWidth *(i-1) + cellRange.getLastColumn() + 1;
			columnIndex = mergedFC;
			int mergedLC = rangeWidth*i + cellRange.getLastColumn();
			CellRangeAddress cellRangeAddress = new CellRangeAddress(rowNum, rowNum, mergedFC, mergedLC);
			PoiUtils.copyCell(cell, sheet, cellRangeAddress);
			PoiUtils.mergedCells(sheet, mergedFR, mergedLR, mergedFC, mergedLC);
		}
		PoiUtils.setCellValue(sheet, rowNum, columnIndex, value);
	}
	
	
	public static void extendCellVertical(Cell cell,int i,String value){
		CellRangeAddress cellRange = PoiUtils.getCellRange(cell);
		Sheet sheet = cell.getSheet();
		int rowNum = cell.getRowIndex();
		int columnNum = cell.getColumnIndex();
		int rowIndex = i+ rowNum;
		if (cellRange == null) {
			PoiUtils.copyCell(cell, sheet, rowIndex, columnNum);
		}else {
			Integer rangeHeight = cellRange.getLastRow() - cellRange.getFirstRow() + 1;
			int mergedFC = cellRange.getFirstColumn();
			int mergedLC = cellRange.getLastColumn();
			int mergedFR = rangeHeight *(i-1) + cellRange.getLastRow() + 1;
			int mergedLR = rangeHeight*i + cellRange.getLastRow();
			CellRangeAddress cellRangeAddress = new CellRangeAddress(mergedFR, mergedLR, columnNum, columnNum);
			PoiUtils.copyCell(cell, sheet, cellRangeAddress);
			PoiUtils.mergedCells(sheet, mergedFR, mergedLR, mergedFC, mergedLC);
		}
		PoiUtils.setCellValue(sheet, rowIndex, columnNum, value);
	}
	
	
	public static void extendCellHorizontalSpecifiedIndex(Cell cell,int firstCol){
		extendCellHorizontalSpecifiedIndex(cell, firstCol, null);
	}
	
	
	public static void extendCellHorizontalSpecifiedIndex(Cell cell,int firstCol,String value){
		CellRangeAddress cellRange = PoiUtils.getCellRange(cell);
		Sheet sheet = cell.getSheet();
		int rowNum = cell.getRowIndex();
		if (cellRange == null) {
			PoiUtils.copyCell(cell, sheet, rowNum,firstCol);
		}else {
			Integer rangeWidth = cellRange.getLastColumn() - cellRange.getFirstColumn() + 1;
			int mergedFR = cellRange.getFirstRow();
			int mergedLR = cellRange.getLastRow();
			int mergedFC = firstCol;
			int mergedLC = firstCol + rangeWidth -1 ;
			CellRangeAddress cellRangeAddress = new CellRangeAddress(mergedFR, mergedLR, mergedFC, mergedLC);
			PoiUtils.copyCell(cell, sheet, cellRangeAddress);
			PoiUtils.mergedCells(sheet, mergedFR, mergedLR, mergedFC, mergedLC);
		}
		if (!Strings.isEmpty(value)) {
			PoiUtils.setCellValue(sheet, rowNum, firstCol, value);
		}
	}
	
	/**
	 * 删除行
	 * @param sheet sheet页
	 * @param firstIndex 起始行索引
	 * @param lastRowIndex 截止行索引
	 */
	public static void removeRow(Sheet sheet, int firstIndex, int lastRowIndex) {
		List<Integer> removeRegionIndexs = new ArrayList<Integer>();
		
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress region = sheet.getMergedRegion(i);
			if ((region.getFirstRow() >= firstIndex)
					&& (region.getLastRow() <= lastRowIndex)) {
				removeRegionIndexs.add(i);
			}
		}
		for (Integer removeRegionIndex : removeRegionIndexs) {
			sheet.removeMergedRegion(removeRegionIndex);	
		}
		
		for (int i = 0; i <= lastRowIndex-firstIndex; i++) {
			removeRow(sheet, firstIndex);
		}
	}
	
	
	/**
	 * 设置单元格值
	 * @param sheet sheet页
	 * @param rowNum 行
	 * @param columnNum 列
	 * @param value 值 
	 */
	public static void setCellValue(Sheet sheet,int rowNum,int columnNum,Object value){
		Row row = getRow(rowNum, sheet);
		Cell cell = getCell(row, columnNum);
		setCellValue(cell, value);
	}
	
	
	/**
	 * 设置单元格值
	 * @param sheet sheet页
	 * @param rowNum 行
	 * @param columnNum 列
	 * @param value 值 
	 */
	public static void setCellValue(Cell cell,Object value){
		cell.setCellType(Cell.CELL_TYPE_STRING);
		if (value instanceof RichTextString) {
			cell.setCellValue((RichTextString)value);
		}else if (value instanceof Date) {
			cell.setCellValue((Date)value);
		}else if (value instanceof Boolean) {
			cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
			cell.setCellValue((Boolean)value);
		}else if (value instanceof Number) {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(Double.valueOf(value.toString()));
		}else if (value instanceof Calendar) {
			cell.setCellValue((Calendar)value);
		}else {
			cell.setCellValue(value.toString());
		}
	}
	
	
	/**
	 * 得到行,如果为null直接创建行返回
	 * @param rowCounter 行号
	 * @param sheet sheet页
	 * @return 单元格
	 */
	public static Row getRow(int rowCounter, Sheet sheet) {
		Row row = sheet.getRow( rowCounter);
		if (row == null) {
			row = sheet.createRow(rowCounter);
		}
		return row;
	}

	/**
	 * 得到单元格,如果没有直接创建返回
	 * @param row 行
	 * @param column 列号
	 * @return 单元格
	 */
	public static Cell getCell(Row row, int column) {
		Cell cell = row.getCell(column);

		if (cell == null) {
			cell = row.createCell(column);
		}
		return cell;
	}

	
	public static void copyRangeCellsNoIntersect(Sheet sheet,CellRangeAddress fromRangeAddress,Integer newFirstrow,Integer newFirstCol){
		copyRangeOnlyCells(sheet, fromRangeAddress, newFirstrow, newFirstCol);
		copyRangeMergeCells(sheet, fromRangeAddress, newFirstrow, newFirstCol);
	}
	
	public static void cutRangeCellsWithIntersect(Sheet sheet,CellRangeAddress fromRangeAddress,Integer newFirstrow,Integer newFirstCol){
		copyRangeOnlyCells(sheet, fromRangeAddress, newFirstrow, newFirstCol);
		cutRangeMergeCells(sheet, fromRangeAddress, newFirstrow, newFirstCol);
	}
	
	public static void clearCell(Cell cell){
		cell.setCellStyle(null);
		cell.setCellValue("");
	}
	
	
	public static void clear(Sheet sheet,CellRangeAddress fromRangeAddress){
		for (int i = fromRangeAddress.getFirstRow(); i <= fromRangeAddress.getLastRow(); i++) {
			for (int j = fromRangeAddress.getFirstColumn(); j <= fromRangeAddress.getLastColumn(); j++) {
				Cell cell = PoiUtils.getCell(PoiUtils.getRow(i, sheet),j);
				clearCell(cell);
			}
		}
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress region = sheet.getMergedRegion(i);
			if (fromRangeAddress.getFirstRow() <= region.getFirstRow()
					&& fromRangeAddress.getLastRow() >= region.getLastRow()
					&& fromRangeAddress.getFirstColumn() <= region.getFirstColumn()
					&& fromRangeAddress.getLastColumn() >= region.getLastColumn()) {
				sheet.removeMergedRegion(i);
				i--;
			}
		}
	}

	
	public static void copyRangeOnlyCells(Sheet sheet,CellRangeAddress fromRangeAddress,Integer newFirstrow,Integer newFirstCol){
		int firstRow = fromRangeAddress.getFirstRow();
		int lastRow = fromRangeAddress.getLastRow();
		int firstColumn = fromRangeAddress.getFirstColumn();
		int lastColumn = fromRangeAddress.getLastColumn();
		if (fromRangeAddress.getFirstRow() < newFirstrow) {
			firstRow = fromRangeAddress.getLastRow();
			lastRow = fromRangeAddress.getFirstRow();
		}
		if (fromRangeAddress.getFirstColumn() < newFirstCol) {
			firstColumn = fromRangeAddress.getLastColumn();
			lastColumn = fromRangeAddress.getFirstColumn();
		}
		for (int i = firstRow;; ) {
			if (fromRangeAddress.getFirstRow() >= newFirstrow) {
				if(i>lastRow)break;
			}else {
				if(i<lastRow)break;
			}
			for (int j = firstColumn;;) {
				if (fromRangeAddress.getFirstColumn() >= newFirstCol) {
					if(j>lastColumn)break;
				}else {
					if(j<lastColumn)break;
				}
				Cell cell = PoiUtils.getCell(PoiUtils.getRow(i, sheet),j);
				PoiUtils.copyCell(cell, sheet, i - fromRangeAddress.getFirstRow() + newFirstrow, j - fromRangeAddress.getFirstColumn() + newFirstCol);
				if(fromRangeAddress.getFirstColumn() >= newFirstCol){
					j++;
				} else {
					j--;
				}
			}
			if(fromRangeAddress.getFirstRow() >= newFirstrow){
				i++;
			} else {
				i--;
			}
		}
		
	}
	
	public static boolean isEmptyCell(Cell cell){
		boolean flag = true;
		if (cell != null) {
			String cellValue = PoiUtils.getCellValue(cell);
			if (!Strings.isEmpty(cellValue)) {
				flag = false;
			}
		}
		return flag;
	}
	
	public static boolean isEmptyRange(Sheet sheet,CellRangeAddress range){
		boolean flag = true;
		for (int i = range.getFirstRow(); i <= range.getLastRow(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				for (int j = range.getFirstColumn(); j <= range.getLastColumn(); j++) {
					flag = isEmptyCell(row.getCell(j));
					if (flag == false) {
						return flag;
					}
				}
			}
		}
		return flag;
	}
	
	
	public static void cutRangeMergeCells(Sheet sheet,CellRangeAddress fromRangeAddress,Integer newFirstrow,Integer newFirstCol){
		List<CellRangeAddress> shiftedRegions = new ArrayList<CellRangeAddress>();
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress region = sheet.getMergedRegion(i);
			if (fromRangeAddress.getFirstRow() <= region.getFirstRow()
					&& fromRangeAddress.getLastRow() >= region.getLastRow()
					&& fromRangeAddress.getFirstColumn() <= region.getFirstColumn()
					&& fromRangeAddress.getLastColumn() >= region.getLastColumn()) {
				int rangeHeight = region.getLastRow() - region.getFirstRow();
				int rangeFirstRow = newFirstrow + region.getFirstRow() - fromRangeAddress.getFirstRow();
				int rangeLastRow = rangeFirstRow + rangeHeight;
				
				int rangeWidth = region.getLastColumn() - region.getFirstColumn();
				int rangeFirstCol = newFirstCol + region.getFirstColumn() - fromRangeAddress.getFirstColumn();
				int rangeLastCol = rangeFirstCol + rangeWidth;
				
				sheet.removeMergedRegion(i);
				i--;
				
				CellRangeAddress n_r = new CellRangeAddress(rangeFirstRow,
						rangeLastRow,
						rangeFirstCol,
						rangeLastCol);
				shiftedRegions.add(n_r);	
			}
		}
		
		Iterator<CellRangeAddress> iterator = shiftedRegions.iterator();
		while (iterator.hasNext()) {
			CellRangeAddress region = (CellRangeAddress) iterator.next();
			sheet.addMergedRegion(region);
		}	
	}
	
	public static void copyRangeMergeCells(Sheet sheet,CellRangeAddress fromRangeAddress,Integer newFirstrow,Integer newFirstCol){
		List<CellRangeAddress> shiftedRegions = new ArrayList<CellRangeAddress>();
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress region = sheet.getMergedRegion(i);
			if (fromRangeAddress.getFirstRow() <= region.getFirstRow()
					&& fromRangeAddress.getLastRow() >= region.getLastRow()
					&& fromRangeAddress.getFirstColumn() <= region.getFirstColumn()
					&& fromRangeAddress.getLastColumn() >= region.getLastColumn()) {
				int rangeHeight = region.getLastRow() - region.getFirstRow();
				int rangeFirstRow = newFirstrow + region.getFirstRow() - fromRangeAddress.getFirstRow();
				int rangeLastRow = rangeFirstRow + rangeHeight;
				
				int rangeWidth = region.getLastColumn() - region.getFirstColumn();
				int rangeFirstCol = newFirstCol + region.getFirstColumn() - fromRangeAddress.getFirstColumn();
				int rangeLastCol = rangeFirstCol + rangeWidth;
				
				CellRangeAddress n_r = new CellRangeAddress(rangeFirstRow,
						rangeLastRow,
						rangeFirstCol,
						rangeLastCol);
				shiftedRegions.add(n_r);	
			}
		}
		
		Iterator<CellRangeAddress> iterator = shiftedRegions.iterator();
		while (iterator.hasNext()) {
			CellRangeAddress region = (CellRangeAddress) iterator.next();
			sheet.addMergedRegion(region);
		}	
	}
}
