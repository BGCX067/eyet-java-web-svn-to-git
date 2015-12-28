package test;

import com.eyet.framework.orm.Inquery;

import lib.model.UserModel;
import lib.model.entity.User;

public class TestModel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		UserModel userModel = new UserModel();
		
		
		Inquery query = new Inquery();
		query.setInteger("uid", 12);
		//query.setOR();
		query.setString("username", "abc");
		query.setInteger("uid", ">=", 10);
		
		userModel.count();
		
		userModel.selectById(1);
		
		userModel.where(query).order("uid desc").limit(20, 4).select();
		
		/*userModel.order("uid desc").select();
		
		userModel.limit(10, 2).select();*/
		
		userModel.save(new User());
		
		userModel.update(new User());
		
		userModel.delete(new User());
		
		
		
		
	}

}
