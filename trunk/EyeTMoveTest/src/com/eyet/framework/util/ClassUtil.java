package com.eyet.framework.util;

public class ClassUtil {
	
	public static final String DEFAULT_LIB_EVENT = "lib.event.";
	
	public static final String DEFAULT_LIB_EVENT_SUFFIX = "Event";
	
	public static final String DEFAULT_LIB_EVENT_VERIFY = "lib.event.verify.";
	
	public static final String DEFAULT_LIB_EVENT_VERIFY_SUFFIX = "Verify";
	
	/**
	 * 整合获取完整Event类路径
	 * @param orgClassName
	 * @return
	 */
	private static String getEventClassName(String orgClassName){
		StringBuilder sb = new StringBuilder();
		sb.append(DEFAULT_LIB_EVENT);
		sb.append(orgClassName);
		sb.append(DEFAULT_LIB_EVENT_SUFFIX);
		return sb.toString();
		
	}
	
	private static String getVerifyClassName(String orgClassName){
		StringBuilder sb = new StringBuilder();
		sb.append(DEFAULT_LIB_EVENT_VERIFY);
		sb.append(orgClassName);
		sb.append(DEFAULT_LIB_EVENT_VERIFY_SUFFIX);
		return sb.toString();
	}
	
	
	public static Object getInstance(String className){
		try {
			Class objClass = Class.forName(className);
			Object obj = objClass.newInstance();
			return obj;
			
		} catch (ClassNotFoundException e) {
			System.out.println("getInstance null class name:" + className);
			return null;
		} catch (Exception e) {
			System.out.println("getInstance null class name::" + className);
			return null;
		}
	}
	
	public static Object getEventInstance(String className){
		return getInstance(getEventClassName(className));
	}
	
	public static Object getVerifyInstance(String className){
		return getInstance(getVerifyClassName(className));
	}
	
	
	public static Class getClassByName(String className){
		try {
			return Class.forName(className);
						
		} catch (ClassNotFoundException e) {
			System.out.println("Error : Classutil getClass,class name:" + className);
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.out.println("Error : Classutil getClass,class name:" + className);
			e.printStackTrace();
			return null;
		}
	}
	
	public static Class getEventClassByName(String className){
		return getClassByName(getEventClassName(className));
	}
	public static Class getVerifyClassByName(String className){
		return getClassByName(getVerifyClassName(className));
	}
}
