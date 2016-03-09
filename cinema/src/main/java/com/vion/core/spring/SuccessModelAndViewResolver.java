 
package com.vion.core.spring;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import com.vion.core.annotation.Success;
import com.vion.core.model.SuccessedReplyModel;
import com.vion.core.util.Annotations;

/**
 * <b>功能描述</b> <br>
 * 使用{@link Success}处理。使得有返回成功JSON的能力
 * @author YUJB
 * @date 2012-10-30 下午12:36:26
 */
public class SuccessModelAndViewResolver implements  ModelAndViewResolver,Ordered{
	
	/** 日志 */
	protected final Log logger = LogFactory.getLog(getClass());
	
	/** order default highest  */
	private int order = Ordered.HIGHEST_PRECEDENCE;
	
	
	/** httpMessageConverter must be not null  */
	private HttpMessageConverter<SuccessedReplyModel> httpMessageConverter;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.core.Ordered#getOrder()*/
	@Override
	public int getOrder() {
		return order;
	}
	

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver#resolveModelAndView(java.lang.reflect.Method, java.lang.Class, java.lang.Object, org.springframework.ui.ExtendedModelMap, org.springframework.web.context.request.NativeWebRequest)*/
	@Override
	public ModelAndView resolveModelAndView(Method handlerMethod,
			@SuppressWarnings("rawtypes") Class handlerType, Object returnValue,
			ExtendedModelMap implicitModel, NativeWebRequest webRequest) {
		
		Success success = Annotations.getAnnotation(handlerMethod, Success.class);
		
		if(success == null){
			return ModelAndViewResolver.UNRESOLVED;
		}
		
		SuccessedReplyModel successedReplyModel = new SuccessedReplyModel();
		successedReplyModel.setDescribe(success.Describe());
		
		HttpOutputMessage outputMessage = new ServletServerHttpResponse(
						 ((ServletWebRequest)webRequest)
						 .getResponse());
		outputMessage.getHeaders().setContentType(MediaType.TEXT_HTML);
		
		MediaType mediaType = MediaType.ALL;
		
		List<MediaType> supportedMediaTypes = httpMessageConverter.getSupportedMediaTypes();
		if (supportedMediaTypes.size() == 1) {
			mediaType = supportedMediaTypes.get(0);
		}
		try {
			httpMessageConverter.write(successedReplyModel, mediaType, outputMessage);
		} catch (HttpMessageNotWritableException e) {
			logger.error("resolveModelAndView 发生异常 " , e);
		} catch (IOException e) {
			logger.error("resolveModelAndView 发生异常 : " , e);
		}
		
		
		return null;
	}
	
	
	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	
	/**
	 * @return the httpMessageConverter
	 */
	public HttpMessageConverter<SuccessedReplyModel> getHttpMessageConverter() {
		return httpMessageConverter;
	}

	/**
	 * @param httpMessageConverter the httpMessageConverter to set
	 */
	public void setHttpMessageConverter(
			HttpMessageConverter<SuccessedReplyModel> httpMessageConverter) {
		this.httpMessageConverter = httpMessageConverter;
	}

}
