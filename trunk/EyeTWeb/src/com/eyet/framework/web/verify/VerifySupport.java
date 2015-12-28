package com.eyet.framework.web.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eyet.framework.operation.OperationContainer;
import com.eyet.framework.web.wrap.Session;
import com.eyet.framework.web.wrap.SessionWrap;

/**
 * 
 * @author Hexleo
 * 
 * 验证支持类
 * 继承类的验证若存在构造函数则必须调用父类构造函数方法
 * 自定义验证类不推荐重写构造函数
 * 继承的类后缀必须为Verify
 * 且必须与特定的Event相对应
 */
public class VerifySupport implements Verify {
	//使用原生request
	private HttpServletRequest request;
	//使用原生response
	private HttpServletResponse response;
	//使用封装类session
	private Session session;
	
	//请求事件名称
	private String eventName;
	//请求事件方法
	private String eventMethod;
	
	/**
	 * 必须为无参构造函数
	 */
	public VerifySupport(){
		this.request = null;
		this.response = null;
		this.session = null;
		this.eventName = null;
		this.eventMethod = null;
	}
	
	
	/**
	 * 初始化参数设置
	 * @param request
	 * @param response
	 * @param eventName
	 * @param eventMethod
	 */
	public void initVerify(HttpServletRequest request , HttpServletResponse response 
			, String eventName , String eventMethod){
		this.request = request;
		this.response = response;
		this.session = new SessionWrap(request);
		this.eventName = eventName;
		this.eventMethod = eventMethod;
	}
	
	/**
	 * 若不自定义验证类则默认返回true
	 * 子类必须重写此方法
	 */
	public boolean execute() {
		return true;
	}
	/**
	 * 子类必须重写此方法
	 */
	public String failLocation() {
		return null;
	}
	
	
	/**
	 * 一系列getter的方法
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Session getSession() {
		return session;
	}

	public String getEventName() {
		return eventName;
	}

	public String getEventMethod() {
		return eventMethod;
	}
	
	/**
	 * 获取Operation操作类
	 * @param name 传入例如  LoginOperation
	 * @return
	 */
	public Object getOperation(String name){
		OperationContainer oc = OperationContainer.getInstance();
		return oc.getOperation(name);
	}
	
}
