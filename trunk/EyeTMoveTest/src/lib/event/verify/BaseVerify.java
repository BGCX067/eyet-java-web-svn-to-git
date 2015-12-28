package lib.event.verify;

import lib.model.entity.User;
import lib.util.EasyRBAC;

import com.eyet.framework.web.verify.VerifySupport;

public class BaseVerify extends VerifySupport {
	
	public final String AUTH_FAIL = "权限不足，请重新登录！";
	public String errorMsg = "";
	
	public boolean rbacVerify(){
		String eventName = this.getEventName();
		String eventMethod = this.getEventMethod();
		//4 means guest
		int roleid = 4;
		User user = (User) this.getSession().get("user");
		if (user != null) {
			roleid = user.getRoleid();
		}
		return EasyRBAC.isAuth(roleid, eventName, eventMethod);
	}
}
