package com.eyet.framework.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author WHL
 * @time 2012-08-21
 * 
 * @describe
 * 查询条件组织类
 * 主要组成where语句
 * 对数据库方言不敏感
 */
public class Inquery {
	
	//查询条件
	private Stack<String> query;
	//查询条件是否合法
	private boolean legalQuery;
	//问号所对应的值列表
	private List<Object> listValue;
	//原生where语句
	private String where;
	
	public Inquery(){
		query = new Stack<String>();
		legalQuery = false;
		listValue = new ArrayList<Object>();
		where = null;
	}
	
	//组织成代问号的准备语句
	public String getPrepareWhere(){
		//使用原生的where语句，这组装语句不执行
		if(where != null){
			return where;
		}
		if(query.empty()){
			return "";
		}
		StringBuilder PrepareWhere = new StringBuilder();
		Stack<String> querySave = new Stack<String>();
		if(!legalQuery){
			query.pop();
		}
		while(!query.empty()){
			querySave.push(query.pop());
		}
		PrepareWhere.append(" where ");
		String qtmp = null;
		while(!querySave.empty()){
			qtmp = querySave.pop();
			query.push(qtmp);
			PrepareWhere.append(qtmp);
			PrepareWhere.append(" ");
		}
		return PrepareWhere.toString();
	}
	public List<Object> getPrepareValues(){
		return listValue;
	}
	
	
	public void setAND(){
		if(legalQuery){
			query.push("and");
			legalQuery = false;
		}
	}
	
	public void setOR(){
		if(legalQuery){
			query.push("or");
			legalQuery = false;
		}
	}
	
	private void setDefault(){
		//默认缺省为 and
		if(legalQuery){
			query.push("and");
		}
	}
	
	public void setIn(String key , Object[] values){
		if(values == null || values.length == 0)
			return;
		setDefault();
		StringBuilder sb = new StringBuilder();
		sb.append(key + " in(?");
		for(int i=0 ; i<values.length ; i++){
			if(i!=0){
				sb.append(",?");
			}
			listValue.add(values[i]);
		}
		sb.append(")");
		query.push(sb.toString());
		legalQuery = true;
	}
	
	public void setNotIn(String key , Object[] values){
		setIn(key + " not" , values);
	}
	
	private void set(String key , String op , Object value){
		setDefault();
		query.push(key + op + "?"); //未做sql注入检测
		listValue.add(value);
		legalQuery = true;
	}
	public void setString(String key , String value){
		set(key," like " , value);
	}
	
	
	public void setInteger(String key , Integer value){
		//默认等于符号
		set(key,"=",value);
	}
	public void setInteger(String key , String op , Integer value){
		//自定义符号
		set(key,op,value);
	}
	
	public void setDouble(String key , Double value){
		set(key,"=",value);
	}
	public void setDouble(String key , String op , Double value){
		set(key,op,value);
	}
	
	/**
	 * 使用原生的where语句
	 * @param where
	 */
	public void setWhere(String where){
		if(where == null || where.length() == 0)
			return;
		this.where = " where " + where;
	}
}
