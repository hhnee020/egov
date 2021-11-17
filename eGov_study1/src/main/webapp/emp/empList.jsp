<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script >
function fn_del(empno){
	if(confirm ("정말 삭제 하시겠습니까? ")){
		
		location="empDelete.do?empno="+empno;
		
	}
		
	
	
}
</script>
<body>


총 출력 갯수 : ${total }
<button type="button" onclick="location='empWrite.do'">사원등록</button>
<table border="1">
			
			<tr>
			
				<th>번호</th>
				<th>사원이름</th>
				<th>사원번호</th>
				<th>업무</th>
				<th>부서</th>
				<th>삭제</th>
			</tr>
		
		
		
			<c:forEach var="result" items="${list}" varStatus="status">
            
            <tr>
            <td>${rownum }</td>
            <td><a href="empModify.do?empno=${result.empno } ">${result.ename}</a></td>
            <td>${result.empno }</td>
            <td>${result.job }</td>
            <td>${result.deptno }</td>
            <td><a href="javascript:fn_del(' ${result.empno } ')">del</a> </td>
            </tr>
          
          <c:set var="rownum" value="${rownum-1 }"/>
            
            
        </c:forEach>
		

</table>
<div>
	
	<c:forEach var ="i" begin="1" end ="${totalPage}">
	
	<a href="empList.do?page_no=${i }">${i }</a>
		
	
	</c:forEach>
	
	</div>


</body>
</html>