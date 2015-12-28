package com.eyet.framework.web.filter;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 
 * @author： LYH
 * @describe：请求包装器，将Get提交中URL的参数进行编码转换
 * @time：2012-09-7
 * 
 */

public class GetHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private String encoding = "UTF-8";
	
	public GetHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	public GetHttpServletRequestWrapper(HttpServletRequest request,String encoding) {
		super(request);
		this.encoding = encoding;
	}
	
	@Override
	public Map getParameterMap() {

		Map params=super.getParameterMap();   
        //获得所有参数的值   
        Collection c = params.values();   
        //迭代出每一个参数   
        for(Iterator it=c.iterator();it.hasNext();){   
            String[] values=(String[])it.next();   
            //对每一个字符进行重新的编码解码   
            for(int i=0;i<values.length;i++){   
            	try {
					values[i]=new String(values[i].getBytes("ISO-8859-1"), encoding);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}   
            }   
        }   
		return params;
	}
	
	@Override
	public String getParameter(String name) {
		
		String param = super.getParameter(name);
		if(param == null){
			param = null;
		}else{
			param = encodingConvert(param);
		}
		
		return param;
	}
	
	/**
	 * 对URL中的参数进行编码转换
	 */
	 public String encodingConvert(String param) {   
	        try {   
	            return new String(param.trim().getBytes("ISO-8859-1"), encoding);   
	        } catch (UnsupportedEncodingException e) {   
	            return param;   
	        }   
	    }
}
