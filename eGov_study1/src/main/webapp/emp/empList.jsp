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
<span>조회된 데이터 : ${totalCount}</span>
<table border="1">
	<tr>
		<th>번호</th>
		<th>사원이름</th>
		<th>사원번호</th>
		<th>업무</th>
		<th>부서</th>
	</tr>
	<c:forEach var="result" items="${list}" varStatus="status">
		<tr>
			<td>${result.rn}</td>
			<td><a href="empDetail.do?empno=${result.empno}">${result.ename}</a></td>
			<td>${result.empno}</td>
			<td>${result.job}</td>
			<td>${result.deptno}</td>
		</tr>
	</c:forEach>
</table>

<button type ="button" onclick="location='empWrite.do'">등록</button>
	<c:forEach var="i" begin="1" end="${totalPage}">
		<a href="empList.do?page_no=${i}">${i}</a>
	</c:forEach>
</body>
</html>