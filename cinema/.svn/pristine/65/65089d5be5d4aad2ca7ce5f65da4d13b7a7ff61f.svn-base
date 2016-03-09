
package com.vion.core.spring;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.vion.core.exception.SpiderBussinessException;
import com.vion.core.model.JSONFailedReplyModel;
import com.vion.core.util.GenericUtils;


/**
 * <b>功能描述</b> <br>
 * spring MVC 拦截异常 对 {@link JSONFailedReplyModel}处理
 * @author YUJB
 * @date 2014-6-12 下午5:52:28
 */
public class ReplyModelHandlerExceptionResolver extends AbstractHandlerExceptionResolver implements InitializingBean{
	
	private transient Logger logger = LoggerFactory.getLogger(getClass());
	
	private HttpMessageConverter<JSONFailedReplyModel> httpMessageConverter;
	
	private boolean isShowException = false;
	
	private SpiderBusinessExceptionConvert<? extends Exception>[] customExceptionConverts;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver#doResolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)*/
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.info("异常拦截器拦截到异常", ex);
		
		if (!(ex instanceof SpiderBussinessException)) {
			Exception convertedEx = convertException(ex);
			if (convertedEx != null) {
				ex = convertedEx;
			}
		}
		
		JSONFailedReplyModel failedReplyModel = new JSONFailedReplyModel();
		if (isShowException) {
			failedReplyModel.setException(ex);
		}
		if (ex instanceof SpiderBussinessException) {
			failedReplyModel.setDescribe(((SpiderBussinessException)ex).getExceptionDes());
		}else {
			failedReplyModel.setDescribe(ex.getMessage());
		}
		ServletWebRequest webRequest = new ServletWebRequest(request, response);
		try {
			return handlerReplyException(failedReplyModel,webRequest);
		} catch (HttpMessageNotWritableException e) {
			logger.error("handlerReplyException 发生异常 : " , e);
		} catch (IOException e) {
			logger.error("handlerReplyException 发生异常 : " , e);
		}
		return null;
	}
	
	
	
	private ModelAndView handlerReplyException(JSONFailedReplyModel model ,ServletWebRequest webRequest) throws HttpMessageNotWritableException, IOException{
		HttpOutputMessage outputMessage = new ServletServerHttpResponse(webRequest.getResponse());
		outputMessage.getHeaders().setContentType(MediaType.TEXT_HTML);
		if (this.httpMessageConverter != null) {
			
			MediaType mediaType = MediaType.ALL;
			List<MediaType> supportedMediaTypes = httpMessageConverter.getSupportedMediaTypes();
			if (supportedMediaTypes.size() == 1) {
				mediaType = supportedMediaTypes.get(0);
			}
			httpMessageConverter.write(model, mediaType, outputMessage);
		}
		return new ModelAndView();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private SpiderBussinessException convertException(Exception e){
		for (SpiderBusinessExceptionConvert convert : customExceptionConverts) {
			Class<?> clazz = GenericUtils.getTypeParam(convert.getClass(),0);
			if (clazz.isAssignableFrom(e.getClass())) {
				return convert.convert(e);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()*/
	@Override
	public void afterPropertiesSet() throws Exception {
	}



	/**
	 * @return the httpMessageConverter
	 */
	public HttpMessageConverter<JSONFailedReplyModel> getHttpMessageConverter() {
		return httpMessageConverter;
	}



	/**
	 * @param httpMessageConverter the httpMessageConverter to set
	 */
	public void setHttpMessageConverter(
			HttpMessageConverter<JSONFailedReplyModel> httpMessageConverter) {
		this.httpMessageConverter = httpMessageConverter;
	}


	public boolean isShowException() {
		return isShowException;
	}



	public void setShowException(boolean isShowException) {
		this.isShowException = isShowException;
	}



	public void setCustomExceptionConverts(
			SpiderBusinessExceptionConvert<? extends Exception>[] customExceptionConverts) {
		this.customExceptionConverts = customExceptionConverts;
	}

	

}
