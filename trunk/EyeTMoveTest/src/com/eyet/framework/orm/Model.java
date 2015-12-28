package com.eyet.framework.orm;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.eyet.framework.orm.Inquery;
import com.eyet.framework.orm.util.DBConfig;
import com.eyet.framework.orm.util.DBConnectionManager;

/**
 * 
 * @author WHL
 * @time 2012-08-21
 * 
 * @describe
 * 每张表独立成一个Model
 * 由两个类完成
 * 1 关系对象类
 * 命名规则：首字母大写其余小写（如：Table）
 * 继承关系：无继承
 * 数据表名称统一小写
 * 数据字段统一小写
 * id字段在方法末尾加Id标注
 * 2 关系模型类
 * 命名规则：首字母大写其余小写，结尾+Model（如TableModel）
 * 继承关系：如： public class TableModel extends Model<Table> {}
 * 
 */
public class Model<T> {
	
	//sql语句调试开关
	private static Boolean DEBUG_MODEL = null;
	 /*
	* 事务应放在Operate中完成，Model中的操作更接近原子操作
	//事务的操作往往会与异常操作相伴随
	//是否开启对于方法的事务回滚机制，如发生异常时应该回滚事务
	protected boolean SELECT_TRANSACTION = true;
	
	protected boolean SAVE_TRANSACTION = true;
	
	protected boolean UPDATE_TRANSACTION = true;
	
	protected boolean DELECT_TRANSACTION = true;*/
	
	//实体对应的类型
	private Class<T> entityClass;
	
	//where的查询条件
	private Inquery queryWhere;
	//order by条件
	private String queryOrder;
	//limit 条件  主要为分页使用
	private String queryLimit;
	
	//保存最终查询语句,如果Model还有为实现功能，允许使用原生sql语句
	private String querySql;
	
	//数据库连接管理对象
	private DBConnectionManager conMan = null;
	
	public Model() {
		if(DEBUG_MODEL == null){
			DEBUG_MODEL = DBConfig.getSqldebug();
		}
		conMan = DBConnectionManager.getInstance();
		setEntityClass();
		clearQuery();
		
	}

	public void setEntityClass() {
		Type genType = this.getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
        entityClass = (Class<T>) params[0]; 
	}
	
	private void getDebugSQL(){
		if(DEBUG_MODEL && querySql != null){
			System.out.println(querySql);
		}
	}
	
	public void clearQuery(){
		getDebugSQL();
		queryWhere = null;
		queryOrder = null;
		queryLimit = null;
		querySql = null;
	}

	/**
	 * 返回根据查询条件的对应表所有条目数
	 * 若查询条件空缺则返回所有条目数目
	 * @return
	 */
	public Integer count(){
		Integer result;
		querySql = SQLPrepareBuilder.buildCount(entityClass, queryWhere);
		if(queryWhere == null){
			result = SQLexecute.executeCount(conMan, querySql, null);
		}else{
			result = SQLexecute.executeCount(conMan, querySql, queryWhere.getPrepareValues());
		}
		clearQuery();
		return result;
	}

	/**
	 * 根据条件得到选择结果
	 *
	 * @return List<T> 查询结果列表
	 */
	public List<T> select(){
		List<T> result;
		querySql = SQLPrepareBuilder.buildSelect(entityClass, queryWhere, queryOrder, queryLimit);
		if(queryWhere == null){
			result = (List<T>) SQLexecute.executeSelect(conMan, entityClass, querySql, null);
		}else{
			result = (List<T>) SQLexecute.executeSelect(conMan, entityClass, querySql, queryWhere.getPrepareValues());
		}
		clearQuery();
		return result;
	}
	
	/**
	 * 根据Id选择对应记录，只返回一个对象
	 * 
	 * @param id Object 数据对象id
	 * 
	 * @return T 数据对象
	 */
	public T selectById(Object id){
		querySql = SQLPrepareBuilder.buildSelectById(entityClass);
		T result = (T) SQLexecute.executeSelectById(conMan, entityClass, querySql, id);
		clearQuery();
		return result;
	}
	
	/**
	 * 保存数据记录
	 * @param entity
	 * @return boolean 返回是否执行成功
	 */
	public boolean save(T entity){
		querySql = SQLPrepareBuilder.buildSave(entityClass);
		Integer lines = SQLexecute.executeSave(conMan, querySql, entity);
		clearQuery();
		if(lines == 1){ //只插入一条语句
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 批量更新数据记录,使用原生的sql语句 set 部分解决批量更新的问题
	 * 
	 * @param sql 更新用的sql语句
	 * @return Integer 返回是否执行成功语句条数 
	 */
	public Integer update(String set){
		//预防整张表被更新
		if(queryWhere == null)	return 0;
		querySql = SQLPrepareBuilder.buildUpdate(entityClass, queryWhere, set);
		Integer lines = SQLexecute.executeUpdateMany(conMan, querySql, queryWhere.getPrepareValues());
		clearQuery();
		return lines;
	}
	
	/**
	 * 更新一条数据记录 ，id为必填字段
	 * @param entity 需要更新的实体
	 * @return boolean 返回是否执行成功
	 */
	public boolean update(T entity){
		querySql = SQLPrepareBuilder.buildUpdate(entityClass);
		Integer lines = SQLexecute.executeUpdate(conMan, querySql, entity);
		clearQuery();
		if(lines == 1){ //只更新一条语句
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 删除数据记录，使用sql语句批量删除
	 * 使用这条记录前必须使用where语句
	 * @param sql
	 * @return Integer 返回是否执行成功语句条数 
	 */
	public Integer delete(){
		//预防整张表被删除
		if(queryWhere == null)	return 0;
		querySql = SQLPrepareBuilder.buildDelete(entityClass, queryWhere);
		Integer lines = SQLexecute.executeDeleteMany(conMan, querySql, queryWhere.getPrepareValues());
		clearQuery();
		return lines;
	}
	/**
	 * 接受原生where语句进行删除
	 * @param sql
	 * @return
	 */
	/*public Integer delete(String sql){
		return where(sql).delete();
	}*/
	
	/**
	 * 删除一条数据记录 ,使用id进行删除
	 * @param id 实体id
	 * @return boolean 返回是否执行成功
	 */
	public boolean delete(Object id){
		querySql = SQLPrepareBuilder.buildDelete(entityClass);
		Integer lines = SQLexecute.executeDelete(conMan, querySql, id);
		clearQuery();
		if(lines == 1){ //只删除一条语句
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 设置where语句
	 * @param query Inquery
	 * @return Model<T> 使用链式查询方式
	 */
	public Model<T> where(Inquery query){
		this.queryWhere = query;
		return this;
	}
	
	/**
	 * 使用原生的where语句
	 * @param sql
	 * @return Model<T> 使用链式查询方式
	 * 形如：xxxModel.where("uid >= 10").xxx()
	 * 此方法不推荐，存在安全问题，如sql注入
	 */
	public Model<T> where(String sql){
		this.queryWhere = new Inquery();
		this.queryWhere.setWhere(sql);
		return this;
	}
	
	/**
	 * 设置升降序列
	 * @param sql 原生sql语句（仅为order by后边部分）
	 * @return Model<T> 使用链式查询方式
	 */
	public Model<T> order(String sql){
		this.queryOrder = " order by "+sql;
		return this;
	}
	
	/**
	 * 分页项设置
	 * 注意:
	 * mysql拥有limit语句，但sqlserver没有limit语句，需要使用top+子查询的方式
	 * mysql limit <第几条记录开始>,<检索条数>
	 * @param limitNum 每页显示的数目
	 * @param page  显示第几页
	 * @return Model<T> 使用链式查询方式
	 */
	public Model<T> limit(Integer limitNum , Integer page){
		//使用mysql的limit进行分页
		if("mysql".equals(DBConfig.getDbtype())){
			Integer startNum = (page-1)*limitNum;
			this.queryLimit = " limit " + startNum + "," + limitNum;
		}else if("sqlserver".equals(DBConfig.getDbtype())){
			//sqlserver分页操作暂时挂空，因为要组织两条查询语句，逻辑比较复杂
		}
		return this;
	}
	
	/**
	 * 执行原始的sql语句
	 * 返回Object，有程序决定返回的结果
	 * @param sql
	 * @return Object 直接返回ResultSet
	 */
	public Object executeSQL(String sql){
		return SQLexecute.executeSQL(conMan, sql);
	}

}
