package com.eyet.framework.web.init;

import javax.servlet.ServletConfig;

import com.eyet.framework.util.ClassUtil;

/**
 * 
 * @author Hexleo
 *
 * 自定义初始化方法调用路径固定
 */
public class InitConfig {
	
	private static final String DEFAULT_INIT = "lib.init.Init";
	
	public static void init(ServletConfig config){
		InitSupport initSupport = (InitSupport) ClassUtil.getInstance(DEFAULT_INIT);
		if(initSupport != null){
			initSupport.init();
		}
	}
}
