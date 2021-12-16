<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Calendar cal = Calendar.getInstance();
int yy = cal.get(Calendar.YEAR);
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="frm" method="post" action="write2sub.jsp">
이름 : <input type="text" name="username"> <br>
년도 : <select name="year">
	   <%
	   for(int y=(yy-20); y<=yy; y++) { 
	   %>
		<option value="<%=y %>"><%=y %></option>
		<%
	   }
		%>
      </select> <br>
전번 : <input type="text" name="phone"> <br>
주소 : <input type="text" name="addr"> <br>
<input type="submit" value="전송">
</form>

</body>
</html>