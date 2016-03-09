/**
 * 文件名：PermisssionModel.java  
 *  
 * 版本信息：  
 * 日期：2014年8月12日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.meta.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.digester.annotations.rules.ObjectCreate;
import org.apache.commons.digester.annotations.rules.SetNext;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import com.vion.core.exception.NoCriteriaWritingException;
import com.vion.core.security.SecureMetaLoader;
import com.vion.core.util.Classes;
import com.vion.core.util.Strings;


/**
 * <b>功能描述</b> <br>
 * 数据权限元数据模型
 * @author YUJB
 * @date 2014年8月12日 上午11:43:14
 */
@ObjectCreate(pattern="premission-mapping")
public class SecureMetaModel {
	
	private List<MoudleModel> moudleModels;
	
	private List<DefaultFuncModel> defaultFuncModels;
	
	private List<PointCutModel> pointcutModels;
	
	/**
	 * 得到切入点元数据模型
	 * @return 切入点元数据模型
	 */
	public List<PointCutModel> getPointcutModels() {
		return pointcutModels == null ? new ArrayList<PointCutModel>():pointcutModels;
	}

	/**
	 * 设置切入点元数据模型
	 * @param pointcutModels
	 */
	public void setPointcutModels(List<PointCutModel> pointcutModels) {
		this.pointcutModels = pointcutModels;
	}
	
	@SetNext
	public void addEntityModel(PointCutModel pointCutModel) {
		if (pointcutModels == null) {
			pointcutModels = new ArrayList<PointCutModel>();
		}
		pointcutModels.add(pointCutModel);
	}
	
	/**
	 * 得到元数据中配置的数据权限信息列表
	 * @return the defaultFuncModels
	 */
	public List<DefaultFuncModel> getDefaultFuncModels() {
		if (defaultFuncModels == null) {
			return null;
		}
		return Collections.unmodifiableList(defaultFuncModels);
	}
	
	/**
	 * {@link #defaultFuncModels}	
	 * @param defaultFuncModels the defaultFuncModels to set
	 */
	public void setDefaultFuncModels(List<DefaultFuncModel> defaultFuncModels) {
		this.defaultFuncModels = defaultFuncModels;
	}
	
	
	public List<MoudleModel> getMoudleModels() {
		if (moudleModels == null) {
			return null;
		}
		return Collections.unmodifiableList(moudleModels);
	}

	public void setMoudleModels(List<MoudleModel> moudleModels) {
		this.moudleModels = moudleModels;
	}
	
	
	public List<String> getPointCutValues(){
		List<String> retValues = new ArrayList<String>();
		List<PointCutModel> pointCuts = getPointcutModels();
		for (PointCutModel pointCutModel : pointCuts) {
			retValues.add(pointCutModel.getValue());
		}
		return retValues;
	}
	
	
	/**
	 * 通过point codes得到切入点
	 * @param codes 用","分割code  例如："code1","code2"
	 * @return
	 */
	public List<String> getPointCutValueByCode(String codes){
		List<String> retValues = new ArrayList<String>();
		List<PointCutModel> pointCuts = getPointcutModels();
		String[] codeArray = codes.split(",");
		for (String code : codeArray) {
			boolean isMate = false;
			for (PointCutModel pointCutModel : pointCuts) {
				if(code.equals(pointCutModel.getCode())){
					retValues.add(pointCutModel.getValue());
					isMate = true;
				}
			}
			if (!isMate) {
				throw new NoCriteriaWritingException("没有找到编号为【" + code + "】的pointCut");
			}
		}
		return retValues;
	}
	

	@SetNext
	public void addDefaultFuncModels(DefaultFuncModel defaultFuncModel){
		if (defaultFuncModels == null) {
			defaultFuncModels = new ArrayList<DefaultFuncModel>();
		}
		defaultFuncModels.add(defaultFuncModel);
	}
	
	@SetNext
	public void addMoudleModel(MoudleModel moudleModel){
		if (moudleModels == null) {
			moudleModels = new ArrayList<MoudleModel>();
		}
		moudleModels.add(moudleModel);
	}
	
	@SetNext
	public void addImportModel(ImportModel importModel){
		SecureMetaLoader loader = new SecureMetaLoader();
		SecureMetaModel subPM = loader.loaderConfig(importModel.getUrl());
		if (this.moudleModels == null) {
			this.moudleModels = new ArrayList<MoudleModel>();
		}
		this.moudleModels.addAll(subPM.getMoudleModels());
	}
	
	
	/**
	 * 通过code得到模块
	 * @param code
	 * @return
	 */
	public MoudleModel getMoudleModelByCode(String code){
		if (moudleModels != null) {
			for (MoudleModel moudleModel : moudleModels) {
				if (code.equals(moudleModel.getCode())) {
					return moudleModel;
				}
			}
		}
		throw new NoCriteriaWritingException("模块【" + code  + "】没有找到!");
	}
	
	/**
	 * codes是否可以访问当前调用栈模块
	 * @param codes 用","分割code  例如："code1","code2"
	 * @return true：可以访问  ；false：不能访问
	 */
	public boolean isAccessMoudle(String codes){
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (StackTraceElement stackTraceElement : stackTrace) {
			String className = stackTraceElement.getClassName();
			String methodName = stackTraceElement.getMethodName();
			Class<?> clazz = null;
			try {
				clazz = Class.forName(className);
				Method method = Classes.getMethodByName(clazz, methodName);
				List<String> pointCuts = new ArrayList<String>();
				if (!Strings.isEmpty(codes)) {
					pointCuts.addAll(getPointCutValueByCode(codes));
				}else {
					pointCuts.addAll(getPointCutValues());
				}
				for (String pointCut : pointCuts) {
					 AspectJExpressionPointcut AJPointcut = new AspectJExpressionPointcut();  
					 AJPointcut.setExpression(pointCut);  
					 if (method != null) {
						 boolean matches = AJPointcut.matches(method,clazz);
						 if(matches){
							 return true;
						 }
					}
				}
			} catch (ClassNotFoundException e) {
				//e.printStackTrace();
				//no touch;
			}
			
		}
		
		return false;
	}
	
}
