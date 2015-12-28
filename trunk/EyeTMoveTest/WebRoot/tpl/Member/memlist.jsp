<%@ page language="java" import="java.util.*,lib.model.entity.*" pageEncoding="UTF-8"%>
<%
final String PUBLIC = (String)request.getAttribute("PUBLIC");
final String APP = (String)request.getAttribute("APP");
Map<String,Object> param = (Map<String,Object>)request.getAttribute("param");
%>

<form id="pagerForm" method="post" action="<%=APP %>/Member/memlist.move">
	 <input type="hidden" name="pageNum" value="<%=param.get("pageNum") %>" /><!--【必须】value=1可以写死-->

      <input type="hidden" name="numPerPage" value="<%=param.get("numPerPage")%>" /><!--【可选】每页显示多少条-->


</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
	<div class="searchBar">	
		<table class="searchContent">
		 	<tr>
			   
				<td>
					用户ID：<input type="text" name="userID" />
				</td>
				<td>
					用户名称：<input type="text" name="username" />
				</td>
				<td>
					<select class="combox" name="role">
						<option value="">所有角色</option>
						<option value="1">会长</option>
						<option value="2">干事</option>
						<option value="3">普通会员</option>
					</select>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=APP %>/Member/tomemadd.move"  target="dialog" title="添加用户"  width="400" height="200"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="<%=APP %>/Member/memdel.move" class="delete"><span>删除</span></a></li>
			<li><a class="edit" href="<%=APP %>/Member/tomemedit.move?id={user_id}" target="dialog" title="修改用户"  width="400" height="200"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="<%=APP %>/Member/expusertoexcel.move" target="dialog"  title="导出Excel"  width="300" height="100"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="120">用户ID</th>
				<th width="120">用户名称</th>
				<th>用户角色</th>
				<th>角色说明</th>
			</tr>
		</thead>
		
		<tbody>
		<%
		List<User> userlist = (List<User>)param.get("userlist");
		List<Role> rolelist = (List<Role>)param.get("rolelist");
		
		Map<Integer , Role> rolemap = new HashMap<Integer , Role>();
		
		for(Role role : rolelist){
			rolemap.put(role.getId() , role);
		}

		
		for(User user : userlist){
		%>
			<tr target="user_id" rel="<%=user.getId() %>">
			    <td><input name="ids" value="<%=user.getId() %>" type="checkbox"></td>
				<td><%=user.getId() %></td>
				<td><%=user.getUsername() %></td>
				<td><%=rolemap.get(user.getRoleid()).getRname()%></td>
				<td><%=rolemap.get(user.getRoleid()).getRdesc()%></td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="-1"><%=param.get("numPerPage") %></option>
				<option value="2">2</option>
				<option value="1">1</option>
				<option value="200">200</option>
			</select>
			<span>条，共<%=param.get("totalCount")%>条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="<%=param.get("totalCount")%>" numPerPage="<%=param.get("numPerPage") %>" pageNumShown="10" currentPage="<%=param.get("pageNum") %>"></div>

	</div>
</div>

