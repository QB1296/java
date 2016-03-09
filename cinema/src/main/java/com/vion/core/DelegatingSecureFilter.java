package com.vion.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * <b>功能描述</b> <br>
 * Filter 代理,只需要在web.xml中配置代理,Spring bean中类型为Filter的过滤器都将维护到过滤器链中
 * @author YUJB
 * @date 2014年5月13日 下午7:37:17
 */
public class DelegatingSecureFilter implements Filter{
	
	private WebApplicationContext webApplicationContext;

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		Map<String, Filter> filterMapper = webApplicationContext.getBeansOfType(Filter.class);
		
		List<Filter> filters = new ArrayList<Filter>();
		for (String key : filterMapper.keySet()) {
			filters.add(filterMapper.get(key));
		}
		Collections.sort(filters, new Comparator<Filter>() {
			@Override
			public int compare(Filter o1, Filter o2) {
				if (o1 instanceof SortedAble && o2 instanceof SortedAble) {
					return ((SortedAble)o2).sortNumber() - ((SortedAble)o1).sortNumber();
				}
				return 0;
			}
		});
		
		DelegatingFilterChain delegatingFilterChain = new DelegatingFilterChain(filterChain,filters);
		delegatingFilterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		setWebApplicationContext(WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext()));
	}
	
	public void setWebApplicationContext(
			WebApplicationContext webApplicationContext) {
		this.webApplicationContext = webApplicationContext;
	}
	
	/**
	 * <b>功能描述</b> <br>
	 * 代理FilterChain
	 * @author YUJB
	 * @date 2014年12月3日 下午2:28:14
	 */
	private  class DelegatingFilterChain implements FilterChain {
        private final FilterChain originalChain;
        private final List<Filter> additionalFilters;
        private final int size;
        private int currentPosition = 0;

        private DelegatingFilterChain(FilterChain chain, List<Filter> additionalFilters) {
            this.originalChain = chain;
            this.additionalFilters = additionalFilters;
            this.size = additionalFilters.size();
        }

        public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
            if (currentPosition == size) {
                originalChain.doFilter(request, response);
            } else {
                currentPosition++;

                Filter nextFilter = additionalFilters.get(currentPosition - 1);

                nextFilter.doFilter(request, response, this);
            }
        }
    }

	
	

}
