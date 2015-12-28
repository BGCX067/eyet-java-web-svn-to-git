package lib.model.entity;

import com.eyet.framework.orm.annotation.EntityId;

public class User {
	@EntityId
	private Integer id;
	
	private String username;
	private String passwd;
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
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	
	
	
}
