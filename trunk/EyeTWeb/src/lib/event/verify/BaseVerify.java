package lib.event.verify;

import java.util.Map;

import lib.model.entity.User;

import com.eyet.framework.global.Global;
import com.eyet.framework.web.verify.VerifySupport;

public class BaseVerify extends VerifySupport {
	@SuppressWarnings("unchecked")
	public boolean auth(){
		String eventName = this.getEventName();
		String eventMethod = this.getEventMethod();
		int rid = 1;
		User user = (User) this.getSession().get("uesr");
		if (user != null) {
			rid = user.getRoleid();
		}
		Map<Integer, Map<String, Integer>> authMap = (Map<Integer, Map<String, Integer>>) Global.get("auth");
		boolean isAuth = false;
		isAuth = authMap.get(rid).containsKey(eventName+"\\*");
		if (!isAuth) {
			isAuth = authMap.get(rid).containsKey(eventName+"\\"+eventMethod);
		}
		return isAuth;
	}
}
