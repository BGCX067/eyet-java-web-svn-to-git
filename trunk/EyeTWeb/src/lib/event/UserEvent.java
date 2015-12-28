package lib.event;

import com.eyet.framework.web.event.EventSupport;

public class UserEvent extends EventSupport {
	public void userlogin(){
		String username = this.get("username");
		String password = this.get("password");
		System.out.println("success");
		if("1".equals(username)){
			this.set("sex", "boy");
			this.display();
		}else
			this.display("User/fail.move?error=sorry");
		
	}
	public void fail(){
		String error = this.get("error");
		this.set("error", error);
		this.display();
	}

}
