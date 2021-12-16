
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    
<!DOCTYPE html>
<html lang="kr">
 <head>
  <meta charset="UTF-8">
		<title>Document</title>
	
		<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
		<script>
		$( function() {
		  $( "#hiredate" ).datepicker({
		  	changeMonth: true,
		  	changeYear: true
		  });
		  
		} );
		</script>
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
	
	<form action="empModifySave.do" method="post" style="display: inline-block;">
		<div style="width:400px; padding-top:20px; margin:0 auto;">
			<table>
			   	<tr>
			      	<th>사원번호</th> <!-- 자동   -->  <!-- 1001,1002~    -->
					<td>${emp.empno}</td> 
			   </tr>
			   	<tr>
			     	<th>사원이름</th> 
					<td><input type="text" value="${emp.ename}" name="ename"></td> 
			   </tr>

			   <tr>
			      	<th>급여</th> <!-- 숫자여부 -->
					<td>
						<input type="text" name="sal" value="<fmt:parseNumber value="${emp.sal}"/>">
						<%-- <fmt:formatNumber maxFractionDigits="3" value="${emp.sal }"/> --%>
					</td>
			   </tr>
			   
			
			   <tr>
			      	<th>부서</th> <!-- 화면에서 펼침목록으로 출력 -->
					<td><%-- 
					<input type="text" value="${emp.deptno}" name="deptno"> --%>
					<select name="deptno">
						<c:forEach var="resultdept" items="${deptlist}">
								<option value="${resultdept.deptno}"
								<c:if test="${resultdept.deptno == emp.deptno}"> selected</c:if>
								 >${resultdept.deptno}</option>
						</c:forEach>
					</select>
					</td> 
			   </tr>
			
			   <tr>
			      	<th>업무</th> <!-- 화면에서 펼침목록으로 출력 -->
					<td>
					
					<select name="job">
						<c:forEach var="resultjob" items="${joblist}">
								<option value="${resultjob.job}"
								<c:if test="${resultjob.job == emp.job}"> selected</c:if>
								 >${resultjob.job}</option>
						</c:forEach>
					</select>
					</td> 
			   </tr>
			   <tr>
			      	<th>입사일</th> <!-- 화면에서 펼침목록으로 출력 <년><월><일>   -->
			      	<c:set var="yy" value="${fn:substring(emp.hiredate,0,4)}"/>
			      	<c:set var="mm" value="${fn:substring(emp.hiredate,5,7)}"/>
			      	<c:set var="dd" value="${fn:substring(emp.hiredate,8,10)}"/>
					<td><input type="text" value="${mm}/${dd}/${yy}" id="hiredate"name="hiredate"></td>
					<%-- <td><input type="text" value="${emp.hiredate}" id="hiredate"name="hiredate"></td> --%>
			   </tr>
			   <tr>
			      <th colspan="2">
			      		<input type="hidden" value="${emp.empno}" name="empno">
			      		<button type="submit">저장</button>
					<button type="button" onclick="history.back()">취소</button>
			      </th>
			   </tr>
			</table>

		</div>
		
	</form>


</body>
</html>
