package com.eyet.framework.web.wrap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Hexleo
 * 
 * Http Session对象的封装
 *
 * Session 超时设置
 *
 *web.xml   in minutes (分钟)
<session-config>
        <session-timeout>30</session-timeout>
</session-config>

 */
public class SessionWrap implements Session{

	private HttpSession session;
	private HttpServletRequest request;

	public SessionWrap(HttpServletRequest request) {
		this.request = request;
		//不存在时创建
		this.session = request.getSession(true);
	}
	
	/**
	 * 当没发现session时，创建session
	 */
	private void createSession(){
		synchronized(this.session){
			if(this.session == null){
				this.session = request.getSession();
			}
		}
	}
	
	public String getSessionId(){
		if(session == null){
			createSession();
		}
		return session.getId();
	}
	
	public Object get(String key){
		if(session == null){
			createSession();
		}
		return session.getAttribute(key);
	}
	
	public void set(String key , Object value){
		if(session == null){
			createSession();
		}
		session.setAttribute(key, value);
		
	}
	
	public void remove(String key){
		if(session != null){
			session.removeAttribute(key);
		}
		
	}
	
	public void clear(){
		if(session != null){
			session.invalidate();
			session = null;
		}
		
	}

}
