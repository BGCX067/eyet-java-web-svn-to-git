<%@ page language="java" import="java.util.*,lib.model.entity.*" pageEncoding="UTF-8"%>
<%
final String PUBLIC = (String)request.getAttribute("PUBLIC");
final String APP = (String)request.getAttribute("APP");
Map<String,Object> param = (Map<String,Object>)request.getAttribute("param");
%>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>EyeT framework</title>
  </head>
  
  <body>
	<h1>Welcome to use our framork <br />EyeT Team</h1>——Template page
  </body>
</html>
