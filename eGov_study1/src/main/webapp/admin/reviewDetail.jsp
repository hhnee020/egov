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
  			
  			if( $("#comment1").val() == ""  ) {
  				alert("코맨트를 입력해주세요.");
  				$("#comment1").focus();
  				return false;
  			}
  			
  			var formdata = $("#frm").serialize();
  			$.ajax({
  				type : "post",
  				url  : "commentSave.do",
  				data : formdata,
  				
  				datatype:"text",
  				success : function(data){
  					if(data == "ok") {
  						alert("저장성공");
  						location.reload();
  					} else {
  						alert("저장실패");
  					}
  				},
  				error   : function() {
  					alert("전송오류");
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

	<div>
	<form id="frm">
	<input type="hidden" name="p_unq" value="${vo.unq}">
	<table style="width:600px;margin-top:20px;">
		<colgroup>
			<col width="*"/>
			<col width="17%"/>
			<col width="17%"/>
			<col width="17%"/>
			<col width="10%"/>
		</colgroup>
		<tr>
			<th>코맨트</th>  
			<th>이름</th>
			<th>별점</th>
			<th>암호</th>
			<th rowspan="2"><button type="button" id="btn_comment_save">등록</button></th>
		</tr>
		<tr>
			<td><input type="text" name="comment1" id="comment1" style="width:330px;"></td>
			<td><input type="text" name="name" id="name" style="width:60px;"></td>
			<td>
				<select name="mark">
					<option value="5">최고</option>
					<option value="4">좋음</option>
					<option value="3" selected>보통</option>
					<option value="2">부족</option>
					<option value="1">나쁨</option>
				</select>
			</td>
			<td><input type="password" name="pass" id="pass" style="width:60px;"></td>
		</tr>
	</table>
	</form>
	</div>
	
	
	<div>
	  
	<table style="width:600px;margin-top:20px;">
		<colgroup>
			<col width="5%" />
			<col width="*" />
			<col width="10%" />
			<col width="10%" />
			<col width="10%" />
			<col width="7%" />
		</colgroup>
		<tr>
			<th>no.</th>
			<th>코맨트</th>
			<th>이름</th>
			<th>등록</th>
			<th>별점</th>
			<th>수/사 </th>
		</tr>
	
	<c:forEach var="result" items="${comm_list }" varStatus="status">
		<tr align="center">
			<td>${status.count }</td>
			<td>${result.comment1 }</td>
			<td>${result.name }</td>
			<td>${result.rdate }</td>
			<td>${result.markmsg }<br> 
			<c:forEach var="j" begin="1" end="5">
               <c:if test="${result.mark >= j}">★</c:if>
               <c:if test="${result.mark < j}">☆</c:if>
            </c:forEach>
			<br>
			</td>
			<td>M/D</td>
		</tr>
	</c:forEach>
	
	</table>
	
	
	</div>
	
	

		</div>
	</div>
</div>
</body>
</html>











