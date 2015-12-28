package com.eyet.framework.orm.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBConfig {
	private static Map<String,String> dbConfig = null;
	
	private static String dbtype = null;
	
	private static Boolean sqldebug = null;
	
	private static void getDbconfigProperties(){
		InputStream is = DBConfig.class.getClassLoader().getResourceAsStream("dbconfig.properties");
		Properties prop = new Properties();
		//使用默认值进行容错处理
		try {
			prop.load(is);
			dbConfig =  new HashMap<String,String>();
			dbtype = prop.getProperty("dbtype" , "").trim();
			dbConfig.put("dbtype", dbtype);
			dbConfig.put("driver", prop.getProperty("driver" , "").trim());
			dbConfig.put("url", prop.getProperty("url" , "").trim());
			dbConfig.put("user", prop.getProperty("user" , "").trim());
			dbConfig.put("password", prop.getProperty("password" , "").trim());
			dbConfig.put("maxPoolSize", prop.getProperty("maxPoolSize", "100").trim());
			//是否开启调试模式
			if("true".equals(prop.getProperty("sqldebug", "false"))){
				sqldebug = true;
			}else{
				sqldebug = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Map<String,String> getConfig(){
		if(dbConfig == null){
			getDbconfigProperties();
		}
		return dbConfig;
	}
	public static String getDbtype(){
		if(dbtype == null){
			getDbconfigProperties();
		}
		return dbtype;
	}
	public static Boolean getSqldebug(){
		if(sqldebug == null){
			getDbconfigProperties();
		}
		return sqldebug;
	}
	
}
