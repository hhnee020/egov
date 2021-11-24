<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name ="frm" method="post" action="jsp_post_sub.jsp">

이름: <input type="text" name="username">
주소: <input type="text" name="useraddr">
<select name ="year">

<%
for(int y=1930; y<=2021; y++){
	%>
	
	<option value="<%=y %>"><%=y %></option>
	<% 
}
%>
</select>


<input type="submit" value="전송">

</form>



</body>
</html>
