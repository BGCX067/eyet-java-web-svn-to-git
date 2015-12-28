<%@ page language="java" import="java.util.*,lib.model.entity.*" pageEncoding="UTF-8"%>
<%
final String PUBLIC = (String)request.getAttribute("PUBLIC");
final String APP = (String)request.getAttribute("APP");
Map<String,Object> param = (Map<String,Object>)request.getAttribute("param");
%>
<form id="pagerForm" method="post" action="<%=APP %>/Test/page2.move">
     <input type="hidden" name="pageNum" value="<%=param.get("pageNum") %>" /><!--【必须】value=1可以写死-->
     <input type="hidden" name="numPerPage" value="<%=param.get("numPerPage")%>" /><!--【可选】每页显示多少条-->
     <%--
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	--%>
</form>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="demo_page4.html" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="demo_page4.html?uid={sid_user}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="75">
		<thead>
			<tr>
				<th width="80"></th>
				<th width="120">客户号</th>
				<th>客户名称</th>
				<th width="100">客户类型</th>
				<th width="150">证件号码</th>
				<th width="80" align="center">信用等级</th>
				<th width="80">所属行业</th>
				<th width="80">建档日期</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_user" rel="1">
				<td>天津农信社</td>
				<td>A120113196309052434</td>
				<td>天津市华建装饰工程有限公司</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
			</tr>
			<%  String msg = (String)param.get("msg");
				Integer intmsg = (Integer)param.get("intmsg");
				for(int i=1;i<10;i++){
			 %>
			<tr target="sid_user" rel="10">
				<td><%=i %></td>
				<td><%=msg %></td>
				<td><%=intmsg %></td>
				<td><%=msg %></td>
				<td><%=intmsg %></td>
				<td><%=msg %></td>
				<td><%=intmsg %></td>
				<td><%=msg %></td>
			</tr>
			<%} %>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="-1"><%=param.get("numPerPage") %></option>
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="200">200</option>
			</select>
			<span>条，共<%=param.get("totalCount")%>条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="<%=param.get("totalCount")%>" numPerPage="<%=param.get("numPerPage") %>" pageNumShown="10" currentPage="<%=param.get("pageNum") %>"></div>

	</div>
</div>

