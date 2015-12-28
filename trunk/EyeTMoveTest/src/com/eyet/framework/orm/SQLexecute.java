package com.eyet.framework.orm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.eyet.framework.orm.annotation.EntityId;
import com.eyet.framework.orm.util.DBConnectionManager;

/**
 * 
 * @author hexleo
 * 
 *         执行sql查询语句
 * 
 */
public class SQLexecute {

	/**
	 * 支持原生的sql查询操作
	 * 
	 * @param sql
	 * @return ResultSet 直接返回得到的结果
	 */
	public static ResultSet executeSQL(DBConnectionManager conMan, String sql) {
		if (conMan == null)	return null;
		Connection con = conMan.getConnection();
		if (con == null)return null;

		Statement stmt = null; // stmt 与 rs 不能在此关闭，因为需要将ResultSet传递出去
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			conMan.freeConnection(con);//使用完毕则可以马上释放连接
			stmt.execute(sql);
			rs = stmt.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: SQLexecute -> excuteSQL");
		} 
		return rs;
	}

	/**
	 * 执行sql统计函数
	 * 
	 * @param conMan
	 * @param sql
	 * @param values
	 * @return Inetger
	 */
	public static Integer executeCount(DBConnectionManager conMan, String sql,List<Object> values) {
		if (conMan == null)	return null;
		Connection con = conMan.getConnection(); // 获取连接
		if (con == null)return null;

		Integer result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			conMan.freeConnection(con); // 释放连接
			if (values != null && values.size() != 0) {
				int len = values.size();
				for (int i = 0; i < len; i++) {
					ps.setObject(i + 1, values.get(i));
				}
			}
			ps.execute();
			rs = ps.getResultSet();
			while (rs.next()) {
				result = rs.getInt("num");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: SQLexecute -> excuteCount");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 通过id选择对象
	 * @param conMan
	 * @param entity
	 * @param sql
	 * @param value
	 * @return Object 获得对象
	 */
	public static Object executeSelectById(DBConnectionManager conMan,Class entity, String sql, Object value) {
		if (conMan == null)	return null;
		Connection con = conMan.getConnection(); // 获取连接
		if (con == null)return null;

		Object result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			conMan.freeConnection(con); // 释放连接
			ps.setObject(1, value);
			ps.execute();
			rs = ps.getResultSet();
			//查询到结果
			if(rs.next()){
				Object obj = entity.newInstance();
				Class objClass = obj.getClass();
				for (Field field : objClass.getDeclaredFields()) { // 使用getDeclaredFields，获取私有变量
					try {
						// 需要设置参数类型
						Method method = objClass.getMethod(formatSetter(field.getName()), field.getType());
						method.invoke(obj, rs.getObject(field.getName()));
					} catch (NoSuchMethodException e) { // 没有找到对应的方法
						continue;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				result = obj;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: SQLexecute -> executeSelectById");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 执行select方法
	 * 
	 * @param conMan
	 * @param entity
	 * @param sql
	 * @param values
	 * @return List<Object> 查询结果
	 */
	public static List<Object> executeSelect(DBConnectionManager conMan,Class entity, String sql, List<Object> values) {
		if (conMan == null || entity == null)return null;
		Connection con = conMan.getConnection(); // 获取连接
		if (con == null)return null;

		List<Object> result = new ArrayList<Object>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			conMan.freeConnection(con); // 释放连接
			if (values != null && values.size() != 0) {
				int len = values.size();
				for (int i = 0; i < len; i++) {
					ps.setObject(i + 1, values.get(i));
				}
			}
			ps.execute();
			rs = ps.getResultSet();
			while (rs.next()) {
				Object obj = entity.newInstance();
				Class objClass = obj.getClass();
				for (Field field : objClass.getDeclaredFields()) { // 使用getDeclaredFields，获取私有变量
					try {
						// 需要设置参数类型
						Method method = objClass.getMethod(formatSetter(field.getName()), field.getType());
						method.invoke(obj, rs.getObject(field.getName()));
					} catch (NoSuchMethodException e) { // 没有找到对应的方法
						continue;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				result.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: SQLexecute -> excuteSelect");
		} catch (Exception e){
			e.printStackTrace();
		}  finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 执行插入语句
	 * @param conMan
	 * @param sql
	 * @param entity
	 * @return Integer 执行插入语句后影响的行数
	 */
	public static Integer executeSave(DBConnectionManager conMan , String sql, Object entity){
		if (conMan == null)	return 0;
		Connection con = conMan.getConnection(); // 获取连接
		if (con == null) return 0;
		
		Integer result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			conMan.freeConnection(con); // 释放连接
			Class objClass = entity.getClass();
			//insert语句分析部分
			int begin = sql.indexOf('(');
			int end = sql.indexOf(')', begin);
			String[] fields = sql.substring(begin+1, end).split(",");
			int i=1;
			for(String field : fields){
				Object obj = null;
				try {
					Method method = objClass.getMethod(formatGetter(field.trim()));
					obj = method.invoke(entity,null);
					
				} catch (NoSuchMethodException e) { // 没有找到对应的方法
					obj = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
				ps.setObject(i++, obj);
			}
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: SQLexecute -> executeSave");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 根据给定实体更新一个对象
	 * @param conMan
	 * @param sql
	 * @param entity
	 * @return Integer 更新后影响的条目数
	 */
	public static Integer executeUpdate(DBConnectionManager conMan , String sql, Object entity){
		if (conMan == null)	return 0;
		Connection con = conMan.getConnection(); // 获取连接
		if (con == null) return 0;
		
		Integer result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			conMan.freeConnection(con); // 释放连接
			Class objClass = entity.getClass();
			//insert语句分析部分
			int begin = sql.indexOf("set");
			int end = sql.indexOf("where" , begin);
			String[] fields = sql.substring(begin+4, end-1).split(",");
			
			int i=1;
			Object obj = null;
			for(String field : fields){
				String[] ff = field.split("=");
				if(ff.length != 2){
					continue;
				}
				try {
					Method method = objClass.getMethod(formatGetter(ff[0].trim()));
					obj = method.invoke(entity,null);
					
				} catch (NoSuchMethodException e) { // 没有找到对应的方法
					obj = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
				ps.setObject(i++, obj);
			}
			
			Object idObj = null;
			for(Field field : entity.getClass().getDeclaredFields()){
				if(field.isAnnotationPresent(EntityId.class)){
					Method method = objClass.getMethod(formatGetter(field.getName()));
					idObj = method.invoke(entity,null);
					break;
				}
			}
			ps.setObject(i , idObj);
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: SQLexecute -> executeUpdate");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 批量更新数据
	 * @param conMan
	 * @param sql
	 * @param values
	 * @return Integer
	 */
	public static Integer executeUpdateMany(DBConnectionManager conMan , String sql, List<Object> values){
		//预防出现没有where语句而导致更新整张表的错误
		if((values == null || values.size() == 0) && sql.indexOf("where") == -1 )return 0;
		if (conMan == null)	return 0;
		Connection con = conMan.getConnection(); // 获取连接
		if (con == null) return 0;
		
		Integer result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			conMan.freeConnection(con); // 释放连接
			int len = values.size();
			for(int i=0 ; i<len ; i++){
				ps.setObject(i+1 , values.get(i));
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: SQLexecute -> executeUpdateMany");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 根据id删除数据
	 * @param conMan
	 * @param sql
	 * @param id
	 * @return Integer
	 */
	public static Integer executeDelete(DBConnectionManager conMan , String sql, Object id){
		if (conMan == null)	return 0;
		Connection con = conMan.getConnection(); // 获取连接
		if (con == null) return 0;
		
		Integer result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			conMan.freeConnection(con); // 释放连接
			ps.setObject(1, id);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: SQLexecute -> executeDelete");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 批量删除数据
	 * @param conMan
	 * @param sql
	 * @param values
	 * @return Integer
	 */
	public static Integer executeDeleteMany(DBConnectionManager conMan , String sql, List<Object> values){
		//预防出现没有where语句而导致删除整张表的错误
		if((values == null || values.size() == 0) && sql.indexOf("where") == -1 )return 0;
		if (conMan == null)	return 0;
		Connection con = conMan.getConnection(); // 获取连接
		if (con == null) return 0;
		
		Integer result = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			conMan.freeConnection(con); // 释放连接
			int len = values.size();
			for(int i=0 ; i<len ; i++){
				ps.setObject(i+1 , values.get(i));
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: SQLexecute -> executeDeleteMany");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

	/**
	 * 整理格式，获得setter方法名称
	 * 
	 * @return
	 */
	private static String formatSetter(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append("set");
		sb.append(String.valueOf(str.charAt(0)).toUpperCase());
		sb.append(str.substring(1, str.length()));
		return sb.toString();

	}

	/**
	 * 整理格式，获得getter方法名称
	 * 
	 * @return
	 */
	private static String formatGetter(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append("get");
		sb.append(String.valueOf(str.charAt(0)).toUpperCase());
		sb.append(str.substring(1, str.length()));
		return sb.toString();
	}

}
