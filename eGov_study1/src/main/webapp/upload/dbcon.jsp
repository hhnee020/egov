<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.DriverManager"%>

<%   
String url = "jdbc:mysql://localhost:3306/apple7?useUnicode=true&characterEncoding=utf8";
String username = "java7";
String userpass = "123456";

 /* 접속 드라이브 연결 */
Class.forName("com.mysql.jdbc.Driver"); 

 /* 접속 정보 설정 및 적용 */
Connection conn=DriverManager.getConnection (url,username,userpass);

/* 접속 인스턴스 생성  */
Statement stmt = conn.createStatement();
%>

<%
request.setCharacterEncoding("utf-8");
%>
