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
  	<br/>
 	<center><h1><a href="<%=APP+param.get("excelPath")%>">文件下载</a></h1></center>
  </body>
</html>
