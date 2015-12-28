package lib.model.entity;

import com.eyet.framework.orm.annotation.EntityId;

public class Auth {
	@EntityId
	private int id;
	private int roleid;
	private int resourceid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public int getResourceid() {
		return resourceid;
	}
	public void setResourceid(int resourceid) {
		this.resourceid = resourceid;
	}
	

}
