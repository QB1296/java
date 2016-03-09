/**
 * 文件名： HttpUtil.java
 *  
 * 版本信息：  
 * 日期：2015年9月8日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.util;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年9月8日 上午11:31:55
 */
public class HttpUtils {

	static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	
	static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	static{
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static RequestConfig getRequestConfig() {
		RequestConfig defaultRequestConfig = RequestConfig.custom()
				  .setSocketTimeout(60000)
				  .setConnectTimeout(60000)
				  .setConnectionRequestTimeout(60000)
				  .setStaleConnectionCheckEnabled(true)
				  .build();
		return defaultRequestConfig;
	}
	
	/**
	 * @param url
	 * @return
	 * @throws IOException 
	 */
	public static String get(String url) throws IOException{
		return get(url, null);
	}
	
	/**
	 * @param url
	 * @param heads
	 * @return
	 * @throws IOException
	 */
	public static String get(String url, Map<String, String> heads) throws IOException {
		// TODO Auto-generated method stub	
		CloseableHttpClient httpclient = HttpClients.custom()
				.setConnectionManager(cm)
				.setDefaultRequestConfig(getRequestConfig())
				.setConnectionManagerShared(true).build();
		try {
			HttpGet httpget = new HttpGet(url);
			logger.debug("Executing request " + httpget.getRequestLine());
			if (heads != null) {
				for (Entry<String, String> entry : heads.entrySet()) {
					httpget.addHeader(entry.getKey(), entry.getValue());
				}
			}
			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				@Override
				public String handleResponse(final HttpResponse response)
						throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status == HttpStatus.SC_OK) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity)
								: null;
					} else {
						throw new ClientProtocolException(
								"Unexpected response status: " + status);
					}
				}

			};
			return httpclient.execute(httpget, responseHandler);
		} catch (Exception e) {
			logger.error("{}", e);
		} finally {
			httpclient.close();
		}
		return null;
	}
}
