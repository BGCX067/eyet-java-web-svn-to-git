package lib.model.entity;

import com.eyet.framework.orm.annotation.EntityId;

/**
 * 
 * @author WHL
 *
 * 实体类
 * 必须存在一个无参构造方法
 * id必须为自动增长类型
 * 通过注解的方式获得实体id属性，需要引入注释类
 * 
 * 还需要注意的问题就是使用反射读取类信息时会出现顺序问题！
 */
public class User {
	//用注解的方式申明主键id
	@EntityId
	private int uid;
	
	private String username;
	private Double passwd;
	private int roleid;
	
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	//必须存在一个无参构造函数（如果不写默认没有）
	public User() {
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Double getPasswd() {
		return passwd;
	}
	public void setPasswd(Double passwd) {
		this.passwd = passwd;
	}
	
}
