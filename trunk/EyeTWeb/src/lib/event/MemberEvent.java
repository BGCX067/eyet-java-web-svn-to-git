package lib.event;

import java.util.List;

import lib.model.entity.User;
import lib.operation.MemberOperation;

import com.eyet.framework.util.Page;
import com.eyet.framework.web.event.EventSupport;

public class MemberEvent extends EventSupport {
	public void memload(){
		System.out.println("enter memload...");
		Page page = new Page(this);
		MemberOperation mop = (MemberOperation) this.getOperation("MemberOperation");
		List<User> users = mop.memload(page);
		this.set("userlist", users);
		
		this.set("currentPage", page.getCurrentPage());
		this.set("everyPage", page.getEveryPage());
		this.set("totalPage", page.getTotalPage());
		
		this.display();
		
	}

}
