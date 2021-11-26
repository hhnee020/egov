<%@page import="java.util.Calendar"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
  	});
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
		<div style="width:400px; padding-top:20px; margin:0 auto;">
			<form name="frm" method="post" action="empWriteSave.do">
			<table>
			   <tr>
			      <th>사원번호</th> <!-- 자동   -->  <!-- 1001,1002~    -->
			      <td>
		<input type="text" name="empno" id="empno" value="${empno}">
				</td> 
			   </tr>
			   <tr>
			      <th>사원이름</th> 
			      <td>
		<input type="text" name="ename" id="ename" class="ename1"></td>
			   </tr>

			   <tr>
			      <th>급여</th> <!-- 숫자여부 -->
			      <td>
			 			<input type="text" name="sal">
			      </td>
			   </tr>
			   
			
			   <tr>
			      <th>부서</th> <!-- 화면에서 펼침목록으로 출력 -->
			      <td>
			      	<select name="deptno">
			      	
			      <c:forEach var="result" items="${deptList }">
			      		<option value="${result.deptno}">${result.dname}</option>
			      </c:forEach>
			      
			      	</select>
			      </td>
			   </tr>
			
			   <tr>
			      <th>업무</th> <!-- 화면에서 펼침목록으로 출력 -->
			      <td> 
			      
			      <select name="job"> 		      
			     
			      <c:forEach var="result" items="${jobList}">
						<option value="${result.job }">${result.job }</option>
			      </c:forEach>
			      
			      </select>
			      
			      </td>
			   </tr>
			   <tr>
			      <th>입사일</th> <!-- 화면에서 펼침목록으로 출력 <년><월><일>   -->
			      <td>
					  <input type="text" name="hiredate" id="hiredate">
			      </td>
			   </tr>
			   <tr>
			      <th colspan="2">
			      <button type="submit" id="btn_save">등록</button>
			      <button type="reset">취소</button>
			      </th>
			   </tr>
			</table>
			
			<div style="width:400px; text-align:right; margin-top:10px;">
				<button type="button" onclick="location='empList.do'">목록으로</button>
			</div>
			</form>

		</div>


</body>
</html>
