package lib.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.model.entity.Role;
import lib.model.entity.User;
import lib.operation.MemberOperation;
import lib.operation.RoleOperation;

import com.eyet.framework.util.JsonObject;
import com.eyet.framework.util.Page;
import com.eyet.framework.web.event.EventSupport;
import com.eyet.framework.web.wrap.Session;

public class MemberEvent extends EventSupport {
	public void login(){
		
		String checkCode = (String) this.getEventContext().getSession().get("Login_Image_Code");
		
		//验证码判断
		/*if(checkCode == null || !checkCode.equals(this.get("checkcode"))){
			this.display("Error/error.move?e= 验证码错误，登陆失败！");
			return;
		}*/
		
		String username = this.get("username");
		String passwd = this.get("passwd");
		
		MemberOperation mop = (MemberOperation) this.getOperation("MemberOperation");
		User user = mop.dologin(username, passwd);
		
		if(user != null){
			Session session = this.getEventContext().getSession();
			session.set("user", user);
			this.display("Admin/index.move");
		}else{
			this.display("Error/error.move?e=账号或密码错误，登陆失败！");
		}
	}
	
	public void quit(){
		Session session = this.getEventContext().getSession();
		if(session != null){
			session.clear();
		}
		this.display("Index/index.move");
	}
	
	public void memlist(){
		Page page = new Page(this);
		MemberOperation memberOperation = (MemberOperation)this.getOperation("MemberOperation");
		Map<String , List> map = memberOperation.getMemList(page);
		
		this.set("userlist", map.get("userlist"));
		this.set("rolelist", map.get("rolelist"));
		
		this.set("pageNum", page.getCurrentPage());
		this.set("totalCount", page.getTotalCount());
		//this.set("total", page.getTotalCount());
		this.set("numPerPage", page.getEveryPage());
		
		this.display();
	}
	
	public void tomemadd(){
		RoleOperation roleOperation = (RoleOperation)this.getOperation("RoleOperation");
		List<Role> rolelist = roleOperation.getRoleList();
		this.set("rolelist", rolelist);
		this.display();
	}
	
	public void memadd(){
		User user = new User();
		user.setUsername(this.get("username"));
		user.setPasswd(this.get("passwd"));
		user.setRoleid(new Integer(this.get("roleid")));
		MemberOperation memberOperation = (MemberOperation)this.getOperation("MemberOperation");
		memberOperation.useradd(user);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("statusCode", "200");
		map.put("message", "操作成功");
		map.put("navTabId", "p1");
		map.put("callbackType", "closeCurrent");
		
		JsonObject json = JsonObject.fromObject(map);

		this.displayJson(json.toString());
	}
	
	public void memdel(){
		String ids = this.get("ids");
		System.out.println(ids);
		String id[] = ids.split(",");
		MemberOperation memberOperation =(MemberOperation)this.getOperation("MemberOperation");
		//for(String uid : id)
			//System.out.println(uid);
		memberOperation.userdel(id);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("statusCode", "200");
		map.put("message", "操作成功");
		map.put("navTabId", "p1");
		map.put("callbackType", "");
		
		JsonObject json = JsonObject.fromObject(map);
		this.displayJson(json.toString());
	}
	
	public void tomemedit(){
		MemberOperation memberOperation =(MemberOperation)this.getOperation("MemberOperation");
		User user = memberOperation.getuser(this.getInt("id"));
		this.set("user", user);
		
		RoleOperation roleOperation = (RoleOperation)this.getOperation("RoleOperation");
		List<Role> rolelist = roleOperation.getRoleList();
		this.set("rolelist", rolelist);
		
		this.display();
		
	}
	
	public void memedit(){
		User user = new User();
		
		this.fill(user);
		/*user.setId(this.getInt("id"));
		user.setUsername(this.get("username"));
		user.setPasswd(this.get("passwd"));
		user.setRoleid(this.getInt("roleid"));
		*/
		
		MemberOperation memberOperation =(MemberOperation)this.getOperation("MemberOperation");
		Map<String,String> map = new HashMap<String,String>();
		if(memberOperation.edituser(user)){

			map.put("statusCode", "200");
			map.put("message", "操作成功");
			map.put("navTabId", "p1");
			map.put("callbackType", "closeCurrent");		
		}else{		
			map.put("statusCode", "300");
			map.put("message", "操作失败");
			map.put("navTabId", "p1");
			map.put("callbackType", "");
	
		}
		JsonObject json = JsonObject.fromObject(map);
		this.displayJson(json.toString());
	}
	
	public void memauth(){
		Page page = new Page(this);
		MemberOperation memberOperation =(MemberOperation)this.getOperation("MemberOperation");
		this.set("ResultSet", memberOperation.getMemAuth(page));
		
		this.set("pageNum", page.getCurrentPage());
		this.set("totalCount", page.getTotalCount());
		this.set("numPerPage", page.getEveryPage());
		
		this.display();
	}
	
	public void expusertoexcel() throws RuntimeException {
		
		
		MemberOperation memberOperation = (MemberOperation)this.getOperation("MemberOperation");
		String fileName = memberOperation.expusertoexcel();
		this.set("excelPath", "/file/temp/"+fileName);
		this.display();
	}
	
	
}
