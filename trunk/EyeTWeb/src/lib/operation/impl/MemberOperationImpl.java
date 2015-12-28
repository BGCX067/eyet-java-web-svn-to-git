package lib.operation.impl;

import java.util.List;

import lib.model.UserModel;
import lib.model.entity.User;
import lib.operation.MemberOperation;

import com.eyet.framework.util.Page;

public class MemberOperationImpl implements MemberOperation {

	public List<User> memload(Page page) {
		UserModel userModel = new UserModel();
		
		page.setCount(userModel.count());
		
		
		return userModel.order("uid asc").limit(page.getEveryPage(),page.getCurrentPage()).select();
	}
}
