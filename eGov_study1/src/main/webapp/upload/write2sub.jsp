<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("utf-8"); %>

<%
String name = request.getParameter("username");
String year = request.getParameter("year");
String phone = request.getParameter("phone");
String addr = request.getParameter("addr");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

이름 : <%=name %> <br>
년도 : <%=year %>  <br>
전번 : <%=phone %> <br>  
주소 : <%=addr %>  <br>


</body>
</html>