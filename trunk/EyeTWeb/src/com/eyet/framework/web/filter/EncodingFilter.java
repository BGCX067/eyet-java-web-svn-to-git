package com.eyet.framework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author： LYH
 * @describe：字符编码过滤器,对请求进行拦截,解决Post、Get提交的中文乱码问题
 * @time：2012-09-7
 * 
 */

public class EncodingFilter implements Filter{
	private FilterConfig filterConfig = null;
	private String encoding = null;
	
	//初始化
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig; 
		this.encoding  = filterConfig.getInitParameter("encoding");
		
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		System.out.println("请求地址："+req.getRequestURI().toString());
		
		//设置请求字符编码 
		request.setCharacterEncoding(encoding);	
		//设置响应字符编码
		response.setCharacterEncoding(encoding);	
					
		System.out.println("设置请求编码后:"+req.getCharacterEncoding());
		System.out.println("这是"+req.getMethod()+"提交");	
				//如果是Get方式提交	
				if("get".equalsIgnoreCase(req.getMethod().trim().toString())){
					//对请求进行包装,完成URL中参数的编码转换
					req = new GetHttpServletRequestWrapper(req,encoding);
				}
				
	    //执行下一个过滤器
		filterChain.doFilter(req, res);
	}
	//销毁
	public void destroy() {
		this.filterConfig = null;
		this.encoding = null;
		
	}
	
	public FilterConfig getFilterConfig() {
		return filterConfig;
	}
	
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
	

}
