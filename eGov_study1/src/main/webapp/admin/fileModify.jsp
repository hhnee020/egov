<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항 등록화면</title> 
	
	<link rel="stylesheet" href="/css/admin_main.css">
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
  	
  	<script>
  	function fn_filedel(filename) {
  		
  		if( confirm("파일을 삭제하시겠습니까?"+filename) ) {
  		
			$.ajax({
				type : "post",
				data : "unq=${vo.unq}&filename="+filename+"&allfilename=${vo.filename}",
				url  : "filedelete.do",
				
				datatype : "text",
				success : function(data) {
					if( data == "ok" ) {
						alert("삭제완료");
						document.location.reload();
					} else {
						alert("삭제실패");
					}
				},
				error : function() {
					alert("삭제오류");
				}
			});
  		}
  	}
  	
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

  			var f1 = $("#file1").val().length;
  			var f2 = $("#file2").val().length;

  			//alert(f1);
  			
  			if((f1 > 0 && f2 == 0) || (f1 == 0 && f2 > 0)) {
  				if( $("#filecnt").val() == "2" ) {
  					alert("기존 2개 중 1개를 삭제해야 등록 가능합니다.");
  					return false;
  				}
  			}

  			var formdata = new FormData(document.getElementById('frm'));
  			$.ajax({
  				type : "POST",
  				url  : "fileboardModifySave.do",
  				data : formdata,
  				
  				processData: false,
  				contentType: false, 
  				
  				datatype : "text",
  				success : function(data) { 

  					if(data == "ok") {
  						alert("저장완료");
  						location="fileboardList.do";
  					} else if(data == "pass_fail") {
  						alert("암호를 다시 확인해주세요.");
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
			<span style="font-size:20px;font-weight:blod;">자료 게시판 수정</span>
		</div>

		<div style="position:relative; left:20px; top:30px;">
		
	<form name="frm" id="frm" enctype="multipart/form-data">
	
	<input type="hidden" name="unq" value="${vo.unq }">
	<input type="hidden" name="filename" value="${vo.filename }">

	<table style="width:600px;">
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" id="title" value="${vo.title }" style="width:98%"></td>
		</tr>
		
		<tr>
			<th>암호</th>
			<td><input type="password" name="pass" id="pass" style="width:50%"></td>
		</tr>
		
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" id="name" value="${vo.name }" style="width:50%"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
<textarea name="content" id="content" style="width:98%;height:150px;">${vo.content }</textarea></td>
		</tr>
		
		<tr>
			<th>파일</th>
			<td>
			
			<c:set var="filename" value="${vo.filename }" />
			
			<%
      		String filename = (String)pageContext.getAttribute("filename") ;
			String[] array = null;
			if(filename != null && !filename.equals("")) {
				array = filename.split("／");
				for( int i=0; i<array.length; i++ ) {
			%>
					<a href="javascript:window.open(encodeURI('downloadFile.do?requestedFile=<%=array[i] %>'))"><%=array[i] %></a>
	
	<a href="javascript:fn_filedel('<%=array[i] %>')"><img src="/images/icons/delete.PNG" style="width:15px;height:15px;" /></a>
					<br>
			<%
				}
			}
			
			int arrayCnt = 0;
			if( array != null ) {
				arrayCnt = array.length;
			}
			
 			%>
 			<br>
			
			<input type="file" name="file1" id="file1" style="width:50%"> <br>
			<input type="file" name="file2" id="file2" style="width:50%">
			
			<input type="hidden" id="filecnt" value="<%=arrayCnt %>">
			
			</td>
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











