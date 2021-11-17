<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="kr">
 <head>
  <meta charset="UTF-8">
	<title>Document</title>
  
 </head>

<style>
body {
   font-size:12px;
   font-family:'맑은 고딕';
}
table {
   width:400px;
   border:1px solid #ccc;
   border-collapse:collapse;
}
th{
   border:1px solid #ccc;
   padding:5px;
} 
td {
   border:1px solid #ccc;
   padding:5px;
   text-align:left;
}
</style>


<body>

<header>
Header
</header>

<section>
	<article>
		<div style="width:400px; padding-top:20px; margin:0 auto;">

<form name="frm" method="post" action="deptWriteSave.do"> <!-- --sava 처리로 넘어간다  -->
<table>
   <tr>
      <th>부서번호</th>
      <td><input type="text" name="deptno"></td>
   </tr>
   <tr>
      <th>부서이름</th>
      <td><input type="text" name="dname"></td>
   </tr>
   <tr>
      <th>부서위치</th>
      <td><input type="text" name="loc"></td>
   </tr>
   <tr>
      <th colspan="2">
      <button type="submit" >등록</button>
      <button type="reset">취소</button>
      </th>
   </tr>
</table>

<div style="width:400px; text-align:right; margin-top:10px;">
	<button type="button">목록으로</button>
</div>
</form>
		</div>

	</article>
</section>



</body>
</html>
