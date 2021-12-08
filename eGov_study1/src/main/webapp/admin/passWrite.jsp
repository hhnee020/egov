<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>암호 입력 화면</title> 
	
	<link rel="stylesheet" href="/css/admin_main.css">
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
  	
  	<script>
  	$(function() {
		
  		$("#btn_del").click(function(){  
  			
  			if( $.trim($("#pass").val()) == "" ) {
  				alert("암호를 입력해주세요.");
  				$("#pass").focus();
  				return false;
  			}
  			
  			var formdata = $("#frm").serialize();
  			
  			$.ajax({
  				type : "POST",
  				url  : "fileboardDelete.do",
  				data : formdata,
  				
  				datatype : "text",
  				success : function(data) {
  					if(data == "ok") {
  						alert("삭제완료");
  					} else if(data == "pass_fail") {
  						alert("암호가 일치하지 않습니다.");
  					} else {
  						alert("삭제실패");
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
			<span style="font-size:20px;font-weight:blod;">암호 입력 화면</span>
		</div>
		
		<div style="position:relative; left:20px; top:30px;">
		
	<form id="frm">
	
	<input type="hidden" name="unq" value="${unq }" />
	<input type="hidden" name="filename" value="${filename }" />
	
	<table style="width:600px;">
		<tr>
			<th>암호</th>
			<td><input type="password" name="pass" id="pass" style="width:50%"></td>
		</tr>

	</table>
	
	<div style="width:600px; text-align:center; margin-top:10px;">
		<button type="button" id="btn_del">삭제</button>
		<button type="button" onclick="history.back()">이전</button>
	</div>
	</form>
	
		</div>
	</div>
</div>
</body>
</html>











