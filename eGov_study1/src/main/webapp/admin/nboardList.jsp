<%@page import="java.util.Calendar"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	  <link rel="stylesheet" href="css/admin_main.css">
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>


<script>
function fn_action(a,b) {
	document.frm.ord.value=a;
	document.frm.desc.value=b;
	
	document.frm.submit();
}

$(function(){
	$("#allchk").click(function(){
		if($("#allchk").prop("checked")){
			$(".chk").prop("checked",true);
		} else {
			$(".chk").prop("checked",false);
		}
	})
	
	$("#btn_delete").click(function(){
		var array= [];
		$(".chk").each(function(){
			if($(this).prop("checked")){
				array.push($(this).val());
			}
		})

		console.log(array.length);
		if(array.length >= 1){

			$.ajax({
				type: "POST",
				url: "admin_nboardAllDelete.do",
				data: { "array_data" : array},
				dataType:'text',
				success: function(status){
					if(status == "ok"){
						alert("삭제 성공!");
						location.reload();
					} else {
						alert("삭제 실패!");
						location.reload();
					}
				},
				error: function(){
					alert("오류!");
					}
			})
		} else {
			alert("한 건 이상의 데이터를 선택해주세요")
		}
	})
})

function fn_delete(unq){
	
	if(confirm("정말 삭제하시겠습니까?")){
		
		 $.ajax({
				type: "POST",
				url: "admin_nboardDelete.do",
				data: "unq=" + unq,
				datatype: "text",
				success: function(status){
					if(status == "ok"){
						alert("삭제 성공!");
						location.reload();
					} else {
						alert("삭제 실패!");
						location.reload();
					}
				},
				error: function(){
					alert("오류!");
					}
			 	})
		}
}
</script>


<body>

<div class="div1">

	<div class="div_top">
		<h2>관리자모드</h2>
	</div>
	<div class="div2">
	<!-- left menu S -->

	<%@ include file="../include/left_menu.jsp" %>

	<!-- left menu E -->
	</div>
	<div class="div3">
	
		
		<div style="position:relative; left:20px; top:30px; margin-bottom:10px;">
			
		</div>
	
	
		<div style="position:relative; left:20px; top:30px; margin-bottom:5px;">
			<span style="font-size:20px;">공지사항 목록</span> 
		</div>
		<div style="position:relative; left:20px; top:30px; margin-bottom:10px; width:800px;">
			<div>
				<span>총 조회개수 : ${total}</span>
			</div>
			<form name="frm" method="post" action="admin_nboardList.do">
				
				<div style="position:relative; text-align:right;">
					<select name="s_field">
						<option value="title" <c:if test="${s_field =='title'}">selected </c:if> >제목</option>
						<option value="content" <c:if test="${s_field =='content'}">selected </c:if> >내용</option>
						<option value="rdate" <c:if test="${s_field =='rdate'}">selected </c:if> >등록일</option>
					</select>
					<input type="text" name="s_text" style="width:120px;" value="${s_text}">
					<button type="submit" id="btn_search">버튼</button>
				</div>
			</form>
		</div>
	

		<div style="position:relative; left:20px; top:30px;">
			
			<table style="width:800px">
				<colgroup>
					<col style="width:5%"/>
					<col style="width:10%"/>
					<col style="width:*"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
				</colgroup>
				<tr>
					<th><input type="checkbox" name="allcheck" id="allchk"> </th>
					<th>번호</th>
					<th>제목</th>
					<th>이름</th>
					<th>등록일</th>
					<th>변경일</th>
					<th>조회수</th>
					<th>삭제</th>
				</tr>
				<c:forEach var="result" items="${result}">
					<tr>
						<%-- <td>${result.rn}</td> --%>
						<td><input type="checkbox" name="chk" class ="chk" value="${result.unq }"></td>
						<td>${result.rn}</td>
						<td><a href="admin_nboardModify.do?unq=${result.unq }&s_text=${s_text}&s_field=${s_field}">${result.title}</a></td>
						<td>${result.name}</td>
						<td>${result.rdate}</td>
						<td>${result.udate}</td>
						<td>${result.hits}</td>
						<td><a href="javascript:fn_delete(${result.unq });">삭제</a></td>
					</tr>
					
					<c:set var="rownum" value="${rownum-1}"></c:set>
					
				</c:forEach>
			
			</table>
			<div style="wiidth:800px; text-align:left; margin-top:10px;">
				<button type="button" id="btn_delete" onclick="" >check delete</button>
			</div>
			<div style="wiidth:800px; text-align:center; margin-top:10px;">
				<c:forEach var="i" begin="1" end="${totalpage}">
					<a href="admin_nboardList.do?pageNo=${i}&s_field=${s_field}&s_text=${s_text}">${i}</a>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
</body>
</html>











