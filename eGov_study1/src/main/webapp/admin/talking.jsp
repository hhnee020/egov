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

  		$("#btn_comment_save").click(function(){
  			
  			var url = "commentSave.do";
  		
  			if( $("#comment1").val() == ""  ) {
  				alert("코맨트를 입력해주세요.");
  				$("#comment1").focus();
  				return false;
  			}
 
  			var formdata = $("#frm").serialize();
  			$.ajax({
  				type : "post",
  				url  : url,
  				data : formdata,
  				
  				datatype:"text",
  				success : function(data){
  					
  				},
  				error   : function() {
  					
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
			<spring:message code="title.review" /> 상세
			</span>
		</div>

		<div style="position:relative; left:20px; top:30px;">
		
	<table style="width:600px;">
		<colgroup>
			<col width="*"/>
		</colgroup>
		<tr>
			<td>
			<div style="width:98%; height:250px;">${vo.content }</div>
			</td>
		</tr>
	</table>
	
	<div>
	
	<form id="frm">
	
	<input type="hidden" name="p_unq" value="${vo.unq}">
	
	<input type="hidden" name="unq" value="0">
	
	<table style="width:600px;margin-top:20px;">
		<colgroup>
			<col width="*"/>
		</colgroup>
		<tr>
			<td>
			<input type="text" name="comment1" id="comment1" style="width:330px;">
			<button type="submit" id="btn_comment_save" onclick="return false;">등록</button>
			</td>
		</tr>
	</table>
	</form>
	</div>
	
	</div>
	</div>
</div>
</body>
</html>











