package com.eyet.framework.web.util;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.eyet.framework.util.ClassUtil;
import com.eyet.framework.web.event.EventContext;
import com.eyet.framework.web.event.EventSupport;

public class EventUtil {
	
	private EventContext eventContext;

	private EventSupport event;
	
	private boolean isNormal;
	
	/**
	 * 处理对应的Event
	 * @param eventName
	 * @param eventMethod
	 * @param param
	 */
	public EventUtil(String eventName , String eventMethod , HttpServletRequest request){
		System.out.println("eventName:" + eventName + "  eventMethod:" + eventMethod);
		eventContext = new EventContext(request);
		
		isNormal = true;
		event = (EventSupport) ClassUtil.getEventInstance(eventName);
		
		if(event != null){
			event.setEventContext(eventContext);
			event.setRequestParameter(request.getParameterMap());
			executeEvent(ClassUtil.getEventClassByName(eventName),eventMethod);
			System.out.println("event create normal.isForward:" + event.isForward());
		}else{
			System.out.println("event create not normal.");
			isNormal = false;
		}
		
	}
	
	/**
	 * 执行指定的方法
	 * @param eventMethod
	 */
	private void executeEvent(Class eventClass , String eventMethod){
		try {
			Method method = eventClass.getMethod(eventMethod, null);
			method.invoke(event);
		} catch (Exception e) {
			isNormal = false;
			e.printStackTrace();
		}
	}
	
	/**
	 * 运行是否正常
	 * @return
	 */
	public boolean isNormal(){
		return this.isNormal;
	}
	public boolean isForward(){
		return this.event.isForward();
	}
	public String getRedirectLocation(){
		return event.getRedirectLocation();
	}
	
	/**
	 * 返回json数据
	 * @return
	 */
	public boolean isJson(){
		return this.event.isJson();
	}
	public String getJsonObject(){
		return this.event.getJsonObject();
	}
	
	/**
	 * 获取执行后的返回值
	 * @return
	 */
	public Map<String,Object> getResponseParameter(){
		return event.getResponseParameter();
	}
}
