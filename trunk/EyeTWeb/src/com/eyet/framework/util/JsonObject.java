package com.eyet.framework.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
/**
 * 
 * @author: LYH
 * @description: Json工具类，将数据格式化为Json对象返回
 * @time：2012-09-9
 * 
 */
public class JsonObject {
	private Map map = new HashMap();
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	/**
	 * 将Map类似数据转换为JsonObject
	 * @param map
	 * @return
	 */
	public static JsonObject fromObject(Map map){
		JsonObject jsonObject = new JsonObject();
		jsonObject.getMap().putAll(map);
		return jsonObject;
	}
	/**
	 * 重写toString方法,将JsonObject对象转换为json格式的字符串
	 */
	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("{");
		Set jsonSet = map.entrySet();
		for(int i=0;i<jsonSet.size();i++){
			Object key = ((Entry)jsonSet.toArray()[i]).getKey();
			Object value = ((Entry)jsonSet.toArray()[i]).getValue();
			
			if(value==null){
				continue;
			}
			stringBuffer.append("\"").append(key).append("\":");
			if(isString(value)){
			    stringBuffer.append("\"").append(value).append("\"");
			}else if(isInteger(value)){
				stringBuffer.append(value);
			}else if(isDate(value)){
				stringBuffer.append("\"").append(format.format(value)).append("\"");
			}else if(isMap(value)){
				stringBuffer.append(fromObject((Map)value).toString());
			}else if(isList(value)){
				stringBuffer.append(convertList((List)value));
			}else if(value.getClass().isArray()){
				System.out.println("ppp"+value.getClass().getName());
				Class<?> c = value.getClass();
				stringBuffer.append(convertArray((String[])value));
			}else{
				stringBuffer.append(fromObject(value).toString());
			}
			if(i<jsonSet.size()-1){
				stringBuffer.append(",");	
			}
		}
		stringBuffer.append("}");
		return stringBuffer.toString();
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private static boolean isString(Object value){	
			return (value instanceof String || value instanceof Character);
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private static boolean isInteger(Object value){
	
	return (value instanceof Integer || value instanceof Boolean
            || value instanceof Double || value instanceof Float
            || value instanceof Short || value instanceof Long || value instanceof Byte);
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private static boolean isDate(Object value){
		return (value instanceof Date);
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private static boolean isMap(Object value){
		return (value instanceof Map);
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private static boolean isList(Object value){
		return (value instanceof List);
	}
	/**
	 * 对数组类型数据进行转换
	 * @param array
	 * @return
	 */
    public static String convertArray(Object array) {
        if (!array.getClass().isArray()) return "[]";
        
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        Object value = null;
        for (int i = 0; i < Array.getLength(array); i++) {
            value = Array.get(array, i);
            if (value instanceof Date) {
            	stringBuffer.append("\"").append(format.format(value)).append("\"");
            } else if (isString(value)) {
            	stringBuffer.append("\"").append(value).append("\"");
            } else if (isInteger(value)) {
            	stringBuffer.append(i);
            } else {
                stringBuffer.append(fromObject(value));
            }    
            if(i < Array.getLength(array)-1){
            	stringBuffer.append(",");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
    /**
     * 对List类型数据进行转换
     * @param list
     * @return
     */
    public static String convertList(List list) {
    	System.out.println("进入ListToStr");
        if (list == null)
            return null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        Object value = null;
        int i=0;
        for (java.util.Iterator it = list.iterator(); it.hasNext();) {
            value = it.next();
            if (value instanceof Map) {
            	stringBuffer.append(fromObject((Map) value).toString());
            } else if (isInteger(value)) {
            	stringBuffer.append(value);
            } else if (isString(value)) {
            	stringBuffer.append("\"").append(value).append("\"");
            } else {
                stringBuffer.append(fromObject(value).toString());
            }
            i++;
            if(i<list.size()){
            	stringBuffer.append(",");
            }
            
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
    /*
     * 
     */
    public static JsonObject fromObject(Object obj) {
        JsonObject jsonObject = new JsonObject();
        if (obj == null)
            return jsonObject;
        Class cls = obj.getClass();
        Field[] fs = cls.getDeclaredFields();
        Object value = null;
        String fieldName = null;
        Method method = null;
        int len = fs.length;
        for (int i = 0; i < len; i++) {
            fieldName = fs[i].getName();
            try {
                method = cls.getMethod(getGetter(fieldName), (Class[]) null);
                value = method.invoke(obj, (Object[]) null);
            } catch (Exception e) {
                continue;
            }
            jsonObject.put(fieldName, value);
        }
        return jsonObject;
    }
    /**
     * 
     * @param key
     * @param value
     * @return
     */
    public Map put(String key, Object value) {
        map.put(key, value);
        return map;
    }
    /**
     * 
     * @param property
     * @return
     */
    private static String getGetter(String property) {
        return "get" + property.substring(0, 1).toUpperCase()
                + property.substring(1, property.length());
    }

}
