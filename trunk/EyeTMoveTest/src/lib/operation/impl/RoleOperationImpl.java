package lib.operation.impl;

import java.util.List;

import lib.model.RoleModel;
import lib.model.entity.Role;
import lib.operation.RoleOperation;

public class RoleOperationImpl implements RoleOperation {

	public List<Role> getRoleList() {
		// TODO Auto-generated method stub
		RoleModel roleModel = new RoleModel();
		return roleModel.select();
	}

}
