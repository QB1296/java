/**
 * 文件名：HibernateLoaderListener.java  
 *  
 * 版本信息：  
 * 日期：2015年1月13日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.vion.core.dao.FinderResult;
import com.vion.core.dao.IGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 应用启动是将hql语句的执行结果存入到hibernate的二级缓存中
 * @author YUJB
 * @date 2015年1月13日 下午5:36:56
 */
public class HibernateLoaderListener implements ApplicationListener<ContextRefreshedEvent>{

	private String[] hqls;
	
	@Autowired
	private IGeneralDAO generalDAO;
	
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)*/
	@Override
	public void onApplicationEvent(ContextRefreshedEvent cre) {
		if(cre.getApplicationContext().getParent() == null){
			if (hqls != null) {
				for (String hql : hqls) {
					FinderResult fr = generalDAO.findByJPQL(hql);
					fr.originResult();
				}
			}
        }
		
	}
	
	/**
	 * {@link #hqls}
	 * @return the hqls
	 */
	public String[] getHqls() {
		return hqls;
	}
	
	/**
	 * @param hqls the hqls to set
	 */
	public void setHqls(String[] hqls) {
		this.hqls = hqls;
	}

	
	
}
