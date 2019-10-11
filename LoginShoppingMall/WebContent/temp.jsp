<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="shoppingMall.*" %>
<%
	String title = (String) request.getAttribute("title");
	String view = (String) request.getAttribute("view");
	
	if(title==null){
		title = "쇼핑몰 시작 화면";
	}
	if(view ==null){
		view = "index.jsp";
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title %></title>
</head>
<body>
<div id="container">
	<header><h1></h1></header>
	<nav>
		<a href="plist">상품리스트</a>
		<a href="purchaselist">구매리스트</a>
		<a href="basket">마이페이지</a>
		<a href="logout">로그아웃</a>
	</nav>
	<section>
		<jsp:include page="<%=view %>"></jsp:include>
	</section>
	<footer>
		Copyright&copy; 한국쇼핑몰에 오신걸 환영합니다.
	</footer>
</div>	
</body>
</html>