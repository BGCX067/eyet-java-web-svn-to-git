package com.eyet.framework.web.event;

import javax.servlet.http.HttpServletRequest;

import com.eyet.framework.web.wrap.Session;
import com.eyet.framework.web.wrap.SessionWrap;

public class EventContext {
	
	private HttpServletRequest request;
	private Session session;
	
	public EventContext(HttpServletRequest request){
		this.request = request;
		this.session = new SessionWrap(request);
	}
	
	public Session getSession(){
		return session;
	}
	
	public HttpServletRequest getRequest(){
		return request;
	}
	
}
