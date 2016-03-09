/**
 * 文件名：PropertiesAccessor.java  
 *  
 * 版本信息：  
 * 日期：2014年6月11日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年6月11日 下午4:33:07
 */
public class PropertiesAccessor implements  BeanFactoryAware {  
	
    private ConfigurableBeanFactory beanFactory; 

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }

    protected String resolveProperty(String name) {
        String rv = beanFactory.resolveEmbeddedValue("${" + name + "}");
        return rv;
    }

    public String get(Object key) {
        return resolveProperty(key.toString());
    }

    public boolean containsKey(Object key) {
        try {
            resolveProperty(key.toString());
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}