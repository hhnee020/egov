<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1" width="400">
	<tr>
		<th>번호</th>
		<th>부서번호</th>
		<th>부서이름</th>
		<th>부서위치</th>
		<th>삭제</th>
	</tr>
	
	<% int number = 1; %>
	<c:set var="number" value="1" />
	
	<c:forEach var="result" items="${list}" varStatus="status">true
		<tr align="center">
			<td><%=number %> ,${number}, ${status.count}, ${status.last}</td>
			<td><a href="deptModify.do?deptno=${result.deptno }">${result.deptno }</a></td>
			<td>${result.dname }</td>
			<td>${result.loc }</td>
			<td> <a href="deptDelete.do?deptno=${result.deptno}">del</a>  </td>
		</tr>
		<% number++; %>
		<c:set var="number" value="${number+1}"/>
	</c:forEach>
	
</table>
   
   <c:forEach var="i" begin="1" end="10">
   		${i } 
   		<c:if test="${i==5}">
   				<br>
   		</c:if>	
   </c:forEach>


</body>
</html>







