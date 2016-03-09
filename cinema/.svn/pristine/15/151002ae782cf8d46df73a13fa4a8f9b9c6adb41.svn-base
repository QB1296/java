/**
 * 文件名：DataResourceFormBo.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.vion.core.util.Strings;


/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年8月12日 下午5:15:01
 */
public class DataResourceBo {
	
	private List<Moudle> moudles;
	
	
	public List<Moudle> getMoudles() {
		return moudles;
	}


	public void setMoudles(List<Moudle> moudles) {
		this.moudles = moudles;
	}
	
	public void addMoudle(Moudle moudle){
		if (this.moudles == null) {
			this.moudles = new ArrayList<Moudle>();
		}
		this.moudles.add(moudle);
	}

	

	public class Moudle{
		
		private String name;
		
		private String code;
		
		private List<Column> columns;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public List<Column> getColumns() {
			return columns;
		}

		public void setColumns(List<Column> columns) {
			this.columns = columns;
		}
		
		public void addColumn(Column column){
			if (this.columns == null) {
				this.columns = new ArrayList<Column>();
			}
			this.columns.add(column);
		}
		
		
	}
	
	
	public class Column{
		
		private String name;
		
		private String code;
		
		private String show;
		
		private String operate;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getShow() {
			return show;
		}

		public void setShow(String show) {
			this.show = show;
		}

		public String getOperate() {
			return operate;
		}

		public void setOperate(String operate) {
			if (!Strings.isEmpty(operate)) {
				String [] ss = operate.split(" ");
				this.operate = StringUtils.join(ss, ",");
			}else {
				this.operate = operate;
			}
		}
		
		
	}
	
	
	
	public class SelectColumn extends Column{
		
		public SelectColumn() {
			super();
			super.show = "select";
		}

		private String name;
		
		private String code;
		
		private List<Map<String, String>> selectItems;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		
		public List<Map<String, String>> getSelectItems() {
			return selectItems;
		}

		public void setSelectItems(List<Map<String, String>> selectItems) {
			this.selectItems = selectItems;
		}

		@Override
		public void setShow(String show) {
			if (!Strings.isEmpty(show)) {
				super.setShow(show);
			}
		}
		
		
	}
	
	
	public class SingleColumn extends Column{
		
		public SingleColumn() {
			super();
			super.show = "text";
		}
		
		private String type;
		
		private String dataUrl;
		
		private String valueRule;
		
		private String valueProcessor;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDataUrl() {
			return dataUrl;
		}

		public void setDataUrl(String dataUrl) {
			this.dataUrl = dataUrl;
		}

		public String getValueRule() {
			return valueRule;
		}

		public void setValueRule(String valueRule) {
			this.valueRule = valueRule;
		}

		public String getValueProcessor() {
			return valueProcessor;
		}

		public void setValueProcessor(String valueProcessor) {
			this.valueProcessor = valueProcessor;
		}
		
		@Override
		public void setShow(String show) {
			if (!Strings.isEmpty(show)) {
				super.setShow(show);
			}
		}
		
	}

	

	

}
