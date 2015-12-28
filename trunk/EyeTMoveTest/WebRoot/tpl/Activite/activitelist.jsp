<%@ page language="java" import="java.util.*,java.text.*,lib.model.entity.*" pageEncoding="UTF-8"%>
<%
final String PUBLIC = (String)request.getAttribute("PUBLIC");
final String APP = (String)request.getAttribute("APP");
Map<String,Object> param = (Map<String,Object>)request.getAttribute("param");
%>
<form id="pagerForm" method="post" action="<%=APP %>/Activite/activitelist.move">
     <input type="hidden" name="pageNum" value="<%=param.get("pageNum") %>" /><!--【必须】value=1可以写死-->
     <input type="hidden" name="numPerPage" value="<%=param.get("numPerPage")%>" /><!--【可选】每页显示多少条-->
     <input type="hidden" name="title" value="<%=param.get("title") %>" /><!--【可选】查询条件-->
     <%--
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	--%>
</form>
<div class="pageHeader">
	<%--<form onsubmit="return navTabSearch(this);" action="<%=APP %>/Test/page7.move" method="post">
	--%>
	<%--<form method="post" action="<%=APP %>/Test/page6.move" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	--%>
	<form method="post" action="<%=APP %>/Activite/activitelist.move" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>活动名称：<input type="text" name="title" /></td>
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
			<li><a class="add" href="<%=APP %>/Activite/toadd.move" target="dialog"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="<%=APP %>/Activite/del.move" class="delete"><span>删除</span></a></li>
			<li><a class="edit" href="<%=APP %>/Activite/toedit.move?id={act_id}" target="dialog" title="编辑活动"  width="400" height="200"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="<%=APP %>/Activite/expusertoexcel.move" target="dialog"  title="导出Excel"  width="300" height="100"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="120">活动号</th>
				<th>活动主题</th>
				<th width="100">活动内容</th>
				<th width="150">开始时间</th>
				<th width="80" align="center">结束时间</th>
				<th width="80">人数限制</th>
				<th width="80">发布时间</th>
				<th width="80">发布人</th>
			</tr>
		</thead>
		
		<tbody>
		<%
		List<Activite> activitelist = (List<Activite>)param.get("list");
		
		for(Activite act : activitelist){
		%>
			<tr target="act_id" rel="<%=act.getId() %>">
			    <td><input name="ids" value="<%=act.getId() %>" type="checkbox"></td>
			    <td><%=act.getId() %></td>
				<td><%=act.getTitle() %></td>
				<td><%=act.getContext() %></td>
				<td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(act.getBegintime()))%></td>
				<td><%=new Date(act.getEndtime())%></td>
				<td><%=act.getLimitpeople()%></td>
				<td><%=act.getPublishtime()%></td>
				<td><%=act.getPublishpeople()%></td>
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
				<option value="10">10</option>
				<option value="200">200</option>
			</select>
			<span>条，共<%=param.get("totalCount")%>条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="<%=param.get("totalCount")%>" numPerPage="<%=param.get("numPerPage") %>" pageNumShown="10" currentPage="<%=param.get("pageNum") %>"></div>

	</div>
</div>