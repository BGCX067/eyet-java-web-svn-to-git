package lib.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eyet.framework.global.Global;

import lib.model.AuthModel;
import lib.model.ResourceModel;
import lib.model.RoleModel;
import lib.model.entity.Auth;
import lib.model.entity.Resource;
import lib.model.entity.Role;

/**
 * SQL权限调试语句
 * SELECT r.rname,r.rdesc,res.resuri,res.resdesc FROM role r , resource res , auth a
WHERE a.roleid = r.id and a.resourceid = res.id
ORDER by r.id
*/
public class EasyRBAC {
	public static final String RBAC = "RBAC";
	
	private RoleModel roleModel;
	private AuthModel authModel;
	private ResourceModel resModel ;

	public EasyRBAC(){
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
	
	public static boolean isAuth(int roleid, String eventName, String eventMethod){
		boolean auth = false;
		Map<Integer, Map<String, Integer>> authMap = (Map<Integer, Map<String, Integer>>) Global.get(RBAC);	
		if(authMap.containsKey(roleid)){
			auth = authMap.get(roleid).containsKey(eventName+"/*");
			if (!auth) {
				auth = authMap.get(roleid).containsKey(eventName+"/"+eventMethod);
			}
		}
		return auth;
	}
}
