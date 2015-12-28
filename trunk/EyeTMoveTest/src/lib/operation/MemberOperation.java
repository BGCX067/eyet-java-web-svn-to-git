package lib.operation;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import lib.model.entity.User;

import com.eyet.framework.util.Page;

public interface MemberOperation {
	public User dologin(String username , String passwd);
	public Map<String , List> getMemList(Page page);
	public void useradd(User user);
	public void userdel(String[] id);
	public User getuser(int id);
	public boolean edituser(User user);
	public ResultSet getMemAuth(Page page);
	public List<User> getUserList();
	public String expusertoexcel();
}
