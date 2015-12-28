package lib.event;

import lib.operation.LoginOperation;

import com.eyet.framework.web.event.EventSupport;
import com.eyet.framework.web.wrap.Session;

public class IndexEvent extends EventSupport {
	
	//不能有参数
	public void index(){
		String hello = this.get("h");
		
		this.set("hello", hello + ":this is index");
		
		this.getEventContext().getSession().set("user", "user login");
		LoginOperation loginOp = (LoginOperation) this.getOperation("LoginOperation");
		if(loginOp != null){
			loginOp.login("a", "c");
		}
		this.display();
	}
	
	public void view(){
		String hello = this.get("h");
		Session session = this.getEventContext().getSession();
		this.set("hello", hello + ":this is view" + "<br />" + session.get("user"));
		session.clear();
		this.display();//"Index/index.move?h=isredirect."
	}
	
	//json javaee 需要引入json才能完成
	public void jsonview(){
		String hello = this.get("h");
		/*Map<String,Object> map = new HashMap<String,Object>();
		map.put("hello" , hello);
		map.put("int", 100);
		map.put("double", 33.3);
		this.set("json" , );*/
	}
}
