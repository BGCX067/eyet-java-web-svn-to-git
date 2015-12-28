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
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>EyeT Framework</title>

<link href="<%=PUBLIC %>/dwz/themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="<%=PUBLIC %>/dwz/themes/css/core.css" rel="stylesheet" type="text/css" />
<link href="<%=PUBLIC %>/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="<%=PUBLIC %>/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->

<script src="<%=PUBLIC %>/dwz/js/speedup.js" type="text/javascript"></script>
<script src="<%=PUBLIC %>/dwz/js/jquery-1.4.4.js" type="text/javascript"></script>
<script src="<%=PUBLIC %>/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=PUBLIC %>/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=PUBLIC %>/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%=PUBLIC %>/dwz/xheditor/xheditor-1.1.9-zh-cn.min.js" type="text/javascript"></script>
<script src="<%=PUBLIC %>/dwz/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="<%=PUBLIC %>/dwz/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>

<script src="<%=PUBLIC %>/dwz/js/dwz.min.js" type="text/javascript"></script>
<script src="<%=PUBLIC %>/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("<%=PUBLIC %>/dwz/dwz.frag.xml", {
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"<%=PUBLIC %>/dwz/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});
</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<!-- <a class="logo" href="#">标志</a> -->
				<div style="font-size:26px;color:#FFFFFF; margin-top:10px; margin-left:10px;">xxx管理系统</div>
				<ul class="nav">
					<li><a href="#" target="_blank">欢迎您登陆</a></li>
					<li><a href="<%=APP %>/Member/quit.move">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>用户管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<%=APP %>/Member/memlist.move" target="navTab" rel="p1">用户信息列表</a></li>
							<li><a href="<%=APP %>/Member/memauth.move" target="navTab" rel="p2">用户权限查询</a></li>
							<li><a href="#" target="navTab" rel="p3">功能3</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>活动管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<%=APP %>/Activite/activitelist.move" target="navTab" rel="b1">活动信息管理</a></li>
							<li><a href="#" target="navTab" rel="p5">功能5</a></li>
							<li><a href="#" target="navTab" rel="p6">功能6</a></li>
						</ul>
					</div>
					
					<div class="accordionHeader">
						<h2><span>Folder</span>测试页面</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="<%=APP %>/Test/page1.move" target="navTab" rel="mypage1">页面1</a></li>
							<li><a href="<%=APP %>/Test/page2.move" target="navTab" rel="mypage2">页面2</a></li>
							<li><a href="<%=APP %>/Test/page3.move" target="navTab" rel="mypage3">页面3(面板)</a></li>
							<li><a href="<%=APP %>/Test/page3.move" target="dialog" rel="mypage4">页面4(对话框)</a></li>
							<li><a href="<%=APP %>/Test/page5.move" target="navTab" rel="mypage5">页面5(按钮)</a></li>
							<li><a href="<%=APP %>/Test/page6.move" target="navTab" rel="mypage6">页面6(操作成功)</a></li>
							<li><a href="<%=APP %>/Test/page7.move" target="navTab" rel="mypage7">页面7(操作失败)</a></li>
							<li><a href="<%=APP %>/Test/page8.move" target="navTab" rel="mypage8">页面8(会话超时)</a></li>
							<li><a href="<%=APP %>/Test/page9.move" target="navTab" rel="mypage9">页面9(form)</a></li>
							<li><a href="<%=APP %>/Test/page10.move" target="dialog" rel="mypage10" width="645" height="370">页面10(核心Form模板)</a></li>
							<li><a href="<%=APP %>/Test/page11.move" target="navTab" rel="mypage11">页面11(日期控件)</a></li>
							<li><a href="<%=APP %>/Test/page12.move" target="navTab" rel="mypage12">页面12(其它)</a></li>
						</ul>
					</div>
					

				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								<h2><a href="#" target="_blank">EyeT-Java-Web框架使用手册</a></h2>
								<a href="#" target="_blank">EyeT-Java-Web框架演示视频</a>
							</div>
							<p><span>欢迎进入xxx管理系统</span></p>
							<p>官方网址:<a href="#" target="_blank">http://www.xxx.com</a></p>
						</div>
						<div class="pageFormContent" layoutH="0" style="margin:0px;padding:0px;">
							<iframe src="<%=PUBLIC %>/welcomehome.html" width="100%" height="100%"  frameborder="0" scrolling="no" ></iframe>
						</div>
						
					</div> <!-- unit box -->
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2012 <a href="#">EyeT Team</a></div>


</body>
</html>
