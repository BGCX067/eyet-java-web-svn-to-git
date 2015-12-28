package com.eyet.framework.global;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Hexleo
 * 放置全局变量，比如权限表的储存
 */
public class Global {
	
	private static Map<String , Object> globalValue = new HashMap<String , Object>();
	
	public static final String ABSOLUTE_PATH = "ABSOLUTE_PATH_KEY";
	
	public static void init(){
		if(globalValue != null){
			globalValue.clear();
			globalValue = null;
		}
		createGlobalValue();
	}
	
	private static void createGlobalValue(){
		if(globalValue == null){
			globalValue = new HashMap<String , Object>();
		}
	}
	public static Object get(String key){
		synchronized (globalValue) {
			createGlobalValue();
			return globalValue.get(key);		
		}
		
	}
	
	public static void set(String key , Object value){
		synchronized (globalValue){
			createGlobalValue();
			globalValue.put(key, value);
		}
	}
	
}
