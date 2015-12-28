package test;

import java.util.List;

import com.eyet.framework.orm.Inquery;

import lib.model.UserModel;
import lib.model.entity.User;

public class TestModelSqlExe2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserModel userModel = new UserModel();
		User user = new User();
		user.setUid(7);
		user.setUsername("abc");
		user.setPasswd(123.0);
		/*
		if(userModel.save(user)){
			System.out.println("success");
		}else{
			System.out.println("fail");
		}
		
		if(userModel.delete(4)){
			System.out.println("success");
		}else{
			System.out.println("fail");
		}
		
		//query.setIn("aa" , Object[] arrays)
		//userModel.where("uid > 4").delete());
		if(userModel.update(user)){
			System.out.println("success");
		}else{
			System.out.println("fail");
		}*/
		Inquery query = new Inquery();
		Integer[] ii = new Integer[3];
		ii[0] = 7;
		ii[1] = 9;
		ii[2] = 11;
		query.setIn("uid",ii);
		/*List<User> userlist = userModel.where(query).select();
		for(int i=0 ; i<userlist.size() ;i++){
			System.out.println(userlist.get(i).getUid());
		}*/
		
		System.out.println(userModel.where(query).update("passwd=passwd + 10000,username ='add'"));
	}

}
