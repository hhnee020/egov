<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	  <link rel="stylesheet" href="css/admin_main.css">
	  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	  <script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
	  <script>
	  $( function() {
		  $("#btn_save").click(function(){
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
			 
			 var frm = $("#frm").serialize();
			 $.ajax({
				type: "POST",
				url: "admin_nboardWriteSave.do",
				data: frm,
				datatype: "text",
				success: function(status){
					if(status == "ok"){
						alert("저장 성공!");
						location ="admin_nboardList.do";
					} else {
						alert("저장 실패!");
					}
				},
				error: function(){
					alert("오류!");
					}
			 	})
			 })
	  	});
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
			<span style="font-size:20px;">공지사항 등록화면</span> 
		</div>
	
		<div style="position:relative; left:20px; top:30px;">
			<form id="frm">
				
				<table style="width:600px" id="frm">
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" id="title"></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" name="name" id="name"></td>
					</tr>
					<tr>
						<th>내용</th>
						<td id="textareaTD"><textarea name="content" id="content" style="width:90%; height:90%; resize: none;" > </textarea></td>
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