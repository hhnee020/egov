<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><spring:message code="title.review" /></title> 
	
	<link rel="stylesheet" href="/css/admin_main.css">
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
  	
  	<script>
  	$(function() {

  	});
  	</script>
	
</head>

<style>

</style>

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
			<span style="font-size:20px;font-weight:blod;">
			<spring:message code="title.review" /> 상세
			</span>
		</div>

		<div style="position:relative; left:20px; top:30px;">
		
	<form id="frm">
	<table style="width:600px;">
		<colgroup>
			<col width="20%"/>
			<col width="*"/>
		</colgroup>
		<tr>
			<th>제목</th>
			<td>${vo.title }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${vo.name }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${vo.email }</td>
		</tr>
		<tr>
			<th>SNS</th>
			<td>${vo.sns }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
			<div style="width:98%; height:150px;">${vo.content }</div>
			</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${vo.rdate }</td>
		</tr>
		<tr>
			<th>변경일</th>
			<td>${vo.udate }</td>
		</tr>

	</table>
	
	<div style="width:600px; text-align:center; margin-top:10px;">
		<button type="button"  id="btn_save">수정</button>
		<button type="button"  id="btn_delete">삭제</button>
	</div>
	</form>
	
		</div>
	</div>
</div>
</body>
</html>











