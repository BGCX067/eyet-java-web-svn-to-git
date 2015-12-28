package com.eyet.framework.util;

import com.eyet.framework.web.event.EventSupport;

/**
 * 
 * 
 * @author Hexleo
 * 
 * 翻页类
 *
 */
public class Page {
	
	private EventSupport event;
	
	private Integer currentPage = 1;
	
	private Integer everyPage = 10;
	
	private Integer totalPage;
	
	private Integer totalCount;
	
	//for free format
	public Page(EventSupport event , String currentPage , String everyPage){
		this.event = event;
		this.initPage(currentPage, everyPage);
	}
	
	//for DWZ format
	public Page(EventSupport event){
		this.event = event;
		this.initPage("pageNum", "numPerPage");
		
	}
	
	private void initPage(String currentPage , String everyPage){
		String cp = event.get(currentPage);
		String ep = event.get(everyPage);
		if(cp == null || ep == null){
			this.clear();
			return;
		}
		this.currentPage = Integer.valueOf(cp);
		this.everyPage = Integer.valueOf(ep);
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	public void setCount(Integer count){
		this.totalCount = count;
		int inc = 0;
		if(count % this.everyPage != 0){
			inc++;
		}
		this.totalPage = count / this.everyPage + inc;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public Integer getEveryPage() {
		return everyPage;
	}
	
	private void clear(){
		this.currentPage = 1;
		this.everyPage = 10;
		this.totalPage = 0;
	}

	public Integer getTotalCount() {
		return totalCount;
	}
	
	
}
