<%@ page language="java" import="java.util.*,lib.model.entity.*" pageEncoding="UTF-8"%>
<%
final String PUBLIC = (String)request.getAttribute("PUBLIC");
final String APP = (String)request.getAttribute("APP");
Map<String,Object> param = (Map<String,Object>)request.getAttribute("param");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理平台</title>
<link href="<%=PUBLIC %>/dwz/themes/css/login.css" rel="stylesheet" type="text/css" />

<script>

function refreshImg(){
	app = <%="\"" + APP + "\""%>;
	document.getElementById("checkimg").src = app + "/checkimg?r=" + Math.random();
}

function checkSubmit(){
	document.getElementById("errormsg").innerHTML = "";
	username = this.login_form.username.value;
	passwd = this.login_form.passwd.value;
	if(username == null || username == ""){
		document.getElementById("errormsg").innerHTML = "用户名不能为空";
		return false;
	}
	if(passwd == null || passwd == ""){
		document.getElementById("errormsg").innerHTML = "密码不能为空";
		return false;
	}
	return true;
}

</script>

</head>

<body>
	<div id="login">
		<div id="login_header">
			<h1 class="login_logo">
				<!-- <a href="#"><img src="<%=PUBLIC %>/dwz/themes/default/images/login_logo.gif" /></a> -->
				<div style="font-size:32px;color:#000000;" > xxx管理系统</div>
			</h1>
			<div class="login_headerContent">
				<div class="navList">
					<ul>
						<li><a href="#">设为首页</a></li>
						<li><a href="#">反馈</a></li>
						<li><a href="#" target="_blank">帮助</a></li>
					</ul>
				</div>
				<h2 class="login_title"><img src="<%=PUBLIC %>/dwz/themes/default/images/login_title.png" /></h2>
			</div>
		</div>
		<div id="login_content">
			<div class="loginForm">
				<form action="<%=APP %>/Member/login.move" name="login_form" method="post" onsubmit="return checkSubmit()">
					<span id="errormsg" style="color:red"></span>
					<p>
						<label>用户名：</label>
						<input type="text" name="username" size="20" class="login_input" />
					</p>
					<p>
						<label>密码：</label>
						<input type="password" name="passwd" size="20" class="login_input" />
					</p>
					<p>
						<label>验证码：</label>
						<input class="code" type="text" name="checkcode" size="5" />
						<span><img id="checkimg" src="<%=APP %>/checkimg" alt="点击刷新图片" onclick="refreshImg();" width="75" height="24" /></span>
					</p>
					<div class="login_bar">
						<input class="sub" type="submit" value=" " />
					</div>
				</form>
			</div>
			<div class="login_banner"><img src="<%=PUBLIC %>/dwz/themes/default/images/login_banner.jpg" /></div>
			<div class="login_main">
				<ul class="helpList">
					<li><a href="#">如何使用程序</a></li>
					<li><a href="#">忘记密码怎么办？</a></li>
					<li><a href="#">为什么登录失败？</a></li>
				</ul>
				<div class="login_inner">
					<p>EyetWeb是一个免费开源的，快速、简单的面向对象的轻量级jsp开发框架</p>
					<p>EyetWeb遵循GPL开源讲可协议发布，意味着你可以免费使用EyetWeb</p>
				</div>
			</div>
		</div>
		<div id="login_footer">
			Copyright &copy; 2012.
		</div>
	</div>
</body>
</html>
