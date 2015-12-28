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
 * 
 * 所有类型必须为对象，不能为原生类型，例如不能为：int、double
 * 
 */
public class User {
	//用注解的方式申明主键id
	@EntityId
	private Integer id;
	
	private String username;
	private Double passwd;
	private Integer roleid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer uid) {
		this.id = uid;
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
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	
	
	
}
