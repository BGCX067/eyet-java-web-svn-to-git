package com.eyet.framework.orm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author WHL
 * 
 *         数据库连接池类
 * 
 */
public class DBConnectionPool {
	private int inUsed = 0; // 使用的连接数
	private List<Connection> freeConnections = new ArrayList<Connection>();// 容器，空闲连接
	private int maxConn = 0; // 最大连接
	private String dbtype; //数据库类型  mysql / sqlserver
	private String driver; // 驱动
	private String url; // 数据库连接地址
	private String user; // 用户名
	private String password; // 密码

	public DBConnectionPool() {
	}

	/**
	 * 创建新连接
	 * 
	 * @return
	 */
	private Connection newConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("error: db driver not fund!");
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("error: create connection fail!");
		}
		return con;
	}

	/**
	 * 
	 * 从连接池里得到连接
	 * 
	 * @return
	 */
	public synchronized Connection getConnection() {
		Connection con = null;
		if (this.freeConnections.size() > 0) {
			con = (Connection) this.freeConnections.get(0);
			this.freeConnections.remove(0);// 如果连接分配出去了，就从空闲连接里删除
			if (con == null)
				con = getConnection(); // 继续获得连接
		}else if (this.inUsed < this.maxConn) {
			con = newConnection(); // 新建连接
		}else {
			con = null;
		}
		if (con != null) {
			this.inUsed++;
		}
		return con;
	}

	/**
	 * 用完，释放连接
	 * 
	 * @param con
	 */
	public synchronized void freeConnection(Connection con) {
		this.freeConnections.add(con);// 添加到空闲连接的末尾
		this.inUsed--;
	}

	/**
	 *释放全部连接
	 * 
	 */
	public synchronized void release() {
		Iterator<Connection> allConns = this.freeConnections.iterator();
		while (allConns.hasNext()) {
			Connection con = (Connection) allConns.next();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		this.freeConnections.clear();

	}

	public int getMaxConn() {
		return maxConn;
	}

	public String getDbtype() {
		return dbtype;
	}

	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}

	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

}
