package lib.operation;

import java.util.List;

import lib.model.entity.User;

import com.eyet.framework.util.Page;

public interface MemberOperation {
	public List<User> memload(Page page);
	

}
