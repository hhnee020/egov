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
	<form action="deptModifySave.do" method="post">
		<table>
			<tr>
				<th>부서번호</th>
				<td><input type="hidden" name="deptno" value="${deptvo.deptno}">${deptvo.deptno}</td>
			</tr>
			<tr>
				<th>부서이름</th>
				<td><input type="text" name="dname" value="${deptvo.dname}"></td>
			</tr>
			<tr>
				<th>부서지역</th>
				<td><input type="text" name="loc" value="${deptvo.loc}"></td>
			</tr>
		</table>
		<button type="submit">저장</button>
		<button type="reset" onclick ="history.back()">취소</button>
	</form>
</body>
</html>