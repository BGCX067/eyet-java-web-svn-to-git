package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.eyet.framework.orm.Inquery;

import lib.model.UserModel;
import lib.model.entity.User;

public class TestModelSqlExe {

	private static int ii;
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		
		
		/*ResultSet rs = (ResultSet) userModel.executeSQL("select * from user where uid > 2");
		
		while(rs.next()){
			System.out.println(rs.getObject("uid") + " " + rs.getString("username") + " " + rs.getDouble("passwd"));
		}
		Inquery query = new Inquery();
		query.setDouble("passwd",">", 456.0);
		//query.setOR();
		query.setInteger("uid", ">=", 2);
		int r = userModel.where(query).count();
		System.out.println(r);*/
		
		Inquery query = new Inquery();
		query.setDouble("passwd",">", 456.0);
		//query.setOR();
		query.setInteger("uid", ">=", 2);
		
		//List<User> list = userModel.where(query).order("uid desc").select();
		
		long time = System.currentTimeMillis();
		for(int i=100 ; i>0 ; i--){
			ii = i;
			new Thread(new Runnable(){
				public void run(){
					try {
						Thread.sleep(ii*10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					UserModel userModel = new UserModel();
					Inquery query = new Inquery();
					query.setDouble("passwd","<", 456.0);
					query.setInteger("uid", ">", 2);
					List<User> list = userModel.where(query).order("uid desc").select();
					if(list != null){
						//Iterator<User> it = list.iterator();
						//while(it.hasNext()){
							//User user = it.next();
							//System.out.println(user.getUid() + " " + user.getUsername() + " " + user.getPasswd());
						//}
						//System.out.println(list.size());
						System.out.println("获得连接而得值  1");
					}else{
						System.out.println("因为没有获得连接而得到空值  1");
					}
					
				}
			}).start();
			
			new Thread(new Runnable(){
				public void run(){
					try {
						Thread.sleep(ii*10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					UserModel userModel = new UserModel();
					User user = userModel.selectById(6);
					if(user == null){
						System.out.println("因为没有获得连接而得到空值  2");
					}else{
						System.out.println("获得连接而得值  2");
					}
					//if(user != null)
						//System.out.println(user.getUid() + " " + user.getUsername() + " " + user.getPasswd());
				}
			}).start();
		}
		System.out.println((System.currentTimeMillis() - time) );
		
		new Thread(new Runnable(){
			public void run(){
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UserModel userModel = new UserModel();
				User user = userModel.selectById(3);
				System.out.println("sleep");
				//if(user != null)
					//System.out.println(user.getUid() + " " + user.getUsername() + " " + user.getPasswd());
			}
		}); //.start()
	}

}
