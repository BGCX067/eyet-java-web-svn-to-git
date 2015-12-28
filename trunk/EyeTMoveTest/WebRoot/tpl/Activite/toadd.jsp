<%@ page language="java" import="java.util.*,lib.model.entity.*" pageEncoding="UTF-8"%>
<%
final String PUBLIC = (String)request.getAttribute("PUBLIC");
final String APP = (String)request.getAttribute("APP");
Map<String,Object> param = (Map<String,Object>)request.getAttribute("param");
%>
<div class="pageContent">
	<form method="post" action="<%=APP %>/Activite/add.move" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
        <p>
				<label>活动主题：</label>
				<input name="title" class="required" type="text" size="30"  alt="请输入主题"/>
		</p>
		 <p>
				<label>活动内容：</label>
				<input name="context" class="required" type="text" size="30"  alt="请输入内容"/>
		</p>
         <p>
				<label>开始时间：</label>
				<input name="begintime" class="required" type="text" size="30"  alt="请输入开始时间"/>
		</p>
		 <p>
				<label>截止时间：</label>
				<input name="endtime" class="required" type="text" size="30"  alt="请输入截止时间"/>
		</p>
		
        </div>
        <div class="formBar">
			<ul>
				
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">发布</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
    </form>
</div>

