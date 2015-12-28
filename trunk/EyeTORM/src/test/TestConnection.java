package test;

import java.sql.Connection;

import com.eyet.framework.orm.util.DBConnectionManager;

public class TestConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		int i=10;
		while(i-- > 0){
			new Thread(new Runnable(){
				public void run(){
					DBConnectionManager db = DBConnectionManager.getInstance();
					Connection conn = db.getConnection();
					/*try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					db.freeConnection(conn);
				}
			}).start();
		}
		
	}

}
