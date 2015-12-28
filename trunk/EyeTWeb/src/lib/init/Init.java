package lib.init;


import java.util.Map;

import lib.util.auth.RBAC;

import com.eyet.framework.global.Global;
import com.eyet.framework.web.init.InitSupport;

public class Init extends InitSupport {

	@Override
	public void init() {
		RBAC rbac = new RBAC();
		Map<Integer, Map<String, Integer>> authMap = rbac.getAuthMap();
		rbac = null;
		Global.set("auth" , authMap);
		System.out.println("自己实现的init方法。");
	}
}
