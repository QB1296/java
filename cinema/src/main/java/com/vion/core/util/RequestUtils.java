
package com.vion.core.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年5月13日 下午7:37:34
 */
public class RequestUtils {

	/**
	 * 根据HttpServletRequest对象得到ServletPath
	 * @param request
	 * @return
	 */
	public static String getServletPath(HttpServletRequest request) {
		String servletPath = request.getServletPath();

		String requestUri = request.getRequestURI();
		if (requestUri != null && servletPath != null
				&& !requestUri.endsWith(servletPath)) {
			int pos = requestUri.indexOf(servletPath);
			if (pos > -1) {
				servletPath = requestUri.substring(requestUri
						.indexOf(servletPath));
			}
		}

		if (null != servletPath && !"".equals(servletPath)) {
			return servletPath;
		}

		int startIndex = request.getContextPath().equals("") ? 0 : request
				.getContextPath().length();
		int endIndex = request.getPathInfo() == null ? requestUri.length()
				: requestUri.lastIndexOf(request.getPathInfo());

		if (startIndex > endIndex) {
			endIndex = startIndex;
		}

		return requestUri.substring(startIndex, endIndex);
	}
	
	

	/**
	 * 得到Uri
	 * @param request
	 * @return Uri
	 */
	public static String getUri(HttpServletRequest request) {

		String uri = RequestUtils.getServletPath(request);
		if (uri != null && !"".equals(uri)) {
			return uri;
		}

		uri = request.getRequestURI();
		return uri.substring(request.getContextPath().length());
	}
	
	
	/**
	 * 根据名字获取cookie
	 * @param request
	 * @param name cookie名字
	 * @return Cookie 没有返回null
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }  
	}
	
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){ 
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	
	
	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request){
		String ip=request.getHeader("x-forwarded-for");
		if(ip==null || ip.length()==0||"unknown".equalsIgnoreCase(ip)){
			ip=request.getHeader("Proxy-Client-IP");
		}
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
			ip=request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
			ip=request.getRemoteAddr();
		}
		
		return ip;
	}
	
	/**
	 * 获取当前接收到请求的IP地址及端口
	 * @param request
	 * @return IP:Port 表示IP及端口的字符串
	 */
	public static String getServerIpPortPairFromRequest(HttpServletRequest request){
		return request.getLocalAddr()+":" + request.getLocalPort();
	}
}
