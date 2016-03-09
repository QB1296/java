package com.vion.core.spring;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import com.vion.core.annotation.Produces;

/**
 * <b>功能描述</b> <br>
 * spring restful形式变体。使用{@link Produces}在服务端指定MIME类型
 * @author YUJB
 * @date 2014年5月13日 上午11:04:26
 */
public class MethodAnnotationModelAndViewResolver extends WebApplicationObjectSupport implements  ModelAndViewResolver,Ordered{
	
	private List<HttpMessageConverter<Object>> messageConverters;
	
	
	private int order = Ordered.HIGHEST_PRECEDENCE;
	
	@Override
	public ModelAndView resolveModelAndView(Method handlerMethod,
			@SuppressWarnings("rawtypes") Class handlerType, Object returnValue,
			ExtendedModelMap implicitModel, NativeWebRequest webRequest) {
		
		Produces produces = AnnotationUtils.findAnnotation(handlerMethod, Produces.class);
		
		if (returnValue== null || produces == null || returnValue instanceof ModelAndView || returnValue instanceof View) {
			return ModelAndViewResolver.UNRESOLVED;
		}
		
		HttpServletResponse servletResponse = (HttpServletResponse) webRequest.getNativeResponse();
		HttpOutputMessage outputMessage = new ServletServerHttpResponse(servletResponse);
		
		for (HttpMessageConverter<Object> messageConverter : messageConverters) {
			if (messageConverter.canWrite(returnValue.getClass(), MediaType.parseMediaType(produces.value()))) {
				try {
					messageConverter.write(returnValue, MediaType.parseMediaType(produces.value()), outputMessage);
				} catch (HttpMessageNotWritableException e) {
					logger.error("resolveModelAndView 发生异常 : " , e);
				} catch (IOException e) {
					logger.error("resolveModelAndView 发生异常 : " , e);
				}
			}
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void initServletContext(ServletContext servletContext) {
		if (this.messageConverters == null) {
			Map<String, HttpMessageConverter> matchingBeans =
					BeanFactoryUtils.beansOfTypeIncludingAncestors(getApplicationContext(), HttpMessageConverter.class);
			this.messageConverters = new ArrayList<HttpMessageConverter<Object>>(matchingBeans.size());
			for (HttpMessageConverter messageConverter : matchingBeans.values()) {
				if (this != messageConverter) {
					this.messageConverters.add(messageConverter);
				}
			}
		}
		if (this.messageConverters.isEmpty()) {
			logger.warn("不能发现任何  messageConverters; 请配置HttpMessageConverter.class 类型的 bean");
		}
		OrderComparator.sort(this.messageConverters);
	}
	
	

	@Override
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}




}
