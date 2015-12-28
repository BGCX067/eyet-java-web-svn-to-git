package lib.init;


import lib.util.EasyRBAC;

import com.eyet.framework.global.Global;
import com.eyet.framework.web.init.InitSupport;

public class Init extends InitSupport {

	@Override
	public void init() {
		Global.set(EasyRBAC.RBAC, new EasyRBAC().getAuthMap());
	}
}
