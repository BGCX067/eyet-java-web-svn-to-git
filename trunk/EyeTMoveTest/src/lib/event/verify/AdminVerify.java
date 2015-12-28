package lib.event.verify;

import lib.event.verify.BaseVerify;;

public class AdminVerify extends BaseVerify {

	@Override
	public boolean execute() {
		this.errorMsg = this.AUTH_FAIL;
		return this.rbacVerify();
	}

	@Override
	public String failLocation() {
		return "Error/error.move?e=" + errorMsg;
	}
}
