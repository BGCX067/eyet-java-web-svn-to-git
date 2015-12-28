package lib.model.entity;

import com.eyet.framework.orm.annotation.EntityId;

public class Role {
	@EntityId
	private int id;
	private String rname;
	private String rdesc;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRdesc() {
		return rdesc;
	}
	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}


	
	
}
