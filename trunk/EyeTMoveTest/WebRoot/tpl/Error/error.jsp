<%@ page language="java" import="java.util.*,lib.model.entity.*" pageEncoding="UTF-8"%>
<%
final String PUBLIC = (String)request.getAttribute("PUBLIC");
final String APP = (String)request.getAttribute("APP");
Map<String,Object> param = (Map<String,Object>)request.getAttribute("param");
%>
<html>
  <head>
    <title>EyeT framework</title>
  </head>
  
  <body>
	<h1><%=param.get("e") %></h1>
  </body>
</html>
