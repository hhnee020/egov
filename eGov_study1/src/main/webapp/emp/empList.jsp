<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
function fn_del(empno) {
	if( confirm("정말 삭제하시겠습니까?") ) {
		location="empDelete.do?empno="+empno;
	}
}
</script>
<body>
총 출력 개수 : ${total } 
<br>
<table border="1" style="width:600px;">
	<tr>
		<th>번호</th>
		<th>사원이름</th>
		<th>사원번호</th>
		<th>업무</th>
		<th>부서</th>
		<th>삭제</th>
	</tr>
	<c:forEach var="result" items="${list}"  varStatus="status" >
		<tr align="center">
			<td>${rownum }</td>
			<td><a href="empModify.do?empno=${result.empno }">${result.ename }</a></td>
			<td>${result.empno }</td>
			<td>${result.job }</td>
			<td>${result.deptno }</td>
			<td><a href="javascript:fn_del('${result.empno }')">del</a></td>
		</tr>
		<c:set var="rownum" value="${rownum-1}"/>
	</c:forEach>
</table>

<div style="width:600px;margin-top:10px; text-align:right;">
	<button type="button" onclick="location='empWrite.do'">사원등록</button>
</div>

<div style="width:600px;margin-top:10px; text-align:center;">
	<c:forEach var="i" begin="1"  end="${totalPage}">
		
		<a href="empList.do?page_no=${i }">${i }</a>
		 
	</c:forEach>
</div>

</body>
</html>



