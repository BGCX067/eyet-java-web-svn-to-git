package com.eyet.framework.orm.util;

import java.sql.Connection;
import java.util.Map;

import com.eyet.framework.orm.util.DBConnectionPool;

public class DBConnectionManager {

	//使用单例模式
	private static DBConnectionManager instance = null;

	private DBConnectionPool dbConnectionPool;

	private Map<String, String> dbConfig;

	public DBConnectionManager() {
		dbConfig = DBConfig.getConfig();
		createPool();
	}
	/**
	 * 创建单数据连接池
	 */
	private void createPool(){
		dbConnectionPool = new DBConnectionPool();
		dbConnectionPool.setDbtype(dbConfig.get("dbtype"));
		dbConnectionPool.setDriver(dbConfig.get("driver"));
		dbConnectionPool.setUrl(dbConfig.get("url"));
		dbConnectionPool.setUser(dbConfig.get("user"));
		dbConnectionPool.setPassword(dbConfig.get("password"));
		dbConnectionPool.setMaxConn(Integer.parseInt(dbConfig.get("maxPoolSize")));
	}
	
	/**
	 * 返回实例
	 * @return
	 */
	public static synchronized DBConnectionManager getInstance() {
		if (instance == null) {
			instance = new DBConnectionManager();
		}
		return instance;
	}
	/**
	 * 获得数据库连接
	 * @return
	 */
	public Connection getConnection(){
		Connection con = null;
		if(dbConnectionPool != null){
			con = dbConnectionPool.getConnection();
		}
		return con;
	}
	
	/**
	 * 释放连接
	 * @param con 使用完毕的数据库连接
	 */
	public void freeConnection(Connection con){
		if(dbConnectionPool != null){
			dbConnectionPool.freeConnection(con);
		}
	}
	/**
	 * 释放单例数据库连接池
	 */
	public void release(){
		if(dbConnectionPool != null){
			dbConnectionPool.release();
		}
	}
	

}
