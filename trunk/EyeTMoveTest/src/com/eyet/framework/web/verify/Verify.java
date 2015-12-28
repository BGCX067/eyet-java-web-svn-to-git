package com.eyet.framework.web.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Hexleo
 * 
 * 验证接口
 * 
 */
public interface Verify {
	
	/**
	 * 初始化配置
	 * @param request
	 * @param response
	 * @param eventName
	 * @param eventMethod
	 */
	public void initVerify(HttpServletRequest request , HttpServletResponse response 
			, String eventName , String eventMethod);
	
	/**
	 * 执行验证接口
	 * @return  验证结果
	 */
	public boolean execute();
	
	/**
	 * 验证失败后转向
	 * @return
	 */
	public String failLocation();
	
}
