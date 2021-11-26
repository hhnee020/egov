<%@ page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
Calendar cal = Calendar.getInstance();

int yy = cal.get(Calendar.YEAR);
int mm = cal.get(Calendar.MONTH);
int dd = cal.get(Calendar.DATE);

String today = yy + "-" + (mm+1) + "-" + dd; // 2021-8-26







//int y = 2020;
//int m = 7;
cal.set(yy,mm,1);
int lastday = cal.getActualMaximum(Calendar.DATE); // 30
int dayOfweek = cal.get(Calendar.DAY_OF_WEEK); // 4 (일(1) ~ 토(7))

int yy1 = cal.get(Calendar.YEAR);
int mm1 = cal.get(Calendar.MONTH)+1;

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
	font-family:'맑은 고딕',arial;
	color: #1f1f1f;
	line-height:1.5;
}
table {
	border:1px solid #ccc;
	border-collapse:collapse;
	width:400px;
}
th, td {
	border:1px solid #ccc;
	text-align:center;
	height:30px;
}

div.div1 {
	width:400px;
	text-align:center;
	font-size:20px;
	font-weight:bold;
	margin-bottom:5px;
}

</style>

<body>

<div class="div1">
 <%=yy %>년 <%=mm+1 %>월
</div>

<table>
	<colgroup>
		<col width="14%"/>
		<col width="14%"/>
		<col width="14%"/>
		<col width="14%"/>
		<col width="14%"/>
		<col width="14%"/>
		<col width="14%"/>
	</colgroup>
	<tr>
		<th>일</th>
		<th>월</th>
		<th>화</th>
		<th>수</th>
		<th>목</th>
		<th>금</th>
		<th>토</th>
	</tr>
	<tr>
	<%
	int cnt=0;
	
	for(int s=1; s<dayOfweek; s++) {
		cnt++;
		out.print("<td></td>");
	}
	
	String color = "#777777";
	String bold = "none";
	// today = "2021-8-1";
	for(int d=1; d<=lastday; d++) {
		cnt++;
		String vdate = yy1 + "-" + mm1 + "-" + d;
		// vdate = "2021-8-1";
		// vdate = "2021-8-2";
		// vdate = "2021-8-3";
		//  :
		// vdate = "2021-8-26";
		if( today.equals(vdate) ) {
			color="green";
			bold="bold";
		} else if( cnt%7 == 0 ) {
			color="blue";
			bold="none";
		} else if( cnt%7 == 1 ) {
			color="red";
			bold="none";
		} else {
			color="#777777";
			bold="none";
		}
	%>
		<td style="color:<%=color%>;font-weight:<%=bold %>;"><%=d %></td>	
	<%
		if(cnt%7 == 0) {
			out.print("</tr><tr>");
		}
	}
	while( cnt%7 != 0 ) {
		cnt++;
		out.print("<td></td>");
	}
	
	
	%>
	</tr>
</table>


</body>
</html>








