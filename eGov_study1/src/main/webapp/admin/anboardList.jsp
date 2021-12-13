<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>답변 게시판 목록</title>
	<link rel="stylesheet" href="/css/admin_main.css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>

<script>

$(function(){ 
	
	$("#allchk").click(function(){
		if( $("#allchk").prop("checked") == true ) {
			$("input[name='chk']").prop("checked",true);
		} else {
			$("input[name='chk']").prop("checked",false);
		}
	});	
	
	$("#btn_all_delete").click(function(){
		
		var len = $("input[name='chk']").length;
		var values = "";
		
		for(var i=0; i<len; i++) {
			var chk = document.getElementsByName('chk')[i].checked;
			if( chk == true ) {
				values += document.getElementsByName('chk')[i].value;
				values += ",";
			}
		}
		
		if(values.length > 0) {
			
			if(  confirm("일괄 삭제를 진행하시겠습니까?")  ) {
				$.ajax({
					type : "post",
					url  : "admin_nboardAllDelete.do",
					data : "values="+values,
					datatype : "text",
					success : function(data) {
						if(data == "ok") {
							alert("삭제 처리 완료");
							document.location.reload();
						} else {
							alert("삭제 처리 실패!!");
						}	
					},
					error : function() {
						alert("시스템 오류");
					}
				});
			}
		}
	});
	
});

function fn_delete(unq) {
	
	if( confirm("정말 삭제하시겠습니까?") ) {
		$.ajax({
			type : "post",
			url  : "admin_nboardDelete.do",
			data : "unq="+unq,
			datatype : "text",
			success : function(data) {
				if(data == "ok") {
					alert("삭제 처리 완료");
					document.location.reload();
				} else {
					alert("삭제 처리 실패!!");
				}	
			},
			error : function() {
				alert("시스템 오류");
			}
		});
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
			<span style="font-size:20px;font-weight:blod;">답변 게시판 목록</span>
		</div>
		
		<div style="position:relative; width:800px; left:20px; top:30px; margin-bottom:10px;">
	
			<div style="position:relative; float:left; width:400px;" >
			총 출력 개수 : ${vo.total }
			</div>
			
			<form name="frm" method="post" action="admin_nboardList.do">
			<div style="position:relative; text-align:right;" >
				<select name="s_field">
					<option value="title"   <c:if test="${s_field == 'title' }">selected</c:if>>제목</option>
					<option value="content" <c:if test="${s_field == 'content' }">selected</c:if>>내용</option>
					<option value="rdate"   <c:if test="${s_field == 'rdate' }">selected</c:if>>등록일</option>
				</select>
				<input type="text" name="s_text" style="width:120px;" value="${s_text }">
				<button type="submit" id="btn_search">검색</button>
			</div>
			</form>
		
		</div>
		<div style="position:relative; left:20px; top:30px;">
	
	<table style="width:800px;" >
		<colgroup>
			<col width="10%"/>
			<col width="*"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
		</colgroup>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>이름</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>


		<c:set var="rownum" value="${vo.row_no}" />


		<c:forEach var="result" items="${list}"  varStatus="status"  >

		<tr align="center">
			<td>${rownum }</td>
			<td align="left">
			
			<c:set var="lev" value="${result.lev }" />
			<%
			// 1, 101, 10101 , 
			String lev = (String) pageContext.getAttribute("lev");
			int lev_length = lev.length(); // 글자 수
			
			for( int i=1; i<lev_length; i++  ) {
				out.print("&nbsp;&nbsp;");
			}
			if( lev_length > 1 ) out.print("<font color='pink' size='2'>└→</font>");
			%>

	<a href="anboardDetail.do?unq=${result.unq }">${result.title }</a></td>
			<td>${result.name }</td>
			<td>${result.rdate }</td> 
			<td>${result.hits }</td>
		</tr>
		
		
			<c:set var="rownum" value="${rownum-1}"/>
			
		</c:forEach>

	</table>
	
	<div style="width:800px; text-align:center; margin-top:10px;">
			
			<c:forEach var="i" begin="1" end="${vo.total_page }">
				<a href="anboardList.do?page_no=${i }">${i }</a>
			</c:forEach>
	</div>
	
		</div>
	</div>
</div>
</body>
</html>











