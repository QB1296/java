package com.vion.core.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


/**
 * <b>功能描述</b> <br>
 * 基于{@link Repository} 注解的 BeanFactory Bean Post处理。为了业务Dao能继承{@link BaseDaoSupport}的属性
 * @author YUJB
 * @date 2014年5月12日 上午9:11:18
 */
public class RepositoryBeanFactoryPostProcessor implements
		BeanFactoryPostProcessor, PriorityOrdered {

	
	protected final Log logger = LogFactory.getLog(getClass());

	private int order = Ordered.LOWEST_PRECEDENCE; 
	
	/** {@link BaseDaoSupport} 的Bean 名称  */
	private String parentBeanName;
	
	public String getParentBeanName() {
		return parentBeanName;
	}

	public void setParentBeanName(String parentBeanName) {
		this.parentBeanName = parentBeanName;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return this.order;
	}

	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {

		String[] beanNames = beanFactory.getBeanDefinitionNames();
		for (int i = 0; i < beanNames.length; i++) {
			String beanName = beanNames[i];
			BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
			String className = bd.getBeanClassName();
			if (!StringUtils.hasText(className)) {
				continue;
			}
			try {
				Class<?> clazz = Class.forName(className);
				Repository repository = AnnotationUtils.findAnnotation(clazz,
						Repository.class);
				if (repository != null) {
					if (!StringUtils.hasText(parentBeanName)) {
						throw new FatalBeanException(
								"parentBeanName不能为null");
					}
					bd.setParentName(parentBeanName);
				}

			} catch (ClassNotFoundException e) {
				logger.error("Bean [" + beanName + "]无效的className["
						+ bd.getBeanClassName()
						+ "]");
			}
		}

	}
}