
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
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
 </head>


<style>
body {
   font-size:12px;
   font-family:'맑은 고딕';
}
table {
   width:400px;
   border:1px solid #ccc;
   border-collapse:collapse;
}
th {
   border:1px solid #ccc;
   padding:5px;
}
td {
   border:1px solid #ccc;
   padding:5px;
   text-align:left;
}
</style>

<script>
</script>


<body>
		<div style="width:400px; padding-top:20px; margin:0 auto;">
			<table>
			   	<tr>
			      	<th>사원번호</th> <!-- 자동   -->  <!-- 1001,1002~    -->
					<td>${emp.empno}</td> 
			   </tr>
			   	<tr>
			     	<th>사원이름</th> 
					<td>${emp.ename}</td> 
			   </tr>

			   <tr>
			      <th>급여</th> <!-- 숫자여부 -->
					<td>${emp.sal}</td> 
			   </tr>
			   
			
			   <tr>
			      <th>부서</th> <!-- 화면에서 펼침목록으로 출력 -->
					<td>${emp.deptno}</td> 
			   </tr>
			
			   <tr>
			      <th>업무</th> <!-- 화면에서 펼침목록으로 출력 -->
					<td>${emp.job}</td> 
			   </tr>
			   <tr>
			      <th>입사일</th> <!-- 화면에서 펼침목록으로 출력 <년><월><일>   -->
					<td>${emp.hiredate}</td> 
			   </tr>
			   <tr>
			      <th colspan="2">
			      	<form action="empModify.do" method="post" style="display: inline-block;">
			      		<input type="hidden" value="${emp.empno}" name="empno">
			      		<button type="submit">수정</button>
			      	</form>
					<button type="button" onclick="location='empList.do'">목록으로</button>
			      </th>
			   </tr>
			</table>
			

		</div>


</body>
</html>
