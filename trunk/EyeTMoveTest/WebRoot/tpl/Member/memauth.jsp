<%@ page language="java" import="java.util.*,lib.model.entity.*,java.sql.*" pageEncoding="UTF-8"%>
<%
final String PUBLIC = (String)request.getAttribute("PUBLIC");
final String APP = (String)request.getAttribute("APP");
Map<String,Object> param = (Map<String,Object>)request.getAttribute("param");
%>

<form id="pagerForm" method="post" action="<%=APP %>/Member/memauth.move">
	 <input type="hidden" name="pageNum" value="<%=param.get("pageNum") %>" /><!--【必须】value=1可以写死-->

      <input type="hidden" name="numPerPage" value="<%=param.get("numPerPage")%>" /><!--【可选】每页显示多少条-->


</form>

<div class="pageContent">
	
	<table class="table" width="100%" layoutH="48">
		<thead>
			<tr>
				<th width="120">角色名称</th>
				<th width="120">角色描述</th>
				<th>资源列表</th>
				<th>资源描述</th>
			</tr>
		</thead>
		
		<tbody>
		<%
		ResultSet result = (ResultSet)param.get("ResultSet");

		if(result != null)
		while(result.next()){
		%>
			<tr target="id" rel="1">
				<td><%=result.getString("rname") %></td>
				<td><%=result.getString("rdesc") %></td>
				<td><%=result.getString("resuri")%></td>
				<td><%=result.getString("resdesc")%></td>
			</tr>
			<%}%>
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

