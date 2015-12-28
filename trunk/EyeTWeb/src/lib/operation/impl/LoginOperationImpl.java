package lib.operation.impl;

import lib.operation.LoginOperation;


/**
 * 
 * @author Hexleo
 * 
 * 命名规范：
 * 操作名+OperationImpl
 * 
 * 实现对应的接口
 *
 */
public class LoginOperationImpl implements LoginOperation {

	public boolean login(String username, String password) {
		System.out.println("login success" + username + password);
		return false;
	}

	public boolean reg(String username, String password, int role) {
		// TODO Auto-generated method stub
		return false;
	}

}
