package lib.model.entity;

import com.eyet.framework.orm.annotation.EntityId;

public class Activite{
	@EntityId
	private int id;
	private String title;
	private String context;
	private int begintime;
	private int endtime;
	private int limitpeople;
	private int publishtime;
	private String publishpeople;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getBegintime() {
		return begintime;
	}
	public void setBegintime(int begintime) {
		this.begintime = begintime;
	}
	public int getEndtime() {
		return endtime;
	}
	public void setEndtime(int endtime) {
		this.endtime = endtime;
	}
	public int getLimitpeople() {
		return limitpeople;
	}
	public void setLimitpeople(int limitpeople) {
		this.limitpeople = limitpeople;
	}
	public int getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(int publishtime) {
		this.publishtime = publishtime;
	}
	public String getPublishpeople() {
		return publishpeople;
	}
	public void setPublishpeople(String publishpeople) {
		this.publishpeople = publishpeople;
	}
	
	
}
