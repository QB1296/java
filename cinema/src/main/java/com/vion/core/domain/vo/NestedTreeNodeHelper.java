/**
 * 文件名：NestedTreeNodeHelper.java  
 *  
 * 版本信息：  
 * 日期：2014年12月10日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.domain.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vion.core.util.Classes;
import com.vion.core.util.Strings;

/**
 * <b>功能描述</b> <br>
 * 嵌套树帮助类,能够将普通的javaBean List转化成树结构
 * @author YUJB
 * @date 2014年12月10日 下午2:35:45
 */
public  class NestedTreeNodeHelper{
	
	public static class SubFlatedTreeMetaConfig{
		/** bean中id标识名称  */
		private String idField;
		
		/** 树节点显示字段的标识名称  */
		private String textField;
		
		private String[] ignoreFields;
		
		private String fieldNameInParent;

		/**
		 * {@link #idField}
		 * @return the idField
		 */
		public String getIdField() {
			return idField;
		}

		/**
		 * {@link #idField}	
		 * @param idField the idField to set
		 */
		public void setIdField(String idField) {
			this.idField = idField;
		}

		/**
		 * {@link #textField}
		 * @return the textField
		 */
		public String getTextField() {
			return textField;
		}

		/**
		 * {@link #textField}	
		 * @param textField the textField to set
		 */
		public void setTextField(String textField) {
			this.textField = textField;
		}

		/**
		 * {@link #ignoreFields}
		 * @return the ignoreFields
		 */
		public String[] getIgnoreFields() {
			return ignoreFields;
		}

		/**
		 * {@link #ignoreFields}	
		 * @param ignoreFields the ignoreFields to set
		 */
		public void setIgnoreFields(String... ignoreFields) {
			this.ignoreFields = ignoreFields;
		}
		
		/**
		 * {@link #fieldNameInParent}
		 * @return the fieldNameInParent
		 */
		public String getFieldNameInParent() {
			return fieldNameInParent;
		}

		/**
		 * {@link #fieldNameInParent}	
		 * @param fieldNameInParent the fieldNameInParent to set
		 */
		public void setFieldNameInParent(String fieldNameInParent) {
			this.fieldNameInParent = fieldNameInParent;
		}
		
	}
	
	public static class NestedTreeMetaConfig{
		
		/** bean中id标识名称  */
		private String idField;
		
		/** 树节点显示字段的标识名称  */
		private String textField;
		
		/** bean中子节点标识名称  */
		private String childFieldName;
		
		private String[] ignoreFields;

		/**
		 * @param string
		 * @param string2
		 * @param string3
		 */
		public NestedTreeMetaConfig(String idField, String textField,
				String childFieldName) {
			this.idField = idField;
			this.textField = textField;
			this.childFieldName = childFieldName;
					
		}

		/**
		 * {@link #idField}
		 * @return the idField
		 */
		public String getIdField() {
			return idField;
		}

		/**
		 * {@link #idField}	
		 * @param idField the idField to set
		 */
		public void setIdField(String idField) {
			this.idField = idField;
		}

		/**
		 * {@link #textField}
		 * @return the textField
		 */
		public String getTextField() {
			return textField;
		}

		/**
		 * {@link #textField}	
		 * @param textField the textField to set
		 */
		public void setTextField(String textField) {
			this.textField = textField;
		}

		/**
		 * {@link #childFieldName}
		 * @return the childFieldName
		 */
		public String getChildFieldName() {
			return childFieldName;
		}

		/**
		 * {@link #childFieldName}	
		 * @param childFieldName the childFieldName to set
		 */
		public void setChildFieldName(String childFieldName) {
			this.childFieldName = childFieldName;
		}

		/**
		 * {@link #ignoreFields}
		 * @return the ignoreFields
		 */
		public String[] getIgnoreFields() {
			return ignoreFields;
		}

		/**
		 * {@link #ignoreFields}	
		 * @param ignoreFields the ignoreFields to set
		 */
		public void setIgnoreFields(String... ignoreFields) {
			this.ignoreFields = ignoreFields;
		}
		
		
		
	}

	
	public static class FlatedTreeMetaConfig{
		
		
		
		/** bean中id标识名称  */
		private String idField;
		
		/** 树节点显示字段的标识名称  */
		private String textField;
		
		/** bean中Pid标识名称  */
		private String pIdFiled;
		
		/** 每个节点的固有子节点标识名称 ,典型应用场景为数结构表为Pid关联模式,但需要关联外部表一起组装成树形结构 */
		private SubFlatedTreeMetaConfig[] childrensField;
		
		private String[] ignoreFields;
		
		/** 是否是叶子节点 */
		private String leafField;
		
		
		/**
		 * 获得是否是叶子节点 
		 * @return  leafField 是否是叶子节点
		 */
		public String getLeafField() {
			return leafField;
		}

		/** 
		 * 设置是否是叶子节点 
		 * @param leafField 是否是叶子节点 
		 */
		public void setLeafField(String leafField) {
			this.leafField = leafField;
		}

		/**
		 * {@link #ignoreFields}
		 * @return the ignoreFields
		 */
		public String[] getIgnoreFields() {
			return ignoreFields;
		}

		/**
		 * {@link #ignoreFields}	
		 * @param ignoreFields the ignoreFields to set
		 */
		public void setIgnoreFields(String... ignoreFields) {
			this.ignoreFields = ignoreFields;
		}

		/**
		 * {@link #idField}
		 * @return the idField
		 */
		public String getIdField() {
			return idField;
		}

		/**
		 * {@link #idField}	
		 * @param idField the idField to set
		 */
		public void setIdField(String idField) {
			this.idField = idField;
		}

		/**
		 * {@link #textField}
		 * @return the textField
		 */
		public String getTextField() {
			return textField;
		}

		/**
		 * {@link #textField}	
		 * @param textField the textField to set
		 */
		public void setTextField(String textField) {
			this.textField = textField;
		}

		/**
		 * {@link #pIdFiled}
		 * @return the pIdFiled
		 */
		public String getpIdFiled() {
			return pIdFiled;
		}

		/**
		 * {@link #pIdFiled}	
		 * @param pIdFiled the pIdFiled to set
		 */
		public void setpIdFiled(String pIdFiled) {
			this.pIdFiled = pIdFiled;
		}

		/**
		 * {@link #childrensField}
		 * @return the childrensField
		 */
		public SubFlatedTreeMetaConfig[] getChildrensField() {
			return childrensField;
		}

		/**
		 * {@link #childrensField}	
		 * @param childrensField the childrensField to set
		 */
		public void setChildrensField(SubFlatedTreeMetaConfig... childrensField) {
			this.childrensField = childrensField;
		}

		
		
	}
	
	/**
	 * 平铺的javaBean列表转化为嵌套结构的树，支持PId为多个。<span style="color:red">警告：PId为多个的节点必须作为叶子节点出现</span>
	 * @param beans  待转化的数据列表
	 * @param idFieldName bean中id标识名称
	 * @param textFieldName 树节点显示字段的标识名称
	 * @param pIdFieldName bean中Pid标识名称
	 * @return
	 */
	public static List<NestedTreeNode> flatedBeanConvert(List<?> beans,FlatedTreeMetaConfig flatedTreeMetaConfig) {
		return menuTree(beans,flatedTreeMetaConfig);
	}
	
	
	/**
	 * 将嵌套结构的javaBean列表转化成嵌套结构的树
	 * @param beans  待转化的数据列表
	 * @param idFieldName bean中id标识名称
	 * @param textFieldName 树节点显示字段的标识名称
	 * @param childFieldName bean中子节点字段标识名称
	 * @return
	 */
	public static List<NestedTreeNode> nestedBeanConvert(List<?> beans,NestedTreeMetaConfig config) {
		String idFieldName = config.getIdField();
		String textFieldName = config.getTextField();
		String childFieldName = config.getChildFieldName();
		List<NestedTreeNode> retNodes = new ArrayList<NestedTreeNode>();
		for (Object object : beans) {
			String id = Classes.getStringFieldValue(object,idFieldName);
			String text = Classes.getStringFieldValue(object,textFieldName);
			NestedTreeNode node = new NestedTreeNode();
			if (!Strings.isEmpty(childFieldName)) {
				Object childObj = Classes.getFieldValue(object,childFieldName);
				if (childObj != null) {
					List<?> childrenBeans = (List<?>) Classes.getFieldValue(object,childFieldName);
					List<NestedTreeNode> childrenTree = nestedBeanConvert(childrenBeans,config);
					node.setChildren(childrenTree);
				}
			}
			List<String> strArr = new ArrayList<String>();
			if (config.getIgnoreFields().length > 0) {
				Collections.addAll(strArr, config.getIgnoreFields());
			}
			Collections.addAll(strArr, childFieldName);
			node.setData(com.vion.core.util.BeanUtils.convert2Map(object,(String[])strArr.toArray(new String[1+config.getIgnoreFields().length])));
			node.setId(id);
			node.setText(text);
			retNodes.add(node);
		}
		return retNodes;
	}
	
	private static List<Object> multiObjs(Object pIdObj){
		List<Object> pIdArray = new ArrayList<Object>(); 
		if (pIdObj != null && pIdObj instanceof Iterable<?>) {
			Iterator<?> iterator = ((Iterable<?>)pIdObj).iterator();
			while (iterator.hasNext()) {
				Object onePId = (Object) iterator.next();
				pIdArray.add(onePId);
			}
		}else {
			pIdArray.add(pIdObj);
		}
		return pIdArray;
	}
	
	
	private static List<NestedTreeNode> menuTree(List<?> objects,FlatedTreeMetaConfig flatedTreeMetaConfig) {
		
		/*拼装树形结构元数据信息*/
		String idFieldName = flatedTreeMetaConfig.getIdField();
		String textFieldName = flatedTreeMetaConfig.getTextField();
		String pIdFieldName = flatedTreeMetaConfig.getpIdFiled();
		String[] ignoreFieldNames = flatedTreeMetaConfig.getIgnoreFields();
		String leafField = flatedTreeMetaConfig.getLeafField() == null? "isLeaf":flatedTreeMetaConfig.getLeafField();
		
		List<NestedTreeNode> ret = new ArrayList<NestedTreeNode>();
		List<NestedTreeNode> tempRet = new ArrayList<NestedTreeNode>();
		Map<String, Map<String, NestedTreeNode>> tempMap = new HashMap<String, Map<String, NestedTreeNode>>();
		for (Object object : objects) {
			String id = Classes.getStringFieldValue(object,idFieldName);
			String text = Classes.getStringFieldValue(object,textFieldName);
			if (pIdFieldName != null) {
				List<Object> pIdArray = multiObjs(Classes.getFieldValue(object,pIdFieldName));
				for (int i = 0; i < pIdArray.size(); i++) {
					Object pId = pIdArray.get(i);
					NestedTreeNode node = new NestedTreeNode();
					node.setData(com.vion.core.util.BeanUtils.convert2Map(object,ignoreFieldNames));
					node.addData(leafField, "1");
					node.getData().put(pIdFieldName, pId);
					node.setId(id + "_" + i);
					node.setText(text);
					tempRet.add(node);
					Map<String, NestedTreeNode> branchMap = new HashMap<String, NestedTreeNode>();
					branchMap.put(node.getId(), node);
					if (tempMap.containsKey(id)) {
						tempMap.get(id).putAll(branchMap);
					}else {
						tempMap.put(id, branchMap);
					}
				}
				/*处理childrenNodes*/
				SubFlatedTreeMetaConfig[] childrensFields = flatedTreeMetaConfig.getChildrensField();
				if (childrensFields != null) {
					for (SubFlatedTreeMetaConfig childrensField : childrensFields) {
						String subIdField = childrensField.getIdField();
						String subTextField = childrensField.getTextField();
						Object childObj = Classes.getFieldValue(object, childrensField.getFieldNameInParent());
						List<Object> childObjArray = multiObjs(childObj);
						for (int i = 0; i < childObjArray.size(); i++) {
							Object oneChildObj = childObjArray.get(i);
							NestedTreeNode node = new NestedTreeNode();
							node.addData(leafField, "1");
							String subId = Classes.getStringFieldValue(oneChildObj,subIdField);
							node.setData(com.vion.core.util.BeanUtils.convert2Map(oneChildObj,childrensField.getIgnoreFields()));
							node.getData().put(pIdFieldName, id);
							node.setId(subId + "_" + i);
							node.setText(Classes.getStringFieldValue(oneChildObj,subTextField));
							tempRet.add(node);
						}
					}
				}
			}else {
				NestedTreeNode node = new NestedTreeNode();
				node.setData(com.vion.core.util.BeanUtils.convert2Map(object,ignoreFieldNames));
				node.setId(id);
				node.setText(text);
				node.addData(leafField, "1");
				tempRet.add(node);
			}
		}
		
		for (Iterator<NestedTreeNode> iterator = tempRet.iterator(); iterator.hasNext();) {
			NestedTreeNode node = (NestedTreeNode) iterator.next();
			String id = node.getId();
			if (pIdFieldName == null) {
				ret.add(node);
			}else {
				String pId = node.getData().get(pIdFieldName).toString();
				if (tempMap.containsKey(pId) && id != pId) {
					Map<String, NestedTreeNode> map = tempMap.get(pId);
					for (String key : map.keySet()) {
						int index = tempRet.indexOf(map.get(key));
						tempRet.get(index).getChildren().add(node);
						tempRet.get(index).addData(leafField, "0");
					}
				}else {
					ret.add(node);
				}
			}
		}
		
		return ret;
		
	}
	
	
	
	
}
