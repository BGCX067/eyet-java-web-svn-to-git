package com.eyet.framework.web.init;

import javax.servlet.ServletConfig;

/**
 * 
 * @author Hexleo
 * 
 * 规范自定义Init，必须实现init方法
 *
 */
public abstract class InitSupport {
	
	private ServletConfig config;
	
	public abstract void init();
	
	public void setServletConfig(ServletConfig config){
		this.config = config;
	}
	
	public String get(String name){
		if(config == null){
			return null;
		}
		return config.getInitParameter(name);
	}
	
}
