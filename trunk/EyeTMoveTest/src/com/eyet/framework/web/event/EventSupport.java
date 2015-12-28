package com.eyet.framework.web.event;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.eyet.framework.operation.OperationContainer;

public class EventSupport {
	private Boolean isForward;
	
	private Boolean isJson;
	
	private String jsonObject;
	
	private String redirectLocation;
	
	private EventContext eventContext;
	/*
	 * 接口函数
	 * java.util.Map<java.lang.String,java.lang.String[]>	getParameterMap() 
	 */
	private Map<String,String[]> requestParameter;
	
	private Map<String,Object> responseParameter;
	
	//必须为无参构造函数
	public EventSupport(){
		//默认不转发
		this.isForward = false;
		//默认不是json格式
		this.isJson = false;
		this.redirectLocation = null;
		this.requestParameter = null;
		this.responseParameter = new HashMap<String,Object>();
		this.eventContext = null;
	}
	
	public Boolean isForward(){
		return this.isForward;
	}
	public Boolean isJson(){
		return this.isJson;
	}
	public String getRedirectLocation(){
		return this.redirectLocation;
	}
	
	public Map<String,Object> getResponseParameter(){
		return this.responseParameter;
	}
	
	public EventContext getEventContext(){
		return this.eventContext;
	}
	
	/**
	 * 使用forward的方法显示对应的jsp
	 */
	public void display(){
		this.isForward = true;
	}
	/**
	 * 使用重定向的方法
	 * @param location
	 */
	public void display(String location){
		this.isForward = false;
		try {
			this.redirectLocation = new String (location.getBytes("UTF-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			this.redirectLocation = location;
		}//new String(location.getBytes("UTF-8"), "ISO-8859-1");
	}
	
	/**
	 * 使用servlet直接显示json
	 * @param json
	 */
	public void displayJson(String json){
		this.isJson = true;
		this.jsonObject = json;
	}
	
	/**
	 * 返回json数据
	 * @return
	 */
	public String getJsonObject(){
		return this.jsonObject;
	}
	
	/**
	 * 设置request的参数
	 * @param p
	 */
	public void setRequestParameter(Map<String,String[]> p){
		this.requestParameter = p;
	}
	
	/**
	 * 设置evenContext
	 * @param eventContext
	 */
	public void setEventContext(EventContext eventContext){
		this.eventContext = eventContext;
	}
	
	/**
	 * 获取request中的参数
	 * @param name
	 * @return
	 */
	public String get(String key){
		if(this.requestParameter == null){
			return null;
		}
		//request get parameter map 返回的是 string[]
		String[] result = this.requestParameter.get(key);
		if(result == null){
			return null;
		}
		
		return result[0];
	}
	public Integer getInt(String key){
		String i = this.get(key);
		if(i == null){
			return 0;
		}
		return Integer.valueOf(i);
	}
	public Double getDouble(String key){
		String d = this.get(key);
		if(d == null){
			return 0.0;
		}
		return Double.valueOf(d);
	}
	/**
	 * 设置响应参数
	 * @param name
	 * @return
	 */
	public void set(String key,Object obj){
		this.responseParameter.put(key, obj);
	}
	
	/**
	 * 默认显示页面
	 */
	public void index(){
		this.display("tpl/Default/default.jsp");
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
	
	/**
	 * 对象自动填充字段
	 * @param obj
	 */
	public void fill(Object obj){
		if(obj == null)return;
		String param;
		String fieldName;
		Class objClass = obj.getClass();
		Field[] fields = objClass.getDeclaredFields();
		for(Field field : fields){
			fieldName = field.getName();
			param = this.get(fieldName);
			if(param != null){
				try {
					Method method = objClass.getDeclaredMethod(
							"set"
							+ String.valueOf(fieldName.charAt(0)).toUpperCase()
							+ fieldName.substring(1,fieldName.length()), 
							field.getType());
					if(field.getType() == Integer.class){
						method.invoke(obj, Integer.valueOf(param));
					}else if(field.getType() == Double.class){
						method.invoke(obj, Double.valueOf(param));
					}else{
						method.invoke(obj,param);
					}
				} catch (Exception e) {
					//可能是类型转换异常
					//e.printStackTrace();
				}
			}
		}
		
	}
	
	
}
