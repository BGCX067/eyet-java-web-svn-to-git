package com.eyet.framework.orm;

import java.util.List;

import junit.framework.TestCase;
import lib.model.UserModel;
import lib.model.entity.User;

public class testModel extends TestCase {
	public UserModel userModel = new  UserModel();

	public void testCount() {
		/*User user = new User();
		user.setUsername("user");
		user.setPasswd(12.0);*/
		Inquery q = new Inquery();
		//q.setInteger("uid", ">", 5);
		
		//System.out.println(userModel.count());
		System.out.println(userModel.where("uid > 9").count());
		
		
		
	}

	public void testSelect() {
		Inquery q = new Inquery();
		q.setInteger("uid", ">",9);
		List<User> users = this.userModel.where(q).select();
		for(User u:users)
			System.out.println(u.getUid()+"||"+u.getUsername()+"||"+u.getPasswd());
	}

	public void testSelectById() {
		fail("Not yet implemented");
	}

	public void testSave() {
		fail("Not yet implemented");
	}

	public void testUpdateString() {
		fail("Not yet implemented");
	}

	public void testUpdateT() {
		fail("Not yet implemented");
	}

	public void testDelete() {
		fail("Not yet implemented");
	}

	public void testDeleteString() {
		fail("Not yet implemented");
	}

}
