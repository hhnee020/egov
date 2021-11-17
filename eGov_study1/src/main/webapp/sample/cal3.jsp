<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String year = request.getParameter("year");
String month = request.getParameter("month");
%>
    
<%
// 달력클래스의 인스턴스(객체)화
Calendar cal = Calendar.getInstance();

int yy = cal.get(Calendar.YEAR);
int mm = cal.get(Calendar.MONTH);
int dd = cal.get(Calendar.DATE);

//int yy = 2021;
//int mm = 5;

String today = yy+"-"+(mm+1)+"-"+dd; // 2021-(8+1)-1

int tyy = yy;

if ( year != null && month != null && !year.equals("") && !month.equals("") ) {
	yy = Integer.parseInt(year);
	mm = Integer.parseInt(month)-1;
}

// 출력 월 1일의 요일
cal.set(yy,mm,1);
int wday = cal.get(Calendar.DAY_OF_WEEK); // 일(1)~토(7)


// 출력달 마지막 날
int lastday = cal.getActualMaximum(Calendar.DATE); // 마지막 날짜, 결과 : 30
String vday = yy+"-"+(mm+1);
// 출력달의 오늘인지

int b_year = yy;         
int b_month = (mm+1)-1 ;

if (b_month == 0) {
	b_year = b_year-1;
	b_month = 12;
}

int n_year = yy;
int n_month = (mm+1)+1;
if( n_month == 13 ) {
	n_year = n_year+1;
	n_month = 1;
}


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>
body {
	font-size:12px;
	font-family:'맑은 고딕',arail;
	color:#1a1a1a;
}

.div1 {
	width:400px;  /* 넓이는 테이블 넓이와 동일해야 함 */
	text-align:right;
	font-size:12px;
	margin-bottom:15px;  /* 하단으로부터 x px만큼 띄워줌 */
}

.div2 {
	width:400px;  /* 넓이는 테이블 넓이와 동일해야 함 */
	text-align:center;
	font-size:20px;
	font-weight:bold;
	margin-bottom:5px;  /* 하단으로부터 x px만큼 띄워줌 */	
}


table {
	width:400px;
	border:1px solid #ccc;
	border-collapse:collapse;  <!-- 셀과 셀 사이의 간격 없앰 -->	
}

th,td {
	text-align:center;
	height:35px;
	border:1px solid #ccc;
}

</style>

<body>

<div class="div1">
	<form name="frm" method="post" action="cal3.jsp">
	
		<select name="year" style="width:70px">
			
			<option value=""> 년도 </option>
			
			<%
			for ( int y = (tyy-20); y <= (tyy+10); y++ ) {
			%>
			
			<option value="<%=y %>"><%=y %></option>
			<%
			}
			%>
			
		</select>년
		
		<select name="month" style="width:70px">
			<option value=""> 월 </option>
			<%
			for (int m = 1; m<=12; m++) {
			%>
			<option value="<%=m %>"> <%=m %></option>
			<%
			}
			%>
		</select>월
		<button type="submit">달력보기</button>
	</form>
</div>

<div class="div2">
	       
	   <a href="cal3.jsp?year=<%=b_year %>&month=<%=b_month %>">이전</a> 
	       <%=yy %>년 <%=mm+1 %>월 
	   <a href="cal3.jsp?year=<%=n_year %>&month=<%=n_month %>">다음</a>
</div>
	<table>
		<colgroup> <!-- 셀과 셀 사이의 간격 -->
			<col width = "14%" />
			<col width = "14%" />
			<col width = "14%" />
			<col width = "14%" />
			<col width = "14%" />
			<col width = "14%" />
			<col width = "14%" />			
		</colgroup>
		<tr> <!-- 요일 출력 -->
			<th>일</th>
			<th>월</th>
			<th>화</th>
			<th>수</th>
			<th>목</th>
			<th>금</th>
			<th>토</th>
		</tr>
		
		<tr>
		
		<tr>
		<%
		int cnt = 0;
		for(int i=1; i<wday; i++) {
			cnt++;
			out.print("<td></td>");
		}
		%>
		
		<%
		String color="";
		for( int d=1; d<=lastday; d++ ) {
			cnt++;
			
			if ( cnt%7 == 0 ) color = "blue";
			else if (cnt%7 == 1) color = "red";
			else color = "#1a1a1a";
			
			if ( today.equals(vday + "-" + d) ) {
				color = "green; font-weight:bold";
			}
			/* if ( d == dd ) color= "skyblue;font-weight:bold;"; */
		%>
			<td><span style="color:<%=color%>"> <%=d %> </span> </td>  <!-- span : 자체적인 기능 없음  --> 
			
		<%
			if ( cnt%7 == 0 ) {
				out.print("</tr><tr>");
			}
			
		}
		 %>

		<%
		while( cnt%7 != 0 ) {   /* 빈 공간을 찍어주기 위해서*/
			cnt++;
		%>
			<td></td>
		<%
		}
		%>
		 
		</tr>
		
		
	</table>
	

</body>
</html>