<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><spring:message code="title.anboard" /> 상세화면</title> 
	
	<link rel="stylesheet" href="/css/admin_main.css">
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
  	
  	<script>
  	$(function() {
  		
  		$("#btn_go1").click(function(){
  			location = "anboardModify.do?unq=${vo.unq}";
  		});

  		$("#btn_go2").click(function(){
  			location = "passWrite.do?type=an&unq=${vo.unq}&fid=${vo.fid}&lev=${vo.lev}";
  		});
  		
  		$("#btn_go3").click(function(){
  			location = "replyWrite.do?fid=${vo.fid}&lev=${vo.lev}";
  		});
  		
  		
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
				<spring:message code="title.anboard" /> 상세
			</span>
		</div>
		
		<div style="position:relative; left:20px; top:30px;">
		
	<form id="frm">
	<table style="width:600px;">
		<tr>
			<th width="20%">제목</th>
			<td>${vo.title }</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${vo.name }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td style="height:150px;">${vo.content }</td>
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
		<button type="button" id="btn_go1">수정</button>
		<button type="button" id="btn_go2">삭제</button>
		<button type="button" id="btn_go3">답변</button>
	</div>
	</form>
	
		</div>
	</div>
</div>
</body>
</html>











