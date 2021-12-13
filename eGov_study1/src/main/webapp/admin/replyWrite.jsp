<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><spring:message code="title.anboard" /> 답변화면</title> 
	
	<link rel="stylesheet" href="/css/admin_main.css">
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
  	
  	<script>
  	$(function() {
		
  		$("#btn_save").click(function(){  
  			
  			if( $.trim($("#title").val()) == "" ) {
  				alert("제목을 입력해주세요.");
  				$("#title").focus();
  				return false;
  			}
  			if( $.trim($("#pass").val()) == "" ) {
  				alert("암호를 입력해주세요.");
  				$("#pass").focus();
  				return false;
  			}
  			if( $.trim($("#content").val()) == "" ) {
  				alert("내용을 입력해주세요.");
  				$("#content").focus();
  				return false;
  			}
  
  			var formdata = $("#frm").serialize();
  			
  			$.ajax({
  				type : "POST",
  				url  : "replyWriteSave.do",
  				data : formdata,
  				
  				datatype : "text",
  				success : function(data) {  // ok
  					if(data == "ok") {
  						alert("저장완료");
  						location = "anboardList.do";
  					} else {
  						alert("저장실패");
  					}
  				},
  			    error : function() {
  			    	alert("오류발생");
  			    }
  			});
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
				<spring:message code="title.anboard" /> 답변
			</span>
		</div>
		
		<div style="position:relative; left:20px; top:30px;">
		
	<form id="frm">
	
	<input type="hidden" name="fid" value="${vo.fid }">
	<input type="hidden" name="lev" value="${vo.lev }">
	
	<table style="width:600px;">
		<tr>
			<th>제목</th>
			<td>Re : <input type="text" name="title" id="title" style="width:92%"></td>
		</tr>
		<tr>
			<th>암호</th>
			<td><input type="password" name="pass" id="pass" style="width:50%"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" id="name" style="width:50%"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" id="content" style="width:98%;height:150px;"></textarea></td>
		</tr>

	</table>
	
	<div style="width:600px; text-align:center; margin-top:10px;">
		<button type="button" id="btn_save">저장</button>
		<button type="reset">취소</button>
	</div>
	</form>
	
		</div>
	</div>
</div>
</body>
</html>











