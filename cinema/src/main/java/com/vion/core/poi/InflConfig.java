/**
 * 文件名：InflConfig.java  
 *  
 * 版本信息：  
 * 日期：2015年1月21日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.poi;

/**
 * <b>功能描述</b> <br>
 * 影响配置信息,将影响excel是否新建行,是否跳出哪行扫描
 * @author YUJB
 * @date 2015年1月21日 下午5:45:28
 */
public class InflConfig {
	
	/** 跳过多少行取决于数据  */
	public final static int SKIP_NUM_DATA = -1;
	
	public final static int BREAK_TRUE = 1;
	
	public final static int BREAK_FALSE = 0;
	
	private int skipNum = 0;
	
	private int breakFlag = 0;
	
	/** 插入的行  */
	private int insertRow = 0;
	
	public InflConfig() {
		super();
	}

	public InflConfig(int skipNum, int breakFlag) {
		super();
		this.skipNum = skipNum;
		this.breakFlag = breakFlag;
	}
	
	/**
	 * 获得插入的行 
	 * @return  insertRow 插入的行
	 */
	public int getInsertRow() {
		return insertRow;
	}

	/** 
	 * 设置插入的行 
	 * @param insertRow 插入的行 
	 */
	public void setInsertRow(int insertRow) {
		this.insertRow = insertRow;
	}

	/**
	 * 获得skipNum 
	 * @return  skipNum skipNum
	 */
	public int getSkipNum() {
		return skipNum;
	}

	/** 
	 * 设置skipNum 
	 * @param skipNum skipNum 
	 */
	public void setSkipNum(int skipNum) {
		this.skipNum = skipNum;
	}

	
	public InflConfig skipNumPlus(){
		this.skipNum ++;
		return this;
	}
	
	
	public InflConfig skipNumMinus(){
		this.skipNum --;
		return this;
	}

	public InflConfig breakTrue() {
		breakFlag = BREAK_TRUE;
		return this;
	}
	
	public InflConfig breakFalse() {
		breakFlag = BREAK_FALSE;
		return this;
	}

	
	/**
	 * 获得breakFlag 
	 * @return  breakFlag breakFlag
	 */
	public int getBreakFlag() {
		return breakFlag;
	}

	public static InflConfig noInflConfig(){
		return new InflConfig();
	}
	
	public static InflConfig dynaInflConfig(int skipNum, int breakFlag){
		return new InflConfig(skipNum, breakFlag);
	}

	/**
	 * @param inflConfig
	 */
	public void merge(InflConfig inflConfig) {
		this.breakFlag = inflConfig.breakFlag;
		this.insertRow += inflConfig.insertRow;
		this.skipNum += inflConfig.skipNum;
	}
	
}
