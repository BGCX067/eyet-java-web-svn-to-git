package lib.util.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.model.AuthModel;
import lib.model.ResourceModel;
import lib.model.RoleModel;
import lib.model.entity.Auth;
import lib.model.entity.Resource;
import lib.model.entity.Role;

public class RBAC {
	private RoleModel roleModel;
	private AuthModel authModel;
	private ResourceModel resModel ;

	public RBAC(){
		roleModel = new RoleModel();
		authModel = new AuthModel();
		resModel  = new ResourceModel();
	}
	
	public Map<Integer, Map<String, Integer>> getAuthMap() {
		Map<Integer, Map<String, Integer>> authMap = new HashMap<Integer, Map<String, Integer>>();

		Map<Integer, String> resmap = new HashMap<Integer, String>();

		List<Role> roles = this.roleModel.select();
		List<Auth> auths = this.authModel.select();
		List<Resource> resources = this.resModel.select();
		for (Role r : roles) {
			authMap.put(r.getId(), new HashMap<String, Integer>());
		}
		for (Resource res : resources) {
			resmap.put(res.getId(), res.getResuri());
		}
		for (Auth a : auths) {
			Map<String, Integer> authmap = authMap.get(a.getRoleid());
			authmap.put(resmap.get(a.getResourceid()), 1);
		}
		return authMap;
	}
}
