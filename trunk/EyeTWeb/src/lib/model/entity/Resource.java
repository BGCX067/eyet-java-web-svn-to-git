package lib.model.entity;

import com.eyet.framework.orm.annotation.EntityId;

public class Resource {
	@EntityId
	private int id;
	private String resuri;
	private String resdesc;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResuri() {
		return resuri;
	}
	public void setResuri(String resuri) {
		this.resuri = resuri;
	}
	public String getResdesc() {
		return resdesc;
	}
	public void setResdesc(String resdesc) {
		this.resdesc = resdesc;
	}

	
	

}
