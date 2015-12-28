package lib.event;

import com.eyet.framework.web.event.EventSupport;

public class ErrorEvent extends EventSupport {
	
	public void error(){
		this.set("e", this.get("e"));
		this.display();
	}
}
