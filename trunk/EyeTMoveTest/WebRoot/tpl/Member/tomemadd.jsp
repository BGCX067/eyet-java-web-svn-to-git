<%@ page language="java" import="java.util.*,lib.model.entity.*" pageEncoding="UTF-8"%>
<%
final String PUBLIC = (String)request.getAttribute("PUBLIC");
final String APP = (String)request.getAttribute("APP");
Map<String,Object> param = (Map<String,Object>)request.getAttribute("param");
%>
<div class="pageContent">
	<form method="post" action="<%=APP %>/Member/memadd.move" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
        <p>
				<label>用户名：</label>
				<input name="username" class="required" type="text" size="30"  alt="请输入用户名"/>
		</p>
        <p>
				<label>密码：</label>
				<input name="passwd" class="required" type="password" size="30"  alt="请输入密码"/>
		</p>
		<p>
				<label>用户角色：</label>
				<select  class="combox"  name="roleid">
			      <%
			      List<Role> rolelist = (List<Role>)param.get("rolelist");
			      for(Role role : rolelist){
			      %>
			      <option value=<%=role.getId() %>><%=role.getRname() %></option>
			     <%} %>
		      </select>
		</p>
        </div>
        <div class="formBar">
			<ul>
				
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
    </form>
</div>

