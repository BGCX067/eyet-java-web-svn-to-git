package lib.event;

import com.eyet.framework.web.event.EventSupport;

public class AddEvent extends EventSupport {
	
	public void inaddnews(){
		this.display();
	}
	public void addnews(){
		
		//ListOperation lo = this.getOperation("ListOperationImpl");
		String title = this.get("title");
		
		
		if("t".equals(title)){
			this.display("Add/error.move?e=错误");
		}else{
			this.set("title", "您提交的题目是："+title);
			this.display();
			
		}
	}
	public void error(){
		String e = this.get("e");
		this.set("e", e);
		this.display();
	}
}
