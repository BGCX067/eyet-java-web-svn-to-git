package lib.event.verify;

import com.eyet.framework.global.Global;
import com.eyet.framework.web.verify.VerifySupport;

public class AddVerify extends VerifySupport {

	@Override
	public boolean execute() {
		if("true".equals(Global.get("auth"))){
			return true;
		}
		return false;
	}

	@Override
	public String failLocation() {
		return "Error/adderror.move";
	}

}
