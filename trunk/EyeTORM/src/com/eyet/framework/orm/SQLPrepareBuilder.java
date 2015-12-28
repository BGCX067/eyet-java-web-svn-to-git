package com.eyet.framework.orm;

import java.lang.reflect.Field;


import com.eyet.framework.orm.Inquery;
import com.eyet.framework.orm.annotation.EntityId;

public class SQLPrepareBuilder {
	
	public static String getEntityName(Class entity){
		String entityName = entity.getName();
		int pot = entityName.lastIndexOf('.');
		if(pot == -1){
			entityName = entityName.toLowerCase();
		}else{
			entityName = entityName.substring(pot+1).toLowerCase();
		}
		return entityName;
	}
	
	/**
	 * 范例：select count(*) num from user where uid=? or username like ? and uid>=? 
	 * @param entity
	 * @param query
	 * @return
	 */
	public static String buildCount(Class entity , Inquery query){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) num from ");
		sql.append(getEntityName(entity));
		if(query != null){
			sql.append(query.getPrepareWhere());
		}
		return sql.toString();
	}
	/**
	 * 范例：select * from user where uid=? 
	 * @param entity
	 * @return
	 */
	public static String buildSelectById(Class entity){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		sql.append(getEntityName(entity));
		for(Field field : entity.getDeclaredFields()){
			if(field.isAnnotationPresent(EntityId.class)){
				sql.append(" where " + field.getName() +"=? ");
				break;
			}
		}		
		return sql.toString();
	}
	
	/**
	 * 范例：select * from user where uid=? or username like ? and uid>=?  order by uid desc limit 60,20
	 * @param entity
	 * @param query
	 * @param order
	 * @param limit
	 * @return
	 */
	public static String buildSelect(Class entity , Inquery query , String order , String limit){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ");
		sql.append(getEntityName(entity));
		if(query != null){
			sql.append(query.getPrepareWhere());
		}
		if(order != null){
			sql.append(order);
		}
		if(limit != null){
			sql.append(limit);
		}
		return sql.toString();
	}
	
	/**
	 * 范例：insert into user(username,passwd) values(?,?)
	 * @param entity
	 * @return
	 */
	public static String buildSave(Class entity){
		int fieldNum = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");
		sql.append(getEntityName(entity));
		sql.append("(");
		
		for(Field field : entity.getDeclaredFields()){
			//插入数据操作不需要设置主键，因为主键是自动增长类型
			if(field.isAnnotationPresent(EntityId.class)){
				continue;
			}
			if(fieldNum != 0){
				sql.append(',');
			}
			sql.append(field.getName());
			fieldNum++;
		}
		sql.append(") values(?");
		fieldNum--;
		while(fieldNum-- != 0){
			sql.append(",?");
		}
		sql.append(") ");

		return sql.toString();
	}
	
	/**
	 * 范例：update user set username=? passwd=? where uid=?
	 * @param entity
	 * @return
	 */
	public static String buildUpdate(Class entity){
		StringBuilder sql = new StringBuilder();
		sql.append("update ");
		sql.append(getEntityName(entity));
		sql.append(" set ");
		String whereId = null;
		boolean isFirst = true;
		for(Field field : entity.getDeclaredFields()){
			if(field.isAnnotationPresent(EntityId.class)){
				whereId = " where " + field.getName() + "=?";
				continue;
			}
			if(isFirst){
				sql.append(field.getName()+"=?");
				isFirst = false;
			}else{
				sql.append("," + field.getName()+"=?");
			}
			
		}
		if(whereId == null){
			return "";
		}else{
			sql.append(whereId);
			return sql.toString();
		}
	}
	/**
	 * 范例：update user set uid=12,passwd=123 where username=? and passwd>?
	 * @param entity
	 * @param query
	 * @param sqlSet
	 * @return
	 */
	public static String buildUpdate(Class entity , Inquery query , String sqlSet){
		StringBuilder sql = new StringBuilder();
		sql.append("update ");
		sql.append(getEntityName(entity));
		sql.append(" set ");
		sql.append(sqlSet);
		if(query != null){
			sql.append(query.getPrepareWhere());
		}
		return sql.toString();
	}
	
	/**
	 * 范例：delete from user where uid=?
	 * @param entity
	 * @return
	 */
	public static String buildDelete(Class entity){
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ");
		sql.append(getEntityName(entity));
		String whereId = null;
		for(Field field : entity.getDeclaredFields()){
			if(field.isAnnotationPresent(EntityId.class)){
				whereId = " where " + field.getName() + "=?";
				break;
			}
		}
		if(whereId == null){
			return "";
		}else{
			sql.append(whereId);
			return sql.toString();
		}
	}
	/**
	 * 范例 :delete from user where uid in(?,?,?)
	 * @param entity
	 * @param query
	 * @return
	 */
	public static String buildDelete(Class entity , Inquery query){
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ");
		sql.append(getEntityName(entity));
		if(query != null){
			sql.append(query.getPrepareWhere());
		}
		return sql.toString();
	}
}
