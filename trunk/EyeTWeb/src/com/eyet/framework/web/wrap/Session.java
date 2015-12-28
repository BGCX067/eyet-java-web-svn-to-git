package com.eyet.framework.web.wrap;

/**
 * 
 * @author Hexleo
 * 
 * 对session对象的操作封装
 *
 */
public interface Session {
	
	public String getSessionId();
	
	public Object get(String key);
	
	public void set(String key , Object value);
	
	public void remove(String key);
	
	public void clear();
}
