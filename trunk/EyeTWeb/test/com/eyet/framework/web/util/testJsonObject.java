package com.eyet.framework.web.util;

import java.util.HashMap;
import java.util.Map;

import com.eyet.framework.util.JsonObject;

import junit.framework.TestCase;

public class testJsonObject extends TestCase {

	public void testFromObjectMap() {
		 
		 Map map = new HashMap();
		 
		 map.put("statusCode","200"); 
		 map.put("message","操作成功");
		 map.put("navTabId","");
		 map.put("rel","");
		 map.put("callbackType","closeCurrent");
		 map.put("forwardUrl","");
		 
		 JsonObject jsonObject = JsonObject.fromObject(map);
		 
		 System.out.println(jsonObject);
	}

}
