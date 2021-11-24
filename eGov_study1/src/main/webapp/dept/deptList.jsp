<%@ page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="kr">
 <head>
  <meta charset="UTF-8">
	<title>Document</title>
  	<link rel="stylesheet" href="../css/layout.css">
	<link rel="stylesheet" href="../css/main.css">
 </head>


<body>


<section>
	
	<article>
		<div style="width:400px; padding-top:20px; margin:0 auto;">

			<table>
				<tr>
					<th>번호</th>
					<th>부서이름</th>
					<th>부서번호</th>
					<th>부서위치</th>
					<th>삭제</th>
				</tr>
				<c:set var="number" value="1"/>
				<c:forEach var="result" items="${list}" varStatus="status">
					<tr>
						<%-- <td>${status.count} </td> --%>
						<td>${number}</td>
						<td>${result.deptno}</td>
						<td><a href="deptModify.do?deptno=${result.deptno}">${result.dname}</a></td>
						<td>${result.loc}</td>
						<td><button onclick="location.href='deptDelete.do?deptno=${result.deptno}'">삭제</button></td>
					</tr>
				<c:set var="number" value="${number+1}"/>
				</c:forEach>
				
			</table>
			
			<div style="width:400px; text-align:right; margin-top:10px;">
			<button type="button" onclick="location='deptWrite.do'">부서등록</button>
			</div>
		</div>

	</article>
</section>


</body>
</html>
