package com.eyet.framework.orm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import lib.model.AuthModel;
import lib.model.ResourceModel;
import lib.model.RoleModel;
import lib.model.entity.Auth;
import lib.model.entity.Resource;
import lib.model.entity.Role;

public class testRBAC extends TestCase {
	private RoleModel roleModel = new RoleModel();
	private AuthModel authModel = new AuthModel();
	private ResourceModel resModel = new ResourceModel();

	public void testRbac(){
		//Inquery query = new Inquery();
		
		Map<Integer,Map<String,Integer>> rbac = new HashMap<Integer,Map<String,Integer>>();
		
		Map<Integer , String> resmap = new HashMap<Integer , String>();
		
		List<Role> roles = this.roleModel.select();
		List<Auth> auths = this.authModel.select();
		List<Resource> resources = this.resModel.select();
		for(Role r:roles){
			rbac.put(r.getId()	, new HashMap<String,Integer>());
		}
		for(Resource res : resources){
			resmap.put(res.getId(), res.getResuri());
		}
		
		for(Auth a : auths){
			Map<String , Integer> authmap = rbac.get(a.getRoleid());
			authmap.put(resmap.get(a.getResourceid()), 1);
		}
		
		if(rbac.get(1).containsKey("Add/*")){
			System.out.println("right");
		}else{
			System.out.println("fail");
		}
		
	}

}
