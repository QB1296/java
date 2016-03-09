/**
 * 文件名：RecordFilterRuleObserver.java  
 *  
 * 版本信息：  
 * 日期：2014年10月22日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security;

import java.util.List;

import com.vion.core.SystemContext;
import com.vion.core.exception.NoCriteriaWritingException;
import com.vion.core.security.meta.DataResourceNode;
import com.vion.core.security.meta.model.ColumnModel;
import com.vion.core.security.meta.model.ColumnValueConfigModel;
import com.vion.core.security.meta.model.EntityModel;
import com.vion.core.security.meta.model.MoudleModel;
import com.vion.core.security.meta.model.RowRule;
import com.vion.core.security.meta.model.RowRuleAssistTable;
import com.vion.core.security.meta.model.RowRuleJoin;
import com.vion.core.security.meta.model.RowRuleSQL;
import com.vion.core.security.meta.model.RowRuleValue;
import com.vion.core.security.meta.model.RowRuleValueConfigModel;
import com.vion.core.security.meta.model.SQL;
import com.vion.core.security.meta.model.SecureMetaModel;
import com.vion.core.security.rule.RecordFilterRule;
import com.vion.core.security.rule.SQLSimpleFilterRule;
import com.vion.core.security.rule.SingleFilterRule;
import com.vion.core.util.Classes;

/**
 * <b>功能描述</b> <br>
 * 数据行权限过滤器
 * @author YUJB
 * @date 2014年10月22日 下午5:40:36
 */
public class RecordFilterRuleResolver {
	
	public String moudle;
	
	private SecureMetaModel secureMetaModel;
	
	
	public RecordFilterRuleResolver(String moudle) {
		super();
		this.moudle = moudle;
		this.secureMetaModel = SecureMetaModelHolder.getSecureMetaModel();
	}


	public RecordFilterRule resolveDataResource(DataResourceNode rule){
		
		RecordFilterRule recordFilterRule = null;
		
		String column = rule.getColumn();
		String operate = rule.getOperate();
		String value = rule.getValue();
		
		MoudleModel moudleModel = secureMetaModel.getMoudleModelByCode(moudle);
		
		/*实体column*/
		List<EntityModel> entityModels = moudleModel.getEntityModels();
		for (EntityModel entityModel : entityModels) {
			List<ColumnModel> columnModels = entityModel.getColumnModels();
			if (columnModels != null) {
				for (ColumnModel columnModel : columnModels) {
					if(columnModel.getCode().equals(column)){
						SingleFilterRule filter = new SingleFilterRule();
						filter.setField(columnModel.getCode());
						filter.setEntity(entityModel.getTable());
						filter.setOp(operate);
						ColumnValueConfigModel valueConfigModel = columnModel.getValueConfigModel();
						if (valueConfigModel != null) {
							String valueProcessor = valueConfigModel.getValueProcessor();
							filter.setValue(processValue(valueProcessor,value));
						}else {
							filter.setValue(value);
						}
						filter.setMoudle(moudle);
						recordFilterRule = filter;
					}
				}
			}
		}
		
		/*实体rowRules*/
		List<RowRule> rowRules = moudleModel.getRowRules();
		if (rowRules != null) {
			boolean columnSuccess = false;
			for (RowRule rowRule : rowRules) {
				if(rowRule.getCode().equals(column)){
					columnSuccess = true;
					List<RowRuleValue> rowRuleValues = rowRule.getRowRuleValues();
					if (rowRuleValues != null) {
						for (RowRuleValue rowRuleValue : rowRuleValues) {
							if(rowRuleValue.getValue().equals(value)){
								SQL sql = rowRuleValue.getSql();
								recordFilterRule = convertToSQLSimpleFilterRule(rowRule,sql,null);
							}
						}
					}
					SQL sql = rowRule.getSql();
					if (sql != null) {
						RowRuleValueConfigModel config = rowRule.getValueConfigModel();
						if (config != null) {
							String valueProcessor = config.getValueProcessor();
							value = processValue(valueProcessor,value);
						}
						recordFilterRule = convertToSQLSimpleFilterRule(rowRule,sql,value);
					}
					RowRuleAssistTable assistTable = rowRule.getRowRuleAssistTable();
					if (assistTable != null) {
						RowRuleValueConfigModel config = rowRule.getValueConfigModel();
						if (config != null) {
							String valueProcessor = config.getValueProcessor();
							value = processValue(valueProcessor,value);
						}
						forceAssistTableJoin(rowRule);
						recordFilterRule = convertToSQLSimpleFilterRule(rowRule,rowRule.getSql(),value);
						recordFilterRule.setAssistTable(true);
						recordFilterRule.setAssistColumnCode(rowRule.getRowRuleAssistTable().getCode());
						String assistColumnName = rowRule.getRowRuleAssistTable().getResourceColumn();
						recordFilterRule.setAssistColumnName(assistColumnName);
					}
					
				}
			}
			if (!columnSuccess) {
				throw new NoCriteriaWritingException("【" + column + "】" + "书写不正确在,在模块【" + moudle +"】没有发现响应的RowRule或column");
			}
		}
		return recordFilterRule;
	}
	
	private void forceAssistTableJoin(RowRule rowRule){
		RowRuleAssistTable assistTable = rowRule.getRowRuleAssistTable();
		String resouceCode = assistTable.getCode();
		String rootTable = rowRule.getEntity();
		String resouceColumn= assistTable.getResourceColumn();
		if (resouceColumn.indexOf(".") < 0) {
			resouceColumn = rootTable + "." + resouceColumn;
		}
		RowRuleSQL rowRuleSQL = new RowRuleSQL();
		rowRuleSQL.setValue("${" + resouceColumn  + "} in " +  "( select "  + "vion_ass.data_resource_id from "+ EntityModel.ASSIST_TABLE + " vion_ass  where vion_ass.resource_code  ='" + resouceCode 
				+ "' and " + 
				"vion_ass.data_resource_id =  "+ "${" + resouceColumn  + "}"
				+ " and " + 
				"vion_ass.role_id in (#{roleIds}))"
				);
		rowRule.addSql(rowRuleSQL);
	}
	
	private String processValue(String valueProcessor,Object value){
		if (valueProcessor == null) {
			return String.valueOf(value);
		}
		ValueProcessor processor = SystemContext.getApplicationContext().getBean(valueProcessor,ValueProcessor.class);
		if (processor == null) {
			processor = (ValueProcessor) Classes.constructorNewInstance(Classes.forName(valueProcessor));
		}
		return processor.processorValue(value);
	}
	
	private SQLSimpleFilterRule convertToSQLSimpleFilterRule(RowRule rowRule,SQL sql,Object value){
		MoudleModel moudleModel = secureMetaModel.getMoudleModelByCode(moudle);
		String code = rowRule.getEntity();
		String[] joinCodes = rowRule.getJoinList();
		String entity = moudleModel.getTableByCode(code);
		String[] joins = moudleModel.getTablesByCodes(joinCodes);
		SQLSimpleFilterRule filter = new SQLSimpleFilterRule();
		filter.addRootXmlConfigMapper(code,entity);
		filter.addXmlConfigMapper(joinCodes,joins);
		if (sql != null) {
			filter.setSql(sql.getValue());
		}
		filter.setMoudle(moudle);
		filter.setValue(value);
		filter.setCacheFilter(rowRule.isCacheFilter());
		if (filter.isCacheFilter()) {
			filter.setAffectSQLFilter(rowRule.isAffectSQLFilter());
		}
		filter.setNotJoinTable(!Boolean.valueOf(rowRule.getJoinFilter()));
		filter.setPointCut(rowRule.getPointCut());
		filter.setExcludePointCut(rowRule.getExcludePointCut());
		List<RowRuleJoin> rowRuleJoins = rowRule.getRowRuleJoins();
		for (RowRuleJoin rowRuleJoin : rowRuleJoins) {
			if (rowRuleJoin.getForceJoin().equals("true")) {
				filter.putJoinExpressionMapper(moudleModel.getTableByCode(rowRuleJoin.getName()), rowRuleJoin.getJoinSQL());
				filter.putJoinTypeMapper(moudleModel.getTableByCode(rowRuleJoin.getName()), rowRuleJoin.getJoinType());
			}
		}
		
		return filter;
	}
}
