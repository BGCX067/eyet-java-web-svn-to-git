package com.eyet.framework.web.util;

public class UriParseUtil {
	
	private static final String DEFAULT_EVENT_NAME = "Index";
	
	private static final String DEFAULT_EVENT_METHOD = "index";
	
	/**
	 * 
	 * 分析url  分解为eventName 与eventMethod两个部分
	 * servlet 截获的uri必定含有 ".move"
	 * "/index.move"  =>  Inedx + index
	 * "/Xxx.move" => Xxx + index
	 * "/xxx.move" => Xxx + index
	 * "/Xxx/xxx.move" => Xxx + xxx
	 * 
	 * @param prefix
	 * @param url
	 * @return
	 */
	public static String[] parse(String uri){
		if(uri == null || uri.length() == 0){
			return null;
		}
		String eventName = DEFAULT_EVENT_NAME;
		String eventMethod = DEFAULT_EVENT_METHOD;
		if("/index.move".equals(uri)){
			return formatResult(eventName , eventMethod);
		}
		//最后的点   /a.move   lastPot must >= 2
		int lastPot = uri.indexOf('.');
		if(lastPot < 1){
			return null;
		}
		//最后的斜线
		int lastSep = uri.lastIndexOf('/');
		//  "/Xxx.move" or "/xxx.move"
		if(lastSep == 0){
			eventName = formatEventName(uri.substring(1,lastPot));
			return formatResult(eventName , eventMethod);
		}
		
		// "/Xxx/xxx.move"
		
		eventName = formatEventName(uri.substring(1,lastSep));
		eventMethod = formatEventMethod(uri.substring(lastSep+1, lastPot));
		return formatResult(eventName , eventMethod);
	}
	
	private static String[] formatResult(String eventName , String eventMethod){
		String[] result = new String[2];
		result[0] = eventName;
		result[1] = eventMethod;
		return result;
	}
	
	private static String formatEventName(String eventName){
		if(eventName.length() == 1){
			return eventName.toUpperCase();
		}
		return eventName.substring(0, 1).toUpperCase() + eventName.substring(1, eventName.length()).toLowerCase();
	}
	
	private static String formatEventMethod(String eventMethod){
		return eventMethod.toLowerCase();
	}
}
