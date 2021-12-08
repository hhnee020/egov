<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
		<link rel="stylesheet" href="css/admin_main.css">
		<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script>
			$(function(){
				$("#btn_change").click(function(){
					$("#title").attr("disabled",false);
					$("#name").attr("disabled",false);
					$("#content").attr("disabled",false);
					$("#btn_change").attr("onclick","formd();");
					$("#btn_change").html('저장');
					$(this).attr('id','btn_save');
					
				})
				
			})
			
		</script>
		
		<script>

		function formd(){
				 if($.trim($("#title").val()) == ""){
					 alert("제목을 입력해주세요");
					 $("#title").focus();
					 return false;
				 }
				 
				 if($.trim($("#name").val()) == ""){
					 alert("이름을 입력해주세요");
					 $("#name").focus();
					 return false;
				 }
				 if($.trim($("#content").val()) == ""){
					 alert("내용을 입력해주세요");
					 $("#content").focus();
					 return false;
				 }
				 var unq = '<c:out value="${vo.unq }"/>';
				 $("#unq").val(unq );

				 var frm = $("#frm").serialize();
				 $.ajax({
					type: "POST",
					url: "admin_nboardModifySave.do",
					data: frm,
					datatype: "text",
					success: function(status){
						if(status == "ok"){
							alert("저장 성공!");
							location.reload();
						} else {
							alert("저장 실패!");
							location.reload();
						}
					},
					error: function(){
						alert("오류!");
						}
				 	});
		}
		
		</script>
</head>
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
			<span style="font-size:20px;">${vo.title }</span> 
		</div>
	
		<div style="position:relative; left:20px; top:30px;">
			<form id="frm">
				<input type="hidden" value="" id="unq" name="unq">
				<table style="width:600px" id="frm">
					<tr>
						<th>제목</th>
						<td ><input type="text" name="title" id="title" value="${vo.title}" disabled style="width:90%"></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="name" id="name" value="${vo.name }" disabled style="width:90%"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td id="textareaTD"><textarea name="content" id="content" style="width:90%; height:90%; resize: none;" disabled>${vo.content }</textarea></td>
					</tr>
				
				</table>
				<div style="width:600px; text-align:center; margin-top:10px;">
				<c:if test="${vo.beforeid ne null }">
					<button type="button" id="btn_before" onclick="location='admin_nboardModify.do?unq=${vo.beforeid}&s_text=${s_text}&s_field=${s_field}'">이전</button>
				</c:if>
					<button type="button" id="btn_change" class="">수정</button>
					<button type="reset">목록으로</button>
					<c:if test="${vo.nextid ne null }">
					<button type="button" id="btn_next" onclick="location='admin_nboardModify.do?unq=${vo.nextid}&s_text=${s_text}&s_field=${s_field}'">다음</button>
				</c:if>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>