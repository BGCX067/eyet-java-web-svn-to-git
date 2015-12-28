package test;

import java.util.Map;

import com.eyet.framework.orm.util.DBConfig;

public class TestDBConfig {
	public static void main(String[] args){
		Map<String,String> map = DBConfig.getConfig();
		
		System.out.println(map.get("driver"));
		System.out.println(map.get("url"));
		System.out.println(map.get("user"));
		System.out.println(map.get("password"));
		
		System.out.println(map.get("maxPoolSize"));
	}
}
